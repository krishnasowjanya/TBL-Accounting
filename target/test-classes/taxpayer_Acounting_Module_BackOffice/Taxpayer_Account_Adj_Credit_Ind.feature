Feature: Taxpayer Account Adj Credit Individual scenario of TaxPayer Accounting Modle
Scenario Outline:Taxpayer Account Adj Credit Individual scenario
  Given Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login
  Then Click on TaxPayer Accounting 
 And click on Taxpayer Account Adjustment link
 And click on add button
 Then click on find button
 And enter Tin number "<TIN>" and click search
 And scroll down and select row
 Then click on continue button
 And select charge type "<chargetype>"
 And select adjustment type "<adjtype>"
 Then give reason value "<reason>"
 Then enter revenue ledger code "<code>" and amount "<amount>"
 And click on submit button
 Then Credit reference number will generate "<RefNo>" 
 
Examples:
    | username   |password | browser|TIN           |chargetype | adjtype|    reason                        |code  |amount|RefNo|
    | tripsuser  |Passw0rd | chrome |P0019254    |Liability | Credit |MISCELLANEOUS ADJUSTMENT - CREDIT |111110001| 67587678 |ACAD/| 
  