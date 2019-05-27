/**
 * 
 */
package com.saalchallanges.pages;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.saalchallenge.base.TestBase;

/**
 * @author mohitpuri
 *
 */
public class CreateAccountPage extends TestBase {

	@FindBy(id = "id_gender2")
	WebElement radio_btn_gender;

	@FindBy(id = "customer_firstname")
	WebElement txt_firstname;

	@FindBy(id = "customer_lastname")
	WebElement txt_secondname;

	@FindBy(id = "passwd")
	WebElement txt_passwd;

	@FindBy(id = "days")
	WebElement drd_day;

	@FindBy(id = "months")
	WebElement drd_months;

	@FindBy(id = "years")
	WebElement drd_years;

	@FindBy(id = "company")
	WebElement txt_company;

	@FindBy(id = "address1")
	WebElement txt_address_line1;

	@FindBy(id = "address2")
	WebElement txt_address_line2;

	@FindBy(id = "city")
	WebElement txt_city;

	@FindBy(id = "id_state")
	WebElement drd_state;

	@FindBy(id = "postcode")
	WebElement txt_postalcode;

	@FindBy(id = "other")
	WebElement txt_other;

	@FindBy(id = "phone")
	WebElement txt_phone;

	@FindBy(id = "phone_mobile")
	WebElement txt_phone_mobile;

	@FindBy(id = "alias")
	WebElement txt_alias;

	@FindBy(id = "submitAccount")
	WebElement btn_submit;

	public CreateAccountPage() {
		PageFactory.initElements(driver, this);

		try {
			workbook = new XSSFWorkbook(projectPath + "/DataSheet.xlsx");
			sheet = workbook.getSheetAt(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void click_radio_btn_gender() {
		radio_btn_gender.click();
	}

	public void enter_name_and_pwd() {
		String name = sheet.getRow(2).getCell(1).getStringCellValue();
		String surname = sheet.getRow(3).getCell(1).getStringCellValue();
		String passwd = sheet.getRow(4).getCell(1).getStringCellValue();

		txt_firstname.sendKeys(name);
		txt_secondname.sendKeys(surname);
		txt_passwd.sendKeys(passwd);
	}

	public void select_drd_day() {
		Select select = new Select(drd_day);
		select.selectByValue("1");
	}

	public void select_drd_month() {
		Select select = new Select(drd_months);
		select.selectByValue("1");
	}

	public void select_drd_year() {
		Select select = new Select(drd_years);
		select.selectByValue("2000");
	}

	public void enter_all_address_details() {
		String company = sheet.getRow(5).getCell(1).getStringCellValue();
		String address_line1 = sheet.getRow(6).getCell(1).getStringCellValue();
		String address_line2 = sheet.getRow(7).getCell(1).getStringCellValue();
		String city = sheet.getRow(8).getCell(1).getStringCellValue();
		String state = sheet.getRow(9).getCell(1).getStringCellValue();
		String postal_code = sheet.getRow(10).getCell(1).getStringCellValue();
		String other = sheet.getRow(11).getCell(1).getStringCellValue();
		String phone = sheet.getRow(12).getCell(1).getStringCellValue();
		String phone_mobile = sheet.getRow(13).getCell(1).getStringCellValue();
		String alias = sheet.getRow(14).getCell(1).getStringCellValue();

		txt_company.sendKeys(company);
		txt_address_line1.sendKeys(address_line1);
		txt_address_line2.sendKeys(address_line2);
		txt_city.sendKeys(city);

		Select select = new Select(drd_state);
		select.selectByVisibleText(state);

		txt_postalcode.sendKeys(String.valueOf(postal_code));
		txt_other.sendKeys(other);
		txt_phone.sendKeys(String.valueOf(phone));
		txt_phone_mobile.sendKeys(String.valueOf(phone_mobile));
		txt_alias.sendKeys(alias);

	}

	public void click_btn_submit() {
		btn_submit.click();
	}

	public String name() {
		return sheet.getRow(2).getCell(1).getStringCellValue();
	}

	public String surname() {
		return sheet.getRow(3).getCell(1).getStringCellValue();
	}

}
