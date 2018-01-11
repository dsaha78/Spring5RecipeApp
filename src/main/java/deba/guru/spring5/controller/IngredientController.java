package deba.guru.spring5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import deba.guru.spring5.command.IngredientCommand;
import deba.guru.spring5.command.UnitOfMeasureCommand;
import deba.guru.spring5.service.IngredientService;
import deba.guru.spring5.service.RecipeService;
import deba.guru.spring5.service.UnitoMeasureService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientController {

	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	private final UnitoMeasureService uomService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService,
			UnitoMeasureService uomService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.uomService = uomService;
	}

	@GetMapping
	@RequestMapping({ "recipe/{id}/ingredient" })
	public String listIngredients(@PathVariable String id, Model model) {

		log.debug("Getting list of ingredients for recipe with Id " + id);
		model.addAttribute("recipe", recipeService.getRecipebyId(new Long(id)));
		return "recipe/ingredient/list";
	}

	@GetMapping
	@RequestMapping({ "/recipe/{recipeId}/ingredient/{id}/show" })

	public String showIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {

		log.debug("Showing list of ingredients for recipe with Id " + id);
		model.addAttribute("ingredient",
				ingredientService.findbyRecipeIdAndIngredientId(new Long(recipeId), new Long(id)));
		return "/recipe/ingredient/show";

	}

	@PostMapping
	@RequestMapping({ "/recipe/{recipeId}/ingredient/{id}/update" })
	public String updateRecipe(@PathVariable String recipeId, @PathVariable String id, Model model) {
		log.debug("Showing ingredients for recipe with Id " + id);
		model.addAttribute("ingredient",
				ingredientService.findbyRecipeIdAndIngredientId(new Long(recipeId), new Long(id)));
		model.addAttribute("uomList", uomService.listAllUoms());
		return "recipe/ingredient/ingredientform";
	}

	@PostMapping
	@RequestMapping("recipe/{recipeId}/ingredients")
	public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

		log.debug("saved receipe id:" + savedCommand.getRecipeId());
		log.debug("saved ingredient id:" + savedCommand.getId());

		return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
	}

	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/new")
	public String newRecipe(@PathVariable String recipeId, Model model) {

		// make sure we have a good id value
		// RecipeCommand recipeCommand = recipeService.getRecipeCommandbyId(Long.valueOf(recipeId));
		// todo raise exception if null

		// need to return back parent id for hidden form property
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setRecipeId(Long.valueOf(recipeId));
		model.addAttribute("ingredient", ingredientCommand);

		// init uom
		ingredientCommand.setUom(new UnitOfMeasureCommand());

		model.addAttribute("uomList", uomService.listAllUoms());

		return "recipe/ingredient/ingredientform";

	}

	@GetMapping
	@RequestMapping({ "/recipe/{recipeId}/ingredient/{id}/delete" })
	public String deleteIngredients(@PathVariable String recipeId, @PathVariable String id) {

		log.debug("deleting ingredient id:" + id);
		ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));

		return "redirect:/recipe/" + recipeId + "/ingredient";
	}

}
