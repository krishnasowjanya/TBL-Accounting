@smoke
Feature: De-Register Taxtype
Scenario Outline:  Organization_taxtype DeRegister
 Given Open the browser "<browser>" and launch the application
 Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login
  And  Click on regisration link
  And  Goto Manage taxpayer
  And Goto DeRegister
  And Select Taxpayer Classification Type "<ClasificationType>"
  And Enter TIN number "<TIN>"
  And Click on search
  And CLick on taxtype in grid
  And Select EDD "<EDD>"
  And Select de register Reason "<Reason>"
  Then Click on DeRegister button
  And  Verify the ARN number "<ARN>"
  
   Examples:
    | username   |password | browser|  ClasificationType|        TIN|       EDD|      Reason|     ARN|
      | tripsuser  |Passw0rd | FireFox|       Organisation|C0000026816|  06042029|   No longer liable to tax|Processing Completed - Reference Number - ARN|