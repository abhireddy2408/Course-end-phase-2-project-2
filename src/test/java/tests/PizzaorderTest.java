package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LandingPage;
import utilities.ExcelData;
import utilities.ExtentReporter;

public class PizzaorderTest {
	
	WebDriver driver;
	LandingPage landingPage;
	ExtentReports extentTest;
	String url;
	
	@BeforeClass
	public void setUp() throws IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		landingPage = new LandingPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		// Getting URL from excel file
		
	    String url = ExcelData.excelTestData();
		driver.get(url);
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test
	@Parameters({"location", "name", "mobile", "email", "addressdetails", "couponcode" })
	public void pizzaOrder(String location,String name,String mobile,String email,String addressdetails,String couponcode) {
		
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		landingPage = new LandingPage(driver);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		driver.manage().window().maximize();
//		driver.get("https://www.pizzahut.co.in/");
		ExtentReports extentTest = ExtentReporter.extentRep();
		ExtentTest eTest = extentTest.createTest("PizzaOrder flow");
		eTest.log(Status.INFO, "Navigated to pizzahut Website");
		// auto Black popup screen will be displayed and close it
		try {
			eTest.log(Status.INFO, "If Auto location popup fails will see the PopupError notification on left side of the screen");
			WebDriverWait waitForPopUpError = new WebDriverWait(driver, Duration.ofSeconds(20));
			waitForPopUpError.until(ExpectedConditions.visibilityOf(landingPage.getPopUpError()));
			System.out.println(" landingPage.getPopUpError().isDisplayed() : " + landingPage.getPopUpError().isDisplayed());
			if (landingPage.getPopUpError().isDisplayed()) {
				Thread.sleep(4000);
				System.out.println("after 4000 seconds wait after popuperror");
				eTest.log(Status.INFO, " Got PopupError, will disappear in 5 seconds and wil conitue next steps i.e. Entering location ");
			}
			
		}catch(Exception e) {
			
			eTest.log(Status.INFO, " Popuperror not displayed, Will wait for Auto location Pop up ");
			System.out.println("-----popuperror not displayed so below waitAutoLocationPopUp will be applied ");
			
//			WebDriverWait waitAutoLocationPopUp = new WebDriverWait(driver, Duration.ofSeconds(10));
//			  // Wait for the auto-location popup to be visible
	        try {
	        	WebDriverWait waitAutoLocationPopUp = new WebDriverWait(driver, Duration.ofSeconds(10));
				  // Wait for the auto-location popup to be visible
	            waitAutoLocationPopUp.until(ExpectedConditions.visibilityOf(landingPage.getAutoLocationPopup()));
	            eTest.log(Status.INFO,"Auto-location popup is visible!");
	            System.out.println("Auto-location popup is visible!");
	            System.out.println("------ auto location black pop up screen----");
				landingPage.getCloseAutoLocationPopup().click();
				eTest.log(Status.INFO,"Auto location pop up screen closed");
	        } catch (Exception ee) {
	        	eTest.log(Status.INFO,"Auto-location popup not found or not visible within 10 seconds.");
	            System.out.println("Auto-location popup not found or not visible within 10 seconds.");
	        }
	        
		}
		
		WebDriverWait waitEnterLocation = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitEnterLocation.until(ExpectedConditions.visibilityOf(landingPage.getEnterLocationField()));
		eTest.log(Status.INFO,"User will see an input field for delivery asking to enter location");
		System.out.println("----User see pop up for delivery asking for enter location");
		
		WebElement enterLocation = landingPage.getEnterLocationField();
		enterLocation.sendKeys(location);
		eTest.log(Status.INFO,"User type address as : Bangur Nagar Metro");
		System.out.println("---User type address as:  Bangur Nagar Metro");
		
		//suggesting autocomplete location
		Actions actions = new Actions(driver);
		WebElement suggestedItem = driver.findElement(By.xpath("//button[@role='option']"));
		actions.moveToElement(suggestedItem).click().build().perform();
		eTest.log(Status.INFO,"User select the select the full address from dropdowni.e. Bangur Nagar Metro..");
		
		try {
			WebDriverWait waitselectNeartestLocation = new WebDriverWait(driver, Duration.ofSeconds(15));
			waitselectNeartestLocation.until(ExpectedConditions.visibilityOf(landingPage.getSelectFirstNearestLocationOption()));
			eTest.log(Status.INFO,"if User will see a popup window with multiple location options if they have multi shops on the address");
			eTest.log(Status.INFO,"user will select first dropdown option which is near to that location");
			landingPage.getSelectFirstNearestLocationOption().click();
			eTest.log(Status.INFO,"User will select first auto populate location from the drop down option ");
			System.out.println("---User select first auto populate drop down option---");
			
			eTest.log(Status.INFO,"When you are ordering early hours then you will be asked to select time when to be picked after store open");
			if(driver.findElement(By.xpath("//span[normalize-space()='Start your order']")).isDisplayed()) {
				eTest.log(Status.INFO,"User will see the popup with pick up time and button to click");
				driver.findElement(By.xpath("//span[normalize-space()='Start your order']")).click();
				eTest.log(Status.INFO,"User clicks on Start the order and proceeds to deals page");
			}
		}catch(Exception e) {
			eTest.log(Status.INFO,"After entering location and selecting full address from dropdown, user will navigate to deals page");
			System.out.println(" Navigating to Deals page, After entering loaction as Bangur Nagar Metro and selected location");
		}
		
	
		
		//Check user navigated to deals page by checking /deals/ in  URL
		eTest.log(Status.INFO,"Checking if user navigated to deals page");
		String currentURL = driver.getCurrentUrl();
		System.out.println("--CurrentURL : "+ currentURL);
		System.out.println("--Checking if user navigated to deals page--");
		
		if(currentURL.contains("/deals/")) {
			
			eTest.log(Status.PASS,"User Navigated to deals page successfully");
			System.out.println(" User Navigated to deals page successfully ");
			
		}else {
			eTest.log(Status.FAIL,"Due to some error,User not able to navigate to deals page");
			System.out.println("Due to some error, Not able to navigate to deals page");
		}
		
		//Click on Sides on the menu
		eTest.log(Status.INFO,"Click on Sides menu to select items");
		driver.findElement(By.xpath("//a[@data-synth='link--sides--side']")).click();
		
		// select sides option from menu and select an item which is less than 200 ruppe to be added to basket
		driver.findElement(By.xpath("//button[@data-synth=\"button--cheesy-popcorn-sprinkled-fries-single--one-tap\"]")).click();
		System.out.println("Add cheezySprinkledFries item from sides menu");
		eTest.log(Status.INFO,"Add cheezySprinkledFries item from sides menu");
		// validate item added under basket
		
		System.out.println("validate item i.e. cheezySprinkledFries added under basket");
		eTest.log(Status.INFO,"validate item i.e. cheezySprinkledFries added under basket");
		
		boolean cheezySprinkledFries = driver.findElement(By.xpath("(//div[contains(text(),\"Cheezy Sprinkled Fries\")])[2]")).isDisplayed();
		
		if(cheezySprinkledFries) {
			
			eTest.log(Status.PASS,"cheezySprinkledFries item added under basket");
			System.out.println("cheezySprinkledFries item added under basket");
			
		}else {
			
			eTest.log(Status.FAIL,"cheezySprinkledFries item  is not added under basket");
			System.out.println("cheezySprinkledFries item  is not added under basket");
			
		}
		
		//Check checkout button is enabled now
		
		System.out.println("Validate checkout button is enabled now");
		eTest.log(Status.INFO,"Validate checkout button is enabled now");
		boolean checkOutButtonEnabled = driver.findElement(By.xpath("//button[@data-synth=\"link--checkout\"]")).isEnabled();
		
		if(checkOutButtonEnabled) {
			
			eTest.log(Status.PASS,"CheckOutButton is enabled");
			System.out.println("CheckOutButton is enabled");
			
		}else {
			eTest.log(Status.FAIL,"CheckOuButton is disabled");
			System.out.println("CheckOutButton is disabled");
			
		}
		
		// click on drinks menu and select two items 
		eTest.log(Status.INFO,"Click on drinks menu to select two items from it ");
		driver.findElement(By.xpath("//a[@data-synth='link--drinks--side']")).click();
		
		// add one pepsi item 
		
		eTest.log(Status.INFO,"Add one pepsi item from drinks menu");
		
		driver.findElement(By.xpath("//button[@data-synth='button--pepsi-600ml--one-tap']")).click();
		
		// add one mango item
		
		eTest.log(Status.INFO,"Add one mango item from drinks menu");
		
		driver.findElement(By.xpath("//button[@data-synth='button--sunfeast-mango-smoothie-300ml--one-tap']")).click();
		
		//validate total cart subtotal should be more than 200 rupees
		
		eTest.log(Status.INFO,"validate total cart subtotal should be more than 200 rupees");
		
		String subtotal = driver.findElement(By.xpath("//span[@class='subtotal']")).getText();
		Double subtolalAmount = Double.parseDouble((subtotal).substring(1));
		
		if (subtolalAmount > 200) {
			
			eTest.log(Status.PASS,"Subtotal amount is greater than 200 rupee after adding 2 more items");
			System.out.println("Subtotal amount is greater than 200 rupee after adding 2 more items ");
			
		}else {
			
			eTest.log(Status.FAIL,"Something wrong, Subtotal amount is less than 200 rupee after adding 2 more items");
			System.out.println("Something wrong, Subtotal amount is less than 200 rupee after adding 2 more items");
		}
		 
		
		
		//Click on Checkout button 
		driver.findElement(By.xpath("//a[@data-synth='link--checkout'][@class='button button--primary  justify-between']")).click();
		eTest.log(Status.INFO,"Click on Checkout button");
		//Naviagtes to checkout page 
		
		// Enter Name
		driver.findElement(By.xpath("//input[@id='checkout__name']")).sendKeys(name);
		eTest.log(Status.INFO,"Enter Name on Checkout Page to place an order");
		
		//Enter mobile
		
		driver.findElement(By.xpath("//input[@id='checkout__phone']")).sendKeys(mobile);
		eTest.log(Status.INFO,"Enter Mobile on Checkout Page to place an order");
		
		//Enter Email
		
		driver.findElement(By.xpath("//input[@id='checkout__email']")).sendKeys(email);
		eTest.log(Status.INFO,"Enter Email on Checkout Page to place an order");
		// Enter Address details
		
		driver.findElement(By.xpath("//input[@id='checkout__deliveryAddress.interior']")).sendKeys(addressdetails);
		eTest.log(Status.INFO,"Enter Address on Checkout Page to place an order");
		
		//Click on Apply Gift Card button
		
		driver.findElement(By.xpath("//div[@class='sc-fzqMdD eohbfP']")).click();
		eTest.log(Status.INFO,"Click on Apply Gift Card button");
		
		// Check Apply Gift Card pop up is displayed
		
		driver.findElement(By.xpath("//span[text()='Apply a Gift Card']")).isDisplayed();
		eTest.log(Status.INFO,"Check Apply Gift Card pop up is displayed");
		
		// Select Coupon 
		
		driver.findElement(By.xpath("//div[@class='sc-fznMnq gdZumo']")).click();
		eTest.log(Status.INFO,"Click on the Coupon ");
		
		// Enter Coupon code
		
		driver.findElement(By.xpath("//input[@name='voucherId']")).sendKeys(couponcode);
		eTest.log(Status.INFO," Enter Coupon code");
		
		// Click on Apply button
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		eTest.log(Status.INFO,"Click on Apply button");
		
		// Validate if error is coming when entered invalid coupon code
		eTest.log(Status.INFO,"Validate if error is coming when entered invalid coupon code");
		boolean errorMsgForInvalidCoupon = driver.findElement(By.xpath("//span[text()='Sorry, we don’t currently support that coupon code.']")).isDisplayed();
		
		if(errorMsgForInvalidCoupon) {
			eTest.log(Status.PASS,"Error thrown when entered invalid coupon code, Then it is PASS");
			System.out.println("Error thrown when entered invalid coupon code, Then it is PASS");
		}else {
			eTest.log(Status.FAIL,"No Error thrown when entered invalid coupon code, Then it is FAIL");
			System.out.println("No Error thrown when entered invalid coupon code, Then it is FAIL");
		}
		
		
		//Close the Apply a Coupon code popup
		eTest.log(Status.INFO,"Close the Apply a Coupon code Popup");
		driver.findElement(By.xpath("//button[@class='icon-remove-3 absolute top-0 right-0 mr-20 mt-20']")).click();
		eTest.log(Status.INFO,"Coupon code Popup closed");
		
		//Validate after closing the Apply Coupon Code , page navigated back to deals page
		
		eTest.log(Status.INFO,"Validate, After closing the Apply Coupon Code that Checkout page navigated back to deals page");
		boolean navigatedToDealsPage = driver.getCurrentUrl().contains("deals");
		
		if(navigatedToDealsPage) {
			
			eTest.log(Status.PASS,"After closing the Apply Coupon Code , page navigated back to deals page. Then it is PASS");
			System.out.println("After closing the Apply Coupon Code , page navigated back to deals page. Then it is PASS");
		}else {
			eTest.log(Status.FAIL,"After closing the Apply Coupon Code , page did not navigated back to deals page. Then it is FAILED");
			System.out.println("After closing the Apply Coupon Code , page did not navigated back to deals page. Then it is FAILED");
		}
		
		
		//extent report need to be flush to create report
		extentTest.flush();
		
	}
	
	
	

}
