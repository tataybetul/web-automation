@AddProductToBasket
Feature: Add Product To Basket

  Scenario Outline: Add Product To Basket
    Given it should open login page
    And it should scale full window
    When it should login by email "<email>" and password "<password>"
    Then it should log when butiks has dead image
    When it should click random category
    When it should click random butik
    Then it should log when products has dead image
    When it should click random product
    When it should add product to basket
    When it should open basket page

    Examples:
      | email | password |
      |       |          |
