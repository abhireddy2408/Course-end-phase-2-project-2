package stepdefs;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CheckoutPage;
import pages.LandingPage;
import pages.MenuDetailspage;


public class SearchPizza {
	
	WebDriver driver;
	Double checkoutAmountOneItem;
	Double checkoutAmountTwoItem;
	LandingPage landingPage;
	MenuDetailspage menuDetailspage;
	CheckoutPage checkoutPage;
	Hooks hooks;
	ExtentTest extentTest;

		@Given("User launch Pizzahut application with {string}")
		public void user_launch_pizzahut_application_with(String url) {
			driver = Hooks.driver;
			landingPage = new LandingPage(driver);
			extentTest = Hooks.eTest;
			extentTest.log(Status.INFO, " User Navigating to Pizza hut Webiste :" + url);
			
		    driver.get(url);
			
			try {
				WebDriverWait waitForPopUpError = new WebDriverWait(driver, Duration.ofSeconds(20));
				waitForPopUpError.until(ExpectedConditions.visibilityOf(landingPage.getPopUpError()));
				System.out.println(" landingPage.getPopUpError().isDisplayed() : " + landingPage.getPopUpError().isDisplayed());
				if (landingPage.getPopUpError().isDisplayed()) {
					Thread.sleep(4000);
					System.out.println("after 4000 seconds wait after popuperror");
					extentTest.log(Status.INFO, " Got PopupError, will disappear in 5 seconds and wil conitue next steps ");
				}
				
			}catch(Exception e) {
				
				extentTest.log(Status.WARNING, " Popuperror not displayed, Will wait for Auto location Pop up ");
				System.out.println("-----popuperror not displayed so below waitAutoLocationPopUp will be applied ");
				
//				WebDriverWait waitAutoLocationPopUp = new WebDriverWait(driver, Duration.ofSeconds(10));
//				  // Wait for the auto-location popup to be visible
		        try {
		        	WebDriverWait waitAutoLocationPopUp = new WebDriverWait(driver, Duration.ofSeconds(10));
					  // Wait for the auto-location popup to be visible
		            waitAutoLocationPopUp.until(ExpectedConditions.visibilityOf(landingPage.getAutoLocationPopup()));
		            extentTest.log(Status.INFO,"Auto-location popup is visible!");
		            System.out.println("Auto-location popup is visible!");
		            System.out.println("------ auto location black pop up screen----");
					extentTest.log(Status.INFO,"Auto location pop up screen appeared");
					landingPage.getCloseAutoLocationPopup().click();
		        } catch (Exception ee) {
		        	extentTest.log(Status.WARNING,"Auto-location popup not found or not visible within 5 seconds.");
		            System.out.println("Auto-location popup not found or not visible within 5 seconds.");
		        }
		        
			}
				

				// close the survey if pops up
//				try {
//					if(driver.findElement(By.xpath("//button[@aria-label='Close the survey']/svg")).isDisplayed()) {
//						extentTest.log(Status.INFO,"Survey pop up screen appeared");
//						driver.findElement(By.xpath("//button[@aria-label='Close the survey']/svg")).click();
//						extentTest.log(Status.INFO,"Closed Survey pop up screen");
//						landingPage.getCloseAutoLocationPopup().click();
//						extentTest.log(Status.INFO,"Closed Auto location pop up screen");
//						
//					}
//					
//				}catch(Exception e1) {
//					
//					extentTest.log(Status.INFO,"No survey poped up appeared");
//					System.out.println(" ------No survey poped up so closing the autolocationpopup----");
//					landingPage.getCloseAutoLocationPopup().click();
//					extentTest.log(Status.INFO,"Closed Auto location pop up screen");
//					
//				}

			
		}
		

