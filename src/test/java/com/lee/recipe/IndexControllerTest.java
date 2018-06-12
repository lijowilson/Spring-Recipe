package com.lee.recipe;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.lee.recipe.controllers.IndexController;
import com.lee.recipe.domain.Recipe;
import com.lee.recipe.repository.RecipeRepository;
import com.lee.recipe.service.RecipeService;

import net.bytebuddy.matcher.EqualityMatcher;

public class IndexControllerTest {

	@Mock
	private RecipeService recipeService;
	
	@Mock
	private Model model;
	
	private IndexController indexController;



	
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		indexController = new IndexController(recipeService);
		
		
		
	}

	@Test
	public void test() {
	
	Recipe recip = new Recipe();
		List<Recipe> recipList =new ArrayList<>();
		recipList.add(recip);
		//when(recipeService.getAllRecipes()).thenReturn(recipList);
		
		
		assertEquals(indexController.getIndexPage(model), "index");
		verify(recipeService,times(1)).getAllRecipes();
		verify(model,times(1)).addAttribute(eq("recipes"),anyList());
		
	}
	
	
	@Test
	public void testMockMVC() throws Exception {
		
		MockMvc mock = MockMvcBuilders.standaloneSetup(indexController).build();
		mock.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}

}
