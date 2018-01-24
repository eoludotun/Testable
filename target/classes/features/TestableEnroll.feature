
Feature: User enroll in a course and complete the course 






@positivetest @smoketest
Scenario: User able to enroll and complete course successfully
Given User is on the Enrollment page
 And  Verify the "Welcome to takehome" display on the page
When  User clicks "Enroll Now" button 
 And  User is re-directed to "Sign Up to takehome" page
When  User filled the enrollment form 
 And  User checks the i agree checkbox
 And  User clicks Sign Up button
Then  Validate course author name's = "artash"
 And  Validate "Category:" display on the page
When  User clicks the FREE button 
And   User clicks "Enroll in Course for" cart button
Then  Validate enrollement message = "Thanks for enrolling in this course!"
And   Validate the couurse completed is "0%"
When  User scrolled through all courses and completed all required video
Then  Validate the couurse completed is "100%"

 
 
 
@negativetest @smoketest
Scenario: User unable to Enroll successfully with mis-match password
Given User is on the Enrollment page
 And  Verify the "Welcome to takehome" display on the page
When  User clicks "Enroll Now" button 
 And  User is re-directed to "Sign Up to takehome" page
When  User filled the enrollment form with a mis-match password 
 And  User checks the i agree checkbox
 And  User clicks Sign Up button
Then  Validate password mis-match error message = "Password confirmation doesn't match Password" display