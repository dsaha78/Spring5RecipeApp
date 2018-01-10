package deba.guru.spring5.service;

import java.util.Optional;

import deba.guru.spring5.command.IngredientCommand;
import deba.guru.spring5.converter.IngredientToIngredientCommand;
import deba.guru.spring5.domain.Recipe;
import deba.guru.spring5.repositories.RecipeRepository;

public class IngredientServiceImpl implements IngredientService {

	private RecipeRepository recipeRepository;
	private IngredientToIngredientCommand ingredientToIngredientCommand;

	@Override
	public IngredientCommand findbyRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		if (!recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe Not Found !!");
		}

		Recipe recipe = recipeOptional.get();
		Optional<IngredientCommand> ingretientCommandOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

		if (!ingretientCommandOptional.isPresent()) {
			throw new RuntimeException("Recipe Not Found !!");
		}
		return ingretientCommandOptional.get();
	}

}
