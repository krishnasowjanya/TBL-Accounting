Feature: Re-Activate Taxtype Organization
Scenario Outline:  Organization_ReActivate_taxtype 
 Given Open the browser "<browser>" and launch the application
 Then Open Application URL 
  When Enter the username "<username>" and password "<password>"
  Then click on login
  And  Click on regisration link
  And  Goto Manage taxpayer
  And Goto Reactive Taxtype
  And Select Taxpayer Classification Type "<ClasificationType>"
  And Enter TIN number "<TIN>"
  And Click on search
  And CLick on Reactivate taxtype in grid
  And Select Reactivate EDD "<EDD>"
  And Select Reactivate Taxtype Reason "<Reason>"
  Then Click on Reactivate button
  And  Verify the ARN number "<ARN>"
  
   Examples:
  | URL                | username   |password   | browser|  ClasificationType|        TIN|       EDD|      Reason|     ARN|
  | NRA_BackOffice_URL | tripsuser  |Passw0rd   | chrome|       Organisation| C0000025755|  06042029|   Taxable Turnover exceeds threshold|Processing Completed - Reference Number - ARN|