package com.lee.recipe;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.security.RunAs;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.lee.recipe.controllers.IndexController;
import com.lee.recipe.service.RecipeService;

public class IndexControllerIT {


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
	public void testMockMVC() throws Exception {
		
		MockMvc mock = MockMvcBuilders.standaloneSetup(indexController).build();
		mock.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}

	
}
