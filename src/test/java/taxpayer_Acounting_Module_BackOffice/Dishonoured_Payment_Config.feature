Feature: Dishonoured Payment Config scenario of Taxpayer Accounting Module
Scenario Outline:Dishonoured Payment Config scenario
  Given Open the browser "<browser>" and launch the application
  Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login
  Then Click on TaxPayer Accounting
  And Click on Dishonoured Payment Configure link
  And select payment instrument "<payment>"
  Then enter entry allowed "<entry>"
  And enter no of days blocked "<block>" and checked "<check>"
  And click on save button
  Then gettext Dishonoured Payment Configuration is saved Successfully. "<Success>"
  
 Examples:
    | username   |password | browser|payment   |entry|block|check |Success|  
    | tripsuser  |Passw0rd | chrome | Cheque |  02 |355| 234     |Dishonoured Payment Configuration is saved Successfully.|
  