package deba.guru.spring5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import deba.guru.spring5.service.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

	private RecipeService recipeService;

	public IndexController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	@RequestMapping({ "", "/", "/index" })

	public String getIndexPage(Model model) {

		model.addAttribute("recipes", recipeService.getRecipes());
		log.debug("Inside Controller !!! inside recipe Controller !!");
		return "index";
	}
}
