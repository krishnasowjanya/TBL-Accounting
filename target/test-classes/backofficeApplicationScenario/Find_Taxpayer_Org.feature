Feature: Login Find taxpayer Organization
Scenario Outline: Find Taxpayer Organisation
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
      | tripsuser  |Passw0rd | FireFox|      Organisation| V0017590|
      
      
      