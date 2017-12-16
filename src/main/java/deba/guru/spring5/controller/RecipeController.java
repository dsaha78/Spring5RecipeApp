package deba.guru.spring5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
		return "recipe";
	}

	@RequestMapping({ "/recipe/show/{id}" })
	public String showbyId(@PathVariable String id, Model model) {

		model.addAttribute("recipe", recipeService.getRecipebyId(new Long(id)));
		log.debug("Inside Controller !!! inside recipe Controller !!");
		return "recipe/show";
	}
}