Feature: Login functionality
Scenario Outline: Login functionality
  Given Open the browser "<browser>" and launch the application
  Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login

  Examples:
    | username   |password | browser|
    | tripsuser  |Passw0rd | FireFox|
    
    
 