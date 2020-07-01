Feature: Integrated Taxpayer View Organization Scenario
Scenario Outline:Integrated Taxpayer View Organization scenario of Taxpayer accounting module
 Given Open the browser "<browser>" and launch the application
 Then Open Application URL 
  When Enter the username "<username>" and password "<password>"
  Then click on login
 Then Click on TaxPayer Accounting
  And click on Integrated Taxpayer View
  And Select Taxpayer Classification Type "<ClasificationType>"
  And Enter TIN number "<TIN>"
  And Click on search
   And enter from date "<date>"
  Then TaxType dropdown value "<AccountType>"
 Then Activity dropdown value "<Activity>"
  And click on select button to view 
  
  
  
   Examples:
  | URL                | username   |password   | browser|  ClasificationType|        TIN|      date|     AccountType|  Activity|
  | NRA_BackOffice_URL | tripsuser  |Passw0rd   | chrome|       Organisation|C000002399X |91112201| All  |Account Adjustment|
  
  
  
  
 