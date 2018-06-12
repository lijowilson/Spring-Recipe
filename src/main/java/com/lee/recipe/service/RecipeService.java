package com.lee.recipe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lee.recipe.domain.Recipe;
import com.lee.recipe.repository.RecipeRepository;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class RecipeService {
	
	private RecipeRepository recipeRep;
	
	public RecipeService(RecipeRepository recipeRepository) {
		this.recipeRep = recipeRepository;
	}

	public List<Recipe> getAllRecipes(){
		log.info("inside getAllRecipes Method ...");
		List<Recipe> recipeList = new ArrayList<>();
		recipeRep.findAll().iterator().forEachRemaining(recipeList::add);
		return recipeList;
	}

}
