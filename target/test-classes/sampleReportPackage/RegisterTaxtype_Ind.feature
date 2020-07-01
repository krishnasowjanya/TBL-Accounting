Feature: Register TaxType Individual Scenario
Scenario Outline: Register TaxType Individual Scenario
  Given Open the browser "<browser>" and launch the application
  Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login 
  When I enter valid data on the TaxType Individual page 
| TaxpayerClassificationT |Individual|0 |
| TaxPayer_TIN |N0000027103|1|
| Select TaxType |Pay As You Earn| 2|
| EDR |16092016|3|
| Wait for Record |Record Added|4|


  Then TaxType ARN number will generate
  |ARN number |    Processing Completed - Reference Number - ARN|0|

  Examples:
    | username   |password | browser|
    | tripsuser  |Passw0rd | FireFox|
    
    
    
 
    
    
   
   
    