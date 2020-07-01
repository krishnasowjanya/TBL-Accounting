Feature: Find_Allocation Rules Configuration scenario of Taxpayer Accounting Module
Scenario Outline:Find_Allocation Rules Configuration scenario
  Given Open the browser "<browser>" and launch the application
  Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login
  Then Click on TaxPayer Accounting
  And Click on Find Allocation Rules Configuration link
  Then click on add button twice
  Then select allocation method "<method>"
  And enter allocation priority "<priority>"
  Then click ok button
  And gettext record added "<record>"
  And click on save button
  
  
  
  
  
  
  
  Examples:
    | username   |password | browser|method|priority|record|   
    | tripsuser  |Passw0rd | chrome | Interest |  02|Record Added|      
  