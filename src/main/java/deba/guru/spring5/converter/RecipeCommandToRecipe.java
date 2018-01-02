package deba.guru.spring5.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import deba.guru.spring5.command.CategoryCommand;
import deba.guru.spring5.command.IngredientCommand;
import deba.guru.spring5.command.RecipeCommand;
import deba.guru.spring5.domain.Recipe;
import lombok.Synchronized;

public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private NotesCommandToNotes notesCommandToNotes;
	private CategoryCommandToCategory categoryCommandToCategory;
	private IngredientCommandToIngredient ingredientCommandToIngredient;

	public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes,
			CategoryCommandToCategory categoryCommandToCategory,
			IngredientCommandToIngredient ingredientCommandToIngredient) {
		super();
		this.notesCommandToNotes = notesCommandToNotes;
		this.categoryCommandToCategory = categoryCommandToCategory;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}

	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand source) {
		if (source == null) {
			return null;
		}

		Recipe recipe = new Recipe();
		recipe.setId(source.getId());
		recipe.setCookTime(source.getCookTime());
		recipe.setDescription(source.getDescription());
		recipe.setDifficulty(source.getDifficulty());
		recipe.setDirections(source.getDescription());
		recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));
		recipe.setPrepTime(source.getPrepTime());
		recipe.setServings(source.getServings());
		recipe.setSource(source.getSource());
		recipe.setUrl(source.getUrl());
		
		if(source.getCategories() != null && source.getCategories().size() > 0) {
			source.getCategories().forEach((CategoryCommand categoryCommand) -> recipe.getCategories().add(categoryCommandToCategory.convert(categoryCommand)));
		}
		
		if(source.getIngredients() != null && source.getIngredients().size() > 0) {
			source.getIngredients().forEach((IngredientCommand ingredientCommand) -> recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredientCommand)));
		}

		return recipe;

	}

}
