Feature: Register Individual Taxpayer save Taxpayer Information
Scenario Outline: Register Individual Taxpayer save Taxpayer Information
  Given Open the browser "<browser>" and launch the application
  Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login
  When I Fill the Individual Taxpayer Registration form
  And I enter valid data on the Individualpage and Submit
| First Name | Saanvi S K|0 |
| Last Name | Khedalkar|1|
| CategoryValue |Employee|2|
| Title Value | MR| 3|
| Gender | M|4|
|MothersMaidenName|Suman|5|
And Enter Date Of Birth in additional info tab"<DOB>"
| Marital Status | Married|0|
| Place Of Birth| Mumbai|1| 
| Country Value | Albania|2|
| ReasonForTin Value | A Contractor or Sub-contractor|3|
| Nationality Value | Albania|4|
And Enter identification Date of Issue "<DOI>" 
| Identification | Identification|0|
| Identification Type |Passport|1|
| Identification num |125t|2|
| Country of Issue |Albania|3|
| epermit num |765u|4|
| epermit type |Asylum Seeker Permit|5|
And Enter identification Expiry Date "<IED>"
| Identification Type |Driving Licence|0|
| Identification num |ujs5m|1|
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
| PreciseCategory|Forestry Workers|35 15|
And I enter valid data on the page 
| Attachments|Attachments|36|
| Attachment Date|21082017|37|
|Attachment Pasport|Passport|38|
| Reference number| shreth123|39|
|File upload |D:\Automation Documentation\MRA_AUTOMATION_DEMO|40|
|MothersMaidenName|Suman|41|
| Identification Type |Residence Permit|42|
| Identification num |ujhf6|43|
Then Click On Individual Page Save Button

And  Verify the ARN number "<ARN>"
  

  Examples:
    | username   |password | browser|DOB    |DOI     |IED     |ESD      |ARN|
    | tripsuser  |Passw0rd | FireFox|26091989|11042010|11042022|11022002|Processing Completed - Reference Number - ARN|
    
    
    
 
    
    
   
   
    