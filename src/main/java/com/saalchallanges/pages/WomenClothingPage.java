/**
 * 
 */
package com.saalchallanges.pages;

import java.util.concurrent.TimeUnit;

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
		
		System.out.println("Dress is selected");
		
		selected_dress.click();		
	}
	
	public void select_view_dress() {
		System.out.println("Dress is selected 1");
		selected_dress.click();		
	}
			
	public void click_btn_submit() throws Exception {
		System.out.println("Dress is selected 3");
		Thread.sleep(5000);
		btn_submit.click();
	}
	
	public void click_btn_proceed_checkout() throws InterruptedException {
		driver.manage().timeouts().pageLoadTimeout(com.saalchallenge.util.TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		Thread.sleep(5000);
		btn_proceed_checkout.click();
	}

}
