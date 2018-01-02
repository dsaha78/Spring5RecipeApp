package deba.guru.spring5.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import deba.guru.spring5.command.CategoryCommand;
import deba.guru.spring5.domain.Category;
import lombok.Synchronized;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

	@Synchronized
	@Nullable
	@Override
	public CategoryCommand convert(Category source) {
		if (source == null) {
			return null;
		}
		
		CategoryCommand cc = new CategoryCommand();
		cc.setId(source.getId());
		cc.setDescription(source.getDescription());
		return cc;
	}

}
