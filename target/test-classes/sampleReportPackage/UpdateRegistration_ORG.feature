Feature: Update Organizatio Registration  Scenario
Scenario Outline: Update Organizatio Registration  Scenario
  Given Open the browser "<browser>" and launch the application
  Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login 
  When I enter valid data on the Update Organizatio page 
| TaxpayerClassificationT |Organisation|0 |
| TaxPayer_TIN |M0000026751|1|
| Organization Name |Brothers&com| 2|
| DOB |16091987|3|
| Amend Reson |Change of Additional Details|4|

Then  Organization ARN number will generate
  |ARN number |Processing Completed - Reference Number - ARN|41|

  Examples:
    | username   |password | browser|
    | tripsuser  |Passw0rd | chrome|
    
    
    
 
    
    
   
   
    