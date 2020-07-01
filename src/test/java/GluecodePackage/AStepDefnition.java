package GluecodePackage;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Key;
import org.testng.Assert;



import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sharedsteps.sharedatastep;
import utils.SeleniumDriver;
import cucumber.api.Scenario;

import cucumber.api.DataTable;





public class AStepDefnition {
	
	public static WebDriver driver;
	public WebDriverWait wait;
	public Scenario scenario = null;
	public Properties Pro;

	
	public static sharedatastep sharedata;
	public AStepDefnition(sharedatastep sharedata) {
		
		AStepDefnition.sharedata=sharedata;
		
	}


@Before(order=0)
public void method1()throws Exception
{
   // this.S=S;
	Pro=new Properties();
	FileInputStream fip= new FileInputStream("C:\\Users\\Geeta.S\\eclipse-workspace\\MRA_Cucumber_Framework\\src\\test\\resources\\Objects\\object.properties");
	Pro.load(fip);
}

@After
public void afterClass(Scenario scenario) throws InterruptedException, IOException {
    if (scenario.isFailed()) {
   try {
    byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    File screenshot_with_scenario_name = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(screenshot_with_scenario_name,
      new File("./test-output/Screenshot/" + scenario.getName() + ".png"));
    System.out.println(scenario.getName());
    scenario.embed(screenshot, "image/png");
   } catch (WebDriverException somePlatformsDontSupportScreenshots) {
    System.err.println(somePlatformsDontSupportScreenshots.getMessage());
   }
   //driver.quit();
    }
}


	
	@Given("^Open Application URL$")
	public void open_Application_URL() throws Throwable {
		
		driver = SeleniumDriver.getDriver();
		SeleniumDriver.getDriver().get(Pro.getProperty("MRA_BackOffice_URL"));
        WebDriverWait wait1=new WebDriverWait(driver,60);
     	wait1.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id(Pro.getProperty("BackOffice_UserName_ID")))));
       
    }		
//Code for Login web page
    @When("^Enter the username \"(.*)\" and password \"(.*)\"$")			
    public void enter_the_Username_and_Password(String username,String password) throws Throwable 							
    {	
    	
       driver.findElement(By.id(Pro.getProperty("BackOffice_UserName_ID"))).sendKeys(username);					
       driver.findElement(By.id(Pro.getProperty("BackOffice_Password_ID"))).sendKeys(password);					
    }
    

    @Then("^click on login$")					
    public void	click_on_login() throws Throwable 							
    {		
       driver.findElement(By.id(Pro.getProperty("BackOffice_Login_ID"))).click();
       //driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
       Thread.sleep(5000);
        
    }
  // Code for Logout button of the application and verifying the Title of the page
 @Then("^Logot the application$")
 public void	click_on_logout() throws Throwable 
 {
	 driver.findElement(By.id(Pro.getProperty("Logoutbutton_ID"))).click();	
     
	   	String ActualTitle=driver.getTitle();
	   	if(ActualTitle.contains(Pro.getProperty("title")))
	   	{
	   		
	   		
	        Assert.assertEquals("Trips+"
	        		+ "", ActualTitle);
	        Assert.assertTrue(true);
	   	}
	   	else 
	   	{
	   		
	   		Assert.fail();
	    }	
 }
 
    //------ Register Individual Taxpayer Submit Scenario-------

@When("^I Fill the Individual Taxpayer Registration form$")
public void  I_Fill_the_Individual_Taxpayer_Registration_form() throws Throwable
{
		
	 WebDriverWait wait=new WebDriverWait(driver,70);
	// driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH"))).click();
	  Actions action = new Actions(driver);
	  WebElement Reg=driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
	  action.doubleClick(Reg).build().perform();
	  Reg.click();
	   	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("RegisterTaxpayer_LINK_XPATH")))).click();
	   	WebElement Taxpayer=driver.findElement(By.xpath(Pro.getProperty("RegisterTaxpayer_LINK_XPATH")));
	   	action.click(Taxpayer).build().perform();
	  // 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("RegisterIndividual_LINK_XPATH")))).click();
	   	WebElement Individual=driver.findElement(By.xpath(Pro.getProperty("RegisterIndividual_LINK_XPATH")));
	   	action.click(Individual).build().perform();
	   	Thread.sleep(2000);
     }

@When("^I enter valid data on the Individualpage and Submit$")
public void i_enter_valid_data_on_the_Individualpage_and_Submit(DataTable table) throws Throwable {  
  
	//Initialize data table   
	List<List<String>> data =table.raw();
		                                       
	 driver.findElement(By.id(Pro.getProperty("FirstName_ID"))).sendKeys(data.get(0).get(1));
	 driver.findElement(By.id(Pro.getProperty("LastName_ID"))).sendKeys(data.get(1).get(1));
	 Thread.sleep(2000);
	 Actions action = new Actions(driver);

	 WebElement title=driver.findElement(By.xpath(Pro.getProperty("Title_LINK_XPATH"))); 
	 action.click(title).build().perform();
	 List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("TITLE_LIST_ITEMS_XPATH")));
	 for(WebElement option : list)
 	{
 	    String text2= option.getText();
 	        if(text2.equalsIgnoreCase(data.get(3).get(1)))
 	        	{
 	        	Actions builder = new Actions(driver);
		        builder.moveToElement(option).doubleClick();
		        builder.perform();
  }
 	}
	
 	        	
      Thread.sleep(7000);
      driver.findElement(By.xpath(Pro.getProperty("Categoryofindividual_XPATH"))).click();
    
 	 List<WebElement> CatValue = driver.findElements(By.xpath(Pro.getProperty("Categoryofindividual_LIST_ITEMS_XPATH")));
 	 for(WebElement option1 : CatValue)
  	{
  	    String text= option1.getText();
  	    System.out.println(text);
  	        if(text.equalsIgnoreCase(data.get(2).get(1)))
  	        	{
  	        	Actions builder1 = new Actions(driver);
 		        builder1.moveToElement(option1).doubleClick(option1).build().perform();
 		      //  builder1.perform();
  	        
  	        	}
  	}
 Thread.sleep(4000);
	 List<WebElement> element=driver.findElements(By.xpath(Pro.getProperty("Gender_LINK_XPATH")));
		for (WebElement ele : element)
	{
	  
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].scrollIntoView(true);",ele);
				//ele.click();
			}
		Thread.sleep(2000);

			
		Thread.sleep(2000);	
	 WebElement Gender=driver.findElement(By.xpath(Pro.getProperty("Gender_LINK_XPATH"))); 
	 action.click(Gender).build().perform();
	 List<WebElement> Gen = driver.findElements(By.xpath

			 (Pro.getProperty("GenderItems_LINK_XPATH")));
			 	 for(WebElement option : Gen)
			  	{
			  	    String text= option.getText();
			  	        if(text.equalsIgnoreCase(data.get(4).get(1)))
			  	        	{
			  	        	Actions builder = new Actions(driver);
			 		        builder.moveToElement(option).doubleClick();
			 		        builder.perform();
			  	        }
			  	}
			 	 Thread.sleep(3000);
}


@When("^Enter Date Of Birth in additional info tab\"([^\"]*)\"$")
public void enter_Date_Of_Birth_in_additional_info_tab(String DOB, DataTable AddTable) throws Throwable {
	List<List<String>> data =AddTable.raw();
	Actions action = new Actions(driver);
	Thread.sleep(6000);
	 driver.findElement(By.id(Pro.getProperty ("DOB_ID"))).sendKeys(DOB);
			 	 Thread.sleep(4000);
			 	 WebElement Meritalstatus=driver.findElement(By.xpath(Pro.getProperty("MaritalStatus_LINK_XPATH"))); 
			 	 action.click(Meritalstatus).build().perform();
			 	 List<WebElement> MStatus = driver.findElements(By.xpath(Pro.getProperty("MaritalStatusItems_LINK_XPATH")));
			 	 for(WebElement option : MStatus)
			  	{
			  	    String text= option.getText();
			  	        if(text.equalsIgnoreCase(data.get(0).get(1)))
			  	        	{
			  	        	Actions builder = new Actions(driver);
			 		        builder.moveToElement(option).doubleClick();
			 		        builder.perform();
			  	        }
			  	}
			 	 
			 	
Thread.sleep(3000);
driver.findElement(By.id(Pro.getProperty("PLACEOFBIRTH_LINK_ID"))).sendKeys(data.get(1).get(1));
Thread.sleep(7000);
List<WebElement> element2=driver.findElements(By.id(Pro.getProperty("PLACEOFBIRTH_LINK_ID")));
		for (WebElement ele : element2)
	{
				JavascriptExecutor js1 = (JavascriptExecutor) driver;js1.executeScript("arguments [0].scrollIntoView(true);",ele);
				
			}
		 Thread.sleep(7000);


WebDriverWait wait1=new WebDriverWait(driver,60);
wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("CountyOfResidency_LINK_XPATH")))).click();
List<WebElement> CountryValue = driver.findElements(By.xpath(Pro.getProperty("CountyOfResidency_ITEM_LINK_XPATH")));
for(WebElement option : CountryValue){
String text2= option.getText();
if(text2.equalsIgnoreCase(data.get(2).get(1)))
{
option.click();
break;
}
}

Thread.sleep(5000);	
WebDriverWait wait=new WebDriverWait(driver,60);
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ReasonForTINChange_CLICK_LINK_XPATH")))).click();
List<WebElement> RFTValue = driver.findElements(By.xpath(Pro.getProperty("ReasonForTINChange_ITEM_LINK_XPATH")));
for(WebElement option : RFTValue){
String text2= option.getText();
if(text2.equalsIgnoreCase(data.get(3).get(1)))
{
option.click();
break;
}
}
Thread.sleep(2000);	
WebDriverWait Nwait=new WebDriverWait(driver,60);
Nwait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("NATIONALITY_CLICK_LINK_XPATH")))).click();
List<WebElement> NValue = driver.findElements(By.xpath(Pro.getProperty("NationalityItem_LINK_XATH")));
for(WebElement option : NValue){
String text2= option.getText();
if(text2.equalsIgnoreCase(data.get(4).get(1)))
{
option.click();
break;
}
}

}



@When("^Enter identification Date of Issue \"([^\"]*)\"$")
public void enter_identification_Date_of_Issue(String DOI,DataTable DateTable) throws Throwable {
	
	List<List<String>> data =DateTable.raw();
	Actions action = new Actions(driver);
	List<WebElement> ScrollIdent=driver.findElements(By.id(Pro.getProperty("ScrollTO-Identification_LINK_XPATH")));
		for (WebElement ele : ScrollIdent)
	{
				JavascriptExecutor js1 = (JavascriptExecutor) driver;js1.executeScript("arguments [0].scrollIntoView(true);",ele);
				
			}
		
		List<WebElement> Reg = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
		for(WebElement option : Reg){
		    String text2= option.getText();
		        if(text2.equalsIgnoreCase(data.get(0).get(1)))
		        	{
		            option.click();
		          break;
		        }
		}
		Thread.sleep(1000);
		List<WebElement> IdAdd=driver.findElements(By.id(Pro.getProperty("Identification_Add_ID")));
		for (WebElement ele : IdAdd)
	{
	  
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].scrollIntoView(true);",ele);
				
			}
		 WebElement Identification=driver.findElement(By.id(Pro.getProperty("Identification_Add_ID")));
	     action.click(Identification).build().perform();
	     Thread.sleep(5000);
	     WebElement  iframe= driver.findElement(By.tagName("iframe"));
	 	driver.switchTo().frame(iframe);
	 	WebDriverWait Iwait=new WebDriverWait(driver,60);
	 	Iwait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("IdenificationType_CLICK_XPATH")))).click();
	 	List<WebElement> IdentificationType = driver.findElements(By.xpath(Pro.getProperty("IdenificationType_ITEM__XPATH")));
		for(WebElement option : IdentificationType){
		    String text2= option.getText();
		        if(text2.equalsIgnoreCase(data.get(1).get(1)))
		        	{
		            option.click();
		          break;
		        }
		}
	    Thread.sleep(2000);
		 driver.findElement(By.id(Pro.getProperty("Identification_Number_ID"))).sendKeys(data.get(2).get(1));
	Thread.sleep(7000);
	WebDriverWait Cwait=new WebDriverWait(driver,60);
		Cwait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Country_Of_Issue_XPATH")))).click();
		List<WebElement> CountryIssue = driver.findElements(By.xpath(Pro.getProperty("Country_Of_Issue_Items_XPATH")));
		for(WebElement option : CountryIssue){
		    String text2= option.getText();
		        if(text2.equalsIgnoreCase(data.get(3).get(1)))
		        	{
		            option.click();
		          break;
		        }
		}
		Thread.sleep(2000);
		driver.findElement(By.id(Pro.getProperty("Identification_ePermit_Num_ID"))).sendKeys(data.get(4).get(1));
        driver.findElement(By.id(Pro.getProperty("Identification_ePermit_Num_ID"))).sendKeys(Keys.TAB);

      
	    WebDriverWait epermit=new WebDriverWait(driver,60);
	epermit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Identification_epermit_Type_XPATH")))).click();
 	/*List<WebElement> epermitType = driver.findElements(By.xpath(Pro.getProperty("Identification_epermit_Type_Value_XPATH")));
	for(WebElement option : epermitType){
	    String text2= option.getText();
	        if(text2.equalsIgnoreCase(data.get(5).get(1)))
	        	{
	            option.click();
	          break;
	        }
	}*/
	  action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		Thread.sleep(11000);
		WebElement DateOI=driver.findElement(By.id(Pro.getProperty("DateOfIssue_ID")));
	       action.sendKeys(DateOI, DOI).build().perform();
			Thread.sleep(2000);
			DateOI.sendKeys(Keys.TAB);
 
}

@When("^Enter identification Expiry Date \"([^\"]*)\"$")
public void enter_identification_Expiry_Date(String IED, DataTable Idtable) throws Throwable {
	Thread.sleep(6000);
List<List<String>> data =Idtable.raw();
	Actions action = new Actions(driver);
	driver.findElement(By.id(Pro.getProperty("Identification_Expiry_Date_ID"))).sendKeys(Keys.TAB);
	Thread.sleep(2000);
	WebElement DateIE=driver.findElement(By.id(Pro.getProperty("Identification_Expiry_Date_ID")));
	action.sendKeys(DateIE,IED).build().perform(); 
	Thread.sleep(2000);
	driver.findElement(By.id(Pro.getProperty("Identification_Expiry_Date_ID"))).sendKeys(Keys.TAB);
	List<WebElement> element2=driver.findElements(By.id(Pro.getProperty("Identification_Frame_OK_ID")));
	for (WebElement ele : element2)
{
  
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].scrollIntoView(true);",ele);
			
		}
	Thread.sleep(2000);
	driver.findElement(By.id(Pro.getProperty("Identification_Frame_OK_ID"))).click();
 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	driver.switchTo().defaultContent();

Thread.sleep(2000);
List<WebElement> recidentadd=driver.findElements(By.id(Pro.getProperty("Identification_Add_ID")));
for (WebElement ele : recidentadd)
{

		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].scrollIntoView(true);",ele);
		
	}
	WebElement Identification1=driver.findElement(By.id(Pro.getProperty("Identification_Add_ID")));
	     action.click(Identification1).build().perform();
	     Thread.sleep(5000);
	     WebElement  idfnframe= driver.findElement(By.tagName("iframe"));
	 	driver.switchTo().frame(idfnframe);
	 	WebDriverWait Irecidentwait=new WebDriverWait(driver,60);
	 	Irecidentwait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("IdenificationType_CLICK_XPATH")))).click();
	 	List<WebElement> IdntRecidenrType = driver.findElements(By.xpath(Pro.getProperty("IdenificationType_ITEM__XPATH")));
		for(WebElement option : IdntRecidenrType){
		    String text2= option.getText();
		        if(text2.equalsIgnoreCase(data.get(0).get(1)))
		        	{
		            option.click();
		          break;
		        }
		}
	         Thread.sleep(2000);
		 driver.findElement(By.id(Pro.getProperty("Identification_Number_ID"))).sendKeys(data.get(1).get(1));
	        Thread.sleep(2000);
		driver.findElement(By.id(Pro.getProperty("Identification_Frame_OK_ID"))).click();
driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	driver.switchTo().defaultContent();
	
	
	
	
	WebDriverWait IRecordwait=new WebDriverWait(driver,50);
	IRecordwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Identification_RecordAdded_XPATH"))));
	List<WebElement> RegInd = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
	for(WebElement option : RegInd){
	    String text2= option.getText();
	        if(text2.equalsIgnoreCase(data.get(2).get(1)))
	        	{
	            option.click();
	          break;
	        }
	}
	driver.findElement(By.xpath(Pro.getProperty("Employment_details_Add_XPATH"))).click();
	Thread.sleep(7000);
	 WebElement  Eiframe= driver.findElement(By.tagName("iframe"));
	driver.switchTo().frame(Eiframe);
	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
 driver.findElement(By.id(Pro.getProperty("Employment_details_Position_ID"))).sendKeys(data.get(3).get(1));
 driver.findElement(By.id(Pro.getProperty("Employment_Details_Employer's_Name_ID"))).sendKeys(data.get(4).get(1));
 driver.findElement(By.id(Pro.getProperty("Employment_Details_Employer's_Name_ID"))).sendKeys(Keys.TAB);

}

