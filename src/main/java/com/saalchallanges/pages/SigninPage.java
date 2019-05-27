/**
 * 
 */
package com.saalchallanges.pages;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saalchallenge.base.TestBase;

/**
 * @author mohitpuri
 *
 */
public class SigninPage extends TestBase {
	
	
	@FindBy(className="login")
	WebElement signInButton;
	
	public SigninPage() {
		
		PageFactory.initElements(driver, this);
		
		try {
			projectPath = System.getProperty("user.dir");
			workbook = new XSSFWorkbook(projectPath+"/DataSheet.xlsx");
			sheet = workbook.getSheetAt(0);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	public void appln_url() {
		driver.get(sheet.getRow(24).getCell(1).getStringCellValue());
	}
	public void signin() {
		signInButton.click();		
	}

}
