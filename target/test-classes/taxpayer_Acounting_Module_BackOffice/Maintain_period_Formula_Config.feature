Feature: Maintain period Formula Configuration scenario of TaxPayer Accounting Modle
Scenario Outline:Maintain period Formula Configuration scenario
  Given Open the browser "<browser>" and launch the application
  Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login
  Then Click on TaxPayer Accounting
  And Click on Maintain Period Formula Configuration link
  Then Enter Code "<Code>" and Description "<Description>"
  |Code |Period3|0 |
  |Description |Period Formula3|1|
  And Select Filling Frequency "<Frequency>"
  And Select Payment DueIn first value "<PaymentDueIn1>" 
  And Select Payment DueIn Second value "<PaymentDueIn2>" 
 Then Select Payment DueIn third dropdown value "<PaymentDueIn3>" enter "<Days>"
  And Select Return DueIn first value "<ReturnDueIn1>" 
  And Select Return DueIn Second value "<ReturnDueIn2>" 
  Then Select Return DueIn return third value "<ReturnDueIn3>" enter "<Days>"
 Then enter cinfiguration effective date "<Date>"
  And click on save 
  Then Gettext Record successfully saved "<Record>" 
  
Examples:
    | username   |password | browser| Frequency |  PaymentDueIn1|PaymentDueIn2|PaymentDueIn3|Days|ReturnDueIn1|ReturnDueIn2|ReturnDueIn3|Date|Record|
    | tripsuser  |Passw0rd | chrome| Quarterly|Current Period|Months|Fixed Date|120|Current Period|Months|Working|11004201|Record Successfully Saved.|      

  