@When("^Enter Employee details \"([^\"]*)\"$")
public void enter_Employee_details(String ESD , DataTable Employetable) throws Throwable {
	Thread.sleep(5000);
	
	List<List<String>> data =Employetable.raw();
	Actions action = new Actions(driver);
	WebElement DateES=driver.findElement(By.id(Pro.getProperty("Employment_details_Employment_StartDate_ID")));
	action.sendKeys(DateES, ESD).build().perform();
	DateES.clear();
	action.sendKeys(DateES, ESD).build().perform();
	Thread.sleep(2000);
  
	DateES.sendKeys(Keys.TAB);
	 Thread.sleep(2000);
	 driver.findElement(By.id(Pro.getProperty("Employment_details_Employment_StartDate_ID"))).sendKeys(Keys.TAB);
	 WebDriverWait Empwait=new WebDriverWait(driver,60);
		Empwait.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Employment_details_OK_ID")))).click();
		driver.switchTo().defaultContent();
		WebDriverWait Recordwait=new WebDriverWait(driver,50);
		Recordwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Employment_details_RecordAdded_XPATH"))));
		List<WebElement> Occupation = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
		for(WebElement option : Occupation){
		    String text2= option.getText();
		        if(text2.equalsIgnoreCase(data.get(0).get(1)))
		        	{
		            option.click();
		          break;
		        }
		}
		Thread.sleep(4000);
		WebDriverWait Occupationwait=new WebDriverWait(driver,100);
		Occupationwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Occupation/Business_occupationStatus_XPATH")))).click();                                                  
		List<WebElement> Employed = driver.findElements(By.xpath(Pro.getProperty("Occupation/Business_occupationStatus_ITEM_XPATH")));
		for(WebElement option : Employed){
		    String text2= option.getText();
		        if(text2.equalsIgnoreCase(data.get(1).get(1)))
		        	{
		        	Actions builder = new Actions(driver);
			        builder.moveToElement(option).click(option);
			        builder.perform();
		          //  option.click();
		          break;
		        }
		}
		Thread.sleep(2000);
		 driver.findElement(By.xpath(Pro.getProperty("Occupation/Business_MainCategory_XPATH"))).click();
		 List<WebElement> OccupationValue = driver.findElements(By.xpath(Pro.getProperty("Occupation/Business_MainCategory_ITEM_XPATH")));
			for(WebElement option : OccupationValue){
			    String text2= option.getText();
			        if(text2.equalsIgnoreCase(data.get(2).get(1)))
			        	{
			            option.click();
			          break;
			        }
			}
			Thread.sleep(2000);
			 driver.findElement(By.id(Pro.getProperty("Occupation/Business_PreciseCategory_ID"))).click();
			 Actions action4 = new Actions(driver);
		        action4.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
			Thread.sleep(9000);
	List<WebElement> Addresses = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
				for(WebElement option : Addresses){
				    String text2= option.getText();
				        if(text2.equalsIgnoreCase(data.get(4).get(1)))
				        	{
				            option.click();
				          break;
				        }
				}	
				WebElement Addressadd=driver.findElement(By.id(Pro.getProperty("Addresses_ADD_ID")));
	            action.click(Addressadd).build().perform();
	            Thread.sleep(7000);
	            WebElement  Addressframe= driver.findElement(By.tagName("iframe"));
	        	driver.switchTo().frame(Addressframe);
	        	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	        	WebDriverWait AddressType=new WebDriverWait(driver,50);
	        	AddressType.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_Type_XPATH")))).click();
	        	List<WebElement> AddressValue = driver.findElements(By.xpath(Pro.getProperty("Addresses_Type_ITEM_XPATH")));
				for(WebElement option : AddressValue){
				    String text2= option.getText();
				        if(text2.equalsIgnoreCase(data.get(5).get(1)))
				        	{
				            option.click();
				          break;
				        }
				}
				Thread.sleep(2000);
				WebElement SName=driver.findElement(By.xpath(Pro.getProperty("Addresses_StreetName_XPATH")));
		           action.sendKeys(SName,data.get(6).get(1)).build().perform();
		           WebElement CName=driver.findElement(By.xpath(Pro.getProperty("Addresses_Town/City_XPATH")));
		           action.sendKeys(CName,data.get(7).get(1)).build().perform();
		           Thread.sleep(4000);
		       
		           WebDriverWait Region=new WebDriverWait(driver,50);
		           Region.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_region_XPATH")))).click();
		           List<WebElement> RegionValue = driver.findElements(By.xpath(Pro.getProperty("Addresses_region_ITEM_XPATH")));
			       	for(WebElement option : RegionValue){
			       	    String text2= option.getText();
			       	        if(text2.equalsIgnoreCase(data.get(10).get(1)))
			       	        	{
			       	        	Actions builder = new Actions(driver);
			    		        builder.moveToElement(option).click(option);
			    		        builder.perform();
			       	          
			       	          break;
			       	        }
			       	}
			       	Thread.sleep(2000);
			       	WebDriverWait District=new WebDriverWait(driver,100);
			           District.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_District_XPATH")))).click();
			        	
			           List<WebElement> Dvalue = driver.findElements(By.xpath(Pro.getProperty("Addresses_District_ITEM_XPATH")));
			       	for(WebElement option : Dvalue){
			       	    String text2= option.getText();
			       	        if(text2.equalsIgnoreCase(data.get(9).get(1)))
			       	        	{
			       	            option.click();
			       	          break;
			       	        }
			       	}
		        // Thread.sleep(2000);
		 
			       	List<WebElement> AddressOK=driver.findElements(By.id(Pro.getProperty("Address_Scroll_View_XPATH")));
	    			for (WebElement ele : AddressOK)
	        	{
	        	  
	    					JavascriptExecutor js1 = (JavascriptExecutor) driver;
	    					js1.executeScript("arguments[0].scrollIntoView(true);",ele);
	    					
	    				}
	    			 WebElement AddOK=driver.findElement(By.id(Pro.getProperty("Addresses_OK_ID")));
	                 action.doubleClick(AddOK).build().perform();
	                 AddOK.click();
	                 driver.switchTo().defaultContent();
	                 WebDriverWait AddressRecord=new WebDriverWait(driver,50);
	                 AddressRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_RecordAdded_XPATH"))));
	                 List<WebElement> ContactReg = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
	             	for(WebElement option : ContactReg){
	             	    String text2= option.getText();
	             	        if(text2.equalsIgnoreCase(data.get(11).get(1)))
	             	        	{
	             	            option.click();
	             	          break;
	             	        }
	             	}
	             	driver.findElement(By.xpath(Pro.getProperty("ContactMethods_ADD_XPATH"))).click();
	 		       	Thread.sleep(9000);
	 		       WebElement  ContMethodframe= driver.findElement(By.tagName("iframe"));
	 	        	driver.switchTo().frame(ContMethodframe);
	 	           WebDriverWait Purpose=new WebDriverWait(driver,50);
	 	          Purpose.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_Purpose_XPATH")))).click();
	 	         List<WebElement> PurposeValue = driver.findElements(By.xpath(Pro.getProperty("ContactMethods_Purpose_ITEM_XPATH")));
	          	for(WebElement option : PurposeValue){
	          	    String text2= option.getText();
	          	        if(text2.equalsIgnoreCase(data.get(12).get(1)))
	          	        	{
	          	            option.click();
	          	          break;
	          	        }
	          	}
	          	Thread.sleep(7000);
	          	 WebDriverWait ContactType=new WebDriverWait(driver,50);
	          	ContactType.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_Type_XPATH")))).click();
		         List<WebElement> ContactTypeValue = driver.findElements(By.xpath(Pro.getProperty("ContactMethods_Type_ITEM_XPATH")));
	         	for(WebElement option : ContactTypeValue){
	         	    String text2= option.getText();
	         	        if(text2.equalsIgnoreCase(data.get(13).get(1)))
	         	        	{

		       	        	Actions builder = new Actions(driver);
		    		        builder.moveToElement(option).click(option);
		    		        builder.perform();
	         	           // option.click();
	         	          break;
	         	        }
	         	}
	         	JavascriptExecutor js = (JavascriptExecutor)driver;
	            js.executeScript("arguments[0].click();", (driver.findElement(By.xpath(Pro.getProperty("ContactMethods_PrimaryIdicator_XPATH")))));
	         
	            WebElement PrimInd=driver.findElement(By.xpath(Pro.getProperty("ContactMethods_PrimaryIdicator_XPATH")));
	           action.click(PrimInd).build().perform();
	           WebDriverWait ContctDet=new WebDriverWait(driver,50);
	           ContctDet.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("ContactMethods_ContactMethodDetails_ID")))).click();
	           driver.findElement(By.id(Pro.getProperty("ContactMethods_ContactMethodDetails_ID"))).sendKeys(data.get(14).get(1));
	           driver.findElement(By.xpath(Pro.getProperty("ContactMethods_OK_XPATH"))).click();
	           Thread.sleep(2000);
	           driver.switchTo().defaultContent();
	           Thread.sleep(1000);
}


@And("^I enter valid data on the page$")
public void i_enter_valid_data_on_the_page(DataTable savetable) throws Throwable {
	
	List<List<String>> data =savetable.raw();  
	Actions action=new Actions(driver);
           WebDriverWait ContactRecord=new WebDriverWait(driver,50);
           ContactRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_RecordAdded_XPATH")))).click();
	         List<WebElement> Reg1 = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
        	for(WebElement option : Reg1){
        	    String text2= option.getText();
        	        if(text2.equalsIgnoreCase(data.get(0).get(1)))
        	        	{
        	            option.click();
        	          break;
        	        }
        	}
        	driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_XPATH"))).click();
        	 WebElement  Attachmentframe= driver.findElement(By.tagName("iframe"));
         	driver.switchTo().frame(Attachmentframe);
         	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
         	Thread.sleep(4000);
         	//driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
            driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();
           
            List<WebElement> AttPassport = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
        	for(WebElement option : AttPassport){
        	    String text2= option.getText();
        	        if(text2.equalsIgnoreCase(data.get(2).get(1)))
        	        	{
        	            option.click();
        	          break;
        	        }
        	}
        	driver.findElement(By.xpath(Pro.getProperty("Attachments_RefferenceNumber_XPATH"))).sendKeys(data.get(3).get(1));
        	Thread.sleep(2000);
        	WebElement uploadBtn=driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
            action.click(uploadBtn).build().perform();
            //put path to your image in a clipboard
            StringSelection ss = new StringSelection(data.get(4).get(1));
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

            //imitate mouse events like ENTER, CTRL+C, CTRL+V
            Robot robot = new Robot();
            robot.delay(300);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(600);
            Thread.sleep(5000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(300);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(300);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
            driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
            driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();
            driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
            Thread.sleep(2000);
            driver.switchTo().defaultContent();
            WebDriverWait AttRecord=new WebDriverWait(driver,50);
            AttRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Attachment_Details_RecordAdded_XPATH"))));
      	Thread.sleep(2000);
             
 }  
	

@Then("^Click On Individual Page Save Button$")
public void click_On_Individual_PageSave_Button() throws Throwable {
	Thread.sleep(3000);
driver.findElement(By.xpath(Pro.getProperty("Save_DATA_XPATH"))).click();	
	
}
	
@Then("^Click On Individual Page Submit Button$")
public void click_On_Individual_PageSubmit_Button() throws Throwable {
	WebDriverWait Submit=new WebDriverWait(driver,60);
    Submit.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Individual_Submit_Data_ID")))).click();
Thread.sleep(7000);
	
}
@Then("^ARN number will generate$")					
public void	ARN_number_will_generate(DataTable table) throws Throwable 						
{		
	List<List<String>> data =table.raw();
	WebDriverWait Submit=new WebDriverWait(driver,60);
    Submit.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Individual_Submit_Data_ID")))).click();
Thread.sleep(7000);
WebDriverWait RefNumber=new WebDriverWait(driver,60);
RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID")))).click();
// Capture ARN Number
    String text  =driver.findElement(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID"))).getText();
    System.out.println(text);
    if(text.contains(data.get(0).get(1)))
    		{
    	      //  System.out.println(text);
    			System.out.println("Text Verified and passed");
    		}
             else
                {
    	             System.out.println("Text Not Verfied and failed");
                }      
	

}

// Register Individual Taxpayer save individual taxpayer info  for Processing scenario
@When("^Enter required data to save individual info for Processing scenario$")
public void enter_required_data_to_save_individual_info_for_Processing_scenario(DataTable savetable) throws Throwable {
    
	List<List<String>> data =savetable.raw();  
	Actions action=new Actions(driver);
           WebDriverWait ContactRecord=new WebDriverWait(driver,50);
           ContactRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_RecordAdded_XPATH")))).click();
	         List<WebElement> Reg1 = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
        	for(WebElement option : Reg1){
        	    String text2= option.getText();
        	        if(text2.equalsIgnoreCase(data.get(0).get(1)))
        	        	{
        	            option.click();
        	          break;
        	        }
        	}
        	driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_XPATH"))).click();
        	 WebElement  Attachmentframe= driver.findElement(By.tagName("iframe"));
         	driver.switchTo().frame(Attachmentframe);
         	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
         	driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
            driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();
           
            List<WebElement> AttPassport = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
        	for(WebElement option : AttPassport){
        	    String text2= option.getText();
        	        if(text2.equalsIgnoreCase(data.get(2).get(1)))
        	        	{
        	            option.click();
        	          break;
        	        }
        	}
        	driver.findElement(By.xpath(Pro.getProperty("Attachments_RefferenceNumber_XPATH"))).sendKeys(data.get(3).get(1));
        	Thread.sleep(2000);
        	WebElement uploadBtn=driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
            action.click(uploadBtn).build().perform();
            //put path to your image in a clipboard
            StringSelection ss = new StringSelection(data.get(4).get(1));
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

            //imitate mouse events like ENTER, CTRL+C, CTRL+V
            Robot robot = new Robot();
            robot.delay(300);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(600);
            Thread.sleep(5000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(300);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(300);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
           
            driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
            Thread.sleep(2000);
            driver.switchTo().defaultContent();
            WebDriverWait AttRecord=new WebDriverWait(driver,50);
            AttRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Attachment_Details_RecordAdded_XPATH"))));
      	Thread.sleep(2000);
             
	
	
	
}
	
// Organization 	
	
@When("^I Fill the Organization Taxpayer Registration form$")	
public void	I_Fill_the_Organization_Taxpayer_Registration_form() throws Throwable 
{
	 WebDriverWait wait=new WebDriverWait(driver,70);
		// driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH"))).click();
		  Actions action = new Actions(driver);
		  WebElement Reg=driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
		  action.doubleClick(Reg).build().perform();
		  Reg.click();
		   	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("RegisterTaxpayer_LINK_XPATH")))).click();
		   	WebElement Taxpayer=driver.findElement(By.xpath(Pro.getProperty("RegisterTaxpayer_LINK_XPATH")));
		   	action.click(Taxpayer).build().perform();
		    	WebElement Individual=driver.findElement(By.xpath(Pro.getProperty("Registration_RegisterTaxpayer_RegisterOrganisation_XPATh")));
		   	action.click(Individual).build().perform();
		   	Thread.sleep(2000);
		   

		   	
}

