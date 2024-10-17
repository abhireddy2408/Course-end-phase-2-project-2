package hooks;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExtentReporter;
import utilities.ScreenshotUtil;

public class Hooks {
	
	public static WebDriver driver;
	public static ExtentTest eTest;
	String scenarioName;
	ExtentReports extent;
	//before scenario
	@BeforeMethod
	public void beforeScenario(Scenario scenario) {
		
		//initialize extentReport before each sceanrio
		extent = ExtentReporter.extentRep();
		eTest = extent.createTest(scenario.getName());
		scenarioName = scenario.getName();
		eTest.log(Status.INFO, "Starting Scenario : "+ scenarioName );
		
		System.out.println("Starting the browser before scenario...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		
		
	}

	//After Scenario
	@AfterMethod
	public void afterScenario(Scenario scenario) {
		
		if(scenario.isFailed()) {
			//capture screenshot
			String screenshot = ScreenshotUtil.takeScreenShot(driver, scenarioName);
			
			// Log screenshot in Extends report.
			
			Assert.fail("Test Failed please see the screenshot taken for scenario name :  "+ scenarioName +" failure");
			
			try {
				eTest.addScreenCaptureFromPath(screenshot, "Screenshot of failure");
            } catch (Exception e) {
                eTest.log(Status.FAIL, "Failed to attach screenshot due to: " + e.getMessage());
            }
            
			
		}
		
		eTest.log(Status.INFO, "Ending the Scenario :" + scenarioName);
		
		System.out.println("Quitting the browser after scenario...");
		if(driver != null) {
			driver.quit();
		}
		
		//Flush the extent 
		extent.flush();
	}

}
