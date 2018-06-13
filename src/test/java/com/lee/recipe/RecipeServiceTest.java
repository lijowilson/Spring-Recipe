package com.lee.recipe;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lee.recipe.domain.Recipe;
import com.lee.recipe.repository.CategoryRepository;
import com.lee.recipe.repository.RecipeRepository;
import com.lee.recipe.service.RecipeServiceImpl;

public class RecipeServiceTest {

	RecipeServiceImpl recipeService;
		
	@Mock
	RecipeRepository recipeRepo;
	
	@Mock
	CategoryRepository categoryRepo;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(categoryRepo,recipeRepo);
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
