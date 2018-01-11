package deba.guru.spring5.service;

import deba.guru.spring5.command.IngredientCommand;

public interface IngredientService {

	IngredientCommand findbyRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

	IngredientCommand saveIngredientCommand(IngredientCommand command);

	void deleteById(Long recipeId, Long idToDelete);
}
