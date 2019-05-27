/**
 * 
 */
package com.saalchallanges.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saalchallenge.base.TestBase;

/**
 * @author mohitpuri
 *
 */
public class WomenClothingPage extends TestBase {
	
	@FindBy(xpath="//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")
	WebElement selected_dress;
	
	@FindBy(name="Submit")
	WebElement btn_submit;
	
	@FindBy(xpath="//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
	WebElement btn_proceed_checkout;
	
	
	public WomenClothingPage() {
		
		PageFactory.initElements(driver, this);
			
	}

	public void select_dress() {
		selected_dress.click();		
	}
	
	public void select_view_dress() {
		selected_dress.click();		
	}
			
	public void click_btn_submit() {
		btn_submit.click();
	}
	
	public void click_btn_proceed_checkout() {
		btn_proceed_checkout.click();
	}

}
