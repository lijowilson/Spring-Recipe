package com.lee.recipe.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lee.recipe.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {

	Optional<UnitOfMeasure> findByUom(String uom);
}
