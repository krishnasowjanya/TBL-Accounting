Feature: Maintain period Generation Configuration scenario of TaxPayer Accounting Modle
Scenario Outline:Maintain period Generation Configuration scenario
  Given Open the browser "<browser>" and launch the application
  Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login
  Then Click on TaxPayer Accounting
  And Click on Maintain Period Generation Configuration link
  When click on Create New button
  Then click on Taxtype "<Taxtype>"
  Then click on Tax cycle start month "<month>" day "<day>"
  And click on return type
  And click on TaxCycle end month "<endmonth>" day "<endday>"
  Then click on frequency "<frequency>"
  Then click on formula "<formula>"
  And enter no of period "<period>" and effective date "<date>"
  And click on save button 
  Then Gettext Period Generation Configuration saved successfully. "<Record>"
  
  
  
  
  
  Examples:
    | username   |password | browser|Taxtype            |frequency | month|day|endmonth|endday|formula|Record|period|date|
    | tripsuser  |Passw0rd | chrome |Company Income Tax |Quarterly | March|03 |  March  |  03  |Period Formula|Period Generation Configuration saved successfully.|05|29/05/2020|      
  