package com.lee.recipe.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.lee.recipe.commands.IngredientCommand;
import com.lee.recipe.commands.RecipeCommand;
import com.lee.recipe.commands.UnitOfMeasureCommand;
import com.lee.recipe.converters.RecipeCommandToRecipe;
import com.lee.recipe.converters.RecipeToRecipeCommand;
import com.lee.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.lee.recipe.domain.Category;
import com.lee.recipe.domain.Ingredient;
import com.lee.recipe.domain.Recipe;
import com.lee.recipe.domain.UnitOfMeasure;
import com.lee.recipe.repository.CategoryRepository;
import com.lee.recipe.repository.RecipeRepository;
import com.lee.recipe.repository.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Autowired
	private RecipeToRecipeCommand recipeToRecipeCommand;
	
	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	@Autowired
	UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureCommand;
	
	
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
	public RecipeServiceImpl(CategoryRepository categoryRepository,RecipeRepository recipeRepository,RecipeCommandToRecipe recipeCommandToRecipe,RecipeToRecipeCommand recipeToRecipeCommand,UnitOfMeasureRepository unitOfMeasureRepo) {
		super();
		this.categoryRepo = categoryRepository;
		this.recipeRepo = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
		this.unitOfMeasureRepository = unitOfMeasureRepo;
		
	}


	public List<RecipeCommand> getAllRecipes(){
		log.info("inside getAllRecipes Method ...");
		List<Recipe> recipeList = new ArrayList<>();
		recipeRepo.findAll().iterator().forEachRemaining(recipeList::add);
		List<RecipeCommand> recipeCommList = new ArrayList<>();
		for(Recipe recip:recipeList) {
			recipeCommList.add(recipeToRecipeCommand.convert(recip));
		}
		;
		return recipeCommList;
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

		//logic to update ingredient
		Set<Ingredient> inputTemp = savedObj.getIngredients();
		Set<Ingredient> newIngredient = new HashSet<>();
		for(Ingredient ing:inputTemp) {
			Ingredient dummyVal = ing;
			dummyVal.setRecipe(savedObj);
			newIngredient.add(dummyVal);
		}
		savedObj.setIngredients(newIngredient);
		Recipe savedObj1 = recipeRepo.save(savedObj);
		
		log.info("saved Recipe Object ::"+savedObj1.getId());
		return recipeToRecipeCommand.convert(savedObj1);
		
	}

	@Override
	@Transactional
	public RecipeCommand getRecipeCommandById(Long Id) {
		Optional<Recipe> recipe = recipeRepo.findById(Id);
		
		if(!recipe.isPresent()) {
			throw new RuntimeException();
		}
		
		return recipeToRecipeCommand.convert(recipe.get());
	}

	/**
	 * Delete a Recipe By ID
	 */
	@Override
	@Transactional
	public void deleteRecipeById(Long Id) {
		
		recipeRepo.deleteById(Id);
		
	}

	@Override
	@Transactional
	public void saveImage(Long recipeId, MultipartFile file) {
		log.info("inside saveimage method");
		try {
			Optional<Recipe> savedObj = recipeRepo.findById(recipeId);
		
			if(savedObj.isPresent()) {
				savedObj.get().setImages(file.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<UnitOfMeasureCommand> getAllUomCommands() {
		List<UnitOfMeasure>  uomList = new ArrayList<>();
		List<UnitOfMeasureCommand> uomCommandList = new ArrayList<>();
		unitOfMeasureRepository.findAll().iterator().forEachRemaining(uomList :: add);
		
		for(UnitOfMeasure uomEach:uomList) {
			uomCommandList.add(unitOfMeasureCommand.convert(uomEach));
		}
		
		return uomCommandList;
	}
}
