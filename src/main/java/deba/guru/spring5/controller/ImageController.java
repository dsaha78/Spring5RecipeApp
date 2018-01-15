package deba.guru.spring5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import deba.guru.spring5.service.ImageService;
import deba.guru.spring5.service.RecipeService;

@Controller
public class ImageController {

	private final ImageService imageService;
	private final RecipeService recipeService;

	public ImageController(ImageService imagseService, RecipeService recipeService) {
		super();
		this.imageService = imagseService;
		this.recipeService = recipeService;
	}

	@GetMapping("recipe/{id}/image")
	public String showUploadForm(@PathVariable String id, Model model) {

		model.addAttribute("recipe", recipeService.getRecipebyId(new Long(id)));
		return "recipe/imageuploadform";
	}
	
	@PostMapping("recipe/{id}/image")
	public String handleImagePost(@PathVariable String id,@RequestParam("imagefile") MultipartFile file) {
		
		imageService.saveImageFile(id, file);
		return "redirect:/recipe/"+id+"/show";
	}
	
}
