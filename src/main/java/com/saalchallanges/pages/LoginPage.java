/**
 * 
 */
package com.saalchallanges.pages;

import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saalchallenge.base.TestBase;

/**
 * @author mohitpuri
 *
 */
public class LoginPage extends TestBase{
	
	@FindBy(id="email")
	WebElement emailTextbox;

	@FindBy(id="passwd")
	WebElement passwordTextbox;

	@FindBy(id="SubmitLogin")
	WebElement submitButton;

	@FindBy(xpath="//input[@id='email_create']")
	WebElement txt_email_address;

	@FindBy(id="SubmitCreate")
	WebElement btn_create_account;

	public LoginPage() {
		
		PageFactory.initElements(driver, this);
		try {
			
			workbook = new XSSFWorkbook(projectPath+"/DataSheet.xlsx");
			sheet = workbook.getSheetAt(0);
		} 
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public void login(){
			String username = sheet.getRow(15).getCell(1).getStringCellValue();
			String password = sheet.getRow(16).getCell(1).getStringCellValue();

			emailTextbox.sendKeys(username);
			passwordTextbox.sendKeys(password);
			submitButton.click();
	}

	public void enter_email() {
		
		Random random = new Random();
		int randomValue = random.nextInt(900) + 100;
		System.out.println(randomValue);
		String email = "hf_challenge_" + randomValue + "@gmail.com";
		System.out.println("Email is:"+email);
		driver.manage().timeouts().pageLoadTimeout(com.saalchallenge.util.TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		//String timestamp = String.valueOf(new Date().getTime());
		//String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
		txt_email_address.sendKeys(email);
	}

	public void click_btn_create_account() {
		btn_create_account.click();
	}

}