// Register Taxpayer Organization Scenario submit 
@And("^I enter valid data on the pages of Organization$")
public void I_enter_valid_data_on_the_pages_of_Organization(DataTable table )throws Throwable
{
	 
	   //Initialize data table   
		List<List<String>> data =table.raw();
        Actions action = new Actions(driver);
        driver.findElement(By.xpath(Pro.getProperty("HeaderDetails_OrganisationName_XPATH"))).sendKeys(data.get(1).get(1));
  	  Thread.sleep(2000);
        WebDriverWait Category=new WebDriverWait(driver,60);
        Category.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Organization_OrganisationCategory_XPATH")))).click();
        List<WebElement> CatValue = driver.findElements(By.xpath(Pro.getProperty("HeaderDetails_OrganisationCategory_ITEM_XPATH")));
		 for(WebElement option : CatValue)
	 	{
	 	    String text2= option.getText();
	 	    
	 	   // System.out.println(text2);
	 	        if(text2.equalsIgnoreCase(data.get(0).get(1)))
	 	        	{
                    option.click();
                    break;
	  }
	 	}
	      	
	      Thread.sleep(2000);
	 
	 // driver.findElement(By.xpath(Pro.getProperty("HeaderDetails_OrganisationName_XPATH"))).sendKeys(data.get(1).get(1));
	      List<WebElement> element2=driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ScrollToView_XPATH")));
			for (WebElement ele : element2)
  	{
  	  
					JavascriptExecutor js1 = (JavascriptExecutor) driver;
					js1.executeScript("arguments[0].scrollIntoView(true);",ele);
					
				}
	  driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	  driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_RGDNO_ID"))).sendKeys(data.get(2).get(1));
	  driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	  Thread.sleep(7000);
	 // Boolean status=driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfIncorporation_ID"))).isEnabled();
	  WebElement Date=driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfIncorporation_ID")));
	  if(Date.isEnabled())
	  {
		  System.out.println("enabled");
	  }
	  else
	  {
		  System.out.println("Disabled");
	  }
	  
				
       	   
	 // driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfIncorporation_ID"))).sendKeys(data.get(3).get(1));
	 
	  // driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfCommencement_ID"))).sendKeys(data.get(4).get(1));
	  driver.findElement(By.xpath(Pro.getProperty("Organisation_SourceOfCapital_Xapth"))).sendKeys(data.get(5).get(1));
	  Thread.sleep(2000);
	 // driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_PlaceOfIncorporation_XPATH"))).sendKeys(data.get(6).get(1));
	  
	  WebDriverWait Place=new WebDriverWait(driver,60);
	  Place.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("AdditionalDetails_PlaceOfIncorporation_XPATH")))).click();
      List<WebElement> PlaceValue = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_PlaceOfIncorporation_Values_XPATH")));
		 for(WebElement option : PlaceValue)
	 	{
	 	    String text2= option.getText();
	 	    
	 	   // System.out.println(text2);
	 	        if(text2.equalsIgnoreCase(data.get(6).get(1)))
	 	        	{
                  option.click();
                  break;
	  }
	 	}
	  
	  
	  
	  
	  List<WebElement> BSDetails=driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ScrollToView_XPATH")));
	for (WebElement ele : BSDetails)
	{
	  
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].scrollIntoView(true);",ele);
				
			}
	Thread.sleep(4000);
	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_ReasonForTINApplication_XPATH"))).click();
	      
	 	 List<WebElement> ReasonFTIN = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_ReasonForTINApplication_ITEM_XPATH")));
	 	 for(WebElement option1 : ReasonFTIN)
	  	{
	  	    String text= option1.getText();
	  	   // System.out.println(text);
	  	        if(text.equalsIgnoreCase(data.get(7).get(1)))
	  	        	{
	  	        	Actions builder1 = new Actions(driver);
	 		        builder1.moveToElement(option1).doubleClick(option1).build().perform();
	 	}}
	 	 Thread.sleep(2000);
	 	driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_YearEndMonth_ITEM_XPATH"))).click();
	 	List<WebElement> EndYearMonth = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_YearEndMonth_ITEM_Value_XPATH")));
	 	 for(WebElement option1 : EndYearMonth)
	  	{
	  	    String text= option1.getText();
	  	   // System.out.println(text);
	  	        if(text.equalsIgnoreCase(data.get(19).get(1)))
	  	        	{
	  	        	Actions builder1 = new Actions(driver);
	 		        builder1.moveToElement(option1).doubleClick(option1).build().perform();
	 		        break;
	 	}}
		
		 Thread.sleep(4000);
	WebElement yearDay=driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_YearEndDay_ITEM_XPATH")));
	yearDay.click();
	List<WebElement> EndYearDay = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_YearEndDay_ITEM_Value_XPATH")));
	 for(WebElement option1 : EndYearDay)
 	{
 	    String text= option1.getText();
 	   // System.out.println(text);
 	        if(text.equalsIgnoreCase(data.get(20).get(1)))
 	        	{
 	        	Actions builder1 = new Actions(driver);
		        builder1.moveToElement(option1).doubleClick(option1).build().perform();
		        break;
	}}
	
	 Thread.sleep(4000);

	 /*driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_YearEndMonth_ITEM_XPATH"))).click();	 
	 action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
	 Thread.sleep(2000);*/
	 	 List<WebElement> element=driver.findElements(By.id(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_ID")));
			for (WebElement ele : element)
		{
		  
					JavascriptExecutor js1 = (JavascriptExecutor) driver;
					js1.executeScript("arguments[0].scrollIntoView(true);",ele);
					
				}
	 	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	 	driver.findElement(By.id(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_ID"))).click();
	 	 WebElement  Occupationframe= driver.findElement(By.tagName("iframe"));
     	driver.switchTo().frame(Occupationframe);
     	WebDriverWait OccWait=new WebDriverWait(driver,50);
     	OccWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("Occupation/Business_ADD_WAIT_ID"))));
     	driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_Primaryindicator_XPATH"))).click();
     	
     	 WebElement BSadd=driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_BusinessSector_XPATH")));
         action.click(BSadd).build().perform();
         driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
         List<WebElement> BSecValue = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_BusinessSector_ITEM_XPATH")));
     	for(WebElement option : BSecValue){
     	    String text2= option.getText();
     	        if(text2.equalsIgnoreCase(data.get(8).get(1)))
     	        	{
                          option.click();
     	                   break;
     	        }
     	}
     	Thread.sleep(2000);
     	 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.id(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_OK_ID"))).click();
     	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
     	driver.switchTo().defaultContent();
     	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
     	WebDriverWait BSecrecord=new WebDriverWait(driver,50);
     	BSecrecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("AdditionalDetails_RecordAdded_XPATH"))));
     	Thread.sleep(2000);
     	List<WebElement> ScrollAddress=driver.findElements(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID")));
		for (WebElement ele : ScrollAddress)
	{
	  
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].scrollIntoView(true);",ele);
				
			}
     	
     	driver.findElement(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID"))).click();

           List<WebElement> SummarytabValue = driver.findElements(By.xpath(Pro.getProperty("Organisation_Summary_Tabs_ITEM_XPATH")));
     			 for(WebElement option : SummarytabValue)
     		 	{
     		 	    String text2= option.getText();
     		 	        if(text2.equalsIgnoreCase(data.get(9).get(1)))
     		 	        	{
     		 	        	Actions builder = new Actions(driver);
     				       builder.moveToElement(option).doubleClick();
     				        builder.perform();
     		 	        	
     		  }
     		 	}
     			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
     			 
     			WebElement Addressadd=driver.findElement(By.id(Pro.getProperty("Organization_Addresses_Add_ID")));
                action.click(Addressadd).build().perform();
                Thread.sleep(7000);
                WebElement  Addressframe= driver.findElement(By.tagName("iframe"));
            	driver.switchTo().frame(Addressframe);
            	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            	WebDriverWait AddressType=new WebDriverWait(driver,50);
            	AddressType.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_Type_XPATH")))).click();
            	List<WebElement> AddressValue = driver.findElements(By.xpath(Pro.getProperty("Addresses_Type_ITEM_XPATH")));
    			for(WebElement option : AddressValue){
    			    String text2= option.getText();
    			        if(text2.equalsIgnoreCase(data.get(10).get(1)))
    			        	{
    			            option.click();
    			          break;
    			        }
    			}
    			Thread.sleep(2000);
    			WebElement SName=driver.findElement(By.xpath(Pro.getProperty("Addresses_StreetName_XPATH")));
    	           action.sendKeys(SName,data.get(11).get(1)).build().perform();
    	           WebElement CName=driver.findElement(By.xpath(Pro.getProperty("Addresses_Town/City_XPATH")));
    	           action.sendKeys(CName,data.get(12).get(1)).build().perform();
    	           Thread.sleep(4000);
    	             	         
    	      WebDriverWait Region=new WebDriverWait(driver,50);
    	           Region.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_region_XPATH")))).click();
    	           List<WebElement> RegionValue = driver.findElements(By.xpath(Pro.getProperty("Addresses_region_ITEM_XPATH")));
    		       	for(WebElement option : RegionValue){
    		       	    String text2= option.getText();
    		       	        if(text2.equalsIgnoreCase(data.get(14).get(1)))
    		       	        	{
    		       	        	Actions builder = new Actions(driver);
    		    		        builder.moveToElement(option).click(option);
    		    		        builder.perform();
    		       	          
    		       	          break;
    		       	        }
    		       	}
    		       	Thread.sleep(2000);
    		       	WebDriverWait District=new WebDriverWait(driver,100);
    		           District.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_District_XPATH")))).click();
    		        	
    		           List<WebElement> Dvalue = driver.findElements(By.xpath(Pro.getProperty("Addresses_District_ITEM_XPATH")));
    		       	for(WebElement option : Dvalue){
    		       	    String text2= option.getText();
    		       	        if(text2.equalsIgnoreCase(data.get(13).get(1)))
    		       	        	{
    		       	            option.click();
    		       	          break;
    		       	        }
    		       	}
    		       	
    		       	List<WebElement> AddressOK=driver.findElements(By.id(Pro.getProperty("Addresses_OK_ID")));
        			for (WebElement ele : AddressOK)
            	{
            	  
        					JavascriptExecutor js1 = (JavascriptExecutor) driver;
        					js1.executeScript("arguments[0].scrollIntoView(true);",ele);
        					
        				}
        			 WebElement AddOK=driver.findElement(By.id(Pro.getProperty("Addresses_OK_ID")));
                     action.click(AddOK).build().perform();
                     driver.switchTo().defaultContent();
                     WebDriverWait AddressRecord=new WebDriverWait(driver,100);
                     AddressRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organisation_Addresses_RecordAdded_XPATH"))));
                     driver.findElement(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID"))).click();

                     List<WebElement> SummarytabValue2 = driver.findElements(By.xpath(Pro.getProperty("Organisation_Summary_Tabs_ITEM_XPATH")));
               			 for(WebElement option : SummarytabValue2)
               		 	{
               		 	    String text2= option.getText();
               		 	        if(text2.equalsIgnoreCase(data.get(15).get(1)))
               		 	        	{
               		 	        	Actions builder = new Actions(driver);
               				       builder.moveToElement(option).doubleClick();
               				        builder.perform();
               		 	        	
               		  }
               		 	}
                     
               			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
                 	driver.findElement(By.id(Pro.getProperty("ContactMethods_ADD_ID"))).click();
     		       	Thread.sleep(9000);
     		       WebElement  ContMethodframe= driver.findElement(By.tagName("iframe"));
     	        	driver.switchTo().frame(ContMethodframe);driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
     	           WebDriverWait Purpose=new WebDriverWait(driver,50);
     	          Purpose.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_Purpose_XPATH")))).click();
     	         List<WebElement> PurposeValue = driver.findElements(By.xpath(Pro.getProperty("ContactMethods_Purpose_ITEM_XPATH")));
              	for(WebElement option : PurposeValue){
              	    String text2= option.getText();
              	        if(text2.equalsIgnoreCase(data.get(16).get(1)))
              	        	{
              	        	
              	           option.click();
              	          break;
              	        }
              	}
              	
              	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
              	Thread.sleep(2000);
              	 WebDriverWait ContactType=new WebDriverWait(driver,50);
              	ContactType.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_Type_XPATH")))).click();
    	         List<WebElement> ContactTypeValue = driver.findElements(By.xpath(Pro.getProperty("ContactMethods_Type_ITEM_XPATH")));
             	for(WebElement option : ContactTypeValue){
             	    String text2= option.getText();
             	        if(text2.equalsIgnoreCase(data.get(17).get(1)))
             	        	{

    	       	        	Actions builder = new Actions(driver);
    	    		        builder.moveToElement(option).click(option);
    	    		        builder.perform();
             	           // option.click();
             	          break;
             	        }
             	}
             	JavascriptExecutor js = (JavascriptExecutor)driver;
                js.executeScript("arguments[0].click();", (driver.findElement(By.xpath(Pro.getProperty("ContactMethods_PrimaryIdicator_XPATH")))));
             
                WebElement PrimInd=driver.findElement(By.xpath(Pro.getProperty("ContactMethods_PrimaryIdicator_XPATH")));
               action.click(PrimInd).build().perform();
               driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
               WebDriverWait ContctDet=new WebDriverWait(driver,50);
               ContctDet.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_ContactMethodDetails_XPATH"))));
               Thread.sleep(1000);
               WebElement Contdetails=driver.findElement(By.xpath(Pro.getProperty("ContactMethods_ContactMethodDetails_XPATH")));
               action.sendKeys(Contdetails, data.get(18).get(1)).build().perform();
               Thread.sleep(1000);
               driver.findElement(By.xpath(Pro.getProperty("ContactMethods_OK_XPATH"))).click();
               Thread.sleep(2000);
               driver.switchTo().defaultContent();
   
               
               
               
               
               
               
}
               
         

@When("^Enter Attachment Tab details$")
public void enter_Attachment_Tab_details(DataTable Attchtable) throws Throwable {

	List<List<String>> data =Attchtable.raw();  
	//Actions action=new Actions(driver);
	
	
	 WebDriverWait ContactRecord=new WebDriverWait(driver,50);
     ContactRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organisation_ContactMethods_RecordAdded_XPATH"))));
     driver.findElement(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID"))).click();

     List<WebElement> OrgAttachment = driver.findElements(By.xpath(Pro.getProperty("Organisation_Summary_Tabs_ITEM_XPATH")));
			 for(WebElement option : OrgAttachment)
		 	{
		 	    String text2= option.getText();
		 	        if(text2.equalsIgnoreCase(data.get(0).get(1)))
		 	        	{
		 	        	Actions builder = new Actions(driver);
				       builder.moveToElement(option).doubleClick();
				        builder.perform();
		 	        	
		  }
		 	}
  	driver.findElement(By.id(Pro.getProperty("Attachments_ADD_ID"))).click();
  	 WebElement  Attachmentframe= driver.findElement(By.tagName("iframe"));
   	driver.switchTo().frame(Attachmentframe);
   	Thread.sleep(3000);
   	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
   	driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
      driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();
     
      List<WebElement> AttPassport = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
  	for(WebElement option : AttPassport){
  	    String text2= option.getText();
  	        if(text2.equalsIgnoreCase(data.get(2).get(1)))
  	        	{
  	            option.click();
  	          break;
  	        }
  	}
	Thread.sleep(2000);
  	driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_RefferenceNumber_XPATH"))).sendKeys(data.get(3).get(1));
  	Thread.sleep(2000);
  	WebElement uploadBtn=driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
     // action.click(uploadBtn).build().perform();
  	uploadBtn.click();
      //put path to your image in a clipboard
      StringSelection ss = new StringSelection(data.get(4).get(1));
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

      //imitate mouse events like ENTER, CTRL+C, CTRL+V
      Robot robot = new Robot();
      robot.delay(300);
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.delay(300);
      robot.keyPress(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.delay(600);
     
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.delay(300);
      robot.keyPress(KeyEvent.VK_ENTER);
      
      robot.keyRelease(KeyEvent.VK_ENTER);
      Thread.sleep(2000);
      driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
      driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();
      driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
      Thread.sleep(2000);
      driver.switchTo().defaultContent();
      WebDriverWait AttRecord=new WebDriverWait(driver,50);
      AttRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organization_Attachment_Details_RecordAdded_XPATH"))));
	Thread.sleep(2000);
//Attachment-----	Approval Letter from Line Ministry
  
	 
driver.findElement(By.id(Pro.getProperty("Attachments_ADD_ID"))).click();

WebElement  AttLetterframe= driver.findElement(By.tagName("iframe"));
driver.switchTo().frame(AttLetterframe);
Thread.sleep(3000);
driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
//driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();

List<WebElement> AttLettert = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
for(WebElement option : AttLettert){
  String text2= option.getText();
      if(text2.equalsIgnoreCase(data.get(6).get(1)))
      	{
          option.click();
        break;
      }
}
Thread.sleep(2000);
driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_RefferenceNumber_XPATH"))).sendKeys(data.get(5).get(1));
Thread.sleep(2000);
WebElement LetterBtn=driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
// action.click(LetterBtn).build().perform();
LetterBtn.click();
//put path to your image in a clipboard
StringSelection ss1 = new StringSelection(data.get(4).get(1));
Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss1, null);

//imitate mouse events like ENTER, CTRL+C, CTRL+V
Robot robot1 = new Robot();
robot1.delay(300);
robot1.keyPress(KeyEvent.VK_ENTER);
robot1.keyRelease(KeyEvent.VK_ENTER);
robot1.keyPress(KeyEvent.VK_CONTROL);
robot1.delay(300);
robot1.keyPress(KeyEvent.VK_V);
robot1.keyRelease(KeyEvent.VK_V);
robot1.keyRelease(KeyEvent.VK_CONTROL);
robot1.delay(600);
Thread.sleep(5000);
robot1.keyPress(KeyEvent.VK_ENTER);
robot1.delay(300);
robot1.keyPress(KeyEvent.VK_ENTER);
robot1.delay(300);
robot1.keyRelease(KeyEvent.VK_ENTER);
Thread.sleep(2000);
driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();
driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
Thread.sleep(2000);
driver.switchTo().defaultContent();
WebDriverWait AttRecord1=new WebDriverWait(driver,50);
AttRecord1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organization_Attachment_Details_RecordAdded_XPATH"))));
Thread.sleep(2000);
//End....
// Certificate of Incorporation
driver.findElement(By.id(Pro.getProperty("Attachments_ADD_ID"))).click();
WebElement  AttCertiframe= driver.findElement(By.tagName("iframe"));
driver.switchTo().frame(AttCertiframe);
Thread.sleep(3000);
driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
//driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();

List<WebElement> AttCertificate = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
for(WebElement option : AttCertificate){
String text2= option.getText();
  if(text2.equalsIgnoreCase(data.get(7).get(1)))
  	{
      option.click();
    break;
  }
}
Thread.sleep(2000);
driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_RefferenceNumber_XPATH"))).sendKeys(data.get(9).get(1));
Thread.sleep(2000);
WebElement CertiBtn=driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
CertiBtn.click();
// action.click(CertiBtn).build().perform();
//put path to your image in a clipboard
StringSelection ss2 = new StringSelection(data.get(4).get(1));
Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);

//imitate mouse events like ENTER, CTRL+C, CTRL+V
Robot robot2 = new Robot();
robot2.delay(300);
robot2.keyPress(KeyEvent.VK_ENTER);
robot2.keyRelease(KeyEvent.VK_ENTER);
robot2.keyPress(KeyEvent.VK_CONTROL);
robot2.keyPress(KeyEvent.VK_V);
robot2.keyRelease(KeyEvent.VK_V);
robot2.keyRelease(KeyEvent.VK_CONTROL);
robot2.delay(600);
Thread.sleep(5000);
robot2.keyPress(KeyEvent.VK_ENTER);
robot2.delay(300);
robot2.keyPress(KeyEvent.VK_ENTER);
robot2.delay(300);
robot2.keyRelease(KeyEvent.VK_ENTER);
Thread.sleep(2000);
driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();
driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
Thread.sleep(2000);
driver.switchTo().defaultContent();
WebDriverWait AttRecord2=new WebDriverWait(driver,50);
AttRecord2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organization_Attachment_Details_RecordAdded_XPATH"))));
Thread.sleep(2000);
}



@When("^Enter Directors Tab Info for Organization taxpayer$")
public void enter_Directors_Tab_Info_for_Organization_taxpayer(DataTable Dirtable) throws Throwable {
	
	List<List<String>> data =Dirtable.raw();
    Actions action = new Actions(driver);
  	driver.findElement(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID"))).click();

    List<WebElement> DirectorValue = driver.findElements(By.xpath(Pro.getProperty("Organisation_Summary_Tabs_ITEM_XPATH")));
			 for(WebElement option : DirectorValue)
		 	{
		 	    String text2= option.getText();
		 	        if(text2.equalsIgnoreCase(data.get(0).get(1)))
		 	        	{
		 	        	Actions builder = new Actions(driver);
				       builder.moveToElement(option).doubleClick();
				        builder.perform();
		 	        	
		  }
		 	}
			 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			driver.findElement(By.id(Pro.getProperty("Directors_ADD_ID"))).click();
			 WebElement  directorframe= driver.findElement(By.tagName("iframe"));
     	driver.switchTo().frame(directorframe);
        
WebElement DirecTin=driver.findElement(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_ID")));
action.click(DirecTin).build().perform();
int frame=driver.findElements(By.tagName("iframe")).size();
System.out.println(frame);
driver.switchTo().parentFrame();
driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
int frame1=driver.findElements(By.tagName("iframe")).size();
System.out.println(frame1);
driver.switchTo().frame(1);
WebDriverWait DirctAdd=new WebDriverWait(driver,60);
DirctAdd.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_Webdriverwait_ID")))).click();
Thread.sleep(2000);
WebElement DTinadd=driver.findElement(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_TIN_NAME_ID")));
action.sendKeys(DTinadd, data.get(1).get(1)).build().perform();
Thread.sleep(2000);
WebDriverWait Searchwait=new WebDriverWait(driver,100);
Searchwait.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_Search_ID")))).click();


//driver.findElement(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_Search_ID"))).click();
driver.switchTo().defaultContent();
driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
WebElement  DirTinframe= driver.findElement(By.tagName("iframe"));
driver.switchTo().frame(DirTinframe);
Thread.sleep(2000);
driver.findElement(By.xpath(Pro.getProperty("Directors_Principal_XPATH"))).click();
Thread.sleep(2000);
driver.findElement(By.xpath(Pro.getProperty("Directors_ADD_DirectorStartDate_XPATH"))).sendKeys(data.get(2).get(1));
Thread.sleep(2000);
//driver.findElement(By.xpath(Pro.getProperty("Directors_Principal_XPATH"))).click();
Thread.sleep(1000);
driver.findElement(By.id(Pro.getProperty("Directors_ADD_OK_ID"))).click();
driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
driver.switchTo().defaultContent();
Thread.sleep(2000);



}

@Then("^Click On Organization Page Submit Button$")
public void click_On_Organization_Page_Submit_Button() throws Throwable {
	WebDriverWait Submit=new WebDriverWait(driver,60);
    Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("RegisterOrganisation_SUBMIT_XPATH")))).click();
Thread.sleep(7000);
}


// Register Organization Taxpayer save Taxpayer Information
@Then("^Click On Organization Page Save Button$")
public void click_On_Organization_Page_Save_Button() throws Throwable {
	WebDriverWait Save=new WebDriverWait(driver,60);
	Save.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("RegisterOrganisation_SAVE_Data_ID")))).click();
Thread.sleep(7000);
}


//Register Organization Taxpayer save Taxpayer Information

