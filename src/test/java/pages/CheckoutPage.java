package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//input[@id='checkout__name']")
	private WebElement nameFieldCheckout;
	
	@FindBy(xpath="//input[@id='checkout__phone']")
	private WebElement mobileFieldCheckout;
	
	@FindBy(xpath="//input[@id='checkout__email']")
	private WebElement emailFieldCheckout;
	
	@FindBy(xpath="//img[@alt='Net banking']")
	private WebElement netbankingCheckboxCheckout;
	
	@FindBy(xpath="//input[@id='marketingOptIn']/following-sibling::span[1]")
	private WebElement agreeCheckboxCheckout;
	
	@FindBy(xpath="//button[@id='submit-checkout']")
	private WebElement placeOrderButtonCheckout;
	
	
	public WebElement getNameFieldCheckout() {
		
		return nameFieldCheckout;
		
	}
	
	public WebElement getMobileFieldCheckout() {
		
		return mobileFieldCheckout;
	}
	
	public WebElement getEmailFieldCheckout() {
		
		return emailFieldCheckout;
	}
	
	public WebElement getNetbankingCheckboxCheckout() {
		
		return netbankingCheckboxCheckout;
	}
	
	public WebElement getAgreeCheckboxCheckout() {
		
		return agreeCheckboxCheckout;
	}
	
	public WebElement getPlaceOrderButtonCheckout() {
		
		return placeOrderButtonCheckout;
	}
	
	

}
