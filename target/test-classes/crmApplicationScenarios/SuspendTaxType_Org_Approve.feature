Feature: Register TaxType Organization Taxpayer Approve
Scenario Outline:  Register TaxType Organization Taxpayer Approve Scenario
 Given Open the browser "<browser>" and launch the application
 Then Open CRM URL
 And Close Popup Window
 And Click on Case management Sub module
 And Goto Queues
 Then switch to frame
 And Select SearchResult Dropdown element
 And Selecte Queues Dropdown element
 And Enter the Reference number "<ReferenceNumber>" in search box.
 And Click on Serch icon
 And Click selected Reference Number
 And Pick the selected Reference number
 And Click on Case management Sub module
 And Goto Dashboard
 Then Enter Reference number in search box"<ReferenceNumber>"
 And Click Dashboard serch icon
 And Click on appeard Reference number
 And Click on Open Case Record link
 And Click on NextStage button one time
And Select Approval outcome dropdown value to Approve for Register TaxType Ind
Then Click on Save button
And Verify the String "<Read>"
And Click on Taxpayer name
Then Get Status of Taxpayer "<Status>" 

Examples:
    | username        |password |browser|ReferenceNumber |Read |Status|
    | tripscrmuser11  |Passw0rd | chrome|*ARN/00021466/2020|saving|Registered|
    