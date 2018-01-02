package deba.guru.spring5.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import deba.guru.spring5.command.CategoryCommand;
import deba.guru.spring5.domain.Category;

public class CategoryToCategoryCommandTest {

	CategoryToCategoryCommand converter;
	Long id = 1L;
	String description = "CATEGORY_DESCRIPTION";
	@Before
	public void setUp() throws Exception {
		converter = new CategoryToCategoryCommand();
	}
	
	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new Category()));
	}
	
	@Test
	public void testConvert() {
		
		Category c = new Category();
		c.setId(id);
		c.setDescription(description);
		
		CategoryCommand cc = converter.convert(c);
		
		assertNotNull(cc);
		assertEquals(id, cc.getId());
		assertEquals(description, cc.getDescription());
		
	}

}
