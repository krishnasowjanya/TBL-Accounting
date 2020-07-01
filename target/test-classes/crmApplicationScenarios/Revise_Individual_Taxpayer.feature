Feature: Individual Taxpayer Revision
Scenario Outline:  Individual Taxpayer Revision Scenario
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
 And Click on NextStage button
 Then Goto view AttachmentDetails screen
 And Download the Attachment
 Then Select Identification Outcome dropdown value for Individual Taxpayer Reject
And Click on NextStage button
And Select Approval outcome dropdown value to Revise"<Revise>"
Then Enter Outcome Notes "<Notes>"
And Enter Outcome Reason
Then Click on Save button
And Verify the String "<Read>"
And Click on Taxpayer name
Then Get Status of Taxpayer "<Status>"

Examples:
    | username        |password |browser|ReferenceNumber| Revise |Read     |    Notes            |Reason|Status|
    | tripscrmuser11  |Passw0rd | chrome|*ARN/00021451/2020|Revision|Read only|Invalid Documentation|Duplicate entity found||Rejected|