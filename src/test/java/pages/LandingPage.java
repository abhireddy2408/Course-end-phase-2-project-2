package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//span[contains(text(),'Sorry, an error occurred while searching for that address.')]")
	private WebElement popUpError;
	
	
	@FindBy(xpath="//button[@class='button button--secondary text-center text-16 mt-20 sm:mt-40']/span[1]")
	private WebElement autoLocationPopup;
	
	@FindBy(xpath="//button[@class='button button--secondary text-center text-16 mt-20 sm:mt-40']/span[1]")
	private WebElement closeAutoLocationPopup;
	
	@FindBy(xpath="//input[@placeholder='Enter your location for delivery']")
	private WebElement enterLocationField;
	
	@FindBy(xpath="//div[@data-testid ='button-select-collection'][1]")
	private WebElement selectFirstNearestLocationOption;
	
	public WebElement getPopUpError() {
		
		return popUpError;
		
	}
	
	public WebElement getAutoLocationPopup() {
		
		return autoLocationPopup;
	}
	
	public WebElement getCloseAutoLocationPopup() {
		
		return closeAutoLocationPopup;
	}
	
	public WebElement getEnterLocationField() {
		return enterLocationField;
	}
	
	public WebElement getSelectFirstNearestLocationOption() {
		
		return selectFirstNearestLocationOption;
	}
	
	
	

}
