package deba.guru.spring5.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import deba.guru.spring5.command.NotesCommand;
import deba.guru.spring5.domain.Notes;
import lombok.Synchronized;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

	@Synchronized
	@Nullable
	@Override
	public Notes convert(NotesCommand source) {
		if (source == null) {
			return null;
		}
		
		Notes n = new Notes();
		n.setId(source.getId());
		n.setRecipeNotes(source.getRecipeNotes());
		
		return n;
	}

}
