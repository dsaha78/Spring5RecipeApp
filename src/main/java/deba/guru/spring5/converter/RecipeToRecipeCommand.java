package deba.guru.spring5.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import deba.guru.spring5.command.RecipeCommand;
import deba.guru.spring5.domain.Category;
import deba.guru.spring5.domain.Ingredient;
import deba.guru.spring5.domain.Recipe;
import lombok.Synchronized;

public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

	private NotesToNotesCommand notesToNotesCommand;
	private CategoryToCategoryCommand categoryToCategoryCommand;
	private IngredientToIngredientCommand ingredientToIngredientCommand;

	public RecipeToRecipeCommand(NotesToNotesCommand notesToNotesCommand,
			CategoryToCategoryCommand categoryToCategoryCommand,
			IngredientToIngredientCommand ingredientToIngredientCommand) {
		super();
		this.notesToNotesCommand = notesToNotesCommand;
		this.categoryToCategoryCommand = categoryToCategoryCommand;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
	}

	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		if (source == null) {
			return null;
		}

		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(source.getId());
		recipeCommand.setCookTime(source.getCookTime());
		recipeCommand.setDescription(source.getDescription());
		recipeCommand.setDifficulty(source.getDifficulty());
		recipeCommand.setDirections(source.getDirections());
		recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));
		recipeCommand.setPrepTime(source.getPrepTime());
		recipeCommand.setServings(source.getServings());
		recipeCommand.setSource(source.getSource());
		recipeCommand.setUrl(source.getUrl());
		
		if(source.getCategories() != null && source.getCategories().size() > 0) {
			source.getCategories().forEach((Category category) -> recipeCommand.getCategories().add(categoryToCategoryCommand.convert(category)));
		}
		
		if(source.getIngredients() != null && source.getIngredients().size() > 0) {
			source.getIngredients().forEach((Ingredient ingredient) -> recipeCommand.getIngredients().add(ingredientToIngredientCommand.convert(ingredient)));
		}

		return recipeCommand;

	}

}
