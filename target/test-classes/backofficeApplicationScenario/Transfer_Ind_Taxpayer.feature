Feature: Transfer Taxpayer
Scenario Outline:  Indvidual_Taxpayer_Transfer_Taxoffice
 Given Open the browser "<browser>" and launch the application
 Then Open Application URL
  When Enter the username "<username>" and password "<password>"
  Then click on login
  And  Click on regisration link
  And  Goto Manage taxpayer
  And Goto Transfer Taxpayer
  And Select Taxpayer Classification Type "<ClasificationType>"
  And Enter TIN number "<TIN>"
  And Click on search
  And Select New Office "<NewOffice>"
  And Select Date of transfer "<DateOfTransfer>"
  And Select Reason "<Reason>"
  Then Click on tarnsfer 
  And  Verify the ARN number "<ARN>"
  
  
  
  
  Examples:
    | username   |password | browser|  ClasificationType|     TIN|  NewOffice| DateOfTransfer|Reason|ARN|
      | tripsuser  |Passw0rd | FireFox|      Individual| N0000026980|    Freetown Central TD2 (STO)|  06042025|The individual taxpayer location changed|Processing Completed - Reference Number - ARN|
      