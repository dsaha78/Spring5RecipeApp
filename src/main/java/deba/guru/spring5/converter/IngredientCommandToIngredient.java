package deba.guru.spring5.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import deba.guru.spring5.command.IngredientCommand;
import deba.guru.spring5.domain.Ingredient;
import lombok.Synchronized;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

	private final UnitOfMeasureCommandToUnitOfMeasure converter;
	
	public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure converter) {
		this.converter = converter;
	}
	
	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(IngredientCommand source) {
		if (source == null) {
			return null;
		}
		
		Ingredient i = new Ingredient();
		i.setId(source.getId());
		i.setAmount(source.getAmount());
		i.setDescription(source.getDescription());
		i.setUom(converter.convert(source.getUnitOfMeasureCommand()));
		return i;
	}

}
