/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 *
 * Permission has been explicitly granted to the University of Minnesota
 * Software Engineering Center to use and distribute this source for
 * educational purposes, including delivering online education through
 * Coursera or other entities.
 *
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including
 * fitness for purpose.
 *
 *
 * Modified 20171114 by Ian De Silva -- Updated to adhere to coding standards.
 *
 */
package edu.ncsu.csc326.coffeemaker;

import cucumber.api.java.en.*;
import edu.ncsu.csc326.coffeemaker.CoffeeMaker;

import static org.junit.Assert.*;

/**
 * Contains the step definitions for the cucumber tests.  This parses the
 * Gherkin steps and translates them into meaningful test steps.
 */
public class TestSteps {

    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;
    private Recipe recipe5;
    private CoffeeMaker coffeeMaker;
    private RecipeBook recipeBook;
    private int recipeId;
    private int moneyPaid;


    private void initialize() {
        recipeBook = new RecipeBook();
        coffeeMaker = new CoffeeMaker(recipeBook, new Inventory());
        recipeId = 0;
        moneyPaid = 0;
    }

    @Given("^an empty recipe book$")
    public void anEmptyRecipeBook() throws Throwable {
        initialize();
    }


    @Given("a default recipe book")
    public void aDefaultRecipeBook() throws Throwable {
        initialize();

        //Set up for r1
        recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipe1.setAmtChocolate("0");
        recipe1.setAmtCoffee("3");
        recipe1.setAmtMilk("1");
        recipe1.setAmtSugar("1");
        recipe1.setPrice("50");

        //Set up for r2
        recipe2 = new Recipe();
        recipe2.setName("Mocha");
        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("1");
        recipe2.setAmtSugar("1");
        recipe2.setPrice("75");

        //Set up for r3
        recipe3 = new Recipe();
        recipe3.setName("Latte");
        recipe3.setAmtChocolate("0");
        recipe3.setAmtCoffee("3");
        recipe3.setAmtMilk("3");
        recipe3.setAmtSugar("1");
        recipe3.setPrice("100");

        //Set up for r4
        recipe4 = new Recipe();
        recipe4.setName("Hot Chocolate");
        recipe4.setAmtChocolate("4");
        recipe4.setAmtCoffee("0");
        recipe4.setAmtMilk("1");
        recipe4.setAmtSugar("1");
        recipe4.setPrice("65");

        //Set up for r5 (added by MWW)
        recipe5 = new Recipe();
        recipe5.setName("Super Hot Chocolate");
        recipe5.setAmtChocolate("6");
        recipe5.setAmtCoffee("0");
        recipe5.setAmtMilk("1");
        recipe5.setAmtSugar("1");
        recipe5.setPrice("100");

        recipeBook.addRecipe(recipe1);
        recipeBook.addRecipe(recipe2);
        recipeBook.addRecipe(recipe3);
    }

    @When("I paid for recipe (\\d+) with (\\d+) Baht")
    public void iPaidForRecipe(int recipe, int money) throws Throwable {
        String beverage = coffeeMaker.getRecipes()[recipe].getName();
        String amount = String.valueOf(money);
        this.recipeId = recipe;
        this.moneyPaid = money;
        System.out.println("I paid for " + beverage + " with " + amount + "Baht");
    }

    @Then("I receive (\\d+) Baht as changes")
    public void iRecieveChanges(int changes) throws Throwable {
        assertEquals(changes, coffeeMaker.makeCoffee(this.recipeId, this.moneyPaid));
    }
}
