Feature: CoffeeMakerFeature

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
   Then I receive 75 Baht as changes