@When("^Enter required data to save organization info for Processing scenario$")
public void enter_required_data_to_save_organization_info_for_Processing_scenario(DataTable Dirtable) throws Throwable {
    
	List<List<String>> data =Dirtable.raw();
    Actions action = new Actions(driver);
  	driver.findElement(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID"))).click();

    List<WebElement> DirectorValue = driver.findElements(By.xpath(Pro.getProperty("Organisation_Summary_Tabs_ITEM_XPATH")));
			 for(WebElement option : DirectorValue)
		 	{
		 	    String text2= option.getText();
		 	        if(text2.equalsIgnoreCase(data.get(0).get(1)))
		 	        	{
		 	        	Actions builder = new Actions(driver);
				       builder.moveToElement(option).doubleClick();
				        builder.perform();
		 	        	
		  }
		 	}
			 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			driver.findElement(By.id(Pro.getProperty("Directors_ADD_ID"))).click();
			 WebElement  directorframe= driver.findElement(By.tagName("iframe"));
     	driver.switchTo().frame(directorframe);
        
WebElement DirecTin=driver.findElement(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_ID")));
action.click(DirecTin).build().perform();
int frame=driver.findElements(By.tagName("iframe")).size();
System.out.println(frame);
driver.switchTo().parentFrame();
driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
int frame1=driver.findElements(By.tagName("iframe")).size();
System.out.println(frame1);
driver.switchTo().frame(1);
WebDriverWait DirctAdd=new WebDriverWait(driver,60);
DirctAdd.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_Webdriverwait_ID")))).click();
Thread.sleep(2000);
WebElement DTinadd=driver.findElement(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_TIN_NAME_ID")));
action.sendKeys(DTinadd, data.get(1).get(1)).build().perform();
Thread.sleep(2000);
WebDriverWait Searchwait=new WebDriverWait(driver,100);
Searchwait.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_Search_ID")))).click();


//driver.findElement(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_Search_ID"))).click();
driver.switchTo().defaultContent();
driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
WebElement  DirTinframe= driver.findElement(By.tagName("iframe"));
driver.switchTo().frame(DirTinframe);
Thread.sleep(2000);
//driver.findElement(By.xpath(Pro.getProperty("Directors_Principal_XPATH"))).click();
Thread.sleep(2000);
driver.findElement(By.xpath(Pro.getProperty("Directors_ADD_DirectorStartDate_XPATH"))).sendKeys(data.get(2).get(1));
Thread.sleep(2000);
driver.findElement(By.xpath(Pro.getProperty("Directors_Principal_XPATH"))).click();
Thread.sleep(1000);
driver.findElement(By.id(Pro.getProperty("Directors_ADD_OK_ID"))).click();
driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
driver.switchTo().defaultContent();
Thread.sleep(2000);



}

//Register Organizatio Taxpayer Save Process Application

@When("^Enter Attachment Tab details for Processing Application$")
public void enter_Attachment_Tab_details_for_Processing_Application(DataTable Attchtable) throws Throwable {


	List<List<String>> data =Attchtable.raw();  
	//Actions action=new Actions(driver);
	
	
	 WebDriverWait ContactRecord=new WebDriverWait(driver,50);
     ContactRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organisation_ContactMethods_RecordAdded_XPATH"))));
     driver.findElement(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID"))).click();

     List<WebElement> OrgAttachment = driver.findElements(By.xpath(Pro.getProperty("Organisation_Summary_Tabs_ITEM_XPATH")));
			 for(WebElement option : OrgAttachment)
		 	{
		 	    String text2= option.getText();
		 	        if(text2.equalsIgnoreCase(data.get(0).get(1)))
		 	        	{
		 	        	Actions builder = new Actions(driver);
				       builder.moveToElement(option).doubleClick();
				        builder.perform();
		 	        	
		  }
		 	}
  	driver.findElement(By.id(Pro.getProperty("Attachments_ADD_ID"))).click();
  	 WebElement  Attachmentframe= driver.findElement(By.tagName("iframe"));
   	driver.switchTo().frame(Attachmentframe);
   	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
   	driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
      driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();
     
      List<WebElement> AttPassport = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
  	for(WebElement option : AttPassport){
  	    String text2= option.getText();
  	        if(text2.equalsIgnoreCase(data.get(2).get(1)))
  	        	{
  	            option.click();
  	          break;
  	        }
  	}
  	Thread.sleep(2000);
  	driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_RefferenceNumber_XPATH"))).sendKeys(data.get(3).get(1));
  	Thread.sleep(2000);
  	WebElement uploadBtn=driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
     // action.click(uploadBtn).build().perform();
  	uploadBtn.click();
      //put path to your image in a clipboard
      StringSelection ss = new StringSelection(data.get(4).get(1));
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

      //imitate mouse events like ENTER, CTRL+C, CTRL+V
      Robot robot = new Robot();
      robot.delay(300);
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.delay(300);
      robot.keyPress(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.delay(600);
      Thread.sleep(5000);
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.delay(300);
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.delay(300);
      robot.keyRelease(KeyEvent.VK_ENTER);
      Thread.sleep(2000);
     // driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
     // driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();
      driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
      Thread.sleep(2000);
      driver.switchTo().defaultContent();
      WebDriverWait AttRecord=new WebDriverWait(driver,50);
      AttRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organization_Attachment_Details_RecordAdded_XPATH"))));
	Thread.sleep(2000);
//Attachment-----	Approval Letter from Line Ministry
  
	 
driver.findElement(By.id(Pro.getProperty("Attachments_ADD_ID"))).click();
WebElement  AttLetterframe= driver.findElement(By.tagName("iframe"));
driver.switchTo().frame(AttLetterframe);
driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();

List<WebElement> AttLettert = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
for(WebElement option : AttLettert){
  String text2= option.getText();
      if(text2.equalsIgnoreCase(data.get(6).get(1)))
      	{
          option.click();
        break;
      }
}
Thread.sleep(2000);
driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_RefferenceNumber_XPATH"))).sendKeys(data.get(5).get(1));
Thread.sleep(2000);
WebElement LetterBtn=driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
// action.click(LetterBtn).build().perform();
LetterBtn.click();
//put path to your image in a clipboard
StringSelection ss1 = new StringSelection(data.get(4).get(1));
Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss1, null);

//imitate mouse events like ENTER, CTRL+C, CTRL+V
Robot robot1 = new Robot();
robot1.delay(300);
robot1.keyPress(KeyEvent.VK_ENTER);
robot1.keyRelease(KeyEvent.VK_ENTER);
robot1.keyPress(KeyEvent.VK_CONTROL);
robot1.keyPress(KeyEvent.VK_V);
robot1.keyRelease(KeyEvent.VK_V);
robot1.keyRelease(KeyEvent.VK_CONTROL);
robot1.delay(600);
Thread.sleep(5000);
robot1.keyPress(KeyEvent.VK_ENTER);
robot1.delay(300);
robot1.keyPress(KeyEvent.VK_ENTER);
robot1.delay(300);
robot1.keyRelease(KeyEvent.VK_ENTER);
Thread.sleep(2000);
driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();
driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
Thread.sleep(2000);
driver.switchTo().defaultContent();
WebDriverWait AttRecord1=new WebDriverWait(driver,50);
AttRecord1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organization_Attachment_Details_RecordAdded_XPATH"))));
Thread.sleep(2000);
//End....
// Certificate of Incorporation
driver.findElement(By.id(Pro.getProperty("Attachments_ADD_ID"))).click();
WebElement  AttCertiframe= driver.findElement(By.tagName("iframe"));
driver.switchTo().frame(AttCertiframe);
driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();

List<WebElement> AttCertificate = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
for(WebElement option : AttCertificate){
String text2= option.getText();
  if(text2.equalsIgnoreCase(data.get(7).get(1)))
  	{
      option.click();
    break;
  }
}
Thread.sleep(2000);
driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_RefferenceNumber_XPATH"))).sendKeys(data.get(9).get(1));
Thread.sleep(2000);
WebElement CertiBtn=driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
CertiBtn.click();
// action.click(CertiBtn).build().perform();
//put path to your image in a clipboard
StringSelection ss2 = new StringSelection(data.get(4).get(1));
Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);

//imitate mouse events like ENTER, CTRL+C, CTRL+V
Robot robot2 = new Robot();
robot2.delay(300);
robot2.keyPress(KeyEvent.VK_ENTER);
robot2.keyRelease(KeyEvent.VK_ENTER);
robot2.keyPress(KeyEvent.VK_CONTROL);
robot2.keyPress(KeyEvent.VK_V);
robot2.keyRelease(KeyEvent.VK_V);
robot2.keyRelease(KeyEvent.VK_CONTROL);
robot2.delay(600);
Thread.sleep(5000);
robot2.keyPress(KeyEvent.VK_ENTER);
robot2.delay(300);
robot2.keyPress(KeyEvent.VK_ENTER);
robot2.delay(300);
robot2.keyRelease(KeyEvent.VK_ENTER);
Thread.sleep(2000);
driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();
driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
Thread.sleep(2000);
driver.switchTo().defaultContent();
WebDriverWait AttRecord2=new WebDriverWait(driver,50);
AttRecord2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organization_Attachment_Details_RecordAdded_XPATH"))));
Thread.sleep(2000);
}





@Then("^Organization ARN number will generate$")					
public void	Organization_ARN_number_will_generate(DataTable table) throws Throwable 						
{		
	List<List<String>> data =table.raw();
	WebDriverWait Submit=new WebDriverWait(driver,60);
    Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("RegisterOrganisation_SUBMIT_XPATH")))).click();
Thread.sleep(7000);
WebDriverWait RefNumber=new WebDriverWait(driver,60);
RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID")))).click();
// Capture ARN Number
    String text  =driver.findElement(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID"))).getText();
    System.out.println(text);
    if(text.contains(data.get(0).get(1)))
    		{
    	      //  System.out.println(text);
    			System.out.println("Text Verified and passed");
    		}
             else
                {
    	             System.out.println("Text Not Verfied and failed");
                }      
	

}

// Process Registration Application Individual Taxpayer Scenario


@Then("^Click on Register Taxpayer tab$")
public void click_on_Register_Taxpayer_tab() throws Throwable {
	//Actions action =new Actions(driver);
   	WebElement Taxpayer=driver.findElement(By.xpath(Pro.getProperty("RegisterTaxpayer_LINK_XPATH")));
   	//action.click(Taxpayer).build().perform();
   	Taxpayer.click();
 
}

@Then("^Click on Process Registration application$")
public void click_on_Process_Registration_application() throws Throwable {
	Actions action=new Actions(driver);
	
   	WebElement Taxpayer=driver.findElement(By.xpath(Pro.getProperty("Registration_RegisterTaxpayer_ProcessRegistrationApplication_XPATH")));
   	action.click(Taxpayer).build().perform();
    
}

@Then("^Enter Application reference number \"([^\"]*)\"$")
public void enter_Application_reference_number(String ReferenceNumber) throws Throwable {
	Thread.sleep(1000);
	driver.findElement(By.id(Pro.getProperty("ProcessRegistrationApplication_ApplicationRefferenceNumber_ID"))).sendKeys(ReferenceNumber);
  Thread.sleep(2000);
}

@Then("^Click on Search$")
public void click_on_Search() throws Throwable {
	driver.findElement(By.xpath(Pro.getProperty("ProcessRegistrationApplication_Search_XPATH"))).click();
   
}

@Then("^Select the row from the grid$")
public void select_the_row_from_the_grid() throws Throwable {
	Thread.sleep(1000);
	Actions action=new Actions(driver);
	WebElement PrimInd=driver.findElement(By.xpath(Pro.getProperty("ProcessRegistrationApplication_SelectFirstRow_XPATH")));
    action.doubleClick(PrimInd).build().perform();

}

@Then("^Click on Edit button of individual page$")
public void click_on_Edit_button_of_individual_page() throws Throwable {
	driver.findElement(By.id(Pro.getProperty("ProcessRegistrationApplication_IND_Edit_ID"))).click();
	Thread.sleep(2000);
   
}

@Then("^Scroll page to the attachment tab$")
public void scroll_page_to_the_attachment_tab() throws Throwable {
	 List<WebElement> element=driver.findElements(By.id(Pro.getProperty("Applicant_Attachments_ScrollToViwe_ID")));
		for (WebElement ele : element)
	{
	  
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].scrollIntoView(true);",ele);
				
			}
		Thread.sleep(2000);
   
}

@Then("^Click on Attachment Tab$")
public void click_on_Attachment_Tab() throws Throwable {
	
	driver.findElement(By.xpath(Pro.getProperty("Exemption_ScrollToView_XPATH"))).click();
	
}

@Then("^Select first row of the grid for individual page$")
public void select_first_row_of_the_grid_for_individual_page() throws Throwable {
	Thread.sleep(1000);
	Actions action=new Actions(driver);
	WebElement PrimInd=driver.findElement(By.xpath(Pro.getProperty("ProcessRegistrationApplication_IND_Edit_Attachment_FirstRow_XPATH")));
    action.doubleClick(PrimInd).build().perform();
    Thread.sleep(2000);

   
}

@Then("^Click edit the Individual attachment information$")
public void click_edit_the_Individual_attachment_information() throws Throwable {
	Actions action=new Actions(driver);
	WebElement PrimInd=driver.findElement(By.xpath(Pro.getProperty("ProcessRegistrationApplication_IND_Attachment_Edit_XPATH")));
    action.doubleClick(PrimInd).build().perform();
    Thread.sleep(2000);
    WebElement  iframe= driver.findElement(By.tagName("iframe"));
	driver.switchTo().frame(iframe);
	
	
    
}

@Then("^Check the PN folder check box$")
public void check_the_PN_folder_check_box() throws Throwable {
	
	driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
   
}

@Then("^Check the Verified check box$")
public void check_the_Verified_check_box() throws Throwable {
	driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();
  
}

@Then("^Click on OK Button$")
public void click_on_OK_Button() throws Throwable {
	Thread.sleep(3000);
	driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
	driver.switchTo().defaultContent();
	Thread.sleep(2000);
    
}

//Process Registration Application Organization Taxpayer Scenario

@Then("^Select first row of the grid for Organization page$")
public void select_first_row_of_the_grid_for_Organization_page() throws Throwable {
	Thread.sleep(1000);
	Actions action=new Actions(driver);
	WebElement PrimInd=driver.findElement(By.xpath(Pro.getProperty("ProcessRegistrationApplication_ORG_Edit_Attachment_FirstRow_XPATH")));
    action.doubleClick(PrimInd).build().perform();
    Thread.sleep(2000);

   
}






//Register Individual taxtype  Individual scenario
@When("^I enter valid data on the TaxType Individual page$")
public void i_enter_valid_data_on_the_TaxType_Individual_page(DataTable arg1) throws Throwable {
{
	//Initialize data table   
		List<List<String>> data =arg1.raw();
		Actions action = new Actions(driver);
		  WebElement Reg=driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
		  action.doubleClick(Reg).build().perform();
		  Reg.click();
  WebElement manage=driver.findElement(By.xpath(Pro.getProperty("ManageTaxpayer_LINK_XPATH")));
		  	  action.doubleClick(manage).build().perform();
		  WebDriverWait Taxtype=new WebDriverWait(driver,60);
		  Taxtype.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Registration_RegisterTaxType_XPATH")))).click();
		
		  driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_XPATH"))).click();;
	 List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
			 for(WebElement option : list)
		 	{
		 	    String text2= option.getText();
		 	   System.out.println(text2);
		 	    
		 	    
		 	        if(text2.equalsIgnoreCase(data.get(0).get(1)))
		 	        	{
		 	        	
		 	        	option.click();
		 	        	break;
		  }
		 	}
			 driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TIN_ID"))).sendKeys(data.get(1).get(1));

			 
			 WebDriverWait Searchwait=new WebDriverWait(driver,100);
			 Searchwait.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Individual_Search_ID")))).click();
			 
             Thread.sleep(2000);
			List<WebElement> element2=driver.findElements(By.id(Pro.getProperty("SrollToView_Title_ID")));
 			for (WebElement ele : element2)
     	{
     	  
 					JavascriptExecutor js1 = (JavascriptExecutor) driver;
 					js1.executeScript("arguments[0].scrollIntoView(true);",ele);
 					
 				}
 				
 			 WebElement RegTaxType=driver.findElement(By.id(Pro.getProperty("Registration_RegisterTaxType_RegisterTaxTypeButton_ID")));
 	           action.click(RegTaxType).build().perform();  
 	          Thread.sleep(10000);
 	         WebElement  Taxtypeframe= driver.findElement(By.tagName("iframe"));
         	driver.switchTo().frame(Taxtypeframe);
         	WebDriverWait selectTaxtype=new WebDriverWait(driver,60);
         	selectTaxtype.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Registration_RegisterTaxType_TaxType_ID")))).click();
         	List<WebElement> TaxTypeValue = driver.findElements(By.xpath(Pro.getProperty("Registration_RegisterTaxType_TaxType_ITEM_XPATH")));
			 for(WebElement option : TaxTypeValue)
		 	{
		 	    String text2= option.getText();
		 	 
		 	    
		 	    
		 	        if(text2.equalsIgnoreCase(data.get(2).get(1)))
		 	        	{
		 	        	
		 	        	option.click();
		 	        	break;
		  }
		 	}
			Thread.sleep(2000); 
			driver.findElement(By.id(Pro.getProperty("Registration_RegisterTaxType_EDR_ID"))).sendKeys(data.get(3).get(1));
			
			driver.findElement(By.id(Pro.getProperty("Registration_RegisterTaxType_OK_ID"))).click(); 
			driver.switchTo().defaultContent();
			 String Recordwait=driver.findElement(By.xpath(Pro.getProperty("Registration_RegisterTaxType_RecordAdded_XPATH"))).getText();
	            System.out.println(Recordwait);
	            if(Recordwait.contains(data.get(4).get(1)))
	            		{
	            	
	            			System.out.println("Text Verified and passed");
	            		}
	                     else
	                        {
	            	             System.out.println("Text Not Verfied and failed");
	                         }
	                    
	            Thread.sleep(1000);
}

		
}

@Then("^TaxType ARN number will generate$")
public void taxtype_ARN_number_will_generate(DataTable TaxType) throws Throwable {
			
		List<List<String>> data =TaxType.raw();
		WebDriverWait Submit=new WebDriverWait(driver,60);
	    Submit.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("TaxType_Submit_ID")))).click();
	Thread.sleep(7000);
	WebDriverWait RefNumber=new WebDriverWait(driver,60);
	RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID")))).click();
	// Capture ARN Number
	    String text  =driver.findElement(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID"))).getText();
	    System.out.println(text);
	    if(text.contains(data.get(0).get(1)))
	    		{
	    	      //  System.out.println(text);
	    			System.out.println("Text Verified and passed");
	    		}
	             else
	                {
	    	             System.out.println("Text Not Verfied and failed");
	                }      
		

	}

@When("^I enter valid data on the TaxType Organization page$")
public void i_enter_valid_data_on_the_TaxType_Organization_page(DataTable arg1) throws Throwable {
	{
	//Initialize data table   
			List<List<String>> data =arg1.raw();
			Actions action = new Actions(driver);
			  WebElement Reg=driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
			  action.doubleClick(Reg).build().perform();
			  Reg.click();

			  WebElement manage=driver.findElement(By.xpath(Pro.getProperty("ManageTaxpayer_LINK_XPATH")));
		  	  action.doubleClick(manage).build().perform();
		  WebDriverWait Taxtype=new WebDriverWait(driver,60);
		  Taxtype.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Registration_RegisterTaxType_XPATH")))).click();
			  
			  driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_XPATH"))).click();;
		 List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
				 for(WebElement option : list)
			 	{
			 	    String text2= option.getText();
			 	   System.out.println(text2);
			 	    
			 	    
			 	        if(text2.equalsIgnoreCase(data.get(0).get(1)))
			 	        	{
			 	        	
			 	        	option.click();
			 	        	break;
			  }
			 	}
				 driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TIN_ID"))).sendKeys(data.get(1).get(1));

				 
				 WebDriverWait Searchwait=new WebDriverWait(driver,100);
				 Searchwait.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Individual_Search_ID")))).click();
				 
	             Thread.sleep(2000);
				List<WebElement> element2=driver.findElements(By.id(Pro.getProperty("SrollToView_Title_ID")));
	 			for (WebElement ele : element2)
	     	{
	     	  
	 					JavascriptExecutor js1 = (JavascriptExecutor) driver;
	 					js1.executeScript("arguments[0].scrollIntoView(true);",ele);
	 					
	 				}
	 				
	 			 WebElement RegTaxType=driver.findElement(By.id(Pro.getProperty("Registration_RegisterTaxType_RegisterTaxTypeButton_ID")));
	 	           action.click(RegTaxType).build().perform();  
	 	          Thread.sleep(10000);
	 	         WebElement  Taxtypeframe= driver.findElement(By.tagName("iframe"));
	         	driver.switchTo().frame(Taxtypeframe);
	         	WebDriverWait selectTaxtype=new WebDriverWait(driver,60);
	         	selectTaxtype.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Registration_RegisterTaxType_TaxType_ID")))).click();
	         	List<WebElement> TaxTypeValue = driver.findElements(By.xpath(Pro.getProperty("Registration_RegisterTaxType_TaxType_ITEM_XPATH")));
				 for(WebElement option : TaxTypeValue)
			 	{
			 	    String text2= option.getText();
			 	 
			 	    
			 	    System.out.println(text2);
			 	        if(text2.equalsIgnoreCase(data.get(2).get(1)))
			 	        	{
			 	        	
			 	        	option.click();
			 	        	break;
			  }
			 	}
				Thread.sleep(2000); 
				driver.findElement(By.id(Pro.getProperty("Registration_RegisterTaxType_EDR_ID"))).sendKeys(data.get(3).get(1));
				
				driver.findElement(By.id(Pro.getProperty("Registration_RegisterTaxType_OK_ID"))).click(); 
				driver.switchTo().defaultContent();
				 String Recordwait=driver.findElement(By.xpath(Pro.getProperty("Registration_RegisterTaxType_RecordAdded_XPATH"))).getText();
		            System.out.println(Recordwait);
		            if(Recordwait.contains(data.get(4).get(1)))
		            		{
		            	
		            			System.out.println("Text Verified and passed");
		            		}
		                     else
		                        {
		            	             System.out.println("Text Not Verfied and failed");
		                         }
		                    
                                }
	
		                    Thread.sleep(1000);
}

// Update Individual Taxpayer Scenario

@When("^I enter valid data on the Update Individual page $")
public void i_enter_valid_data_on_the_Update_Individual_page(DataTable UpadateTable) throws Throwable {

	//Initialize data table   
	List<List<String>> data =UpadateTable.raw();
	Actions action = new Actions(driver);
	  WebElement Reg=driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
	  action.doubleClick(Reg).build().perform();
	  Reg.click();
	  WebDriverWait Taxtype=new WebDriverWait(driver,100);
	  Taxtype.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ManageTaxpayer_LINK_XPATH")))).click();
	  driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_XPATH"))).click();
	 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	  driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_XPATH"))).click();;
		 List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
				 for(WebElement option : list)
			 	{
			 	    String text2= option.getText();
			 	   System.out.println(text2);
			 	    
			 	    
			 	        if(text2.equalsIgnoreCase(data.get(0).get(1)))
			 	        	{
			 	        	
			 	        	option.click();
			 	        	break;
			  }
			 	}
	  
		 driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TIN_ID"))).sendKeys(data.get(1).get(1));
		 Thread.sleep(1000);
		 driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_ID"))).click();
		 Thread.sleep(5000);
		 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		 driver.findElement(By.id(Pro.getProperty("LastName_ID"))).clear();
		 Thread.sleep(1000);
		WebElement LastName=driver.findElement(By.id(Pro.getProperty("LastName_ID")));
		 action.sendKeys(LastName, data.get(2).get(1)).build().perform();
		
		 Thread.sleep(4000);
		 List<WebElement> element=driver.findElements(By.xpath(Pro.getProperty("Gender_LINK_XPATH")));
			for (WebElement ele : element)
		{
		  
					JavascriptExecutor js1 = (JavascriptExecutor) driver;
					js1.executeScript("arguments[0].scrollIntoView(true);",ele);
					//ele.click();
				}
			Thread.sleep(2000);
			driver.findElement(By.id(Pro.getProperty ("DOB_ID"))).clear();		
	driver.findElement(By.id(Pro.getProperty ("DOB_ID"))).sendKeys(data.get(3).get(1));
	 List<WebElement> AmendScroll=driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_AmendmentReason_Lable_Scroll_XPATH")));
		for (WebElement ele : AmendScroll)
	{
	  
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].scrollIntoView(true);",ele);
				//ele.click();
			}
		 
driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_Individual_AmendmentReason_XPATH"))).click();
List<WebElement> AmendValue = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_Individual_AmendmentReason_ITEM_XPATH")));
 for(WebElement option : AmendValue)
	{
	    String text2= option.getText();
	    System.out.println(option);
	        if(text2.equalsIgnoreCase(data.get(4).get(1)))
	        	{
	        	Actions builder = new Actions(driver);
	       builder.moveToElement(option).doubleClick();
	        builder.perform();
	        
	        break;
	        
	        
	        	
}
	        Thread.sleep(3000);
	}
}
// Update Organization Taxpayer Scenario
 @When("^I enter valid data on the Update Organizatio page$")
 public void i_enter_valid_data_on_the_Update_Organizatio_page(DataTable UpdteOrgTable) throws Throwable {
	//Initialize data table   
		List<List<String>> data =UpdteOrgTable.raw();
		Actions action = new Actions(driver);
		  WebElement Reg=driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
		  action.doubleClick(Reg).build().perform();
		  Reg.click();
		  WebDriverWait Taxtype=new WebDriverWait(driver,100);
		  Taxtype.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ManageTaxpayer_LINK_XPATH")))).click();
		  driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_XPATH"))).click();
		 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		  driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_XPATH"))).click();;
			 List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
					 for(WebElement option : list)
				 	{
				 	    String text2= option.getText();
				 	   System.out.println(text2);
				 	    
				 	    
				 	        if(text2.equalsIgnoreCase(data.get(0).get(1)))
				 	        	{
				 	        	
				 	        	option.click();
				 	        	break;
				  }
				 	}
		  
			 driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TIN_ID"))).sendKeys(data.get(1).get(1));
			 Thread.sleep(1000);
			 driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_ID"))).click();
			 Thread.sleep(5000);
			 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			 driver.findElement(By.xpath(Pro.getProperty("HeaderDetails_OrganisationName_XPATH"))).clear();
			 Thread.sleep(1000);
			WebElement LastName=driver.findElement(By.xpath(Pro.getProperty("HeaderDetails_OrganisationName_XPATH")));
			 action.sendKeys(LastName, data.get(2).get(1)).build().perform();
			
			 Thread.sleep(4000);
			 List<WebElement> element=driver.findElements(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfCommencement_ID")));
				for (WebElement ele : element)
			{
			  
						JavascriptExecutor js1 = (JavascriptExecutor) driver;
						js1.executeScript("arguments[0].scrollIntoView(true);",ele);
						//ele.click();
					}
				Thread.sleep(2000);
				
				  
				driver.findElement(By.id(Pro.getProperty ("Organisation_AdditionalDetails_DateOfCommencement_ID"))).clear();		
		driver.findElement(By.id(Pro.getProperty ("Organisation_AdditionalDetails_DateOfCommencement_ID"))).sendKeys(data.get(3).get(1));
		Thread.sleep(1000);
		List<WebElement> AmendScroll=driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_AmendmentReason_Lable_Scroll_XPATH")));
			for (WebElement ele : AmendScroll)
		{
		  
					JavascriptExecutor js1 = (JavascriptExecutor) driver;
					js1.executeScript("arguments[0].scrollIntoView(true);",ele);
					//ele.click();
				}
			 
	driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_Organisation_AmendmentReason_XPATH"))).click();
	List<WebElement> AmendValue = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_Organisation_AmendmentReason_ITEM_XPATH")));
	 for(WebElement option : AmendValue)
		{
		    String text2= option.getText();
		    System.out.println(option);
		        if(text2.equalsIgnoreCase(data.get(4).get(1)))
		        	{
		        	Actions builder = new Actions(driver);
		       builder.moveToElement(option).doubleClick();
		        builder.perform();
		        
		        break;
		        
		        
		        	
	}
		        Thread.sleep(3000);
		}
	}


//------------------Login Find taxpayer------------
 
 @Then("^Goto Find taxpayer$")
 public void goto_Find_taxpayer() throws Throwable
 {	
 	Actions action = new Actions(driver);
 	  WebElement Reg=driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Findtaxpayer_ITEM_XPATH")));
 	  action.doubleClick(Reg).build().perform();
 	  
 	  
  
 }

 
 
 
 
 
 // Transfer Individual taxpayer scenario code

@Then("^Click on regisration link$")
public void click_on_regisration_link() throws Throwable {
   
	
	 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	 Actions action = new Actions(driver);
	  WebElement Reg=driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
	  action.doubleClick(Reg).build().perform();
	  action.click().build().perform();
	  
   
}
@Then("^Goto Manage taxpayer$")
public void goto_Manage_taxpayer() throws Throwable {
	Actions action = new Actions(driver);
	  WebElement manage=driver.findElement(By.xpath(Pro.getProperty("ManageTaxpayer_LINK_XPATH")));
	  action.doubleClick(manage).build().perform();
	  //action.build().perform();
	  
	  
	
}

@Then("^Goto Transfer Taxpayer$")
public void goto_Transfer_Taxpayer() throws Throwable {

	WebElement Reg=driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_XPATH"))); 
	
	  Reg.click();
	  
	  Thread.sleep(5000);
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("window.scrollBy(0,1000)");

}

@Then("^Select Taxpayer Classification Type \"([^\"]*)\"$")
public void select_Taxpayer_Classification_Type(String ClasificationType) throws Throwable {
	 Thread.sleep(2000);
	Actions action = new Actions(driver);
	 
	 WebElement title=driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_XPATH"))); 

	 
	 action.click(title).build().perform();
	 List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
	 for(WebElement option : list)
 	{
		 Thread.sleep(2000);
 	    String text2= option.getText();
 	        if(text2.equalsIgnoreCase(ClasificationType))
 	        	{
 	        	Actions builder = new Actions(driver);
		        builder.moveToElement(option).doubleClick();
		      builder.perform();
		        
}
 	}
}

@Then("^Enter TIN number \"([^\"]*)\"$")
public void enter_TIN_number(String TIN) throws Throwable {
	Thread.sleep(3000);
	driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TIN_ID"))).sendKeys(TIN);		
}

@Then("^Click on search$")
public void click_on_search() throws Throwable {
	driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Individual_Search_ID"))).click();
	
}

@Then("^Select New Office \"([^\"]*)\"$")
public void select_New_Office(String NewOffice) throws Throwable {
	 Actions action = new Actions(driver);
	 Thread.sleep(3000);
		 WebElement title=driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Search_NewOffice_XPATH"))); 

		 
		 action.click(title).build().perform();
		 
		 List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Search_NewOffice_ITEM_XPATH")));
		 
		 for(WebElement option : list)
	 	{
	 	    String text2= option.getText();
	 	        if(text2.equalsIgnoreCase(NewOffice))
	 	        	{
	 	        	Actions builder = new Actions(driver);
	 	        	 Thread.sleep(3000);
			        builder.moveToElement(option).doubleClick();
			        Thread.sleep(3000);
			        
			        builder.perform();
			     break;
			        
	 	        	}
	 	}Thread.sleep(2000);
	
}

@Then("^Select Date of transfer \"([^\"]*)\"$")
public void select_Date_of_transfer(String DateOfTransfer) throws Throwable {
	
	List<WebElement> element2=driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Search_DateOfTransfer_XPATH")));
    
	for (WebElement ele : element2)
{
  
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].scrollIntoView(true);",ele);
			
		}
	driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Search_DateOfTransfer_XPATH"))).sendKeys(DateOfTransfer);
    
  
	
}

@Then("^Select Reason \"([^\"]*)\"$")
public void select_Reason(String Reason) throws Throwable {
	Actions action = new Actions(driver);
	 Thread.sleep(3000);
		 WebElement title=driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Search_Reason_XPATH"))); 
action.click(title).build().perform();
		 
		 List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Search_Reason_ITEM_XPATH")));
		 
		 for(WebElement option : list)
	 	{
	 	    String text2= option.getText();
	 	        if(text2.equalsIgnoreCase(Reason))
	 	        	{
	 	        	Actions builder = new Actions(driver);
	 	        	 Thread.sleep(3000);
			        builder.moveToElement(option).doubleClick();
			        Thread.sleep(3000);
			        
			        builder.perform();
			     break;
			        
	 	        	}
	 	}Thread.sleep(2000);
	
}	
	
	    
	 


@Then("^Click on tarnsfer$")
public void click_on_tarnsfer() throws Throwable {
	
	WebDriverWait RefNumber=new WebDriverWait(driver,100);
	RefNumber.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Search_Transfer_XPATH")))).click();
	Thread.sleep(6000);
}

@Then("^Verify the ARN number \"([^\"]*)\"$")
public void verify_the_ARN_number_ARN(String ARN) throws Throwable {
	
	WebDriverWait RefNumber=new WebDriverWait(driver,60);
	RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID")))).click();
	// Capture ARN Number
	    String text  =driver.findElement(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID"))).getText();
	   
	    System.out.println(text);
	    System.out.println("substring is "+ text.substring(42));
	 String A_BackOffice_ARN=text.substring(42);
	   
	    sharedatastep.A_CRMARN="*"+A_BackOffice_ARN;
   // System.out.println("Actual ARN to be used in CRM is "+"*"+text.substring(42));
	   
    
    System.out.println(sharedatastep.A_CRMARN);
    System.out.println("Actual ARN to be used in CRM is " +sharedatastep.A_CRMARN);
	   
	    if(text.contains(ARN))
	    		{
	    	      //  System.out.println(text);
	    			System.out.println("Text Verified and passed");
	    		}
	             else
	                {
	    	             System.out.println("Text Not Verfied and failed");
	                }      
		
Thread.sleep(20000);
	}
		 



//Re-Register TaxType Individual Scenario

@When("^I enter valid data on the Re-RegisterTaxType Individual page$")
public void i_enter_valid_data_on_the_Re_RegisterTaxType_Individual_page(DataTable ReRegTable) throws Throwable {


	//Initialize data table   
		List<List<String>> data =ReRegTable.raw();
		Actions action = new Actions(driver);
		  WebElement Reg=driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
		  action.doubleClick(Reg).build().perform();
		  Reg.click();
		  WebDriverWait Taxtype=new WebDriverWait(driver,100);
		  Taxtype.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ManageTaxpayer_LINK_XPATH")))).click();

			 List<WebElement> ReRegisterTax = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Context_ITEM_XPATH")));
						 for(WebElement option : ReRegisterTax)
					 	{
					 	    String text2= option.getText();
					 	  // System.out.println(text2);
					 	    
					 	    
					 	        if(text2.equalsIgnoreCase(data.get(0).get(1)))
					 	        	{
					 	        	
					 	        	option.click();
					 	        	break;
					  }
					 	}
		  
		  Thread.sleep(1000);
		  
		 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		  driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_XPATH"))).click();;
			 List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
					 for(WebElement option : list)
				 	{
				 	    String text2= option.getText();
				 	  // System.out.println(text2);
				 	    
				 	    
				 	        if(text2.equalsIgnoreCase(data.get(1).get(1)))
				 	        	{
				 	        	
				 	        	option.click();
				 	        	break;
				  }
				 	}
					 
					 Thread.sleep(1000);
			 driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TIN_ID"))).sendKeys(data.get(2).get(1));
			 Thread.sleep(1000);
			 driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_ID"))).click();
			 Thread.sleep(5000);

			 List<WebElement> element=driver.findElements(By.id(Pro.getProperty("Registration_ManageTaxPayer_Re-RegisterTax_Search_ReregisterTax_ScrollToView_ID")));
				for (WebElement ele : element)
			{
			  
						JavascriptExecutor js1 = (JavascriptExecutor) driver;
						js1.executeScript("arguments[0].scrollIntoView(true);",ele);
						//ele.click();
					}
				
		
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
 WebElement ReturnType=driver.findElement(By.id(Pro.getProperty("Re-RegisterTaxType_Return_ID")));
     action.doubleClick(ReturnType).build().perform();
     Thread.sleep(2000);     
   driver.findElement(By.xpath(Pro.getProperty ("Registration_ManageTaxPayer_Re-RegisterTax_Search_Reason_XPATH"))).click();	
     List<WebElement> ReRegValue = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Re-RegisterTax_Search_Reason_ITEM_XPATH")));
	 for(WebElement option : ReRegValue)
		{
		    String text2= option.getText();
		    System.out.println(option);
		        if(text2.equalsIgnoreCase(data.get(3).get(1)))
		        	{
		        	Actions builder = new Actions(driver);
		       builder.moveToElement(option).doubleClick();
		        builder.perform();
		        
		        break;
}
		          
		        Thread.sleep(2000);   
		        
		}
}
	

@Then("^ReRegister ARN number will generate$")
	 public void reregister_ARN_number_will_generate(DataTable ReRegTable1) throws Throwable {
	
	 
	List<List<String>> data =ReRegTable1.raw();
	WebDriverWait Submit=new WebDriverWait(driver,60);
    Submit.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Registration_ManageTaxPayer_Re-RegisterTax_Search_RegisterBtn_ID")))).click();
Thread.sleep(7000);
WebDriverWait RefNumber=new WebDriverWait(driver,60);
RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID")))).click();
// Capture ARN Number
    String text  =driver.findElement(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID"))).getText();
    System.out.println(text);
    if(text.contains(data.get(0).get(1)))
    		{
    	      //  System.out.println(text);
    			System.out.println("Text Verified and passed");
    		}
             else
                {
    	             System.out.println("Text Not Verfied and failed");
                }      
	

}
	 
//Re-Register TaxType Organization Scenario

@When("^I enter valid data on the Re-RegisterTaxType Organization page$")
public void i_enter_valid_data_on_the_Re_RegisterTaxType_Organization_page(DataTable ReRegTable) throws Throwable {


	//Initialize data table   
		List<List<String>> data =ReRegTable.raw();
		Actions action = new Actions(driver);
		  WebElement Reg=driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
		  action.doubleClick(Reg).build().perform();
		  Reg.click();
		  WebDriverWait Taxtype=new WebDriverWait(driver,100);
		  Taxtype.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ManageTaxpayer_LINK_XPATH")))).click();

			 List<WebElement> ReRegisterTax = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Context_ITEM_XPATH")));
						 for(WebElement option : ReRegisterTax)
					 	{
					 	    String text2= option.getText();
					 	  // System.out.println(text2);
					 	    
					 	    
					 	        if(text2.equalsIgnoreCase(data.get(0).get(1)))
					 	        	{
					 	        	
					 	        	option.click();
					 	        	break;
					  }
					 	}
		  
		  Thread.sleep(1000);
		  
		 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		  driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_XPATH"))).click();;
			 List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
					 for(WebElement option : list)
				 	{
				 	    String text2= option.getText();
				 	  // System.out.println(text2);
				 	    
				 	    
				 	        if(text2.equalsIgnoreCase(data.get(1).get(1)))
				 	        	{
				 	        	
				 	        	option.click();
				 	        	break;
				  }
				 	}
					 
					 Thread.sleep(1000);
			 driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TIN_ID"))).sendKeys(data.get(2).get(1));
			 Thread.sleep(1000);
			 driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_ID"))).click();
			 Thread.sleep(5000);

			 List<WebElement> element=driver.findElements(By.id(Pro.getProperty("Registration_ManageTaxPayer_Re-RegisterTax_Search_ReregisterTax_ScrollToView_ID")));
				for (WebElement ele : element)
			{
			  
						JavascriptExecutor js1 = (JavascriptExecutor) driver;
						js1.executeScript("arguments[0].scrollIntoView(true);",ele);
						//ele.click();
					}
				
		
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
WebElement ReturnType=driver.findElement(By.id(Pro.getProperty("Re-RegisterTaxType_Return_ID")));
   action.doubleClick(ReturnType).build().perform();
   Thread.sleep(2000);     
 driver.findElement(By.xpath(Pro.getProperty ("Registration_ManageTaxPayer_Re-RegisterTax_Search_Reason_XPATH"))).click();	
   List<WebElement> ReRegValue = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Re-RegisterTax_Search_Reason_ITEM_XPATH")));
	 for(WebElement option : ReRegValue)
		{
		    String text2= option.getText();
		    System.out.println(option);
		        if(text2.equalsIgnoreCase(data.get(3).get(1)))
		        	{
		        	Actions builder = new Actions(driver);
		       builder.moveToElement(option).doubleClick();
		        builder.perform();
		        
		        break;
}
		     
		        
		        Thread.sleep(2000);   
		        
		}
}	 
	 
	// Suspend Individual TaxType
@Then("^Goto Suspend TaxType \"([^\"]*)\"$")
public void goto_Suspend_TaxType(String SuspendTaxType) throws Throwable {
	
driver.findElement(By.xpath(Pro.getProperty("SuspendTaxtType_XPATH"))).click();
				
			 	
	
}

/*@Then("^Enter  Suspension Reason$")
public void enter_Suspension_Reason(DataTable SuspendTable) throws Throwable {
	List<List<String>> data =SuspendTable.raw();
	List<WebElement> element2=driver.findElements(By.id(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_ScrollToView_ID")));
	for (WebElement ele : element2)
{
  
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].scrollIntoView(true);",ele);
			
		}
	Actions action = new Actions(driver);
	 WebElement PrimInd=driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_SelectfirstRow_XPATH")));
     action.click(PrimInd).build().perform();
	
    WebElement element1=driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_SuspensionStartDate_XPATH")));
     action.sendKeys(element1, data.get(0).get(1)).build().perform();
     Thread.sleep(2000);
     WebDriverWait waitReason=new WebDriverWait(driver,50);
     waitReason.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_Reason_Lable_Wait_ID"))));

     driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_SuspensionEndDate_XPATH"))).sendKeys(data.get(2).get(1));
     Thread.sleep(2000);)
     driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_Reason_XPATH"))).click();

     List<WebElement> SummarytabValue = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_Reason_ITEM_XPATH")));
			 for(WebElement option : SummarytabValue)
		 	{
		 	    String text2= option.getText();
		 	        if(text2.equalsIgnoreCase(data.get(0).get(1)))
		 	        	{
		 	        	System.out.println(text2);
		 	        	Actions builder = new Actions(driver);
				       builder.moveToElement(option).doubleClick().build().perform();
		 	        	break;
		 	        	}
		 	}
		  Thread.sleep(5000);

/*WebDriverWait RefNumber=new WebDriverWait(driver,100);
RefNumber.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_Suspend_BTN_XPATH")))).click();
Thread.sleep(6000);

}*/
@Then("^Enter Suspension Satrt Date \"([^\"]*)\"$")
public void enter_Suspension_Satrt_Date(String StartDate) throws Throwable {
	List<WebElement> element2=driver.findElements(By.id(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_ScrollToView_ID")));
	for (WebElement ele : element2)
{
  
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].scrollIntoView(true);",ele);
			
		}
	Actions action = new Actions(driver);
	 WebElement PrimInd=driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_SelectfirstRow_XPATH")));
     action.click(PrimInd).build().perform();
	
     WebElement element1=driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_SuspensionStartDate_XPATH")));
     action.sendKeys(element1, StartDate).build().perform();
     Thread.sleep(2000);
}

@Then("^Enter Suspension End Date \"([^\"]*)\"$")
public void enter_Suspension_End_Date(String EndDate,DataTable SuspendTable ) throws Throwable {
	List<List<String>> data =SuspendTable.raw();
	 WebDriverWait waitReason=new WebDriverWait(driver,50);
     waitReason.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_Reason_Lable_Wait_ID"))));

     driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_SuspensionEndDate_XPATH"))).sendKeys(EndDate);
     Thread.sleep(2000);
     driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_Reason_XPATH"))).click();

     List<WebElement> SummarytabValue = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_Reason_ITEM_XPATH")));
			 for(WebElement option : SummarytabValue)
		 	{
		 	    String text2= option.getText();
		 	        if(text2.equalsIgnoreCase(data.get(0).get(1)))
		 	        	{
		 	        	System.out.println(text2);
		 	        	Actions builder = new Actions(driver);
				       builder.moveToElement(option).doubleClick().build().perform();
		 	        	break;
		 	        	}
		 	}
		  Thread.sleep(6000);

WebDriverWait RefNumber=new WebDriverWait(driver,100);
RefNumber.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_Suspend_BTN_XPATH")))).click();
Thread.sleep(6000);
   
    
}









//De-register Individual TaxType



@And("^Select de register Reason \"([^\"]*)\"$")
public void select_de_register_Reason(String Reason) throws Throwable {

	
	Actions action = new Actions(driver);
			 WebElement reason1=driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_Reason_XPATH"))); 
	action.click(reason1).build().perform();
			 
	 List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_Reason_ITEM_XPATH")));
	 
	 for(WebElement option : list)
 	{
 	    String text2= option.getText();
 	    System.out.println(text2);
 	        if(text2.equalsIgnoreCase(Reason))
 	        	{
 	        	System.out.println(Reason);
 	        	Actions builder = new Actions(driver);
 	        	// Thread.sleep(3000);
		        builder.moveToElement(option).doubleClick();
		        Thread.sleep(3000);
		        
		        builder.perform();
		     break;
		        
 	        	}
 	}Thread.sleep(3000);

			 
			   	
			   
	
	
}

@Then("^Goto DeRegister$")
public void goto_DeRegister() throws Throwable {

	WebElement deregister =driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_XPATH")));
	deregister.click();
	Thread.sleep(2000);
    
}

@Then("^CLick on taxtype in grid$")
public void click_on_taxtype_in_grid() throws Throwable {

	
	 List<WebElement> element=driver.findElements(By.id(Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_ScrollToView_ID")));
	    for (WebElement ele : element)
	{
	  
	            JavascriptExecutor js1 = (JavascriptExecutor) driver;
	            js1.executeScript("arguments[0].scrollIntoView(true);",ele);
	            //ele.click();
	        }
		
		Actions action = new Actions(driver);
	WebElement ReturnType=driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_SelectfirstRow_XPATH")));
	//ReturnType.click();
	action.click(ReturnType).build().perform();
	Thread.sleep(2000);
   
}

@Then("^Select EDD \"([^\"]*)\"$")
public void select_EDD(String EDD) throws Throwable {
	 WebElement elementEDD=	driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_EDD_XPATH")));
	  elementEDD.sendKeys(EDD); 	
	  Thread.sleep(2000);
   
}

@Then("^Click on DeRegister button$")
public void click_on_DeRegister_button() throws Throwable {

    WebDriverWait RefNumber=new WebDriverWait(driver,30);
	RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_DeRegister_ID")))).click();
	Thread.sleep(6000);
	
    
}



//Re-Active  Individual TaxType


@Then("^Goto Reactive Taxtype$")
public void goto_Reactive_Taxtype() throws Throwable {
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	WebElement Reactivate=driver.findElement(By.xpath(Pro.getProperty("Re-Activate_XPATH")));
	Reactivate.click();
	Thread.sleep(2000);
	
    
}
@Then("^CLick on Reactivate taxtype in grid$")
public void click_on_Reactivate_taxtype_in_grid() throws Throwable {
	List<WebElement> element=driver.findElements(By.id(Pro.getProperty("Registration_ManageTaxPayer_Re-ActivateTax_ScrollToView_TaxDetails_ID")));
    for (WebElement ele : element)
{
  
            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);",ele);
            //ele.click();
        }
	
	Actions action = new Actions(driver);
WebElement ReturnType=driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Re-ActivateTax_SelectfirstRow_XPATH")));
//ReturnType.click();
action.click(ReturnType).build().perform();
Thread.sleep(2000);

	
}
@Then("^Select Reactivate EDD \"([^\"]*)\"$")
public void select_Reactivate_EDD(String EDD) throws Throwable {
	
	WebElement elementEDD=	driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_Re-ActivateTax_EffectiveDateOfReactivation_ID")));
	  elementEDD.sendKeys(EDD); 	
	  Thread.sleep(2000);
}


@Then("^Select Reactivate Taxtype Reason \"([^\"]*)\"$")
public void select_Reactivate_Taxtype_Reason(String Reason) throws Throwable {
   
	Actions action = new Actions(driver);
	 WebElement reason1=driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Re-ActivateTax_Reason_XPATH"))); 
action.click(reason1).build().perform();
	 
List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Re-ActivateTax_Reason_ITEM_XPATH")));

for(WebElement option : list)
{
String text2= option.getText();
System.out.println(text2);
    if(text2.equalsIgnoreCase(Reason))
    	{
    	System.out.println(Reason);
    	Actions builder = new Actions(driver);
    	// Thread.sleep(3000);
       builder.moveToElement(option).doubleClick();
       Thread.sleep(3000);
       
       builder.perform();
    break;
       
    	}
}Thread.sleep(3000);

}

@Then("^Click on Reactivate button$")
public void click_on_Reactivate_button() throws Throwable {
	
	 WebDriverWait RefNumber=new WebDriverWait(driver,30);
		RefNumber.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Re-ActivateTax_Re-active_Btn_XPATH")))).click();
		Thread.sleep(6000);
 
}
//--------------Taxpayer_Accounting_Module --------BackOffice---------------------
//------Taxpayer_Account_Enquiry_Ind------------

@Then("^Click on TaxPayer Accounting$")
public void click_on_TaxPayer_Accounting() throws Throwable {
	WebDriverWait wait=new WebDriverWait(driver,60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("TaxpayerAccounting_XPATH")))).click();
	
}

@Then("^click on TaxPayer Accounting Enquiry$")
public void click_on_TaxPayer_Accounting_Enquiry() throws Throwable {
	driver.findElement(By.xpath(Pro.getProperty("TaxpayerAccounting_Enquiry_XPATH"))).click();
 
}

@Then("^TaxType Account dropdown$")
public void taxtype_Account_dropdown() throws Throwable {
	 List<WebElement> element=driver.findElements(By.xpath(Pro.getProperty("AccountDetails_ScrollToView_XPATH")));
		for (WebElement ele : element)
	{
	  
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].scrollIntoView(true);",ele);
				
			}
  
}

@Then("^TaxType Account dropdown value \"([^\"]*)\"$")
public void taxtype_Account_dropdown_value(String AccountType) throws Throwable {


	Actions action = new Actions(driver);
  WebElement Type=driver.findElement(By.xpath(Pro.getProperty("TaxTypeAccount_XPATH"))); 
action.doubleClick(Type).build().perform();
Type.click();

List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
for(WebElement option : list)
{
  String text2= option.getText();
      if(text2.equalsIgnoreCase(AccountType))
      	{
      	System.out.println(AccountType);
      	Actions builder = new Actions(driver);
	        builder.moveToElement(option).doubleClick().build().perform();
	        option.click();
	        break;
	       
	       
}
      Thread.sleep(5000);
}}

@Then("^click on select button$")
public void click_on_select_button() throws Throwable {
	driver.findElement(By.id(Pro.getProperty("TaxTypeAccount_Select_ID"))).click();
 
}




//------------Maintain period Formula Configuration scenario-------------
@Then("^Click on Maintain Period Formula Configuration link$")
public void click_on_Maintain_Period_Formula_Configuration_link() throws Throwable {
	driver.findElement(By.xpath(Pro.getProperty("TaxpayerAccounting_Maitain_XPATH"))).click();
  
}

@Then("^Enter Code \"([^\"]*)\" and Description \"([^\"]*)\"$")
public void enter_Code_and_Description(String arg1, String arg2, DataTable table) throws Throwable {
	List<List<String>> data =table.raw();

driver.findElement(By.xpath(Pro.getProperty("Maintain_period_Formula_Config_CreateNew_XPATH"))).click();
	driver.findElement(By.id(Pro.getProperty("Maintain_period_Formula_Config_Code_ID"))).sendKeys(data.get(0).get(1));
	driver.findElement(By.id(Pro.getProperty("Maintain_period_Formula_Config_Description_ID"))).sendKeys(data.get(1).get(1));

 
}

@Then("^Select Filling Frequency \"([^\"]*)\"$")
public void select_Filling_Frequency(String Frequency) throws Throwable {

	WebDriverWait Submit=new WebDriverWait(driver,60);
  Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Filing_Frequency_XPATH")))).click();

	
	List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Filing_Frequency_ITEM_XPATH")));
	for(WebElement option : list)
	{
	    String text2= option.getText();
	        if(text2.equalsIgnoreCase(Frequency))
	        	{
	        	//System.out.println(Frequency);
	        	Actions builder = new Actions(driver);
		        builder.moveToElement(option).doubleClick().build().perform();
		       
		        break;
		       
		       
	}
	        Thread.sleep(7000);
	}
	
	
  
}

@Then("^Select Payment DueIn first value \"([^\"]*)\"$")
public void select_Payment_DueIn_first_value(String PaymentDueIn1) throws Throwable {
	
	WebDriverWait Submit=new WebDriverWait(driver,60);
  Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("PaymentDueIn_First_DropDown_XPATH")))).click();

	
	List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("PaymentDueIn_First_DropDown_ITEM_XPATH")));
	for(WebElement option : list)
	{
	    String text2= option.getText();
	        if(text2.equalsIgnoreCase(PaymentDueIn1))
	        	{
	        	
	        	Actions builder = new Actions(driver);
		        builder.moveToElement(option).doubleClick().build().perform();
		       
		        break;
		       
		       
	}
	        Thread.sleep(2000);
	}
 
}

