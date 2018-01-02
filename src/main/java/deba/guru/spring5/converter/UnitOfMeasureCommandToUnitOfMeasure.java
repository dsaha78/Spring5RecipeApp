package deba.guru.spring5.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import deba.guru.spring5.command.UnitOfMeasureCommand;
import deba.guru.spring5.domain.UnitOfMeasure;
import lombok.Synchronized;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand source) {
		if (source == null) {
			return null;
		}
		final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setId(source.getId());
		unitOfMeasure.setDescription(source.getDescription());

		return unitOfMeasure;
	}

}
