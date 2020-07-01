Feature: Register TaxType Organization Scenario
Scenario Outline: Register TaxType Organization Scenario
  Given Open the browser "<browser>" and launch the application
  Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login 
  When I enter valid data on the TaxType Organization page 
| TaxpayerClassificationT |Organisation|0 |
| TaxPayer_TIN |C0000027006|1|
| Select TaxType |Rental Income Tax| 2|
| EDR |16092016|3|
| Wait for Record |Record Added|4|


  Then TaxType ARN number will generate
  |ARN number |    Processing Completed - Reference Number - ARN|0|

  Examples:
    | username   |password | browser|
    | tripsuser  |Passw0rd | FireFox|
    
    
    
 
    
    
   
   
    