package deba.guru.spring5.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import deba.guru.spring5.command.CategoryCommand;
import deba.guru.spring5.domain.Category;

public class CategoryCommandToCategoryTest {

	CategoryCommandToCategory converter;
	Long id = 1L;
	String description = "CATEGORY_DESCRIPTION";
	@Before
	public void setUp() throws Exception {
		converter = new CategoryCommandToCategory();
	}
	
	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new CategoryCommand()));
	}
	
	@Test
	public void testConvert() {
		
		CategoryCommand cc = new CategoryCommand();
		cc.setId(id);
		cc.setDescription(description);
		
		Category c = converter.convert(cc);
		
		assertNotNull(c);
		assertEquals(id, c.getId());
		assertEquals(description, c.getDescription());
		
	}

}
