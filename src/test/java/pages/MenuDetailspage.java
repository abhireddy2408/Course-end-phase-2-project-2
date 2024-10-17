package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuDetailspage {
	
	WebDriver driver;
	
	public MenuDetailspage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="(//span[contains(@class,'border-grey-light')])[1]")
	private WebElement vegetarianRadioFlag;
	
	@FindBy(xpath="(//span[text()='Pizzas'])[2]")
	private WebElement pizzaMenuBarOptionField;
	
	@FindBy(xpath="//button[@data-synth='button--margherita-bestseller-pan-personal--one-tap']")
	private WebElement selectMargeritaPizzaButton;
	
	@FindBy(xpath="//a[contains(@href,'margherita') and contains(@title, 'Click to make changes')]")
	private WebElement itemMargeritaPizzaunderBasket;
	
	@FindBy(xpath="//span[@class='ml-auto text-right']/span[@data-synth='basket-value']")
	private WebElement checkoutAmountElement;
	
	@FindBy(xpath="//span[@class='subtotal']")
	private WebElement subTotalAmount;
	
	@FindBy(xpath="(//span[@class='mr-auto'])[2]/following-sibling::span[1]")
	private WebElement totalTaxAmount;
	
	@FindBy(xpath="//span[@class='mr-auto text-left']/span[contains(text(),'item')]")
	private WebElement itemCountElement;
	
	@FindBy(xpath="//a[@data-synth='link--drinks--side']/span[text()='Drinks']")
	private WebElement drinksOptionMenu;
	
	@FindBy(xpath="//button[@data-synth='button--pepsi-600ml--one-tap']")
	private WebElement pepsiOptionButton;
	
	@FindBy(xpath="//button[@data-synth='basket-item-remove--margherita-bestseller-pan-personal']")
	private WebElement margeritaPizzaRemoveElement;
	
	@FindBy(xpath="//span[text()='Checkout']")
	private WebElement checkoutButton;
	
	public WebElement getVegetarianRadioFlag() {
		
		return vegetarianRadioFlag;
		
	}
	
	public WebElement getPizzaMenuBarOptionField() {
		
		return pizzaMenuBarOptionField;
		
	}
	
	public WebElement getSelectMargeritaPizzaButton() {
		
		return selectMargeritaPizzaButton;
	}
	
	public WebElement getItemMargeritaPizzaunderBasket() {
		
		return itemMargeritaPizzaunderBasket;
		
	}
	public WebElement getCheckoutAmountElement() {
		
		return checkoutAmountElement;
	}
	
	public WebElement getSubTotalAmount() {
		
		return subTotalAmount;
	}
	
	public WebElement getTotalTaxAmount() {
		
		return totalTaxAmount;
	}
	
	public WebElement getItemCountElement() {
		
		return itemCountElement;
	}
	
	public WebElement getDrinksOptionMenu() {
		
		return drinksOptionMenu;
	}
	
	public WebElement getPepsiOptionButton() {
		
		return pepsiOptionButton;
	}
	public WebElement getMargeritaPizzaRemoveElement() {
		
		return margeritaPizzaRemoveElement;
	}
	public WebElement getCheckoutButton() {
		
		return checkoutButton;
	}
	

}
