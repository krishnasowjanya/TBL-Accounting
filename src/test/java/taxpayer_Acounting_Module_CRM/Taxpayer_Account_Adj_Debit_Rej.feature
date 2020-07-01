Feature: Taxpayer Account Adj Debit  Individual Taxpayer Reject Scenario
Scenario Outline:  Taxpayer Account Adj Debit  Individual Taxpayer Reject Scenario
 Given Open the browser "<browser>" and launch the application
 Then Open CRM URL for Accounting Module
 And Close Popup Window
 And Click on Case management Sub module
 And Goto Queues
 Then switch to frame
 And Select SearchResult Dropdown element
 And Selecte Queues Dropdown element
 And Enter the Reference number "<ReferenceNumber>" in search box.
 And Click on Serch icon
 And Click selected Reference Number
 And Pick the selected Reference number for Accounting Module
 And Click on Case management Sub module
 And Goto Dashboard
 Then Enter Reference number in search box "<ReferenceNumber>"
 And Click Dashboard serch icon
 And Click on appeard Reference number
 And Click on Open Case Record link
 And Select Approval outcome dropdown value to Reject for Taxpayer accounting
Then Enter Outcome Notes "<Notes>"
And Enter Outcome Reason for Taxpayer accounting
Then Click on Save button
And Verify the String "<Read>"
Then Get Status of Taxpayer "<Status>" as Rejected
 

Examples:
    | username        |password |browser|ReferenceNumber|Read     |    Notes            |Reason|Status|
    | tripscrmuser11  |Passw0rd | chrome|*ACAD/000001553/2020|Read only|Invalid Documentation|Duplicate entity found|Rejected|
    