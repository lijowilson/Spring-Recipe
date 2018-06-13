package com.lee.recipe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lee.recipe.commands.RecipeCommand;
import com.lee.recipe.converters.RecipeCommandToRecipe;
import com.lee.recipe.converters.RecipeToRecipeCommand;
import com.lee.recipe.domain.Category;
import com.lee.recipe.domain.Recipe;
import com.lee.recipe.repository.CategoryRepository;
import com.lee.recipe.repository.RecipeRepository;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	
	private RecipeCommandToRecipe recipeCommandToRecipe;
	
	
	private RecipeToRecipeCommand recipeToRecipeCommand;
	
	
	/**
	 * 
	 */
	public RecipeServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param categoryRepo
	 */
	public RecipeServiceImpl(CategoryRepository categoryRepository,RecipeRepository recipeRepository,RecipeCommandToRecipe recipeCommandToRecipe,RecipeToRecipeCommand recipeToRecipeCommand) {
		super();
		this.categoryRepo = categoryRepository;
		this.recipeRepo = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
		
	}


	public List<Recipe> getAllRecipes(){
		log.info("inside getAllRecipes Method ...");
		List<Recipe> recipeList = new ArrayList<>();
		recipeRepo.findAll().iterator().forEachRemaining(recipeList::add);
		return recipeList;
	}

	public Recipe getRecipeById(Long Id){
		log.info("inside getRecipeById Method ...");
		Optional<Recipe> recipe = recipeRepo.findById(Id);
		
		if(!recipe.isPresent()) {
			throw new RuntimeException();
		}
		return recipe.get();
	}
	
	public List<Category> getAllCategories(){
		log.info("inside getAllRecipes Method ...");
		List<Category> categoryList = new ArrayList<>();
		categoryRepo.findAll().iterator().forEachRemaining(categoryList::add);
		return categoryList;
	}



	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
		Recipe recipeOb = recipeCommandToRecipe.convert(recipeCommand);
		Recipe savedObj = recipeRepo.save(recipeOb);
		log.info("saved Recipe Object ::"+savedObj.getId());
		return recipeToRecipeCommand.convert(savedObj);
		
	}
}
