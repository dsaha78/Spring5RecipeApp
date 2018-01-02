package deba.guru.spring5.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import deba.guru.spring5.command.CategoryCommand;
import deba.guru.spring5.command.IngredientCommand;
import deba.guru.spring5.command.NotesCommand;
import deba.guru.spring5.command.RecipeCommand;
import deba.guru.spring5.domain.Difficulty;
import deba.guru.spring5.domain.Recipe;

public class RecipeCommandToRecipeTest {

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

	private RecipeCommandToRecipe converter;

	@Before
	public void setUp() throws Exception {
		converter = new RecipeCommandToRecipe(new NotesCommandToNotes(), new CategoryCommandToCategory(),
				new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));
	}

	@Test
	public void testNullObject() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new RecipeCommand()));
	}

	@Test
	public void convert() throws Exception {
		RecipeCommand rc = new RecipeCommand();
		rc.setId(RECIPE_ID);
		rc.setCookTime(COOK_TIME);
		rc.setPrepTime(PREP_TIME);
		rc.setDescription(DESCRIPTION);
		rc.setDifficulty(DIFFICULTY);
		rc.setDirections(DIRECTIONS);
		rc.setServings(SERVINGS);
		rc.setSource(SOURCE);
		rc.setUrl(URL);

		NotesCommand notes = new NotesCommand();
		notes.setId(NOTES_ID);

		rc.setNotes(notes);

		CategoryCommand c1 = new CategoryCommand();
		c1.setId(CAT_ID_1);

		CategoryCommand c2 = new CategoryCommand();
		c2.setId(CAT_ID2);

		rc.getCategories().add(c1);
		rc.getCategories().add(c2);

		IngredientCommand i1 = new IngredientCommand();
		i1.setId(INGRED_ID_1);

		IngredientCommand i2 = new IngredientCommand();
		i2.setId(INGRED_ID_2);

		rc.getIngredients().add(i1);
		rc.getIngredients().add(i2);

		Recipe r = converter.convert(rc);

		assertNotNull(r);
		assertEquals(RECIPE_ID, r.getId());
		assertEquals(COOK_TIME, r.getCookTime());
		assertEquals(PREP_TIME, r.getPrepTime());
		assertEquals(DESCRIPTION, r.getDescription());
		assertEquals(DIFFICULTY, r.getDifficulty());
		assertEquals(DIRECTIONS, r.getDirections());
		assertEquals(SERVINGS, r.getServings());
		assertEquals(SOURCE, r.getSource());
		assertEquals(URL, r.getUrl());
		assertEquals(NOTES_ID, r.getNotes().getId());
		assertEquals(2, r.getCategories().size());
		assertEquals(2, r.getIngredients().size());
	}

}
