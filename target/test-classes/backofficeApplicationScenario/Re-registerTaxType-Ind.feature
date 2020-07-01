Feature: Re-Register TaxType  Individual   Scenario
Scenario Outline: Re-Register TaxType  Individual   Scenario
  Given Open the browser "<browser>" and launch the application
  Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login 
  When I enter valid data on the Re-RegisterTaxType Individual page 
| Re-Register Tax |Re-Register Tax|0 |
| TaxpayerClassificationT |Individual|1 |
| TaxPayer_TIN |N0000026980|2|
| Re-Reg Reason|Taxable Turnover exceeds threshold|3|
| EOR |16092002|4|
| Amend Reson |Change of Additional Details|5|
| Return Type |Pay as yoy earn|6|

    
Then ReRegister ARN number will generate
|ARN number |    Processing Completed - Reference Number - ARN|0|

  Examples:
    | username   |password | browser|
    | tripsuser  |Passw0rd | FireFox|
    
    
    
 
    
    
   
   
    