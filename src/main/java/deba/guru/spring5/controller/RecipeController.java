package deba.guru.spring5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import deba.guru.spring5.command.RecipeCommand;
import deba.guru.spring5.service.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecipeController {

	private RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({ "/recipe" })
	public String getIndexPage(Model model) {

		model.addAttribute("recipes", recipeService.getRecipes());
		log.debug("Inside Controller !!! inside recipe Controller !!");
		return "index";
	}

	@RequestMapping({ "/recipe/{id}/show" })
	public String showbyId(@PathVariable String id, Model model) {

		model.addAttribute("recipe", recipeService.getRecipebyId(new Long(id)));
		log.debug("Inside Controller !!! inside recipe Controller !!");
		return "recipe/show";
	}

	@RequestMapping({ "/recipe/{id}/update" })
	public String updateRecipe(@PathVariable String id, Model model) {

		model.addAttribute("recipe", recipeService.getRecipeCommandbyId(new Long(id)));
		return "recipe/recipeform";
	}

	@RequestMapping({"/recipe/{id}/delete"})
	public String deleteRecipe(@PathVariable String id, Model model) {
		recipeService.deletebyId(new Long(id));
		//model.addAttribute("recipes", recipeService.getRecipes());
		return "redirect:/";
	}
	
	@GetMapping
	@RequestMapping({ "/recipe/new" })
	public String newRecipe(Model model) {
		model.addAttribute("recipe",new RecipeCommand());
		return "recipe/recipeform";
	}

	@PostMapping
	// Why the following mapping not working!!
	//@RequestMapping(name = "recipeSaveorUpdate", method = RequestMethod.POST)
	@RequestMapping("recipeSaveorUpdate")
	public String saveorUpdate(@ModelAttribute RecipeCommand recipeCommand) {
		RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);
		return "redirect:/recipe/" + savedRecipe.getId()+"/show";
	}
}