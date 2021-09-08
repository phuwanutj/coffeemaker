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
 * Modifications 
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to 
 *                           coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

/**
 * Unit tests for CoffeeMaker class.
 * 
 * @author Phuwanut Jiamwatthanaloet
 */
public class CoffeeMakerTest {
    
    /**
     * The object under test.
     */
    private CoffeeMaker coffeeMaker;
    private CoffeeMaker coffeeMaker2;
    private RecipeBook recipeBookStub;
    private Recipe [] stubRecipies;

    // Sample recipes to use in testing.
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;
    private Recipe recipe5;
    private Recipe recipe6;
    private Recipe recipe7;
    private Recipe recipe8;

    /**
     * Initializes some recipes to test with and the {@link CoffeeMaker} 
     * object we wish to test.
     * 
     * @throws RecipeException  if there was an error parsing the ingredient 
     *      amount when setting up the recipe.
     */
    @Before
    public void setUp() throws RecipeException {
        coffeeMaker = new CoffeeMaker();

        recipeBookStub = mock(RecipeBook.class);
        coffeeMaker2 = new CoffeeMaker(recipeBookStub, new Inventory());

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

        //Set up for r5
        recipe5 = new Recipe();
        recipe5.setName("Coffee2");
        recipe5.setAmtChocolate("4");
        recipe5.setAmtCoffee("20");
        recipe5.setAmtMilk("2");
        recipe5.setAmtSugar("2");
        recipe5.setPrice("100");

        //Set up for r6
        recipe6 = new Recipe();
        recipe6.setName("Milk2");
        recipe6.setAmtChocolate("4");
        recipe6.setAmtCoffee("5");
        recipe6.setAmtMilk("20");
        recipe6.setAmtSugar("2");
        recipe6.setPrice("100");

        //Set up for r7
        recipe7 = new Recipe();
        recipe7.setName("Sugar2");
        recipe7.setAmtChocolate("4");
        recipe7.setAmtCoffee("5");
        recipe7.setAmtMilk("8");
        recipe7.setAmtSugar("20");
        recipe7.setPrice("100");

        //Set up for r8
        recipe8 = new Recipe();
        recipe8.setName("Chocolate2");
        recipe8.setAmtChocolate("40");
        recipe8.setAmtCoffee("5");
        recipe8.setAmtMilk("8");
        recipe8.setAmtSugar("2");
        recipe8.setPrice("100");

        stubRecipies = new Recipe [] {recipe1, recipe2, recipe3};
    }
    
    /**
     * Test adding recipe when the recipe is valid.
     */
    @Test
    public void testAddRecipe() {
        assertTrue(coffeeMaker.addRecipe(recipe1));
        assertTrue(coffeeMaker.addRecipe(recipe2));
        assertTrue(coffeeMaker.addRecipe(recipe3));
        assertFalse(coffeeMaker.addRecipe(recipe4));
    }
    
    /**
     * Test adding duplicated recipe.
     */
    @Test
    public void testAddRecipeWithDuplicateRecipe() {
        coffeeMaker.addRecipe(recipe1);
        assertFalse(coffeeMaker.addRecipe(recipe1));
    }
    