@Then("^Select Payment DueIn Second value \"([^\"]*)\"$")
public void select_Payment_DueIn_Second_value(String PaymentDueIn2) throws Throwable {
	WebDriverWait Submit=new WebDriverWait(driver,60);
  Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("PaymentDueIn_Second_DropDown_XPATH")))).click();

	
	List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("PaymentDueIn_Second_DropDown_ITEM_XPATH")));
	for(WebElement option : list)
	{
	    String text2= option.getText();
	        if(text2.equalsIgnoreCase(PaymentDueIn2))
	        	{
	        	
	        	Actions builder = new Actions(driver);
		        builder.moveToElement(option).doubleClick().build().perform();
		       
		        break;
		       
		       
	}
	        Thread.sleep(2000);
	}
  
}

@Then("^Select Payment DueIn third dropdown value \"([^\"]*)\" enter \"([^\"]*)\"$")
public void select_Payment_DueIn_third_dropdown_value_enter(String PaymentDueIn3, String Days) throws Throwable {	driver.findElement(By.id(Pro.getProperty("PaymentDueIn_Days_ID"))).sendKeys(Days);
	WebDriverWait Submit=new WebDriverWait(driver,60);
  Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("PaymentDueIn_Third_DropDown_XPATH")))).click();

	
	List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("PaymentDueIn_Third_DropDown__ITEM_XPATH")));
	for(WebElement option : list)
	{
	    String text2= option.getText();
	        if(text2.equalsIgnoreCase(PaymentDueIn3))
	        	{
	        
	        	Actions builder = new Actions(driver);
		        builder.moveToElement(option).doubleClick().build().perform();
		       
		        break;
		       
		       
	}
	        Thread.sleep(2000);
	        
	}
  
}
 


@Then("^Select Return DueIn first value \"([^\"]*)\"$")
public void select_Return_DueIn_first_value(String ReturnDueIn1) throws Throwable {
	WebDriverWait Submit=new WebDriverWait(driver,60);
  Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ReturnDueIn_First_DropDown_XPATH")))).click();

	
	List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("ReturnDueIn_First_DropDown_ITEM_XPATH")));
	for(WebElement option : list)
	{
	    String text2= option.getText();
	        if(text2.equalsIgnoreCase(ReturnDueIn1))
	        	{
	        	
	        	Actions builder = new Actions(driver);
		        builder.moveToElement(option).doubleClick().build().perform();
		       
		        break;
		       
		       
	}
	        Thread.sleep(2000);
	}
 

}

@Then("^Select Return DueIn Second value \"([^\"]*)\"$")
public void select_Return_DueIn_Second_value(String ReturnDueIn2) throws Throwable {
	WebDriverWait Submit=new WebDriverWait(driver,60);
  Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ReturnDueIn_Second_DropDown_XPATH")))).click();

	
	List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("ReturnDueIn_Second_DropDown_ITEM_XPATH")));
	for(WebElement option : list)
	{
	    String text2= option.getText();
	        if(text2.equalsIgnoreCase(ReturnDueIn2))
	        	{
	        	
	        	Actions builder = new Actions(driver);
		        builder.moveToElement(option).doubleClick().build().perform();
		       
		        break;
		       
		       
	}
	        Thread.sleep(2000);
	}
 
 
}


@Then("^Select Return DueIn return third value \"([^\"]*)\" enter \"([^\"]*)\"$")
public void select_Return_DueIn_return_third_value_enter(String ReturnDueIn3, String Days) throws Throwable {
	WebDriverWait Submit=new WebDriverWait(driver,60);
  Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ReturnDueIn_Third_DropDown_XPATH")))).click();

	
	List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("ReturnDueIn_Third_DropDown_ITEM_XPATH")));
	for(WebElement option : list)
	{
	    String text2= option.getText();
	        if(text2.equalsIgnoreCase(ReturnDueIn3))
	        	{
	        	
	        	Actions builder = new Actions(driver);
		        builder.moveToElement(option).doubleClick().build().perform();
		       
		        break;
		       
		       
	}
	        Thread.sleep(2000);
	}
	driver.findElement(By.id(Pro.getProperty("ReturnDueIn_Days_ID"))).sendKeys(Days);
 
}

@Then("^enter cinfiguration effective date \"([^\"]*)\"$")
public void enter_cinfiguration_effective_date(String Date) throws Throwable {
	Thread.sleep(2000);
	driver.findElement(By.id(Pro.getProperty("Maintain_period_Formula_Config_EffectiveDate_ID"))).sendKeys(Date);
  
}

@Then("^click on save$")
public void click_on_save() throws Throwable {
	driver.findElement(By.id(Pro.getProperty("Maintain_period_Formula_Config__Save_ID"))).click();
 
}

@Then("^Gettext Record successfully saved \"([^\"]*)\"$")
public void gettext_Record_successfully_saved(String Record) throws Throwable {
	Thread.sleep(3000);
	 String text  =driver.findElement(By.xpath(Pro.getProperty("Maintain_period_Formula_Config_GetText_XPATH"))).getText();
  
   if(text.contains(Record))
   		{
   	System.out.println(text);
   			System.out.println("Text Verified and passed");
   		}
            else
               {
   	             System.out.println("Text Not Verfied and failed");
                }
           
}

//-----------------------------Maintain period Generation Configuration scenario of TaxPayer Accounting Modle-------------

@Then("^Click on Maintain Period Generation Configuration link$")
public void click_on_Maintain_Period_Generation_Configuration_link() throws Throwable {
	
	WebDriverWait click=new WebDriverWait(driver,60);
	click.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("TaxpayerAccounting_Maitain_Generation_XPATH")))).click();

  
}

@When("^click on Create New button$")
public void click_on_Create_New_button() throws Throwable {
	driver.findElement(By.xpath(Pro.getProperty("Maintain_period_Formula_Config_CreateNew_XPATH"))).click();
	
 
}

@Then("^click on Taxtype \"([^\"]*)\"$")
public void click_on_Taxtype(String Taxtype) throws Throwable {
	WebDriverWait Submit=new WebDriverWait(driver,60);
  Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxType_XPATH")))).click();

	
	List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxType_ITEM_XPATH")));
	for(WebElement option : list)
	{
	    String text2= option.getText();
	        if(text2.equalsIgnoreCase(Taxtype))
	        	{
	        	
	        	Actions builder = new Actions(driver);
		        builder.moveToElement(option).doubleClick().build().perform();
		       
		        break;
		       
		       
	}
	        Thread.sleep(2000);
	}
}

