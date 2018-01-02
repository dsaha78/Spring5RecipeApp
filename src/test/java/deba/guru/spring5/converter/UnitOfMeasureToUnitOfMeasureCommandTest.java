package deba.guru.spring5.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import deba.guru.spring5.command.UnitOfMeasureCommand;
import deba.guru.spring5.domain.UnitOfMeasure;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

	private static final Long ID = 1L;
	private static final String DESCRIPTION = "DESCIPTION";

	private UnitOfMeasureToUnitOfMeasureCommand converter;

	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasureToUnitOfMeasureCommand();
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
		uom.setId(ID);
		uom.setDescription(DESCRIPTION);

		UnitOfMeasureCommand uomC = converter.convert(uom);

		assertNotNull(uom);
		assertEquals(ID, uomC.getId());
		assertEquals(DESCRIPTION, uomC.getDescription());
	}
}
