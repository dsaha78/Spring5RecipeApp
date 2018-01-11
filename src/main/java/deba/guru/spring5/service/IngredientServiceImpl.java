package deba.guru.spring5.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import deba.guru.spring5.command.IngredientCommand;
import deba.guru.spring5.converter.IngredientCommandToIngredient;
import deba.guru.spring5.converter.IngredientToIngredientCommand;
import deba.guru.spring5.domain.Ingredient;
import deba.guru.spring5.domain.Recipe;
import deba.guru.spring5.repositories.RecipeRepository;
import deba.guru.spring5.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;

	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
			IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}

	@Override
	public IngredientCommand findbyRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		if (!recipeOptional.isPresent()) {
			log.error("Ingredient Noy Found !!");
			throw new RuntimeException("Recipe Not Found !!");
		}

		Recipe recipe = recipeOptional.get();
		Optional<IngredientCommand> ingretientCommandOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

		if (!ingretientCommandOptional.isPresent()) {
			log.error("Ingredient Noy Found !!");
			throw new RuntimeException("Ingredient Not Found !!");
		}
		return ingretientCommandOptional.get();
	}

	@Override
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

		if (!recipeOptional.isPresent()) {
			log.error("Recipe not found for id: " + command.getRecipeId());
			return new IngredientCommand();
		} else {
			Recipe recipe = recipeOptional.get();

			Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
					.filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();

			if (ingredientOptional.isPresent()) {
				Ingredient ingredienttoSave = ingredientOptional.get();
				ingredienttoSave.setAmount(command.getAmount());
				ingredienttoSave.setDescription(command.getDescription());
				ingredienttoSave.setUom(unitOfMeasureRepository.findById(command.getUom().getId())
						.orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));

			} else {
				// add new Ingredient
				recipe.addIngredient(ingredientCommandToIngredient.convert(command));
			}
			Recipe savedRecipe = recipeRepository.save(recipe);
			/**
			 * return
			 * ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
			 * .filter(recipeIngredient ->
			 * recipeIngredient.getId().equals(command.getId())).findFirst().get());
			 **/
			Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
					.filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId())).findFirst();

			// check by description
			if (!savedIngredientOptional.isPresent()) {
				// not totally safe... But best guess
				savedIngredientOptional = savedRecipe.getIngredients().stream()
						.filter(recipeIngredients -> recipeIngredients.getDescription()
								.equals(command.getDescription()))
						.filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
						.filter(recipeIngredients -> recipeIngredients.getUom().getId()
								.equals(command.getUom().getId()))
						.findFirst();
			}

			// to do check for fail
			return ingredientToIngredientCommand.convert(savedIngredientOptional.get());

		}

	}
	
	@Override
    public void deleteById(Long recipeId, Long idToDelete) {

        log.debug("Deleting ingredient: " + recipeId + ":" + idToDelete);

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(recipeOptional.isPresent()){
            Recipe recipe = recipeOptional.get();
            log.debug("found recipe");

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(idToDelete))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                log.debug("found Ingredient");
                Ingredient ingredientToDelete = ingredientOptional.get();
                ingredientToDelete.setRecipe(null);
                recipe.getIngredients().remove(ingredientOptional.get());
                recipeRepository.save(recipe);
            }
        } else {
            log.debug("Recipe Id Not found. Id:" + recipeId);
        }
}

}
