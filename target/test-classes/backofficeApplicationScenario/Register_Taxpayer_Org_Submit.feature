Feature: Register Organizatio Taxpayer Submit Taxpayer Information
Scenario Outline:Register Organizatio Taxpayer Submit Taxpayer Information
  Given Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login
  When I Fill the Organization Taxpayer Registration form 
  And I enter valid data on the pages of Organization 
| CategoryValue |Co-operative Society (Other)|0|
| Organization Name |EFG|1|
| RGD Number |ujh|2|
| DOE | 12092018|3|
| DOC|12092020|4| 
| Source of Capital |Home Loan|5|
| Place Of Incorporation |ALBANIA|6|
| ReasonForTin Value |Exporting goods|7|
| Business Sector Value |0112 - Growing of rice|8|
| Address Submodule|Addresses|9|
| AddressValue|Local Postal Address|10|
| SName|United States|11|
| City|United States|12|
| ProvisionValue|Karonga|30 9|
| ReogonValue|Northern Region|31 10|
| Contact Method|Contact Methods|15|
|Purpose Value |Business|16|
|ContactTypeValue |Email|17|
|ContactDetails |geeta.s@technobraingroup.com|18|
|EndYearMonth |February|19|
|EndYeadDay |01|20|
And Enter Attachment Tab details
| Attachments|Attachments|19 0|
| Attachment Date|21082016|20 1|
|Attachment Pasport|Business Registration Certificate|21 2|
| Reference number|ug|22 3|
|File upload |D:\Automation Documentation\MRA_AUTOMATION_DEMO|23 4|
| Attachments|Doccument | 24 5|
| Attachments|Certificate of Incorporation| 25 6|
| Attachments|Letter Of Authorization| 26 7|
| Attachment Reference number|7t| 27 8|
| Attachment Reference number|9u| 28 9|
When Enter Directors Tab Info for Organization taxpayer
|Directors|Directors| 0|
|DirectorsTinName|P0021518| 1|
|Directors Start Date|16112016| 2|
Then Click On Organization Page Submit Button
And  Verify the ARN number "<ARN>"

  Examples:
    | username   |password | browser|ARN|
    | tripsuser  |Passw0rd | FireFox|Processing Completed - Reference Number - ARN|
    

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
    