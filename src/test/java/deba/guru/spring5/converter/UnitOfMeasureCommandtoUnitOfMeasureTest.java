package deba.guru.spring5.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import deba.guru.spring5.command.UnitOfMeasureCommand;
import deba.guru.spring5.domain.UnitOfMeasure;

public class UnitOfMeasureCommandtoUnitOfMeasureTest {

	public static final Long id = 1L;
	public static final String description = "DESCIPTION";

	UnitOfMeasureCommandtoUnitOfMeasure converter;

	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasureCommandtoUnitOfMeasure();
	}

	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyParameter() {
		assertNotNull(converter.convert(new UnitOfMeasureCommand()));
	}

	@Test
	public void convert() {
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(id);
		uomCommand.setDescription(description);

		UnitOfMeasure uom = converter.convert(uomCommand);

		assertNotNull(uom);
		assertEquals(id, uomCommand.getId());
		assertEquals(description, uom.getDescription());
	}
}