		@When("User wait for auto location black pop up screen")
		public void user_wait_for_auto_location_black_pop_up_screen() throws InterruptedException {
			
//			landingPage = new LandingPage(driver);
//			System.out.println(" wait");
//			try {
//				if (landingPage.getPopUpError().isDisplayed()) {
//					Thread.sleep(4000);
//					System.out.println("after 4000 seconds wait after popuperror");
//					extentTest.log(Status.INFO, " Got PopupError, will disappear in 5 seconds and wil conitue next steps ");
//				}
//				
//			}catch(Exception e) {
//				
//				extentTest.log(Status.INFO, " Popuperror not displayed, Will wait for Auto location Pop up ");
//				System.out.println("-----popuperror not displayed so below waitAutoLocationPopUp will be applied ");
//				
//				WebDriverWait waitAutoLocationPopUp = new WebDriverWait(driver, Duration.ofSeconds(5));
//				waitAutoLocationPopUp.until(ExpectedConditions.visibilityOf(landingPage.getAutoLocationPopup()));
//				System.out.println("------ auto location black pop up screen----");
//				extentTest.log(Status.INFO,"auto location pop up screen appeared");
//				// close the survey if pops up
//				try {
//					if(driver.findElement(By.xpath("//button[@aria-label='Close the survey']/svg")).isDisplayed()) {
//						extentTest.log(Status.INFO,"Survey pop up screen appeared");
//						driver.findElement(By.xpath("//button[@aria-label='Close the survey']/svg")).click();
//						extentTest.log(Status.INFO,"Closed Survey pop up screen");
//						WebElement closeEl = closeElementMethod();
//						extentTest.log(Status.INFO,"Closed Auto location pop up screen");
//						closeEl.click();
//					}
//					
//				}catch(Exception e1) {
//					
//					extentTest.log(Status.INFO,"No survey poped up appeared");
//					System.out.println(" ------No survey poped up so closing the autolocationpopup----");
//					WebElement closeEl = closeElementMethod();
//					extentTest.log(Status.INFO,"Closed Auto location pop up screen");
//					closeEl.click();
//				}
//
//			}
			
			
		}
		
		
		@Then("User close the pop up screen")
		public void user_close_the_pop_up_screen() {
			
//			WebElement closeElement = driver.findElement(By.xpath("//button[@class='button button--secondary text-center text-16 mt-20 sm:mt-40']/span[1]"));
//			closeElement.click();
			System.out.println("User close the pop up screen");
		
		}
		
		@Then("User see pop up for delivery asking for enter location")
		public void user_see_pop_up_for_delivery_asking_for_enter_location() {
			WebDriverWait waitEnterLocation = new WebDriverWait(driver, Duration.ofSeconds(10));
			waitEnterLocation.until(ExpectedConditions.visibilityOf(landingPage.getEnterLocationField()));
			extentTest.log(Status.INFO,"User will see an input field for delivery asking to enter location");
			System.out.println("----User see pop up for delivery asking for enter location");
		}
		
		@Then("User type address as {string}")
		public void user_type_address_as(String location) {
			WebElement enterLocation = landingPage.getEnterLocationField();
			enterLocation.sendKeys(location);
			extentTest.log(Status.INFO,"User type address as " + location);
			System.out.println("---User type address as:" +location);
		}
		
		@Then("User select first auto populate drop down option")
		public void user_select_first_auto_populate_drop_down_option() {
			//suggesting autocomplete location
			
			Actions actions = new Actions(driver);
			WebElement suggestedItem = driver.findElement(By.xpath("//button[@role='option']"));
			actions.moveToElement(suggestedItem).click().build().perform();
			
			WebDriverWait waitselectNeartestLocation = new WebDriverWait(driver, Duration.ofSeconds(15));
			waitselectNeartestLocation.until(ExpectedConditions.visibilityOf(landingPage.getSelectFirstNearestLocationOption()));
			landingPage.getSelectFirstNearestLocationOption().click();
			extentTest.log(Status.INFO,"User will select first auto populate location from the drop down option ");
			System.out.println("---User select first auto populate drop down option---");
			
			try {
				
				if(driver.findElement(By.xpath("//span[normalize-space()='Start your order']")).isDisplayed()) {
					driver.findElement(By.xpath("//span[normalize-space()='Start your order']")).click();
				}
				
			}catch(Exception e) {
				System.out.println(e);
			}
			
		}
		
