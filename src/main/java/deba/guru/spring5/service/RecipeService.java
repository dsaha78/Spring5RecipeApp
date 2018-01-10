package deba.guru.spring5.service;

import java.util.Set;

import deba.guru.spring5.command.RecipeCommand;
import deba.guru.spring5.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();

	Recipe getRecipebyId(Long Id);
	
	RecipeCommand saveRecipeCommand(RecipeCommand recipeCpmmand);
	RecipeCommand getRecipeCommandbyId(long id);
	void deletebyId(long id);

}
