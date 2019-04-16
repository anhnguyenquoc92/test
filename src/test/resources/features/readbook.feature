Feature: ReadBook feature

This feature deals with the read book functionality of the application 
  
  
  Scenario: Test a GET request
  Given have 2 book in database 
	When i send request "http://localhost:8080/api/query/books"
	Then a 200 status code should be returned
	#And the request body should contain the JSON representation of the "book" type
	And dog
   
   