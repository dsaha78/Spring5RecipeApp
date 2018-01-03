package deba.guru.spring5.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import deba.guru.spring5.command.IngredientCommand;
import deba.guru.spring5.domain.Ingredient;
import deba.guru.spring5.domain.Recipe;
import deba.guru.spring5.domain.UnitOfMeasure;

public class IngredientToIngredientCommandTest {

	public static final Recipe RECIPE = new Recipe();
	public static final BigDecimal AMOUNT = new BigDecimal("1");
	public static final String DESCRIPTION = "DESCRIPTION";
	public static final Long UOM_ID = new Long(2L);
	public static final Long ID = new Long(1L);

	IngredientToIngredientCommand converter;

	@Before
	public void setUp() throws Exception {
		converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
	}

	@Test
	public void testNullConvert() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new Ingredient()));
	}

	@Test
	public void testConvertNullUOM() throws Exception {

		Ingredient i = new Ingredient();
		i.setId(ID);
		i.setRecipe(RECIPE);
		i.setAmount(AMOUNT);
		i.setDescription(DESCRIPTION);
		i.setUom(null);

		IngredientCommand ic = converter.convert(i);

		assertNull(ic.getUom());
		assertEquals(ID, ic.getId());
		assertEquals(AMOUNT, ic.getAmount());
		assertEquals(DESCRIPTION, ic.getDescription());
	}

	@Test
	public void testConvertWithUom() throws Exception {
		
		Ingredient i = new Ingredient();
		i.setId(ID);
		i.setRecipe(RECIPE);
		i.setAmount(AMOUNT);
		i.setDescription(DESCRIPTION);

		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(UOM_ID);

		i.setUom(uom);
		
		IngredientCommand ic = converter.convert(i);

		assertEquals(ID, ic.getId());
		assertNotNull(ic.getUom());
		assertEquals(UOM_ID, ic.getUom().getId());
		assertEquals(AMOUNT, ic.getAmount());
		assertEquals(DESCRIPTION, ic.getDescription());
	}

}
