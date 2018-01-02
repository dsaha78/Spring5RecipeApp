package deba.guru.spring5.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import deba.guru.spring5.converter.RecipeCommandToRecipe;
import deba.guru.spring5.converter.RecipeToRecipeCommand;
import deba.guru.spring5.domain.Recipe;
import deba.guru.spring5.repositories.RecipeRepository;
import deba.guru.spring5.service.RecipeServiceImpl;

public class RecipeServiceImplTest {

	RecipeServiceImpl recipeServiceImpl;

	@Mock
	RecipeRepository recipeRepository;
	
	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;
	
	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);
		recipeServiceImpl = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}

	@Test
	public void getRecipe() {
		
		Set<Recipe> recipeData = new HashSet<Recipe>();
		Recipe recipe = new Recipe();
		recipeData.add(recipe);
		
		when(recipeServiceImpl.getRecipes()).thenReturn(recipeData);
		
		Set<Recipe> recipes = recipeServiceImpl.getRecipes();
		assertEquals(1, recipes.size());
		verify(recipeRepository, times(1)).findAll();
	}
	
	
	@Test
	public void getRecipebyIdTest() {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		Optional<Recipe> returnRecipe =  recipeRepository.findById(1L);
		
		assertNotNull("Recipe is NULL !!!!", returnRecipe.get());
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
	}
}
