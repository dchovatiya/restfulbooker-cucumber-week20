Feature: Testing the Booking functionality
@SMOKE
  Scenario: Verify user should be able to access booking
    When User send GET request to see all booking
    Then User must get back a valid status code
@SMOKE
  Scenario: Create a new booking and verify that the booking is created
    When I create a new booking by providing firstName, lastName, totalPrice, depositPaid, checkIn,  checkOut and additionalNeeds
    Then I verify that new booking is created with the given credentials
@SMOKE
  Scenario: Update a booking and verify that the booking is updated
    When I update booking by providing firstName, lastName, totalPrice, depositPaid, checkIn,  checkOut, additionalNeeds
    Then I verify that booking is updated with firstName, lastName, additionalNeeds in database

  Scenario: Delete the booking data and verify its deleted from the database
    When I delete the booking data
    Then I verify that same booking data should get deleted
