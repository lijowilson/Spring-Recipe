package com.lee.recipe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lee.recipe.domain.Category;
import com.lee.recipe.domain.Recipe;
import com.lee.recipe.repository.CategoryRepository;
import com.lee.recipe.repository.RecipeRepository;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class RecipeService {
	
	
	/**
	 * 
	 */
	public RecipeService() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private RecipeRepository recipeRep;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	/**
	 * @param categoryRepo
	 */
	public RecipeService(CategoryRepository categoryRepository,RecipeRepository recipeRepository) {
		super();
		this.categoryRepo = categoryRepository;
		this.recipeRep = recipeRepository;
	}


	public List<Recipe> getAllRecipes(){
		log.info("inside getAllRecipes Method ...");
		List<Recipe> recipeList = new ArrayList<>();
		recipeRep.findAll().iterator().forEachRemaining(recipeList::add);
		return recipeList;
	}

	public Recipe getRecipeById(Long Id){
		log.info("inside getRecipeById Method ...");
		Optional<Recipe> recipe = recipeRep.findById(Id);
		
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
}
