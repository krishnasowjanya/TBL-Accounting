Feature: Register Organizatio Taxpayer Save Process Application
Scenario Outline:Register Organizatio Taxpayer Save Process Application
  Given Open the browser "<browser>" and launch the application
  Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login
  When I Fill the Organization Taxpayer Registration form 
  And I enter valid data on the pages of Organization 
| CategoryValue |Co-operative Society (Other)|0|
| Organization Name |OrgTestData|1|
| RGD Number |7yhgu|2|
| DOE | 12092018|3|
| DOC|12092020|4| 
| Source of Capital |Home Loan|5|
| Place Of Incorporation |India|6|
| ReasonForTin Value |Exporting goods|7|
| Business Sector Value |0112 - Growing of rice|8|
| Address Submodule|Addresses|9|
| AddressValue|Local Postal Address|10|
| SName|United States|11|
| City|United States|12|
| ProvisionValue|North|13|
| ReogonValue|Bombali|14|
| Contact Method|Contact Methods|15|
|Purpose Value |Business|16|
|ContactTypeValue |Mobile|17|
|ContactDetails |+918897765989|18|
And Enter Attachment Tab details for Processing Application
| Attachments|Attachments|19 0|
| Attachment Date|21082016|20 1|
|Attachment Pasport|Passport|21 2|
| Reference number| shrtytg|22 3|
|File upload |D:\Automation Documentation\MRA_AUTOMATION_DEMO|23 4|
| Attachments|Doccument | 24 5|
| Attachments|Approval Letter from Line Ministry| 25 6|
| Attachments|Certificate of Incorporation| 26 7|
| Attachment Reference number|hgf6| 27 8|
| Attachment Reference number| rt6| 28 9|
When Enter Directors Tab Info for Organization taxpayer
|Directors|Directors| 0|
|DirectorsTinName|N0000025909| 1|
|Directors Start Date|16112016| 2|
Then Click On Organization Page Save Button
And  Verify the ARN number "<ARN>"

  Examples:
    | username   |password | browser|ARN|
    | tripsuser  |Passw0rd | FireFox|Processing Completed - Reference Number - ARN|