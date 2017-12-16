package deba.guru.spring5.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import deba.guru.spring5.domain.Category;

public class CategoryTest {

	Category category;

	@Before
	public void setUp() {
		category = new Category();
	}

	@Test
	public void getId() {
		Long idValues = 4L;
		category.setId(idValues);
		assertEquals(idValues, category.getId());
	}

	@Test
	public void getDescription() {

	}

	@Test
	public void getRecipes() {

	}
}
