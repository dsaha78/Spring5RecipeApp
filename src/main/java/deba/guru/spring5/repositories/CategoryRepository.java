package deba.guru.spring5.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import deba.guru.spring5.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

	
	Optional<Category> findByDescription(String description);
}
