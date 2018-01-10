package deba.guru.spring5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import deba.guru.spring5.service.IngredientService;
import deba.guru.spring5.service.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientController {

	private RecipeService recipeService;
	private IngredientService ingredientService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
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
		
		model.addAttribute("ingredient", ingredientService.findbyRecipeIdAndIngredientId(new Long(recipeId), new Long(id)));
		return "/recipe/ingredient/show";

	}
}
