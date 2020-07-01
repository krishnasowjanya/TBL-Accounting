Feature: Process Registration Application Individual Taxpayer
Scenario Outline:  Process Registration Application Individual Taxpayer 
 Given Open the browser "<browser>" and launch the application
 Then Open Application URL 
  When Enter the username "<username>" and password "<password>"
  Then click on login
  And  Click on regisration link
  And Click on Register Taxpayer tab
  And Click on Process Registration application
  And Select Taxpayer Classification Type "<ClasificationType>"
  And Enter Application reference number "<ReferenceNumber>"
  And Click on Search 
  And Select the row from the grid
  And Click on Edit button of individual page
  And Click on Attachment Tab 
  And Scroll page to the attachment tab
   And Select first row of the grid for individual page
  And Click edit the Individual attachment information
  And Check the PN folder check box
  And Check the Verified check box
  And Click on OK Button
  Then Click On Individual Page Submit Button 
  And  Verify the ARN number "<ARN>"
 
  
  
  
  
   Examples:
  | URL                | username   |password   | browser|  ClasificationType| ReferenceNumber| Attachments| ARN|
  | NRA_BackOffice_URL | tripsuser  |Passw0rd   | FireFox|       Individual| ARN/00021425/2020|     Attachments|Processing Completed - Reference Number - ARN|