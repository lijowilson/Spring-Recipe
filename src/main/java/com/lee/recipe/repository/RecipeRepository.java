package com.lee.recipe.repository;

import org.springframework.data.repository.CrudRepository;

import com.lee.recipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
