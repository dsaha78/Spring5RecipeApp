package deba.guru.spring5.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import deba.guru.spring5.command.RecipeCommand;
import deba.guru.spring5.domain.Category;
import deba.guru.spring5.domain.Difficulty;
import deba.guru.spring5.domain.Ingredient;
import deba.guru.spring5.domain.Notes;
import deba.guru.spring5.domain.Recipe;

public class RecipeToRecipeCommandTest {

	public static final Long RECIPE_ID = 1L;
	public static final Integer COOK_TIME = Integer.valueOf("7");
	public static final Integer PREP_TIME = Integer.valueOf("10");
	public static final String DESCRIPTION = "My Recipe";
	public static final String DIRECTIONS = "Directions";
	public static final Difficulty DIFFICULTY = Difficulty.EASY;
	public static final Integer SERVINGS = Integer.valueOf("4");
	public static final String SOURCE = "Source";
	public static final String URL = "Some URL";
	public static final Long CAT_ID_1 = 1L;
	public static final Long CAT_ID2 = 2L;
	public static final Long INGRED_ID_1 = 3L;
	public static final Long INGRED_ID_2 = 4L;
	public static final Long NOTES_ID = 9L;
	
	private RecipeToRecipeCommand converter;

	@Before
	public void setUp() throws Exception {
		converter = new RecipeToRecipeCommand(new NotesToNotesCommand(), new CategoryToCategoryCommand(),
				new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
	}

	@Test
	public void testNullObject() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new Recipe()));
	}

	@Test
	public void convert() throws Exception {
		Recipe r = new Recipe();
		r.setId(RECIPE_ID);
		r.setCookTime(COOK_TIME);
		r.setPrepTime(PREP_TIME);
		r.setDescription(DESCRIPTION);
		r.setDifficulty(DIFFICULTY);
		r.setDirections(DIRECTIONS);
		r.setServings(SERVINGS);
		r.setSource(SOURCE);
		r.setUrl(URL);

		Notes notes = new Notes();
		notes.setId(NOTES_ID);

		r.setNotes(notes);

		Category c1 = new Category();
		c1.setId(CAT_ID_1);

		Category c2 = new Category();
		c2.setId(CAT_ID2);

		r.getCategories().add(c1);
		r.getCategories().add(c2);

		Ingredient i1 = new Ingredient();
		i1.setId(INGRED_ID_1);

		Ingredient i2 = new Ingredient();
		i2.setId(INGRED_ID_2);

		r.getIngredients().add(i1);
		r.getIngredients().add(i2);

		RecipeCommand rc = converter.convert(r);

		assertNotNull(rc);
		assertEquals(RECIPE_ID, rc.getId());
		assertEquals(COOK_TIME, rc.getCookTime());
		assertEquals(PREP_TIME, rc.getPrepTime());
		assertEquals(DESCRIPTION, rc.getDescription());
		assertEquals(DIFFICULTY, rc.getDifficulty());
		assertEquals(DIRECTIONS, rc.getDirections());
		assertEquals(SERVINGS, rc.getServings());
		assertEquals(SOURCE, rc.getSource());
		assertEquals(URL, rc.getUrl());
		assertEquals(NOTES_ID, rc.getNotes().getId());
		assertEquals(2, rc.getCategories().size());
		assertEquals(2, rc.getIngredients().size());

	}

}
