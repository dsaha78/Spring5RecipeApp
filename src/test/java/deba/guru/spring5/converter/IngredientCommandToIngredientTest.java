package deba.guru.spring5.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import deba.guru.spring5.command.IngredientCommand;
import deba.guru.spring5.command.UnitOfMeasureCommand;
import deba.guru.spring5.domain.Ingredient;

public class IngredientCommandToIngredientTest {

	public static final BigDecimal AMOUNT = new BigDecimal("1");
	public static final String DESCRIPTION = "DESCRIPTION";
	public static final Long ID = new Long(1L);
	public static final Long UOM_ID = new Long(2L);

	IngredientCommandToIngredient converter;

	@Before
	public void setUp() throws Exception {
		converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
	}

	@Test
	public void testNullObject() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new IngredientCommand()));
	}

	@Test
	public void convert() throws Exception {
		IngredientCommand ic = new IngredientCommand();
		ic.setId(ID);
		ic.setAmount(AMOUNT);
		ic.setDescription(DESCRIPTION);
		UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
		unitOfMeasureCommand.setId(UOM_ID);
		ic.setUom(unitOfMeasureCommand);

		Ingredient i = converter.convert(ic);

		assertNotNull(i);
		assertNotNull(i.getUom());
		assertEquals(ID, i.getId());
		assertEquals(AMOUNT, i.getAmount());
		assertEquals(DESCRIPTION, i.getDescription());
		assertEquals(UOM_ID, i.getUom().getId());
	}

	@Test
	public void convertWithNullUOM() throws Exception {
		IngredientCommand ic = new IngredientCommand();
		ic.setId(ID);
		ic.setAmount(AMOUNT);
		ic.setDescription(DESCRIPTION);

		Ingredient i = converter.convert(ic);

		assertNotNull(i);
		assertNull(i.getUom());
		assertEquals(ID, i.getId());
		assertEquals(AMOUNT, i.getAmount());
		assertEquals(DESCRIPTION, i.getDescription());

	}

}
