package deba.guru.spring5.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import deba.guru.spring5.service.RecipeService;

public class RecipeController {

	private RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({ "/recipe" })
	public String getIndexPage(Model model) {

		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}
}