package deba.guru.spring5.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import deba.guru.spring5.command.CategoryCommand;
import deba.guru.spring5.domain.Category;
import lombok.Synchronized;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>{

	@Synchronized
	@Nullable
	@Override
	public Category convert(CategoryCommand source) {
		if (source == null) {
			return null;
		}
		Category c = new Category();
		c.setId(source.getId());
		c.setDescription(source.getDescription());
		return c;
	}

}
