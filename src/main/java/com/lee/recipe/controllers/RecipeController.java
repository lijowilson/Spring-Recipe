package com.lee.recipe.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.lee.recipe.commands.RecipeCommand;
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
		
		
		
		RecipeCommand recipe =recipeService.getRecipeCommandById(new Long(recipeId));
		model.addAttribute("recipe",recipe);
		return "recipe";
	}
	
	@RequestMapping({"/recipe/new"})
	public String newRecipePage(Model model) {
		log.info("Inside newRecipePage method::");
		model.addAttribute("recipe",new RecipeCommand());
		
		//uomList populate uomlist
		model.addAttribute("uomList",recipeService.getAllUomCommands());
		return "recipeForm";
	}
	

	@RequestMapping(value={"/recipe/save"},method=RequestMethod.POST)
	public String saveRecipe( final @RequestPart(value = "uploadfile", required = false) MultipartFile file,@ModelAttribute RecipeCommand command) {
		log.info("Inside saveRecipe method::");
		
		if(!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				System.out.println("bytearray lenght"+bytes.length);
				command.setImages(bytes);
			//	command.setImages(bytes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		RecipeCommand commandTemp = recipeService.saveRecipeCommand(command);
		
		return "redirect:/recipe/show/"+commandTemp.getId();
	}
	/**
	 * Updating the recipe based on id
	 * @param recipeId
	 * @param model
	 * @return
	 */
	@RequestMapping({"/recipe/{recipeId}/update"})
	public String updateRecipePage(@PathVariable String recipeId,Model model) {
		log.info("Inside updateRecipePage method::");
		RecipeCommand recipe =recipeService.getRecipeCommandById(new Long(recipeId));
		String dummyDirection = recipe.getDirections();
		log.info("original directions::"+dummyDirection);
		if(null != dummyDirection)
		dummyDirection = dummyDirection.replaceAll("</li>,<li>","</li><li>");
		//dummyDirection = dummyDirection.replaceAll("</li>","");
		log.info("updated directions::"+dummyDirection);
		
		recipe.setDirections(dummyDirection);
		model.addAttribute("recipe",recipe);
		
		//populate uom list
		//uomList populate uomlist
		model.addAttribute("uomList",recipeService.getAllUomCommands());
	
		
		return "recipeForm";
	}
	
	/**
	 * Delete Recipe Based on Id
	 * @param recipeId
	 * @param model
	 * @return
	 */
	@RequestMapping({"/recipe/{recipeId}/delete"})
	public String deleteRecipePage(@PathVariable String recipeId,Model model) {
		log.info("Inside deleteRecipePage method::");
		recipeService.deleteRecipeById(new Long(recipeId));
		return "redirect:/";
	}
	
}
