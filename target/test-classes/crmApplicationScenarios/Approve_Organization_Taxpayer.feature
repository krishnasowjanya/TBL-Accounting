Feature: Organization Taxpayer Approve
Scenario Outline:Organization Taxpayer Approve Scenario
Given Open CRM URL
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
 Then Enter Reference number in search box "<ReferenceNumber>"
 And Click Dashboard serch icon
 And Click on appeard Reference number
 And Click on Open Case Record link
 And Click on Orgnization NextStage button
 Then Goto view Organization AttachmentDetails screen
 And Download Organization Attachments
 Then Select Organization Approval outcome dropdown value 
 Then Click on Save button
 And Verify the String "<Read>"
 And Click on Taxpayer name
Then Get the TIN Number 

Examples:
    | username        |password |browser|ReferenceNumber|Approve |Read |           
    | tripscrmuser11  |Passw0rd | chrome|*ARN/00020520/2019|Approve|Read only|
    