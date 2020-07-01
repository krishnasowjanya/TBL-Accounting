package RunnerPackage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.testng.annotations.BeforeClass;

import com.cucumber.listener.Reporter;

//import com.cucumber.listener.ExtentCucumberFormatter;
//import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//import com.cucumber.listener.ExtentCucumberFormatter;




@RunWith(Cucumber.class)
@CucumberOptions(
        
      
        		features = {"src/test/java/crmApplicationScenarios/Approve_Organization_Taxpayer.feature"},
        		glue = {"GluecodePackage"},    
        		plugin = {"json:target/positive/cucumber.json", "pretty", "html:target/positive/cucumber.html","com.cucumber.listener.ExtentCucumberFormatter:target/report.html"},
       /*plugin= {"pretty", "html:target/cucumber-reports/cucumber-pretty",
               "json:target/cucumber-reports/CucumberTestReport.json",
               "rerun:target/cucumber-reports/rerun.txt"}
    		   features ={"src/test/java/crmApplicationScenarios/UpdateTaxpayer_Indidual_Approve.feature"},
       		glue = {"crmGluecodePackage"},*/
        strict = false,
        dryRun = false,
    monochrome = true)


public class Runnerclass{	
	@AfterClass
	public static void extent()
	{
		Reporter.loadXMLConfig(new File("/src/test/resources/extent-config.xml"));
	}
	/*@BeforeClass public static void setup() { 
		  // Initiates the extent report and generates the output in the output/Run_<unique timestamp>/report.html file bydefault. 
	  SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy_hh.mm.ss");
	  Date curDate = new Date(); 
	  String strDate = sdf.format(curDate); 
	  String fileName = System.getProperty("user.dir")+"\\target\\Extent_Reports\\" + strDate+".html";
	  File newFile = new File(fileName);
	  ExtentCucumberFormatter.initiateExtentCucumberFormatter(newFile,false);
	  //static report name
	  ExtentCucumberFormatter.initiateExtentCucumberFormatter(new
	  File("D:\\cucumber-testing-master\\ExtenReports\\extentreports.html"),false);
	  // Loads the extent config xml to customize on the report.
	  ExtentCucumberFormatter.loadConfig(new
	  File("src/test/resources/extent.properties"));
	  
	  // User can add the system information as follows
	  ExtentCucumberFormatter.addSystemInfo("Browser Name", "Firefox");
	  ExtentCucumberFormatter.addSystemInfo("Browser version", "v31.0");
	  ExtentCucumberFormatter.addSystemInfo("Selenium version", "v2.53.0");
	  
	  // Also you can add system information using a hash map
	  Map systemInfo = new HashMap();
	  systemInfo.put("Cucumber version", "v1.2.3");
	  systemInfo.put("Extent Cucumber Reporter version", "v1.1.0");
	  ExtentCucumberFormatter.addSystemInfo(systemInfo); }*/
	 
}  