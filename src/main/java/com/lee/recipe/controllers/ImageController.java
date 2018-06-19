package com.lee.recipe.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lee.recipe.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ImageController {

	 //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "webapp//images//uploads//";

    @Autowired
    RecipeService recipeService;
    
	@PostMapping("/recipe/ImageUpload")
	public ResponseEntity<?>  uploadImage( @RequestParam("uploadfile") MultipartFile uploadfile,@RequestParam("recipeId") Long recipeId) {

        log.debug("Single file upload!");

        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

     
        try {
        	recipeService.saveImage(recipeId, uploadfile);
        
           // saveUploadedFiles(Arrays.asList(uploadfile));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

	
		  
		  return new ResponseEntity<>(HttpStatus.OK);
	}
	
	 //save file
	private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

		for (MultipartFile file : files) {
			try {

				if (file.isEmpty()) {
					continue; // next pls
				}

				byte[] bytes = file.getBytes();
				Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
				Files.write(path, bytes);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
