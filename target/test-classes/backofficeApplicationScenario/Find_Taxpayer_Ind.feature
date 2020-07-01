@regression
Feature: Login Find taxpayer Individual 
Scenario Outline: Find Taxpayer Individual
  Given Open the browser "<browser>" and launch the application
  Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login
  And  Click on regisration link
  And  Goto Manage taxpayer
  And  Goto Find taxpayer
  And Select Taxpayer Classification Type "<ClasificationType>"
  And Enter TIN number "<TIN>"
  And Click on search

  
 
 
  Examples:
    | username   |password | browser|  ClasificationType|     TIN|
    | tripsuser  |Passw0rd | FireFox|      Individual| C0000026816| 
      
      
      

      