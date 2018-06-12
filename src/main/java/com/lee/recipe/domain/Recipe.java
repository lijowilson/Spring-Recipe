package com.lee.recipe.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude= {"notes"})
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	@Lob
	private String direction;
	
	//TODO add dificulty
	//private Difficulty difficulty;
	
	@Lob
	private byte[] images;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	private Notes notes;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="recipe")
	private Set<Ingredient> ingredients = new HashSet<>();
	
	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;
	
	@ManyToMany
	@JoinTable(name="recipe_category",joinColumns= {@JoinColumn(name="recipe_id")},inverseJoinColumns= {@JoinColumn(name="category_id")})
	//@JoinTable(name="recipe_category",joinColumns=@JoinColumn("recipe_id"),inverseJoinColumns=@JoinColumn("category_id"))
	private Set<Category> category= new HashSet<>();

	

	public void setNotes(Notes notes) {
		this.notes = notes;
		notes.setRecipe(this);
	}

}
