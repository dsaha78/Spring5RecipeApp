package deba.guru.spring5.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import deba.guru.spring5.command.IngredientCommand;
import deba.guru.spring5.domain.Ingredient;
import lombok.Synchronized;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

	private final UnitOfMeasureToUnitOfMeasureCommand converter;
	
	public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand converter) {
		this.converter = converter;
	}
	
	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(Ingredient source) {
		if (source == null) {
			return null;
		}
		
		IngredientCommand ic = new IngredientCommand();
		ic.setId(source.getId());
		ic.setAmount(source.getAmount());
		ic.setDescription(source.getDescription());
		ic.setRecipeId(source.getRecipe().getId());
		ic.setUom(converter.convert(source.getUom()));
		return ic;
	}

}
