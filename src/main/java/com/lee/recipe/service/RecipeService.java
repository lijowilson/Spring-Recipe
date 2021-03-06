package com.lee.recipe.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lee.recipe.commands.RecipeCommand;
import com.lee.recipe.commands.UnitOfMeasureCommand;
import com.lee.recipe.domain.Category;
import com.lee.recipe.domain.Recipe;

public interface RecipeService {
	 List<Recipe> getAllRecipes();

	 Recipe getRecipeById(Long Id);

	 RecipeCommand saveRecipeCommand(RecipeCommand command);
	 
	 List<Category> getAllCategories();
	 
	 RecipeCommand getRecipeCommandById(Long Id);
	
	 void deleteRecipeById(Long Id);
	 
	 void saveImage(Long recipeId,MultipartFile file);
	 
	 List<UnitOfMeasureCommand> getAllUomCommands();
}
