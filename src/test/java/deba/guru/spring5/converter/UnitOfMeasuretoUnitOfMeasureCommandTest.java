package deba.guru.spring5.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import deba.guru.spring5.command.UnitOfMeasureCommand;
import deba.guru.spring5.domain.UnitOfMeasure;

public class UnitOfMeasuretoUnitOfMeasureCommandTest {

	public static final Long id = 1L;
	public static final String description = "DESCIPTION";

	UnitOfMeasuretoUnitOfMeasureCommand converter;

	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasuretoUnitOfMeasureCommand();
	}

	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyParameter() {
		assertNotNull(converter.convert(new UnitOfMeasure()));
	}

	@Test
	public void convert() {
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(id);
		uom.setDescription(description);

		UnitOfMeasureCommand uomC = converter.convert(uom);

		assertNotNull(uom);
		assertEquals(id, uomC.getId());
		assertEquals(description, uomC.getDescription());
	}
}
