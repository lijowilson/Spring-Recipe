package com.lee.recipe;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.lee.recipe.controllers.IndexController;
import com.lee.recipe.domain.Recipe;
import com.lee.recipe.service.RecipeService;

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
