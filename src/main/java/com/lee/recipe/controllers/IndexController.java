package com.lee.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lee.recipe.service.RecipeService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class IndexController {

	RecipeService recipeService;
	
	
	/**
	 * @param recipeService
	 */
	public IndexController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}


	@RequestMapping({"/","","/index"})
	public String getIndexPage(Model model) {
		log.info("Inside getIndexPage method::");
		
		model.addAttribute("recipes",recipeService.getAllRecipes());
		model.addAttribute("categories",recipeService.getAllCategories());
		return "home";
	}
			
}
