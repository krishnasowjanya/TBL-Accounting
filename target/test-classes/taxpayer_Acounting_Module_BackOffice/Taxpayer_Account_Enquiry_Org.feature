Feature: Taxpayer Account Enquiry Individual
Scenario Outline: Taxpayer Account Enquiry Individual
  Given Open the browser "<browser>" and launch the application
  Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login
  Then Click on TaxPayer Accounting
  And click on TaxPayer Accounting Enquiry
  And Select Taxpayer Classification Type "<ClasificationType>"
  And Enter TIN number "<TIN>"
  And Click on search
  And TaxType Account dropdown
  Then TaxType Account dropdown value "<AccountType>"
  And click on select button
  
  
  
  
  Examples:
    | username   |password | browser|  ClasificationType|        TIN|AccountType|     ARN|
      | tripsuser  |Passw0rd | FireFox| ORGANIZATION|C0000026956|Suspense|Processing Completed - Reference Number - ARN|

  