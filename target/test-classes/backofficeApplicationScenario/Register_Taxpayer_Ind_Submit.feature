Feature: Submit Registration Application  [SUC:01-01]
Scenario Outline: UAT_TCS 01.01.4: To verify the process of Registering an individual successfully with mandatory fields
  Given Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login
  When I Fill the Individual Taxpayer Registration form
  And I enter valid data on the Individualpage and Submit
| First Name |Geet|0 |
| Last Name | hiremat|1|
| CategoryValue |Employee|2|
| Title Value | MR| 3|
| Gender | M|4|
|MothersMaidenName|hjg|5|
And Enter Date Of Birth in additional info tab"<DOB>"
| Marital Status | Married|0|
| Place Of Birth| Mumbai|1| 
| Country Value | Albania|2|
| ReasonForTin Value | A Contractor or Sub-contractor|3|
| Nationality Value | Albania|4|
And Enter identification Date of Issue "<DOI>" 
| Identification | Identification|0|
| Identification Type |Passport|1|
| Identification num |9urj|2|
| Country of Issue |Albania|3|
| epermit num |765u|4|
| epermit type |Asylum Seeker Permit|5|
And Enter identification Expiry Date "<IED>"
| Identification Type |Driving Licence|0|
| Identification num |uhf|1|
|Register Ind |Employment Details|2|
|Employment Position |Senior Executive|3|
|Employer's Name|SiddharthReddy|4|
And Enter Employee details "<ESD>"
| Occupation|Occupation/Business Interests|0|
| Occupation status|Employed|1|
| Occupation main category|AGRICULTURE, ANIMAL HUSBANDARY, FORESTRY WORKERS, FISHERMEN AND HUNTERS|2|
| Occupation precise value|Agriculture and Animal Husbandry Workers|3|
| Address Submodule|Addresses|4|
| AddressValue|Local Postal Address|26 5|
| SName|United States|27 6|
| City|United States|28 7|
| Mname|New jersy|29 8|
| ProvisionValue|Karonga|30 9|
| ReogonValue|Northern Region|31 10|
| Contact Method|Contact Methods|32 11|
|Purpose Value |Personal|33 12|
|ContactTypeValue |Email|34 13|
| ContactDetails|geeta.s@technobraingroup.com|35 14|
And I enter valid data on the page 
| Attachments|Attachments|36|
| Attachment Date|21082017|37|
|Attachment Pasport|Passport|38|
| Reference number|kbfl|39|
|File upload |D:\Automation Documentation\MRA_AUTOMATION_DEMO|40|
|MothersMaidenName|fvnb |41|
| Identification Type |Residence Permit|42|
| Identification num |dfjvn|43|
Then Click On Individual Page Submit Button
And  Verify the ARN number "<ARN>"
  

  Examples:
    | username   |password | browser|DOB    |DOI     |IED     |ESD      |ARN|
    | tripsuser  |Passw0rd | FireFox|26091989|11042010|11042022|11022002|Processing Completed - Reference Number - ARN|
    
    
    
 
    
    
   
   
    