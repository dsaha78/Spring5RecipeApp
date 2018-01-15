package deba.guru.spring5.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import deba.guru.spring5.domain.Recipe;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

	public final RecipeService recipeService;

	public ImageServiceImpl(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@Override
	public void saveImageFile(String id, MultipartFile file) {

		try {
			Recipe recipe = recipeService.getRecipebyId(new Long(id));
			Byte[] fileinBytes = new Byte[file.getBytes().length];

			int i = 0;
			for (byte b : file.getBytes()) {
				fileinBytes[i++] = b;
			}
			recipe.setImage(fileinBytes);
			recipeService.saveRecipe(recipe);

		} catch (IOException e) {
			log.error("IOException Occurs !!!");
			e.printStackTrace();
		}

	}

}
