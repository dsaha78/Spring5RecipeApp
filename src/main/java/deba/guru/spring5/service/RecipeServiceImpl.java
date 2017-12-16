package deba.guru.spring5.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import deba.guru.spring5.domain.Recipe;
import deba.guru.spring5.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {

		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {

		Set<Recipe> recipes = new HashSet<Recipe>();
		recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
		log.debug("inside RecipeService Call !!!!");
		return recipes;

		//return (Set<Recipe>) recipeRepository.findAll();
	}
	
	@Override
	public Recipe getRecipebyId(Long Id) {
		
		Optional<Recipe> recipeOptional = recipeRepository.findById(Id);
		if(!recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe Not Found !!");
		}
		return recipeOptional.get();
	}

}
