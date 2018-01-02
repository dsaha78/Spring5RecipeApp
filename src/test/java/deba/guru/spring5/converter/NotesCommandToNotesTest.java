package deba.guru.spring5.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import deba.guru.spring5.command.NotesCommand;
import deba.guru.spring5.domain.Notes;

public class NotesCommandToNotesTest {

	NotesCommandToNotes converter;
	private static final Long ID = 1L;
	private static final String RECIPE_NOTES = "RecipeNotes";
	
	@Before
	public void setUp() throws Exception {
		converter = new NotesCommandToNotes();
	}
	
	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new NotesCommand()));
	}

	@Test
	public void test() {
		NotesCommand nc = new NotesCommand();
		nc.setId(ID);
		nc.setRecipeNotes(RECIPE_NOTES);
		
		Notes n = converter.convert(nc);
		
		assertNotNull(n);
		assertEquals(ID, n.getId());
		assertEquals(RECIPE_NOTES, n.getRecipeNotes());
	}

}
