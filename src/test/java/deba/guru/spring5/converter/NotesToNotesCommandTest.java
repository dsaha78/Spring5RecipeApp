package deba.guru.spring5.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import deba.guru.spring5.command.NotesCommand;
import deba.guru.spring5.domain.Notes;

public class NotesToNotesCommandTest {

	NotesToNotesCommand converter;
	private static final Long ID = 1L;
	private static final String RECIPE_NOTES = "RecipeNotes";
	
	@Before
	public void setUp() throws Exception {
		converter = new NotesToNotesCommand();
	}
	
	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new Notes()));
	}

	@Test
	public void test() {
		Notes n = new Notes();
		n.setId(ID);
		n.setRecipeNotes(RECIPE_NOTES);
		
		NotesCommand nc = converter.convert(n);
		
		assertNotNull(nc);
		assertEquals(ID, nc.getId());
		assertEquals(RECIPE_NOTES, nc.getRecipeNotes());
	}

}
