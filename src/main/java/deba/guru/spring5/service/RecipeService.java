package deba.guru.spring5.service;

import java.util.Set;

import deba.guru.spring5.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();

	Recipe getRecipebyId(Long Id);
}
