package deba.guru.spring5.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import deba.guru.spring5.service.IngredientService;
import deba.guru.spring5.service.RecipeService;

public class IngredientControllerTest {

	@Mock
	RecipeService recipeService;
	@Mock
	IngredientService ingredientService;

	MockMvc mockMvc;

	IngredientController controller;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new IngredientController(recipeService, ingredientService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}

	@Test
	public void testListIngredients() throws Exception {

		mockMvc.perform(get("/recipe/1/ingredients")).andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/list"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));

		verify(recipeService, times(1)).getRecipeCommandbyId(anyLong());

	}

	@Test
	public void testShowIngredients() throws Exception {

		mockMvc.perform(get("/recipe/1/ingredient/2/show")).andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/show"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("ingredient"));

		verify(recipeService, times(1)).getRecipeCommandbyId(anyLong());

	}
}
