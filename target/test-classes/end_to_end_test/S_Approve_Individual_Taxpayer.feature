Feature: Individual Taxpayer Approve
Scenario Outline: 01 Individual Taxpayer Approve Scenario
 Given Open the browser "<browser>" and launch the application
 Then Open CRM URL
 And Close Popup Window
 And Click on Case management Sub module
 And Goto Queues
 Then switch to frame
 And Select SearchResult Dropdown element
 And Selecte Queues Dropdown element
 And Enter the Reference number in search box.
 And Click on Serch icon
 And Click selected Reference Number
 And Pick the selected Reference number
 And Click on Case management Sub module
 And Goto Dashboard
 Then Enter Reference number in search box"<crm_ARN>"
 And Click Dashboard serch icon
 And Click on appeard Reference number
 And Click on Open Case Record link
 And Click on NextStage button
 Then Goto view AttachmentDetails screen
 And Download the Attachment
 Then Select Identification Outcome dropdown value for Individual Taxpayer Approval
And Click on NextStage button
And Select Approval outcome dropdown value to Approve"<Approve>"
Then Click on Save button
And Verify the String "<Read>"
And Click on Taxpayer name
Then Get the TIN Number 

Examples:
    | username        |password |browser| Approve |Read |            ARN|TIN|
    | tripscrmuser11  |Passw0rd | chrome|Approve|Read only|Processing Completed - Reference Number - ARN|N00|
    