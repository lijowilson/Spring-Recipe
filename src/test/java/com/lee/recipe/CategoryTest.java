package com.lee.recipe;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.lee.recipe.domain.Category;

public class CategoryTest {

	private Category category;
	@Before
	public void run() {
		category = new Category();
	}
	
	@Test
	public void getId() throws Exception {
		Long testVal = 33L;
		category.setId(testVal);
		assertEquals(testVal, category.getId());
		
	}
}
