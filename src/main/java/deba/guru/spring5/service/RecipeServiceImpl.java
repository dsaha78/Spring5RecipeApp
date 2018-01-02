package deba.guru.spring5.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import deba.guru.spring5.command.RecipeCommand;
import deba.guru.spring5.converter.RecipeCommandToRecipe;
import deba.guru.spring5.converter.RecipeToRecipeCommand;
import deba.guru.spring5.domain.Recipe;
import deba.guru.spring5.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandConverter;
	private final RecipeToRecipeCommand recipeConverter;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandConverter,
			RecipeToRecipeCommand recipeConverter) {

		this.recipeRepository = recipeRepository;
		this.recipeCommandConverter = recipeCommandConverter;
		this.recipeConverter = recipeConverter;
	}

	@Override
	public Set<Recipe> getRecipes() {

		Set<Recipe> recipes = new HashSet<Recipe>();
		recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
		log.debug("inside RecipeService Call !!!!");
		return recipes;

		// return (Set<Recipe>) recipeRepository.findAll();
	}

	@Override
	public Recipe getRecipebyId(Long Id) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(Id);
		if (!recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe Not Found !!");
		}
		return recipeOptional.get();
	}

	@Override
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {

		Recipe detachedRecipe = recipeCommandConverter.convert(recipeCommand);
		Recipe savedRecipe = recipeRepository.save(detachedRecipe);
		log.debug("Recipe Save !! ID is "+savedRecipe.getId());
		return recipeConverter.convert(savedRecipe);
	}

}
