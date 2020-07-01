Feature: [SUC:03-06]  To verify the Process of debiting or Crediting Revenue Accounts
Scenario Outline:UAT_M3_06-10 To verify the Process of debiting or Crediting Revenue Accounts
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
 Then Credit reference number will generate 
 
Examples:
    | username   |password | browser|TIN           |chargetype | adjtype|    reason                        |code  |amount|
    | tripsuser  |Passw0rd | chrome |P0019254    |Liability | Credit |MISCELLANEOUS ADJUSTMENT - CREDIT |111110001| 67587678 | 
  
 Scenario Outline:  Taxpayer Account Adj Credit App Individual Taxpayer Approve Scenario
 Given Open CRM URL for Accounting Module
 And Close Popup Window
 And Click on Case management Sub module
 And Goto Queues
 Then switch to frame
 And Select SearchResult Dropdown element
 And Selecte Queues Dropdown element
 And Enter the Reference number 
 And Click on Serch icon
 And Click selected Reference Number
 And Pick the selected Reference number
 And Click on Case management Sub module
 And Goto Dashboard
 Then Enter Reference number in search box
 And Click Dashboard serch icon
 And Click on appeard Reference number
 And Click on Open Case Record link
 And Select Approval outcome dropdown value to Approve for Taxpayer accounting
Then Click on Save button
And Verify the String "<Read>"
And Click on Taxpayer name
Then Get Status of Taxpayer "<Status>" 

Examples:
   |Read |Status|
   |saving|Registered|
    