package deba.guru.spring5.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import deba.guru.spring5.command.UnitOfMeasureCommand;
import deba.guru.spring5.domain.UnitOfMeasure;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

	private static final Long ID = 1L;
	private static final String DESCRIPTION = "DESCIPTION";

	UnitOfMeasureCommandToUnitOfMeasure converter;

	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasureCommandToUnitOfMeasure();
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
		uomCommand.setId(ID);
		uomCommand.setDescription(DESCRIPTION);

		UnitOfMeasure uom = converter.convert(uomCommand);

		assertNotNull(uom);
		assertEquals(ID, uomCommand.getId());
		assertEquals(DESCRIPTION, uom.getDescription());
	}
}
