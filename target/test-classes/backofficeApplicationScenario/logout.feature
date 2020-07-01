Feature: Logout functionality
Scenario Outline: Logout functionality
  Given Open the browser "<browser>" and launch the application
  Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login
  Then Logot the application
  
   Examples:
    | username   |password | 
    | tripsuser  |Passw0rd | 
  