		@When("User navigate to deails page")
		public void user_navigate_to_deails_page() throws InterruptedException {
	
			// Write a code to check menu detail page is visible 
			extentTest.log(Status.INFO,"Page navigates to details page");
			System.out.println("--User navigate to deails page--");

		}
		
		@Then("User validate vegetarian radio button flag is off")
		public void user_validate_vegetarian_radio_button_flag_is_off() {
			menuDetailspage = new MenuDetailspage(driver);
			boolean vegetarianRadioFlag = menuDetailspage.getVegetarianRadioFlag().isSelected();
			Assertions.assertEquals(false, vegetarianRadioFlag);
			extentTest.log(Status.INFO,"User validate vegetarian radio button flag is off: " + vegetarianRadioFlag);
			System.out.println("-----User validate vegetarian radio button flag is off: " + vegetarianRadioFlag);
		}

		@Then("User clicks on Pizzas menu bar option")
		public void user_clicks_on_pizzas_menu_bar_option() {
			//Pizzas menu bar click 
			menuDetailspage.getPizzaMenuBarOptionField().click();
			extentTest.log(Status.INFO,"User clicks on Pizzas menu bar option");
			System.out.println("---User clicks on Pizzas menu bar option---");
		}

		@When("User select add button of any pizza from Recommended")
		public void user_select_add_button_of_any_pizza_from_recommended() {
			// Select add button of any pizza i.e. Margherita pizza
			menuDetailspage.getSelectMargeritaPizzaButton().click();
			extentTest.log(Status.INFO,"User select add button of any pizza from Recommended i.e. Margherita pizza-");
			System.out.println("---User select add button of any pizza from Recommended--");
		}

		@Then("User see that the pizza is getting added under Your Basket")
		public void user_see_that_the_pizza_is_getting_added_under_your_basket() {
			// user see the item add under basket item
			boolean itemDisplayedUnderBasketitem = menuDetailspage.getItemMargeritaPizzaunderBasket().isDisplayed();
			Assertions.assertTrue(itemDisplayedUnderBasketitem);
			extentTest.log(Status.INFO,"pizza is getting added under Your Basket :" + itemDisplayedUnderBasketitem);
			System.out.println("----pizza is getting added under Your Basket : " + itemDisplayedUnderBasketitem);
		}

		@Then("User validate pizza price plus Tax is checkout price")
		public void user_validate_pizza_price_plus_tax_is_checkout_price() {
			
			double AmmountPayableOneItem = amountPayableSubtotalPlusTax();
			checkoutAmountOneItem = checkoutAmount();
			
			Assertions.assertEquals(checkoutAmountOneItem, AmmountPayableOneItem);
			extentTest.log(Status.INFO,"pizza price plus Tax is checkout price: " + subTotalMethod() +" + "+ totalTaxMethod() + " = "+ checkoutAmountOneItem);
			System.out.println("----pizza price plus Tax is checkout price: " + subTotalMethod() +" + "+ totalTaxMethod() + " = "+ checkoutAmountOneItem);
		}
		
		public Double amountPayableSubtotalPlusTax() {
			
			double AmmountPayableSubtotalPlusTax = subTotalMethod() + totalTaxMethod();
			return AmmountPayableSubtotalPlusTax;
			
		}
		
		public Double checkoutAmount() {
			
			String checkoutAmount = menuDetailspage.getCheckoutAmountElement().getText();
			
			StringBuilder checkoutAmountStringBuilder = new StringBuilder(checkoutAmount);
			checkoutAmountStringBuilder.deleteCharAt(0);
			String checkoutAmountString = checkoutAmountStringBuilder.toString();
			double checkoutAmountInt = Double.parseDouble(checkoutAmountString);
			
			return checkoutAmountInt;
		}
		
