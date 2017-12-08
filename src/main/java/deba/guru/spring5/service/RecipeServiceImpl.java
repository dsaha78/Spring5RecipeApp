package deba.guru.spring5.service;

import java.util.Set;

import deba.guru.spring5.domain.Recipe;
import deba.guru.spring5.repositories.RecipeRepository;

public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {

		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {

		// Set<Recipe> recipes = new HashSet<Recipe>();
		// recipeRepository.findAll().iterator().forEachRemaining(recipes::add);

		return (Set<Recipe>) recipeRepository.findAll();
	}

}
