package com.lee.recipe.domain;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude= {"recipe"})
@Entity
public class Ingredient {

	
	
	/**
	 * 
	 */
	public Ingredient() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param description
	 * @param amount
	 * @param unitOfMeasure
	 */
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure,Recipe recipeTemp) {
		this.description = description;
		Amount = amount;
		this.unitOfMeasure = unitOfMeasure;
		this.recipe = recipeTemp;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private String description;
	private BigDecimal Amount;
	
	@ManyToOne
	private Recipe recipe;
	
	@OneToOne(fetch=FetchType.EAGER)
	private UnitOfMeasure unitOfMeasure;
	
}
