package com.lee.recipe.bootstrap;

import static org.mockito.Mockito.RETURNS_MOCKS;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.lee.recipe.domain.Category;
import com.lee.recipe.domain.UnitOfMeasure;
import com.lee.recipe.repository.CategoryRepository;
import com.lee.recipe.repository.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Profile({"dev","prod"})
public class RecipeBootstrapMySQL implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
   // private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrapMySQL(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		log.info("started with loading bootstrap data..");

		if(null != categoryRepository && categoryRepository.count() == 0L)
		loadCategory();

		log.info("Started with loading bootstrap data for uom");

		if(null != unitOfMeasureRepository && unitOfMeasureRepository.count() == 0L)
		loadUOM();

	}

    //loading category
	private void loadCategory() {

		log.info("loading bootstrap category data");

		Category cat1 = null;

		String description = "American";
		cat1 = new Category();
		cat1.setDescription(description);
		categoryRepository.save(cat1);

		description = "Italiano";
		cat1 = new Category();
		cat1.setDescription(description);
		categoryRepository.save(cat1);

		description = "Chinese";
		cat1 = new Category();
		cat1.setDescription(description);
		categoryRepository.save(cat1);

		description = "British";
		cat1 = new Category();
		cat1.setDescription(description);
		categoryRepository.save(cat1);

		description = "Bahrain";
		cat1 = new Category();
		cat1.setDescription(description);
		categoryRepository.save(cat1);

		description = "Swiss";
		cat1 = new Category();
		cat1.setDescription(description);
		categoryRepository.save(cat1);

		log.info("Done with loading bootstrap data for category");
	}
    
    //loading uom
	private void loadUOM() {

		log.info("started with loading uom data");
		UnitOfMeasure uom = null;
		String uomStr = "";

		uomStr = "Tablespoon";
		uom = new UnitOfMeasure();
		uom.setUom(uomStr);
		uom.setUom(uomStr);
		unitOfMeasureRepository.save(uom);

		uomStr = "Teaspoon";
		uom = new UnitOfMeasure();
		uom.setUom(uomStr);
		uom.setUom(uomStr);
		unitOfMeasureRepository.save(uom);

		uomStr = "Ounce";
		uom = new UnitOfMeasure();
		uom.setUom(uomStr);
		uom.setUom(uomStr);
		unitOfMeasureRepository.save(uom);

		uomStr = "Inches";
		uom = new UnitOfMeasure();
		uom.setUom(uomStr);
		uom.setUom(uomStr);
		unitOfMeasureRepository.save(uom);

		uomStr = "Litre";
		uom = new UnitOfMeasure();
		uom.setUom(uomStr);
		uom.setUom(uomStr);
		unitOfMeasureRepository.save(uom);

		uomStr = "Each";
		uom = new UnitOfMeasure();
		uom.setUom(uomStr);
		uom.setUom(uomStr);
		unitOfMeasureRepository.save(uom);

		uomStr = "Dash";
		uom = new UnitOfMeasure();
		uom.setUom(uomStr);
		uom.setUom(uomStr);
		unitOfMeasureRepository.save(uom);

		uomStr = "Pint";
		uom = new UnitOfMeasure();
		uom.setUom(uomStr);
		uom.setUom(uomStr);
		unitOfMeasureRepository.save(uom);

		uomStr = "Cup";
		uom = new UnitOfMeasure();
		uom.setUom(uomStr);
		uom.setUom(uomStr);
		unitOfMeasureRepository.save(uom);

		log.info("Done with loading uom data");
	}
    /*
    private List<Recipe> getRecipes() {
    
    	log.warn("getRecipes", "Method Entry :: getRecipes()");
    	List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByUom("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByUom("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByUom("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByUom("Dash");

        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByUom("Pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByUom("Cup");

        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        log.info("getting Optionals");
        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teapoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = dashUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();

        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> britishCategoryOptional = categoryRepository.findByDescription("British");

        if(!britishCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category britishCategory = britishCategoryOptional.get();

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(20);
        guacRecipe.setDifficulty(Difficulty.VERY_DIFFICULT);
        guacRecipe.setServings(5);
        guacRecipe.setDirection("<li>Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon</li>" +
                "<li> Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)</li>" +
                "\n" +
                "<li> Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.</li>" +
                "<li> Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd</li>");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teapoonUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom, guacRecipe));
        guacRecipe.setImageURL("pexels-photo-1147687.jpeg");
        
        String fileName = "static/images/pexels-photo-1147687.jpeg";
		ClassLoader classLoader = this.getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		try {
			byte[] byteArTemp = Files.readAllBytes(file.toPath());
			//guacRecipe.setImages(byteArTemp);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
        guacRecipe.getCategory().add(americanCategory);
        guacRecipe.getCategory().add(britishCategory);

        //add to return list
        recipes.add(guacRecipe);

        log.info("Done with creating Recipes!!!");
        
        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirection("<li> Prepare a gas or charcoal grill for medium-high, direct heat.</li>" +
                "<li> Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.</li>" +
                "\n" +
                "\n" +
                "<li> Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.</li>" +
                "<li>Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.</li>" +
                "<li> Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges." +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm</li>");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
        tacoNotes.setRecipe(tacosRecipe);
        tacosRecipe.setNotes(tacoNotes);
        tacosRecipe.setServings(5);


        tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teapoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teapoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teapoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teapoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), tableSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tableSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupsUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupsUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom, tacosRecipe));

        tacosRecipe.getCategory().add(americanCategory);
        tacosRecipe.getCategory().add(britishCategory);
        tacosRecipe.setImageURL("pexels-photo-76093.jpeg");
        String fileName1 = "static/images/pexels-photo-76093.jpeg";
      		File file1 = new File(classLoader.getResource(fileName1).getFile());
      		try {
      			byte[] byteArTemp = Files.readAllBytes(file1.toPath());
      			
      			//tacosRecipe.setImages(byteArTemp);
      		} catch (IOException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		}
        recipes.add(tacosRecipe);
        log.info("done with taco recipes");
        return recipes;
    }
    */
}