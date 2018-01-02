package deba.guru.spring5.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import deba.guru.spring5.command.NotesCommand;
import deba.guru.spring5.domain.Notes;
import lombok.Synchronized;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

	@Synchronized
	@Nullable
	@Override
	public NotesCommand convert(Notes source) {

		if (source == null) {
			return null;
		}
		NotesCommand nc = new NotesCommand();
		nc.setId(source.getId());
		nc.setRecipeNotes(source.getRecipeNotes());

		return nc;
	}
}