		public Double subTotalMethod() {
			String subTotal = menuDetailspage.getSubTotalAmount().getText();
			StringBuilder subTotalStringbuilder = new StringBuilder(subTotal);
			subTotalStringbuilder.deleteCharAt(0);
			String subTotalString = subTotalStringbuilder.toString();
			return Double.parseDouble(subTotalString);
		}
		public Double totalTaxMethod() {
			String totalTax = menuDetailspage.getTotalTaxAmount().getText();
			StringBuilder totalTaxStringbuilder = new StringBuilder(totalTax);
			totalTaxStringbuilder.deleteCharAt(0);
			String totalTaxString = totalTaxStringbuilder.toString();
			return Double.parseDouble(totalTaxString);
		}

		@Then("User validate checkout button contains Item count")
		public void user_validate_checkout_button_contains_item_count() {
			//User validate checkout button contains Item count
			
			String itemCountString = menuDetailspage.getItemCountElement().getText();
			int itemCount = Integer.parseInt((itemCountString).replaceAll("[^0-9]", ""));
			
			Assertions.assertEquals(1, itemCount);
			extentTest.log(Status.INFO,"checkout button contains 1 Item count: "+ itemCount);
			System.out.println("---checkout button contains 1 Item count: "+ itemCount);
		}

		@Then("User validate checkout button contains total price count")
		public void user_validate_checkout_button_contains_total_price_count() {
			Assertions.assertEquals(checkoutAmount(), amountPayableSubtotalPlusTax());
			extentTest.log(Status.INFO,"checkoutAmount equal to  AmmountPayable: "+ checkoutAmount() + " equal " + amountPayableSubtotalPlusTax());
			System.out.println("---checkoutAmount equal to  AmmountPayable: "+ checkoutAmount() + " equal " + amountPayableSubtotalPlusTax());
		}

		@Then("User clicks on Drinks option")
		public void user_clicks_on_drinks_option() {
			//User clicks on Drinks option
			menuDetailspage.getDrinksOptionMenu().click();
			extentTest.log(Status.INFO,"User clicks on Drinks option");
			System.out.println("---User clicks on Drinks option");
		}

		@Then("User select Pepsi option to add into the Basket")
		public void user_select_pepsi_option_to_add_into_the_basket() {
			//User select Pepsi option to add into the Basket
			menuDetailspage.getPepsiOptionButton().click();
			extentTest.log(Status.INFO,"User select Pepsi option to add into the Basket");
			System.out.println("---User select Pepsi option to add into the Basket");	
		}

		@Then("User see {int} items are showing under checkout button")
		public void user_see_items_are_showing_under_checkout_button(Integer int1) {

			//User see 2 items are showing under checkout button
			String itemCountString2 = menuDetailspage.getItemCountElement().getText();
			int itemCount2 = Integer.parseInt((itemCountString2).replaceAll("[^0-9]", ""));
			
			Assertions.assertEquals(int1, itemCount2);
			extentTest.log(Status.INFO,"User see items are showing under checkout button: " + itemCount2);
			System.out.println("----User see items are showing under checkout button: " + itemCount2);
		}

		@Then("User see total price is now more than before")
		public void user_see_total_price_is_now_more_than_before() {
			
			checkoutAmountTwoItem = checkoutAmount();
			
			if (checkoutAmountTwoItem > checkoutAmountOneItem) {
				
				Assertions.assertTrue(true);
			}else {
				Assertions.assertTrue(false);
			}
			extentTest.log(Status.INFO,"User see total price is now more than before: " + checkoutAmountTwoItem + " greater than previous "+ checkoutAmountOneItem );
			System.out.println("---User see total price is now more than before:" + checkoutAmountTwoItem + "greater than previous"+ checkoutAmountOneItem );
		}

		@Then("User remove the Pizza item from Basket")
		public void user_remove_the_pizza_item_from_basket() {
			// User remove the Pizza item from Basket
			menuDetailspage.getMargeritaPizzaRemoveElement().click();
			extentTest.log(Status.INFO,"User remove the Pizza item from Basket");
			System.out.println("---User remove the Pizza item from Basket");				
		}

