Feature: Suspend Organization TaxType
Scenario Outline:  Suspend Organization TaxType
 Given Open the browser "<browser>" and launch the application
 Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login
  And  Click on regisration link
  And  Goto Manage taxpayer
  And Goto Suspend TaxType "<SuspendTaxType>"
  And Select Taxpayer Classification Type "<ClasificationType>"
  And Enter TIN number "<TIN>"
  And Click on search
  Then Enter Suspension Satrt Date "<StartDate>"
  And Enter Suspension End Date "<EndDate>"
  |Suspension Reason |No longer liable to tax type|0 |
  And  Verify the ARN number "<ARN>"
  
  
  
  
  Examples:
    | username   |password | browser|  ClasificationType|  TIN |  SuspendTaxType|StartDate|EndDate|  ARN|
    | tripsuser  |Passw0rd | FireFox|      Organisation| C0000027014|SUSPEND TAX TYPE|09042022|09042025|Processing Completed - Reference Number - ARN|
      