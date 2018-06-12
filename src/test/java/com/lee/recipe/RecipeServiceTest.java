package com.lee.recipe;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lee.recipe.domain.Recipe;
import com.lee.recipe.repository.RecipeRepository;
import com.lee.recipe.service.RecipeService;

public class RecipeServiceTest {

	RecipeService recipeService;
	
	@Mock
	RecipeRepository recipeRepo;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeService(recipeRepo);
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		Recipe recip = new Recipe();
		List<Recipe> recipList =new ArrayList<>();
		recipList.add(recip);
		when(recipeService.getAllRecipes()).thenReturn(recipList);
		
		assertEquals(recipeService.getAllRecipes().size(),1);
		
	}

}
