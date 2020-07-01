Feature: Update Individual Registration  Scenario
Scenario Outline: Update Individual Registration  Scenario
  Given Open the browser "<browser>" and launch the application
  Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login 
  When I enter valid data on the Update Individual page 
| TaxpayerClassificationT |Individual|0 |
| TaxPayer_TIN |N0000027022|1|
| Last Name |Hiremath| 2|
| DOB |16091987|3|
| Amend Reson |Change of Additional Details|4|
Then ARN number will generate
|ARN number |    Processing Completed - Reference Number - ARN|0|

 Examples:
    | username   |password | browser|
    | tripsuser  |Passw0rd | FireFox|
    
    
    
 
    
    
   
   
    