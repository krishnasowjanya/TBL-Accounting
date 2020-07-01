Feature: Manage Credit Allocation scenario of TaxPayer Accounting Modle
Scenario Outline:Manage Credit Allocation scenario
  Given Open the browser "<browser>" and launch the application
  Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login
  Then Click on TaxPayer Accounting 
 And click on Manage Credit Allocation link
 And click on add button
 Then click on find button
  And Enter TIN number "<TIN>"
  And Click on search button 
 Then select taxType "<Taxtype>"
 And check the suspense checkbox
 Then switch to frame
  And select document type "<Documenttype>"
  Then click on Search button for selected account
  And Click on Outstanding Liability Taxtype
  Then switch to frame
  Then enter month "<month>" and year "<year>"
Then click on Search button for selected account
 And click on submit button
 Then Credit reference number will generate "<RefNo>" 
 
Examples:
    | username   |password | browser|TIN         |Taxtype         |Documenttype      | month|   year  |code  |amount|RefNo|
    | tripsuser  |Passw0rd | chrome |N0000026352 |Personal Income Tax |Account Adjustment| 01 |2020 |1112500| 67587678 |ACAD/| 
  