Feature: user should be able to create a category
  @createCategory
  Scenario: verify user successfully creates a category
    Given base url "https://backend.cashwise.us/api/myaccount"
    And I have access
    And I have the endpoint "/categories"
    And I have "category_title" with "Foods" in request body
    And I have "category_description" with "Foods and Drinks" in request body
    And I have "flag" with "true" in request body
    When I send POST request
    Then verify status code is 201

  @createCategory
  Scenario: verify flag accepts only boolean
    Given base url "https://backend.cashwise.us/api/myaccount"
    And I have access
    And I have the endpoint "/categories"
    And I have "category_title" with "Foods" in request body
    And I have "category_description" with "Foods and Drinks" in request body
    And I have "flag" with "real madrid" in request body
    When I send POST request
    Then verify status code is 500
    And I have "flag" with "false" in request body
    When I send POST request
    Then verify status code is 201
    Then I delete "Foods" category in database
    Then verify "Foods" is deleted from database

  @createCategory1
  Scenario Outline: verify flag accepts only boolean
    Given base url "https://backend.cashwise.us/api/myaccount"
    And I have access
    And I have the endpoint "/categories"
    And I have "category_title" with "Foods" in request body
    And I have "category_description" with "Foods and Drinks" in request body
    And I have "flag" with "<valueOfFlag>" in request body
    When I send POST request
    Then verify status code is 500
    Examples:
      | valueOfFlag |
      | real madrid |
      | falssseeee  |
      |             |
      | trueFalse   |
      | 1234        |
      | null        |


  Scenario: verify category title doesn't accept more than 255 characters
    Given base url "https://backend.cashwise.us/api/myaccount"
    And I have access
    And I have the endpoint "/categories"
    And I have "category_title" with "FoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsFoodsvvvFoods" in request body
    And I have "category_description" with "Foods and Drinks" in request body
    And I have "flag" with "<valueOfFlag>" in request body
    When I send POST request
    Then verify status code is 500



