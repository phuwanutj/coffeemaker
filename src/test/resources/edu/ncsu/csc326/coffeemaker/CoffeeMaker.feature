Feature: CoffeeMaker Feature

    Scenario: Purchase Coffee with amount of money more than price of Coffee

       Given a default recipe book
       When I paid for recipe 0 with 100 Baht
       Then I receive 50 Baht as changes

    Scenario: Purchase Coffee with correct amount of money

       Given a default recipe book
       When I paid for recipe 0 with 50 Baht
       Then I receive 0 Baht as changes

    Scenario: Purchase Mocha when Chocolate is not enough

       Given a default recipe book
       When I paid for recipe 1 with 100 Baht
       Then I receive 100 Baht as changes

    Scenario: I add a Tea to recipe book

        Given an empty recipe book
        When I create a new recipe called Tea
        Then I add new recipe to recipe book

    Scenario: I delete a recipe from recipe book

        Given a default recipe book
        When I choose to delete recipe 0
        Then I delete that recipe from recipe book

    Scenario: I edit a Latte to include more sugar

        Given a default recipe book
        When I want to edit recipe 2 to be change sugar amount to 8
        Then that recipe has 8 units of sugar



