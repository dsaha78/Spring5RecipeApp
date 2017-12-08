package deba.guru.spring5.repositories;

import org.springframework.data.repository.CrudRepository;

import deba.guru.spring5.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
