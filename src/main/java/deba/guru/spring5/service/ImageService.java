package deba.guru.spring5.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	public void saveImageFile(String id, MultipartFile file);
}
