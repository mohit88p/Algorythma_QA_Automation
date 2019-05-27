/**
 * 
 */
package com.saalchallanges.pages;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.saalchallenge.base.TestBase;

/**
 * @author mohitpuri
 *
 */
public class AccountPage extends TestBase{
	
	@FindBy(css="h1")
	WebElement lbl_myaccount;

	@FindBy(linkText="Women")
	WebElement btn_women;

	@FindBy(css="h1")
	WebElement heading;

	@FindBy(className="logout")
	WebElement logout;

	@FindBy(className="info-account")	
	WebElement txt_info_account;
	
	@FindBy(className="account")
	WebElement lbl_fullname;
	
	public AccountPage() {
		PageFactory.initElements(driver, this);
		try {
			
			workbook = new XSSFWorkbook(projectPath+"/DataSheet.xlsx");
			sheet = workbook.getSheetAt(0);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	public String myaccounts() {
		String a = lbl_myaccount.getText();
		return a;
	}
	public void click_btn_women() {
		btn_women.click();

	}

	public String get_heading_label() {
		System.out.println("PRINT");
		String lbl_heading = heading.getText();
		return lbl_heading;
	}

	public boolean my_account_in_url() {
		String url_contains = sheet.getRow(21).getCell(1).getStringCellValue();
		return driver.getCurrentUrl().contains(url_contains);		
	}	

	public boolean is_disp_logout() {
		return logout.isDisplayed();			
	}

	public String get_txt_account_info() {
		String txt_order_confirmation = txt_info_account.getText();
		return txt_order_confirmation;
	}

	public String lbl_txt_account_info() {
		return sheet.getRow(20).getCell(1).getStringCellValue();		
	}
	public String lbl_my_account_heading() {
		return sheet.getRow(22).getCell(1).getStringCellValue();
	}
	public String get_fullname() {
		return sheet.getRow(23).getCell(1).getStringCellValue();
	}
	
	public String get_fullname_label() {
		System.out.println("Aaaja");
		String full_name = lbl_fullname.getText();
		return full_name;
	}

}
