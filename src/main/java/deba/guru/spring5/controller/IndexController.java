package deba.guru.spring5.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import deba.guru.spring5.domain.Category;
import deba.guru.spring5.domain.UnitOfMeasure;
import deba.guru.spring5.repositories.CategoryRepository;
import deba.guru.spring5.repositories.UnitOfMeasureRepository;

@Controller
public class IndexController {

	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@RequestMapping({ "", "/", "/index" })
	public String getIndexPage() {
		
		Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
		Optional<UnitOfMeasure> unitofMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		System.out.printf("Category Id is %s",categoryOptional.get().getId());
		System.out.printf("UoM id is %s", unitofMeasureOptional.get().getId());
		return "index";
	}
}