		@Then("see Price tag got removed from the checkout button")
		public void see_price_tag_got_removed_from_the_checkout_button() {
			
			Double checkoutAmountOneItemAfterOneRemoved = checkoutAmount();
			
			if (checkoutAmountOneItemAfterOneRemoved < checkoutAmountTwoItem) {
				
				Assertions.assertTrue(true);
			} else {
				Assertions.assertFalse(false);
				
			}
			extentTest.log(Status.INFO,"see Price tag got one ietm removed from the checkout button: "+ checkoutAmountOneItemAfterOneRemoved + " , before ammount was:" + checkoutAmountTwoItem);
			System.out.println("----see Price tag got one ietm removed from the checkout button: "+ checkoutAmountOneItemAfterOneRemoved + " , before ammount was:" + checkoutAmountTwoItem);
		}

		@Then("User see {int} item showing in checkout button")
		public void user_see_item_showing_in_checkout_button(Integer int1) {
			//User see 1 item showing in checkout button
			String itemCountString3 = menuDetailspage.getItemCountElement().getText();
			int itemCount3 = Integer.parseInt((itemCountString3).replaceAll("[^0-9]", ""));
			
			Assertions.assertEquals(int1, itemCount3);
			extentTest.log(Status.INFO,"User see int item showing in checkout button : "+ itemCount3);
			System.out.println("---User see int item showing in checkout button:"+ itemCount3);
		}

		@Then("User Clicks on Checkout button")
		public void user_clicks_on_checkout_button() {
			//User Clicks on Checkout button
			
			menuDetailspage.getCheckoutButton().click();
			extentTest.log(Status.INFO,"User Clicks on Checkout button");
			System.out.println("---User Clicks on Checkout button");
		}

		@Then("User goes to checkout page enters {string} {string} and {string}")
		public void user_goes_to_checkout_page(String name, String mobile, String email) {
			extentTest.log(Status.INFO,"User goes to checkout page");
			System.out.println("---User goes to checkout page---");
			// close the survey if pops up
			try {
				if(driver.findElement(By.xpath("//button[@aria-label='Close the survey']/svg")).isDisplayed()) {
					
					driver.findElement(By.xpath("//button[@aria-label='Close the survey']/svg")).click();
					
					//checkout page
					
					checkoutPage = new CheckoutPage(driver);
					
					//name enter
					checkoutPage.getNameFieldCheckout().sendKeys(name);
					
					//mobile
					checkoutPage.getMobileFieldCheckout().sendKeys(mobile);
					
					//email
					checkoutPage.getEmailFieldCheckout().sendKeys(email);
					
					// Payment type Netbanking
					checkoutPage.getNetbankingCheckboxCheckout().click();
					
					// I agree check button
					checkoutPage.getAgreeCheckboxCheckout().click();
					// submit button place your order
					
					
					checkoutPage.getPlaceOrderButtonCheckout().click();
				
					
				}
			}catch(NoSuchElementException e) {
				
				extentTest.log(Status.WARNING,"Survey Element not found moved to next steps");
				System.out.println("---- Element not found moved to next steps---");
				
				//checkout page
				
				checkoutPage = new CheckoutPage(driver);
				
				//name enter
				checkoutPage.getNameFieldCheckout().sendKeys(name);
				
				//mobile
				checkoutPage.getMobileFieldCheckout().sendKeys(mobile);
				
				//email
				checkoutPage.getEmailFieldCheckout().sendKeys(email);
				
				// Payment type Netbanking
				checkoutPage.getNetbankingCheckboxCheckout().click();
				
				// I agree check button
				checkoutPage.getAgreeCheckboxCheckout().click();
				// submit button place your order
				
				
				checkoutPage.getPlaceOrderButtonCheckout().click();
				extentTest.log(Status.PASS,"clicked on Place a order");
				System.out.println("--clicked on Place a order--");
				
			}
			
			
		}

}