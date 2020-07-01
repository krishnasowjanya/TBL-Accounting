Feature: Integrated Taxpayer View Individual Scenario
Scenario Outline:Integrated Taxpayer View Individual scenario of Taxpayer accounting module
 Given Open the browser "<browser>" and launch the application
 Then Open Application URL 
  When Enter the username "<username>" and password "<password>"
  Then click on login
 Then Click on TaxPayer Accounting
  And click on Integrated Taxpayer View
 And Enter TIN number "<TIN>"
  And Click on search
   And enter from date "<date>"
  Then TaxType dropdown value "<AccountType>"
 Then Activity dropdown value "<Activity>"
  And click on select button to view 
  
  
  
   Examples:
  | URL                | username   |password   | browser|  ClasificationType|        TIN|      date|     AccountType|  Activity|
  | NRA_BackOffice_URL | tripsuser  |Passw0rd   | chrome|        Individual| N0000026883|91012201| All  |Account Adjustment|
  
  
  
  
 