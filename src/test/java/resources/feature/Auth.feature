Feature: Generating Auth token
@SMOKE
  Scenario: User should be able to generate a new auth token by entering username and password
    When User send admin "admin" and password "password123" as a payload body
    Then User should be able to generate and use that auth token for access to the Update and Delete booking
