package com.lee.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lee.recipe.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RecipeController {

	RecipeService recipeService;
	
	
	/**
	 * @param recipeService
	 */
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}


	@RequestMapping({"/recipe/show/{recipeId}"})
	public String getRecipePage(@PathVariable String recipeId,Model model) {
		log.info("Inside getIndexPage method::");
		
		model.addAttribute("recipe",recipeService.getRecipeById(new Long(recipeId)));
		return "recipe";
	}
}