    /**
     * Test deleting a recipe that does not exist.
     */
    @Test
    public void testDeleteRecipe() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.deleteRecipe(0);
        assertNotEquals(recipe1, coffeeMaker.getRecipes()[0]);
        assertNull(coffeeMaker.getRecipes()[0]);
    }
    
    /**
     * Test deleting a recipe that does not exist.
     */
    @Test
    public void testDeleteRecipeThatDoesNotExist() {
        assertNull(coffeeMaker.deleteRecipe(2));
    }
    
    /**
     * Test editing a recipe.
     */
    @Test
    public void testEditRecipe() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.editRecipe(1, recipe1);
        assertEquals(recipe1, coffeeMaker.getRecipes()[1]);
        assertEquals(recipe2.getName(), coffeeMaker.getRecipes()[1].getName());
    }
    
    /**
     * Test adding inventory when all values are valid.
     * 
     * @throws InventoryException  if there was an error parsing the quanity
     *      to a positive integer.
     */
    @Test
    public void testAddInventoryWithValidValue() throws InventoryException {
        coffeeMaker.addInventory("3","3","2","6");
        coffeeMaker.addInventory("0","0","0","0");
        coffeeMaker.addInventory("0","3","1","7");
        coffeeMaker.addInventory("5","0","7","8");
        coffeeMaker.addInventory("5","8","0","8");
        coffeeMaker.addInventory("4","5","6","0");
    }
   
    /**
     * Test adding inventory when coffee value is invalid.
     * 
     * @throws InventoryException  if there was an error parsing the quanity
     *      to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryWithInvalidCoffeeValue() throws InventoryException {
        try {
            coffeeMaker.addInventory("ca", "4", "7", "6");
        }
        catch (InventoryException e) {
            coffeeMaker.addInventory("-8", "4", "5", "7");
        }
    }
    
    /**
     * Test adding inventory when milk value is invalid.
     * 
     * @throws InventoryException  if there was an error parsing the quanity
     *      to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryWithInvalidMilkValue() throws InventoryException {
        try {
            coffeeMaker.addInventory("4", "da", "7", "6");
        }
        catch (InventoryException e) {
            coffeeMaker.addInventory("6", "-48", "5", "7");
        }
    }
    
    /**
     * Test adding inventory when sugar value is invalid.
     * 
     * @throws InventoryException  if there was an error parsing the quanity
     *      to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryWithInvalidSugarValue() throws InventoryException {
        try {
            coffeeMaker.addInventory("9", "4", "ba", "6");
        }
        catch (InventoryException e) {
            coffeeMaker.addInventory("8", "4", "-5", "7");
        }
    }
    
    /**
     * Test adding inventory when chocolate value is invalid.
     * 
     * @throws InventoryException  if there was an error parsing the quanity
     *      to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryWithInvalidChocolateValue() throws InventoryException {
        try {
            coffeeMaker.addInventory("9", "4", "5", "ea");
        }
        catch (InventoryException e) {
            coffeeMaker.addInventory("8", "4", "5", "-7");
        }
    }
    
    /**
     * Test if the inventory has been update correctly after setting new inventory value.
     */
    @Test
    public void testCheckInventory() {
        Inventory inventory = new Inventory();
        inventory.setCoffee(3);
        inventory.setMilk(4);
        inventory.setSugar(5);
        inventory.setChocolate(6);
        assertEquals("Coffee: 3\nMilk: 4\nSugar: 5\nChocolate: 6\n", coffeeMaker.checkInventory());
    }
    
    /**
     * Test purchase of beverage when the input is valid.
     */
    @Test
    public void testMakeCoffeeWithValidInput() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        assertEquals(50, coffeeMaker.makeCoffee(0, 100));
        assertEquals(0, coffeeMaker.makeCoffee(0, 50));
    }

    /**
     * Test purchase of beverage when there is no recipe.   
     */
    @Test 
    public void testMakeCoffeeWithNoRecipe() {
        assertEquals(50, coffeeMaker.makeCoffee(0, 50));
    }
    
    /**
     * Test purchase of beverage when inventory is not enough.
     */
    @Test 
    public void testMakeCoffeeWithNotEnoughInventory() {
        coffeeMaker.addRecipe(recipe2);
        assertEquals(150, coffeeMaker.makeCoffee(0, 150));
    }
    
    /**
     * Test purchase of beverage when amount paid is negative.
     */
    @Test 
    public void testMakeCoffeeWithNegativePrice() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals(-17, coffeeMaker.makeCoffee(0, -17));
    }
    
    /**
     * Test if the inventory has been update correctly after purchase of beverage.
     */
    @Test
    public void testCheckInventoryAfterPurchase() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals(50, coffeeMaker.makeCoffee(0, 100));
        assertEquals("Coffee: 12\nMilk: 14\nSugar: 14\nChocolate: 15\n", coffeeMaker.checkInventory());
    }
    
    /**
     * Test editing a recipe that does not exist.
     */
    @Test
    public void testEditRecipeThatDoesNotExist() {
        coffeeMaker.addRecipe(recipe1);
        assertNull(coffeeMaker.editRecipe(1, recipe1));
    }

    /**
     * Test adding chocolate when the value is negative.
     *
     * @throws InventoryException  if there was an error parsing the quanity
     *      to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void testAddChocolateWithNegativeValue() throws InventoryException {
        Inventory inventory = new Inventory();
        inventory.addChocolate("-35");
    }

    /**
     * Test adding chocolate when the value is alphabet.
     *
     * @throws InventoryException  if there was an error parsing the quanity
     *      to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void testAddChocolateWithAlphabetAsValue() throws InventoryException {
        Inventory inventory = new Inventory();
        inventory.addChocolate("a");
    }

    /**
     * Test adding coffee when the value is negative.
     *
     * @throws InventoryException  if there was an error parsing the quanity
     *      to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void testAddCoffeeWithNegativeValue() throws InventoryException {
        Inventory inventory = new Inventory();
        inventory.addCoffee("-35");
    }

    /**
     * Test adding coffee when the value is alphabet.
     *
     * @throws InventoryException  if there was an error parsing the quanity
     *      to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void testAddCoffeeWithAlphabetAsValue() throws InventoryException {
        Inventory inventory = new Inventory();
        inventory.addCoffee("a");
    }

    /**
     * Test adding milk when the value is negative.
     *
     * @throws InventoryException  if there was an error parsing the quanity
     *      to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void testAddMilkWithNegativeValue() throws InventoryException {
        Inventory inventory = new Inventory();
        inventory.addMilk("-35");
    }

    /**
     * Test adding milk when the value is alphabet.
     *
     * @throws InventoryException  if there was an error parsing the quanity
     *      to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void testAddMilkWithAlphabetAsValue() throws InventoryException {
        Inventory inventory = new Inventory();
        inventory.addMilk("a");
    }

    /**
     * Test adding sugar when the value is negative.
     *
     * @throws InventoryException  if there was an error parsing the quanity
     *      to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void testAddSugarWithNegativeValue() throws InventoryException {
        Inventory inventory = new Inventory();
        inventory.addSugar("-35");
    }

    /**
     * Test adding sugar when the value is alphabet.
     *
     * @throws InventoryException  if there was an error parsing the quanity
     *      to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void testAddSugarWithAlphabetAsValue() throws InventoryException {
        Inventory inventory = new Inventory();
        inventory.addSugar("a");
    }

    /**
     * Test purchase of beverage when coffee is not enough.
     */
    @Test
    public void testMakeCoffeeWithNotEnoughCoffee() {
        coffeeMaker.addRecipe(recipe5);
        assertEquals(150, coffeeMaker.makeCoffee(0, 150));
    }

    /**
     * Test purchase of beverage when milk is not enough.
     */
    @Test
    public void testMakeCoffeeWithNotEnoughMilk() {
        coffeeMaker.addRecipe(recipe6);
        assertEquals(150, coffeeMaker.makeCoffee(0, 150));
    }

    /**
     * Test purchase of beverage when sugar is not enough.
     */
    @Test
    public void testMakeCoffeeWithNotEnoughSugar() {
        coffeeMaker.addRecipe(recipe7);
        assertEquals(150, coffeeMaker.makeCoffee(0, 150));
    }

    /**
     * Test purchase of beverage when chocolate is not enough.
     */
    @Test
    public void testMakeCoffeeWithNotEnoughChocolate() {
        coffeeMaker.addRecipe(recipe8);
        assertEquals(150, coffeeMaker.makeCoffee(0, 150));
    }

    /**
     * Test purchase of beverage with valid recipe.
     */
    @Test
    public void testMakeCoffeeWithValidRecipeUsingMock() {
        when(coffeeMaker2.getRecipes()).thenReturn(stubRecipies);
        assertEquals(50, coffeeMaker2.makeCoffee(0, 100));
        assertEquals(0, coffeeMaker2.makeCoffee(2, 100));
    }

    /**
     * Test purchase of beverage when the inventory is not enough.
     */
    @Test
    public void testMakeCoffeeWithNotEnoughInventoryUsingMock() {
        when(coffeeMaker2.getRecipes()).thenReturn(stubRecipies);
        assertEquals(100, coffeeMaker2.makeCoffee(1, 100));
        assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", coffeeMaker2.checkInventory());
    }

    /**
     * Test purchase of beverage and check if the inventory is updated correctly.
     */
    @Test
    public void testMakeCoffeeAndThenCheckTheInventoryUsingMock() {
        when(coffeeMaker2.getRecipes()).thenReturn(stubRecipies);
        coffeeMaker2.makeCoffee(2, 100);
        assertEquals("Coffee: 12\nMilk: 12\nSugar: 14\nChocolate: 15\n", coffeeMaker2.checkInventory());
    }

    /**
     * Test purchase of beverage when the money is not enough.
     */
    @Test
    public void testMakeCoffeeWithNotEnoughMoneyUsingMock() {
        when(coffeeMaker2.getRecipes()).thenReturn(stubRecipies);
        assertEquals(25, coffeeMaker2.makeCoffee(0, 25));
        assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", coffeeMaker2.checkInventory());
    }
}
