Feature: Taxpayer Account Adj Credit App Individual Taxpayer Approve
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
    | username        |password |browser|ReferenceNumber |Read |Status|
    | tripscrmuser11  |Passw0rd | chrome|*ACAD/000001504/2020|saving|Registered|
    