@Then("^click on Tax cycle start month \"([^\"]*)\" day \"([^\"]*)\"$")
public void click_on_Tax_cycle_start_month_day(String month, String day) throws Throwable {
	
	Thread.sleep(2000);
	WebDriverWait Month=new WebDriverWait(driver,80);
	Month.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxCycle_Start_Month_XPATH")))).click();
	
	
	List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxCycle_Start_Month_ITEM_XPATH")));
	for(WebElement option : list)
	{
	    String text2= option.getText();
	        if(text2.equalsIgnoreCase(month))
	        	{
	        	System.out.println(month);
	        	Actions builder = new Actions(driver);
		        builder.moveToElement(option).doubleClick().build().perform();
		       
		        break;
		       
		       
	}
	        Thread.sleep(4000);
	}         
	        
	
	WebDriverWait Day=new WebDriverWait(driver,80);
	        Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxCycle_Start_XPATH")))).click();

	    	
	    	List<WebElement> list1 = driver.findElements(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxCycle_Start_ITEM_XPATH")));
	    	for(WebElement option1 : list1)
	    	{
	    	    String text= option1.getText();
	    	        if(text.equalsIgnoreCase(day))
	    	        	{
	    	        	System.out.println(day);
	    	        	//System.out.println(option1);
	    	        	Actions builder = new Actions(driver);
	    		        builder.moveToElement(option1).doubleClick(option1).build().perform();
	    		        builder.click().build().perform();
	    		        break;
	    	        	}
	    	}   Thread.sleep(2000);
	    	

	}


@Then("^click on return type$")
public void click_on_return_type() throws Throwable {
	Thread.sleep(2000);
	WebDriverWait type = new WebDriverWait(driver,60);
	type.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_ReturnType_XPATH")))).click();
	Thread.sleep(2000);
	//driver.findElement(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_ReturnType_XPATH"))).click();
	Actions action = new Actions(driver);
  action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
  

}
@Then("^click on TaxCycle end month \"([^\"]*)\" day \"([^\"]*)\"$")
public void click_on_TaxCycle_end_month_day(String endmonth, String endday) throws Throwable {
	Thread.sleep(2000);
	WebDriverWait Month=new WebDriverWait(driver,60);
	Month.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxCycle_End_Month_XPATH")))).click();

	
	List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxCycle_End_Month_ITEM_XPATH")));
	for(WebElement option : list)
	{
	    String text2= option.getText();
	        if(text2.equalsIgnoreCase(endmonth))
	        	{
	        	
	        	Actions builder = new Actions(driver);
		        builder.moveToElement(option).doubleClick().build().perform();
		       
		        break;
		       
		       
	}
	        Thread.sleep(2000);
	}    
	        WebDriverWait Day=new WebDriverWait(driver,60);
	        Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxCycle_End_XPATH")))).click();

	    	
	    	List<WebElement> list1 = driver.findElements(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxCycle_End_ITEM_XPATH")));
	    	for(WebElement option1 : list1)
	    	{
	    	    String text= option1.getText();
	    	        if(text.equalsIgnoreCase(endday))
	    	        	{
	    	        	
	    	        	Actions builder = new Actions(driver);
	    		        builder.moveToElement(option1).doubleClick().build().perform();
	    		       
	    		        break;
	    		       
	    		       
	    	}
	    	        Thread.sleep(2000);
}
	}


@Then("^click on frequency \"([^\"]*)\"$")
public void click_on_frequency(String frequency) throws Throwable {
	 WebDriverWait Day=new WebDriverWait(driver,60);
   Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_Frequency_XPATH")))).click();

	
	List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_Frequency_ITEM_XPATH")));
	for(WebElement option : list)
	{
	    String text= option.getText();
	        if(text.equalsIgnoreCase(frequency))
	        	{
	        	System.out.println(frequency);
	        	Actions builder = new Actions(driver);
		        builder.moveToElement(option).doubleClick().build().perform();
		       
		        break;
		       
		       
	}
	        Thread.sleep(2000);
}
}
 


@Then("^click on formula \"([^\"]*)\"$")
public void click_on_formula(String formula) throws Throwable {
	Thread.sleep(2000);
	 WebDriverWait Day=new WebDriverWait(driver,60);
   Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_Formula_XPATH")))).click();

	
	List<WebElement> Form = driver.findElements(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_Formula_ITEM_XPATH")));
	for(WebElement Form1 : Form)
	{
	    String text= Form1.getText();
	        if(text.equalsIgnoreCase(formula))
	        	{
	        	System.out.println(formula);
	        	Actions builder = new Actions(driver);
		        builder.moveToElement(Form1).doubleClick().build().perform();
		       
		        break;
		       
		       
	}
	        Thread.sleep(2000);
}}


@Then("^enter no of period \"([^\"]*)\" and effective date \"([^\"]*)\"$")
public void enter_no_of_period_and_effective_date(String period, String date) throws Throwable {
	driver.findElement(By.id(Pro.getProperty("Maintain_period_Generation_Conf_NoOfperiod_ID"))).sendKeys(period);
	driver.findElement(By.id(Pro.getProperty("Maintain_period_Generation_Conf_EDate_ID"))).sendKeys(date);
  
}
@Then("^click on save button$")
public void click_on_save_button() throws Throwable {
	driver.findElement(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_Save_XPATH"))).click();
  
}

@Then("^Gettext Period Generation Configuration saved successfully\\. \"([^\"]*)\"$")
public void gettext_Period_Generation_Configuration_saved_successfully(String Record) throws Throwable {
	Thread.sleep(3000);
	 String text  =driver.findElement(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_Record_XPATH"))).getText();
 
  if(text.contains(Record))
  		{
  	System.out.println(text);
  			System.out.println("Text Verified and passed");
  		}
           else
              {
  	             System.out.println("Text Not Verfied and failed");
               }
           
}

//---------Find_Allocation Rules Configuration scenario----------
@Then("^Click on Find Allocation Rules Configuration link$")
public void click_on_Find_Allocation_Rules_Configuration_link() throws Throwable {
	WebDriverWait find=new WebDriverWait(driver,60);
	find.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Find_Allocation_Rules_Config_XPATH")))).click();

 
}

@Then("^click on add button twice$")
public void click_on_add_button_twice() throws Throwable {

driver.findElement(By.xpath(Pro.getProperty("Find_Allocation_Rules_Config_Add_XPATH"))).click();
Thread.sleep(3000);

driver.findElement(By.xpath(Pro.getProperty("Find_Allocation_Rules_Config_Add_XPATH"))).click();
WebElement  iframe= driver.findElement(By.tagName("iframe"));
driver.switchTo().frame(iframe);
  
}

@Then("^select allocation method \"([^\"]*)\"$")
public void select_allocation_method(String method) throws Throwable {
	WebDriverWait Day=new WebDriverWait(driver,60);
  Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Find_Allocation_Rules_Config_Allocation_Method_XPATH")))).click();

	
	List<WebElement> Form = driver.findElements(By.xpath(Pro.getProperty("Find_Allocation_Rules_Config_Allocation_Method_ITEM_XPATH")));
	for(WebElement Form1 : Form)
	{
	    String text= Form1.getText();
	        if(text.equalsIgnoreCase(method))
	        	{
	        	System.out.println(method);
	        	Actions builder = new Actions(driver);
		        builder.moveToElement(Form1).doubleClick().build().perform();
		       
		        break;
		       
		       
	}
	        Thread.sleep(2000);
}
 
}

@Then("^enter allocation priority \"([^\"]*)\"$")
public void enter_allocation_priority(String priority) throws Throwable {
	driver.findElement(By.id(Pro.getProperty("Find_Allocation_Rules_Config_Priority_ID"))).sendKeys(priority);
  
}

@Then("^click ok button$")
public void click_ok_button() throws Throwable {
	driver.findElement(By.xpath(Pro.getProperty("Find_Allocation_Rules_Config_Ok_XPATH"))).click();
	driver.switchTo().defaultContent();
}

@Then("^gettext record added \"([^\"]*)\"$")
public void gettext_record_added(String record) throws Throwable {
	Thread.sleep(3000);
	 String text  =driver.findElement(By.xpath(Pro.getProperty("Find_Allocation_Rules_Config_RecordAdded_XPATH"))).getText();

 if(text.contains(record))
 		{
 	System.out.println(text);
 			System.out.println("Text Verified and passed");
 		}
          else
             {
 	             System.out.println("Text Not Verfied and failed");
              }
	
  
}

//--------------Dishonoured Payment Config scenario of Taxpayer Accounting Module---------


@Then("^Click on Dishonoured Payment Configure link$")
public void click_on_Dishonoured_Payment_Configure_link() throws Throwable {
	WebDriverWait Submit=new WebDriverWait(driver,60);
  Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Dishonoured_Payment_Config_XPATH")))).click();

}

@Then("^select payment instrument \"([^\"]*)\"$")
public void select_payment_instrument(String payment ) throws Throwable {
	WebDriverWait Day=new WebDriverWait(driver,60);
  Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Dishonoured_Payment_Config_Payment_XPATH")))).click();

	
	List<WebElement> Form = driver.findElements(By.xpath(Pro.getProperty("Dishonoured_Payment_Config_Payment_ITEM_XPATH")));
	for(WebElement Form1 : Form)
	{
	    String text= Form1.getText();
	        if(text.equalsIgnoreCase(payment))
	        	{
	        	System.out.println(payment);
	        	Actions builder = new Actions(driver);
		        builder.moveToElement(Form1).doubleClick().build().perform();
		       
		        break;
		       
		       
	}
	        Thread.sleep(2000);
}
 
  
}

@Then("^enter entry allowed \"([^\"]*)\"$")
public void enter_entry_allowed(String entry) throws Throwable {
Thread.sleep(1000);
driver.findElement(By.id(Pro.getProperty("Dishonoured_Payment_Config_Entry_ID"))).clear();
driver.findElement(By.id(Pro.getProperty("Dishonoured_Payment_Config_Entry_ID"))).sendKeys(entry);
  
}

@Then("^enter no of days blocked \"([^\"]*)\" and checked \"([^\"]*)\"$")
public void enter_no_of_days_blocked_and_checked(String block, String check) throws Throwable {
	Thread.sleep(1000);
	driver.findElement(By.id(Pro.getProperty("Dishonoured_Payment_Config_DaysBlocked_ID"))).clear();
driver.findElement(By.id(Pro.getProperty("Dishonoured_Payment_Config_DaysBlocked_ID"))).sendKeys(block);


driver.findElement(By.id(Pro.getProperty("Dishonoured_Payment_Config_DaysChecked_ID"))).clear();
driver.findElement(By.id(Pro.getProperty("Dishonoured_Payment_Config_DaysChecked_ID"))).sendKeys(check);   
}

@Then("^gettext Dishonoured Payment Configuration is saved Successfully\\. \"([^\"]*)\"$")
public void gettext_Dishonoured_Payment_Configuration_is_saved_Successfully(String Success) throws Throwable {
	Thread.sleep(2000);
	 String text  =driver.findElement(By.xpath(Pro.getProperty("Dishonoured_Payment_Config_Message_XPATH"))).getText();

if(text.contains(Success))
		{
	System.out.println(text);
			System.out.println("Text Verified and passed");
		}
         else
            {
	             System.out.println("Text Not Verfied and failed");
             }
}
//----------Taxpayer Account Adj Credit scenario of TaxPayer Accounting Modle---------------

@Then("^click on Taxpayer Account Adjustment link$")
public void click_on_Taxpayer_Account_Adjustment_link() throws Throwable {
	WebDriverWait Submit=new WebDriverWait(driver,60);
  Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Taxpayer_Account_Adjustment_XPATH")))).click();
}

@Then("^click on add button$")
public void click_on_add_button() throws Throwable {

driver.findElement(By.xpath(Pro.getProperty("Find_Allocation_Rules_Config_Add_XPATH"))).click();
  
}

@Then("^click on find button$")
public void click_on_find_button() throws Throwable {

driver.findElement(By.xpath(Pro.getProperty("Taxpayer_Account_Adjustment_Find_XPATH"))).click();
WebElement  iframe= driver.findElement(By.tagName("iframe"));
driver.switchTo().frame(iframe);
Thread.sleep(2000);
 
}

@Then("^enter Tin number \"([^\"]*)\" and click search$")
public void enter_Tin_number_and_click_search(String TIN) throws Throwable {
	
	driver.findElement(By.id(Pro.getProperty("Taxpayer_Account_Adjustment_TIN_ID"))).sendKeys(TIN);
driver.findElement(By.xpath(Pro.getProperty("Taxpayer_Account_Adjustment_Search_XPATH"))).click();
  
}

@Then("^scroll down and select row$")
public void scroll_down_and_select_row() throws Throwable {
	Thread.sleep(4000);
	 List<WebElement> element=driver.findElements(By.xpath(Pro.getProperty("Taxpayer_Account_Adjustment_SrollToView_XPATH")));
		for (WebElement ele : element)
	{
	  
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].scrollIntoView(true);",ele);
				//ele.click();
			}
		Thread.sleep(3000);
		
JavascriptExecutor js = (JavascriptExecutor)driver;
js.executeScript("arguments[0].click();", (driver.findElement(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_FirstRow_XPATH")))));

	Thread.sleep(3000);
		
		 
		  

}

@Then("^click on continue button$")
public void click_on_continue_button() throws Throwable {
	
driver.findElement(By.xpath(Pro.getProperty("Taxpayer_Account_Adjustment_Continue_XPATH"))).click();
driver.switchTo().defaultContent();
}

@Then("^select charge type \"([^\"]*)\"$")
public void select_charge_type(String chargetype) throws Throwable {
	Thread.sleep(2000);
	WebDriverWait Day=new WebDriverWait(driver,60);
	  Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_ChargeType_XPATH")))).click();

		
		List<WebElement> Form = driver.findElements(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_ChargeType_ITEM_XPATH")));
		for(WebElement Form1 : Form)
		{
		    String text= Form1.getText();
		        if(text.equalsIgnoreCase(chargetype))
		        	{
		        	System.out.println(chargetype);
		        	Actions builder = new Actions(driver);
			        builder.moveToElement(Form1).doubleClick().build().perform();
			       
			        break;
			       
			       
		}
		        Thread.sleep(2000);
	}
	 
  
}

@Then("^select adjustment type \"([^\"]*)\"$")
public void select_adjustment_type(String adjtype) throws Throwable {
	WebDriverWait Day=new WebDriverWait(driver,60);
	  Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_AdjesmentType_XPATH")))).click();

		
		List<WebElement> Form = driver.findElements(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_AdjesmentType_ITEM_XPATH")));
		for(WebElement Form1 : Form)
		{
		    String text= Form1.getText();
		        if(text.equalsIgnoreCase(adjtype))
		        	{
		        	System.out.println(adjtype);
		        	Actions builder = new Actions(driver);
			        builder.moveToElement(Form1).doubleClick().build().perform();
			       
			        break;
			       
			       
		}
		        Thread.sleep(2000);
	}
	 
  
}

@Then("^give reason value \"([^\"]*)\"$")
public void give_reason_value(String reason) throws Throwable {
	
	 List<WebElement> element=driver.findElements(By.id(Pro.getProperty("Taxpayer_Account_Adj_Reason_ScrollToView_Reason_ID")));
		for (WebElement ele : element)
	{
	  
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].scrollIntoView(true);",ele);
				//ele.click();
			}
		Thread.sleep(3000);
	
	WebDriverWait Day=new WebDriverWait(driver,60);
	  Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_Reason_XPATH")))).click();

		
		List<WebElement> Form = driver.findElements(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_Reason_ITEM_XPATH")));
		for(WebElement Form1 : Form)
		{
		    String text= Form1.getText();
		        if(text.equalsIgnoreCase(reason))
		        	{
		        	System.out.println(reason);
		        	Actions builder = new Actions(driver);
			        builder.moveToElement(Form1).doubleClick().build().perform();
			       
			        break;
			       
			       
		}
		        Thread.sleep(2000);
	}
	 
  
}

@Then("^enter revenue ledger code \"([^\"]*)\" and amount \"([^\"]*)\"$")
public void enter_revenue_ledger_code_and_amount(String code, String amount) throws Throwable {
	driver.findElement(By.id(Pro.getProperty("Taxpayer_Account_Adj_Revenue_LedgerCode_ID"))).sendKeys(code);
	driver.findElement(By.id(Pro.getProperty("Taxpayer_Account_Adj_Amount_ID"))).sendKeys(amount);
}

@Then("^click on submit button$")
public void click_on_submit_button() throws Throwable {
	Thread.sleep(1000);
	driver.findElement(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_Submit_XPATH"))).click();
 
}
@Then("^Credit reference number will generate$")
public void credit_reference_number_will_generate() throws Throwable {
	Thread.sleep(2000);
	 String text  =driver.findElement(By.xpath(Pro.getProperty("Taxpayer_Account_Adjustment_Gettext_XPATH"))).getText();
	 System.out.println(text);
	    System.out.println("substring is "+ text.substring(42));
	 String A_BackOffice_ARN=text.substring(42);
	   
	    sharedatastep.A_CRMARN="*"+A_BackOffice_ARN;
   // System.out.println("Actual ARN to be used in CRM is "+"*"+text.substring(42));
	   
    
    System.out.println(sharedatastep.A_CRMARN);
    System.out.println("Actual ARN to be used in CRM is " +sharedatastep.A_CRMARN);
if(text.contains("ACAD"))
		{
	System.out.println(text);
			System.out.println("Text Verified and passed");
		}
        else
           {
	             System.out.println("Text Not Verfied and failed");
            }
Thread.sleep(20000);
}
    //----------Manage Credit Allocation scenario of TaxPayer Accounting Modle-----

@Then("^click on Manage Credit Allocation link$")
public void click_on_Manage_Credit_Allocation_link() throws Throwable {
	WebDriverWait wait=new WebDriverWait(driver,60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Manage_Credit_Allocation_XPATH")))).click();

   
}

@Then("^Click on search button$")
public void click_on_search_button() throws Throwable {
	driver.findElement(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_Search_ID"))).click();
}

@Then("^select taxType \"([^\"]*)\"$")
public void select_taxType(String Taxtype) throws Throwable {
	driver.switchTo().defaultContent();
	Thread.sleep(4000);
	WebDriverWait Submit=new WebDriverWait(driver,60);
	  Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Manage_Credit_Allocation_TaxType_XPATH")))).click();

		
		List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Manage_Credit_Allocation_TaxType_ITEM_XPATH")));
		for(WebElement option : list)
		{
		    String text2= option.getText();
		        if(text2.equalsIgnoreCase(Taxtype))
		        	{
		        	
		        	Actions builder = new Actions(driver);
			        builder.moveToElement(option).doubleClick().build().perform();
			       
			        break;
			       
			       
		}
		        Thread.sleep(2000);
		}
	
   
}

@Then("^check the suspense checkbox$")
public void check_the_suspense_checkbox() throws Throwable {
	Thread.sleep(2000);
	driver.findElement(By.xpath(Pro.getProperty("Manage_Credit_Allocation_Suspense_XPATH"))).click();
	Thread.sleep(2000);
	driver.findElement(By.id(Pro.getProperty("Manage_Credit_Allocation_Suspense_Select_ID"))).click();
    
}

@Then("^enter month \"([^\"]*)\" and year \"([^\"]*)\"$")
public void enter_month_and_year(String month, String year) throws Throwable {
	driver.findElement(By.id(Pro.getProperty("Manage_Credit_All_Liability_TaxType_PeriodMonth_ID"))).sendKeys(month);
	driver.findElement(By.id(Pro.getProperty("Manage_Credit_All_Liability_TaxType_PeriodYear_ID"))).sendKeys(year);
}

@Then("^select document type \"([^\"]*)\"$")
public void select_document_type(String Documenttype) throws Throwable {
	
	//Thread.sleep(1000);
	WebDriverWait Submit=new WebDriverWait(driver,60);
	  Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Manage_Credit_All_DocumentType_XPATH")))).click();

		
		List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Manage_Credit_All_DocumentType_ITEM_XPATH")));
		for(WebElement option : list)
		{
		    String text2= option.getText();
		        if(text2.equalsIgnoreCase(Documenttype))
		        	{
		        	
		        	Actions builder = new Actions(driver);
			        builder.moveToElement(option).doubleClick().build().perform();
			       
			        break;
			       
			       
		}
		        Thread.sleep(2000);
		}
	
		
}

@Then("^click on Search button for selected account$")
public void click_on_Search_button_for_selected_account() throws Throwable {
	driver.findElement(By.xpath(Pro.getProperty("Taxpayer_Account_Adjustment_Search_XPATH"))).click();
	driver.switchTo().defaultContent();
	Thread.sleep(2000);
   
}

@Then("^Click on Outstanding Liability Taxtype$")
public void click_on_Outstanding_Liability_Taxtype() throws Throwable {
	driver.findElement(By.xpath(Pro.getProperty("Manage_Credit_All_Liability_TaxType_XPATH"))).click();
	Thread.sleep(2000);
	driver.findElement(By.id(Pro.getProperty("Manage_Credit_All_Liability_TaxType_Select_ID"))).click();
    Thread.sleep(2000);
}






//-----------Dishonoured Payment  scenario of Taxpayer Accounting Module--------


//----------- Integrated Taxpayer View Individual Scenario of Taxpayer Accounting Module-----------
@Then("^click on Integrated Taxpayer View$")
public void click_on_Integrated_Taxpayer_View() throws Throwable {
	driver.findElement(By.xpath(Pro.getProperty("Integrated_Taxpayer_View_XPATH"))).click();
   
}

@Then("^TaxType dropdown value \"([^\"]*)\"$")
public void taxtype_dropdown_value(String AccountType) throws Throwable {
Thread.sleep(2000);

	Actions action = new Actions(driver);
  WebElement Type=driver.findElement(By.xpath(Pro.getProperty("TaxTypeAccount_XPATH"))); 
action.doubleClick(Type).build().perform();
List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Integrated_Taxpayer_View_DocumentType_ITEM_XPATH")));
for(WebElement option : list)
{
  String text2= option.getText();
      if(text2.equalsIgnoreCase(AccountType))
      	{
      	System.out.println(AccountType);
      	Actions builder = new Actions(driver);
	        builder.moveToElement(option).doubleClick().build().perform();

	        break;
	       
	       
}
      Thread.sleep(2000);
      

}}
@Then("^enter from date \"([^\"]*)\"$")
public void enter_from_date(String date) throws Throwable {
	WebElement Date=driver.findElement(By.id(Pro.getProperty("Integrated_Taxpayer_View_Fromdate_ID")));
		Date.clear();
		System.out.println(date);
	Date.sendKeys(date);
	Thread.sleep(2000);
}

@Then("^Activity dropdown value \"([^\"]*)\"$")
public void activity_dropdown_value(String AccountType) throws Throwable {
Thread.sleep(2000);

	Actions action = new Actions(driver);
  WebElement Type=driver.findElement(By.xpath(Pro.getProperty("Integrated_Taxpayer_View_Activity_XPATH"))); 
action.doubleClick(Type).build().perform();
Type.click();

List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Integrated_Taxpayer_View_Activity_ITEM_XPATH")));
for(WebElement option : list)
{
  String text2= option.getText();
      if(text2.equalsIgnoreCase(AccountType))
      	{
      	System.out.println(AccountType);
      	Actions builder = new Actions(driver);
	        builder.moveToElement(option).doubleClick().build().perform();

	        break;
	       
	       
}
      Thread.sleep(3000);
}}
@Then("^click on select button to view$")
public void click_on_select_button_to_view() throws Throwable {
	driver.findElement(By.id(Pro.getProperty("Integrated_Taxpayer_View_Select_ID"))).click();
 
}























//------CRM Scripts------------
//----------Individual_Taxpayer_Approval Scenario------


@Given("^Open CRM URL$")
public void open_CRM_URL() throws Throwable {
	driver = SeleniumDriver.getDriver();
	SeleniumDriver.getDriver().get(Pro.getProperty("MRA_crm_url_Registration"));
	 
}
@Then("^Open CRM URL for Accounting Module$")
public void open_CRM_URL_for_Accounting_Module() throws Throwable {
	driver = SeleniumDriver.getDriver();
	driver.get(Pro.getProperty("CRM_NRA_Accounting_Module_URL"));
}



@When("^Close Popup Window$")
public void close_Popup_Window() throws Throwable {
	
	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	WebElement  specificframe= (driver.findElement(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame__ID"))));
	  driver.switchTo().frame(specificframe);
	WebDriverWait CloseWindow=new WebDriverWait(driver,60);
	CloseWindow.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame_Close_ID")))).click();


 
}

@When("^Click on Case management Sub module$")
public void click_on_Case_management_Sub_module() throws Throwable {
	Actions action=new Actions(driver);
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	WebElement CaseManagement=driver.findElement(By.xpath(Pro.getProperty("CaseManagement_ContextLink_XPATH")));
	action.click(CaseManagement).build().perform();
	

}

@When("^Goto Queues$")
public void goto_Queues() throws Throwable {
	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_XPTH"))).click();
	

}

@Then("^switch to frame$")
public void switch_to_frame() throws Throwable {
	driver.switchTo().defaultContent();
	WebElement  specificframe= (driver.findElement(By.id(Pro.getProperty("NextStage_Frame_ID"))));
	  driver.switchTo().frame(specificframe);
	  Thread.sleep(3000);
	 
}

@Then("^Select SearchResult Dropdown element$")
public void select_SearchResult_Dropdown_element() throws Throwable {
	
	//WebDriverWait wait1=new WebDriverWait(driver,60);
	//wait1.until(ExpectedConditions.elementToBeClickable
	Actions action=new Actions(driver);
	WebElement SearchResult=driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_SearchResult_XPATH")));
	action.moveToElement(SearchResult).doubleClick().build().perform();
	
	
driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_SearchResult_ITEMS_XPATH"))).click();
 
 
}

@Then("^Selecte Queues Dropdown element$")
public void selecte_Queues_Dropdown_element() throws Throwable {
	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_Dropdown_XPATH"))).click();
	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_Dropdown_XPATH"))).click();
	Thread.sleep(2000);
 
}


/*@Then("^Enter the Reference number \"([^\"]*)\" in search box\\.$")
public void enter_the_Reference_number_in_search_box(String ReferenceNumber) throws Throwable {
driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_SearchBox_XPATH"))).sendKeys(ReferenceNumber);

}*/

@Then("^Enter the Reference number$")
public void enter_the_Reference_number() throws Throwable {
	System.out.println("CRMARN is " + sharedatastep.A_CRMARN);
driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_SearchBox_XPATH"))).sendKeys(sharedatastep.A_CRMARN);

}


@Then("^Click on Serch icon$")
public void click_on_Serch_icon() throws Throwable {
	Thread.sleep(1000);
	Actions action=new Actions(driver);
	 WebElement SerachIcon=driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_Search_Icon_XPATH")));
  action.click(SerachIcon).build().perform();

}

@Then("^Click selected Reference Number$")
public void click_selected_Reference_Number() throws Throwable {
	driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_Select_ReffNo_XPATH"))).click();
	driver.switchTo().defaultContent();
	Thread.sleep(2000);

}

@Then("^Pick the selected Reference number$")
public void pick_the_selected_Reference_number() throws Throwable {
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	Actions action=new Actions(driver);
	WebElement option=driver.findElement(By.xpath(Pro.getProperty("CaseManagement_OpenOptions_Link_XPATH")));
	action.doubleClick(option).build().perform();
	option.click();
	Thread.sleep(3000);
 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_PICK_Link_XPATH"))).click();
	Thread.sleep(5000);
	
	
}


@Then("^Goto Dashboard$")
public void goto_Dashboard() throws Throwable {
	
	Thread.sleep(1000);
	WebElement Case=driver.findElement(By.xpath(Pro.getProperty("CaseManagement_ContextLink_XPATH")));
	Case.click();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//Actions action=new Actions(driver);
	driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Dashboard_Link_XPATH"))).click();
	Thread.sleep(2000);
	WebElement  specificframe= (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
	  driver.switchTo().frame(specificframe);
	  WebElement  specificframe1= (driver.findElement(By.id(Pro.getProperty("Search_TextBox_frame_ID"))));
	  driver.switchTo().frame(specificframe1);
	  Thread.sleep(2000);
	  

}

@Then("^Enter Reference number in search box$")
public void enter_Reference_number_in_search_box() throws Throwable {
	
	driver.findElement(By.xpath(Pro.getProperty("Search_Textbox_XPATH"))).sendKeys(sharedatastep.A_CRMARN);
	Thread.sleep(3000);
	

}

@Then("^Click Dashboard serch icon$")
public void click_Dashboard_serch_icon() throws Throwable {
	Actions action=new Actions(driver);
	WebElement SearchResult=driver.findElement(By.id(Pro.getProperty("DashBoard_Search_Icon_ID")));
	action.moveToElement(SearchResult).doubleClick().build().perform();
	Thread.sleep(2000);
 
}

@Then("^Click on appeard Reference number$")
public void click_on_appeard_Reference_number() throws Throwable {
	
	Actions action=new Actions(driver);
	WebElement SelectRef=driver.findElement(By.className(Pro.getProperty("appeared_Reference_number_CLASSNAME")));
	action.moveToElement(SelectRef).doubleClick().build().perform();
	Thread.sleep(2000);
	driver.switchTo().defaultContent();
	Thread.sleep(5000);
 
}

@Then("^Click on Open Case Record link$")
public void click_on_Open_Case_Record_link() throws Throwable {
	
	driver.findElement(By.xpath(Pro.getProperty("DashBoard_SelectRefNo_OpenCase_XPATH"))).click();
driver.findElement(By.xpath(Pro.getProperty("DashBoard_SelectRefNo_OpenApplication_XPATH"))).click();
Thread.sleep(3000);
WebElement  specificframe2= (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
driver.switchTo().frame(specificframe2);
Thread.sleep(4000);


}

@Then("^Click on NextStage button$")
public void click_on_NextStage_button() throws Throwable {
	

driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
Thread.sleep(8000);
WebElement  specificframe2= (driver.findElement(By.id(Pro.getProperty("Individual_NextStage_RefNum_DownloadFrame_ID"))));
driver.switchTo().frame(specificframe2);
 
}

@Then("^Goto view AttachmentDetails screen$")
public void goto_view_AttachmentDetails_screen() throws Throwable {
	
	 List<WebElement> element=driver.findElements(By.xpath(Pro.getProperty("Individual_NextStage_RefNum_DownloadFrame_DownloadLink_XPATH")));
		for (WebElement ele : element)
	{
	  
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].scrollIntoView(true);",ele);
				
			}
		Thread.sleep(2000);

}

@Then("^Download the Attachment$")
public void download_the_Attachment() throws Throwable {
	Actions action=new Actions(driver);
	WebElement PrimInd=driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_RefNum_DownloadFrame_DownloadLink_XPATH")));
 action.click(PrimInd).build().perform();
 Thread.sleep(8000);
 driver.switchTo().defaultContent();
 WebElement  specificframe2= (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
 driver.switchTo().frame(specificframe2);

}

@Then("^Select Identification Outcome dropdown value for Individual Taxpayer Approval$")
public void select_Identification_Outcome_dropdown_value_for_Individual_Taxpayer_Approval() throws Throwable {

driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_RefNum_Identification_OutCome_XPATH"))).click();
Actions action = new Actions(driver);
action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();  
}

@Then("^Select Approval outcome dropdown value to Approve\"([^\"]*)\"$")
public void select_Approval_outcome_dropdown_value_to_Approve(String Approve) throws Throwable {
	//Thread.sleep(7000);
	  driver.switchTo().defaultContent();
	    WebElement  specificframe2= (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
	    driver.switchTo().frame(specificframe2);

driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
Thread.sleep(3000);
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

Actions action=new Actions(driver);
WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Individual_NextStage_RefNum_Approval_OutCome_ID")));
action.doubleClick(Outcome).build().perform();
Outcome.click();
WebDriverWait wait=new WebDriverWait(driver,50);
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("Individual_Quese_Approval_OutCome_Revision_Select_ID"))));
WebElement  dropdownElement = (driver.findElement(By.id(Pro.getProperty("Individual_Quese_Approval_OutCome_Revision_Select_ID"))));
Select dropdown = new Select(dropdownElement);
dropdown.selectByVisibleText(Approve);

}


@Then("^Click on Save button$")
public void click_on_Save_button() throws Throwable {
	driver.switchTo().defaultContent();
	WebElement  specificframe3= (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
	driver.switchTo().frame(specificframe3);
	
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.findElement(By.id(Pro.getProperty("NextStage_RefNum_Save_Btn_ID"))).click();
Thread.sleep(3000);
WebDriverWait wait1=new WebDriverWait(driver,50);
wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("NextStage_RefNum_Save_ReadOnly_Text_ID"))));



}

@Then("^Verify the String \"([^\"]*)\"$")
public void verify_the_String(String saving) throws Throwable {
	
	String text  =driver.findElement(By.id(Pro.getProperty("NextStage_RefNum_Save_ReadOnly_Text_ID"))).getText();
 System.out.println(text);
 if(text.contains(saving))
 		{
 	//System.out.println(text);
 			System.out.println("Text Verified and passed");
 		}
          else
             {
 	             System.out.println("Text Not Verfied and failed");
              }
          Thread.sleep(2000);
 
}

@Then("^Click on Taxpayer name$")
public void click_on_Taxpayer_name() throws Throwable {
	
	List<WebElement> element=driver.findElements(By.id(Pro.getProperty("Save_Taxpayer_Taxpayer_ScrollToView_ID")));
	for (WebElement ele : element)
	{

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].scrollIntoView(true);",ele);
			
		}
	Thread.sleep(3000);
	
	driver.switchTo().defaultContent();
	WebElement  specificframe4= (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
	driver.switchTo().frame(specificframe4);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.id(Pro.getProperty("Taxpayer_ID"))).click();

	Thread.sleep(8000);

	    

}

@Then("^Get the TIN Number$")
public void get_the_TIN_Number() throws Throwable {
	String text  =driver.findElement(By.id(Pro.getProperty("Save_Taxpayer_Gettext_ID"))).getText();
 System.out.println(text);

}
//-------------CRM------------
//--------------- Code for Individual Taxpayer Reject scenario--------------

@Then("^Select Identification Outcome dropdown value for Individual Taxpayer Reject$")
public void select_Identification_Outcome_dropdown_value_for_Individual_Taxpayer_Reject() throws Throwable {


driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_RefNum_Identification_OutCome_XPATH"))).click();
Actions action = new Actions(driver);
action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

	
}
@Then("^Select Approval outcome dropdown value to Reject\"([^\"]*)\"$")
public void select_Approval_outcome_dropdown_value_to_Reject(String Reject) throws Throwable {
	Thread.sleep(7000);
	  driver.switchTo().defaultContent();
	    WebElement  specificframe2= (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
	    driver.switchTo().frame(specificframe2);
	  
//driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
Thread.sleep(3000);
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

Actions action=new Actions(driver);
WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Individual_Quese_Approval_Outcome_ID")));
action.doubleClick(Outcome).build().perform();
Outcome.click();

action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();



}



@Then("^Enter Outcome Notes \"([^\"]*)\"$")
public void enter_Outcome_Notes(String Notes) throws Throwable {
	Thread.sleep(3000);
	Actions action1 = new Actions(driver);
 WebElement element1=driver.findElement(By.id((Pro.getProperty("Individual_NextStage_RefNum_Reject_OutComeNotes_ID"))));
 action1.sendKeys(element1, Notes).build().perform();
 Thread.sleep(5000);

 

}

@Then("^Enter Outcome Reason$")
public void enter_Outcome_Reason() throws Throwable {
	
	 WebElement  specificframe= (driver.findElement(By.xpath(Pro.getProperty("OutComeReason_Frame_XPATH"))));
		driver.switchTo().frame(specificframe);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.findElement(By.xpath(Pro.getProperty("NextStage_RefNum_Reject_OutComeReason_XPATH"))).click();
	WebDriverWait ReasonValue=new WebDriverWait(driver,60);
 ReasonValue.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("NextStage_RefNum_Reject_OutComeReason_Options_XPATH")))).click();
Thread.sleep(8000);
}
@Then("^Get Status of Taxpayer \"([^\"]*)\"$")
public void get_Status_of_Taxpayer(String Status) throws Throwable {
	String text=driver.findElement(By.id(Pro.getProperty("Status_Lable_ID"))).getText();
 System.out.println(text);
 if(text.contains(Status))
	{

		System.out.println("Text Verified and passed");
	}
  else
     {
          System.out.println("Text Not Verfied and failed");
      }
  Thread.sleep(2000);
}

//Revise Individual Taxpayer Scenario.....
@Then("^Select Approval outcome dropdown value to Revise\"([^\"]*)\"$")
public void select_Approval_outcome_dropdown_value_to_Revise(String Revise) throws Throwable {
	Thread.sleep(7000);
	  driver.switchTo().defaultContent();
	    WebElement  specificframe2= (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
	    driver.switchTo().frame(specificframe2);
	  
//driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
Thread.sleep(3000);
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

Actions action=new Actions(driver);
WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Individual_Quese_Approval_Outcome_ID")));
action.doubleClick(Outcome).build().perform();
Outcome.click();
action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();



}

//----------------- Approve Organization Taxpayer Scenario------------

@Then("^Click on Orgnization NextStage button$")
public void click_on_Orgnization_NextStage_button() throws Throwable {
	driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
	Thread.sleep(1000);
	driver.findElement(By.id(Pro.getProperty("Organisation_NextStage_Btn_ID"))).click();
	Thread.sleep(7000);
	
 
}

@Then("^Goto view Organization AttachmentDetails screen$")
public void goto_view_Organization_AttachmentDetails_screen() throws Throwable {
	Thread.sleep(2000);
	WebElement  specificframe= (driver.findElement(By.id(Pro.getProperty("Organisation_NextStage_RefNum_DownloadFrame_ID"))));
 driver.switchTo().frame(specificframe);
	
	 List<WebElement> element=driver.findElements(By.xpath(Pro.getProperty("Organisation_NextStage_RefNum_DownloadFrame_DownloadLink_XPATH")));
		for (WebElement ele : element)
	{
	  
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].scrollIntoView(true);",ele);
				
			}
		Thread.sleep(2000);
	
 
}

@Then("^Download Organization Attachments$")
public void download_Organization_Attachments() throws Throwable {
	Actions action=new Actions(driver);
	WebElement PrimInd=driver.findElement(By.xpath(Pro.getProperty("Organisation_NextStage_RefNum_DownloadFrame_DownloadLink_XPATH")));
 action.click(PrimInd).build().perform();
 Thread.sleep(5000);
 
 
}


@Then("^Select Organization Approval outcome dropdown value$")
public void select_Organization_Approval_outcome_dropdown_value_to_Approve() throws Throwable {
	driver.switchTo().defaultContent();
 WebElement  specificframe2= (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
 driver.switchTo().frame(specificframe2);


Thread.sleep(3000);
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

Actions action=new Actions(driver);
WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Organization_Approval_ID")));
action.doubleClick(Outcome).build().perform();
Outcome.click();
action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();


 
	
}

//------Organization Taxpayer Reject Scenario----

@Then("^Select Organization Approval outcome dropdown value to reject$")
public void select_Organization_Approval_outcome_dropdown_value_to_reject() throws Throwable {
	driver.switchTo().defaultContent();
 WebElement  specificframe2= (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
 driver.switchTo().frame(specificframe2);


Thread.sleep(3000);
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

Actions action=new Actions(driver);
WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Organization_Approval_ID")));
action.doubleClick(Outcome).build().perform();
Outcome.click();
action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
}

//----- Organization Taxpayer Revision Scenario-----
@Then("^Select Organization Approval outcome dropdown value to Revision$")
public void select_Organization_Approval_outcome_dropdown_value_to_Revision() throws Throwable {
	driver.switchTo().defaultContent();
 WebElement  specificframe2= (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
 driver.switchTo().frame(specificframe2);


Thread.sleep(3000);
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

Actions action=new Actions(driver);
WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Organization_Approval_ID")));
action.doubleClick(Outcome).build().perform();
Outcome.click();
action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
}

	
//-----Update Individual Taxpayer Approve------------
@Then("^Click on NextStage button two times$")
public void click_on_NextStage_button_two_times() throws Throwable {
	driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
}
@Then("^Select Approval outcome dropdown value to Approve for Update Ind$")
public void select_Approval_outcome_dropdown_value_to_Approve_for_Update_Ind() throws Throwable {

Thread.sleep(7000);
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

Actions action=new Actions(driver);
WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Individual_Update_Approve_ID")));
action.doubleClick(Outcome).build().perform();
Outcome.click();
action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
Thread.sleep(1000);



}
//-----Update Individual Taxpayer Reject------------
@Then("^Select Approval outcome dropdown value to Reject for Update Ind$")
public void select_Approval_outcome_dropdown_value_to_Reject_for_Update_Ind() throws Throwable {

Thread.sleep(7000);
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

Actions action=new Actions(driver);
WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Individual_Update_Approve_ID")));
action.doubleClick(Outcome).build().perform();
Outcome.click();
action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
Thread.sleep(1000);



}
//--- Register TaxType Individual Taxpayer Approve Scenario--------------
@Then("^Select Approval outcome dropdown value to Approve for Register TaxType Ind$")
public void select_Approval_outcome_dropdown_value_to_Approve_for_Register_TaxType_Ind() throws Throwable {

Thread.sleep(7000);
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

Actions action=new Actions(driver);
WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Organisation_Quese_Approval_OutCome_ID")));
action.doubleClick(Outcome).build().perform();
Outcome.click();
action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
Thread.sleep(1000);


}

@Then("^Click on NextStage button one time$")
public void click_on_NextStage_button_one_time() throws Throwable {
	

driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
Thread.sleep(8000);

 
}

//------Register TaxType Individual Taxpayer  Reject Scenario------
@Then("^Select Approval outcome dropdown value to Reject for Register TaxType Ind$")
public void select_Approval_outcome_dropdown_value_to_Reject_for_Register_TaxType_Ind() throws Throwable {

Thread.sleep(7000);
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

Actions action=new Actions(driver);
WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Organisation_Quese_Approval_OutCome_ID")));
action.doubleClick(Outcome).build().perform();
Outcome.click();
action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
Thread.sleep(1000);


}



//--------Taxpayer Account Adj Credit App Individual Taxpayer Approval Scenario------------
@Then("^Select Approval outcome dropdown value to Approve for Taxpayer accounting$")
public void select_Approval_outcome_dropdown_value_to_Approve_for_Taxpayer_accounting() throws Throwable {

Thread.sleep(7000);
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

Actions action=new Actions(driver);
WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Taxpayer_Accounting_Approval_Outcome_ID")));
action.doubleClick(Outcome).build().perform();
Outcome.click();
action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
Thread.sleep(1000);


}


//--------Taxpayer Account Adj Credit App Individual Taxpayer Reject Scenario------------
@Then("^Select Approval outcome dropdown value to Reject for Taxpayer accounting$")
public void select_Approval_outcome_dropdown_value_to_Reject_for_Taxpayer_accounting() throws Throwable {

Thread.sleep(7000);
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

Actions action=new Actions(driver);
WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Taxpayer_Accounting_Approval_Outcome_ID")));
action.doubleClick(Outcome).build().perform();
Outcome.click();
action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
Thread.sleep(1000);


}
@Then("^Enter Outcome Reason for Taxpayer accounting$")
public void enter_Outcome_Reason_for_Taxpayer_accounting() throws Throwable {
	
	 WebElement  specificframe= (driver.findElement(By.xpath(Pro.getProperty("OutComeReason_Accounting_Frame_XPATH"))));
		driver.switchTo().frame(specificframe);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.findElement(By.xpath(Pro.getProperty("NextStage_RefNum_Reject_OutComeReason_XPATH"))).click();
	WebDriverWait ReasonValue=new WebDriverWait(driver,60);
 ReasonValue.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("NextStage_RefNum_Reject_OutComeReason_Options_XPATH")))).click();
Thread.sleep(8000);
}

@Then("^Pick the selected Reference number for Accounting Module$")
public void pick_the_selected_Reference_number_for_Accounting_Module() throws Throwable {
	Thread.sleep(3000);
 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_PICK_Link_XPATH"))).click();
	Thread.sleep(5000);

}
@Then("^Get Status of Taxpayer \"([^\"]*)\" as Rejected$")
public void get_Status_of_Taxpayer_as_Rejected(String Status) throws Throwable {
	Thread.sleep(3000);
	String text=driver.findElement(By.xpath(Pro.getProperty("Reject_Status_XPATH"))).getText();
 System.out.println(text);
 if(text.contains(Status))
	{

		System.out.println("Text Verified and passed");
	}
  else
     {
          System.out.println("Text Not Verfied and failed");
      }
  Thread.sleep(2000);
 
}











}












































