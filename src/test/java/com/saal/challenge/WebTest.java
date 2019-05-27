package com.saal.challenge;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.saalchallanges.pages.AccountPage;
import com.saalchallanges.pages.CartPage;
import com.saalchallanges.pages.CreateAccountPage;
import com.saalchallanges.pages.LoginPage;
import com.saalchallanges.pages.SigninPage;
import com.saalchallanges.pages.WomenClothingPage;
import com.saalchallenge.base.TestBase;

@Listeners(com.saalchallenge.base.TestNGListeners.class)

public class WebTest extends TestBase {

	String existingUserEmail = "hf_challenge_1234567@hf12345.com";
	String existingUserPassword = "12345678";
	SigninPage signInObj;
	CreateAccountPage createAcc;
	LoginPage lginPg;
	AccountPage myAccount;
	CartPage cartPg;
	WomenClothingPage womenClothing;

	public WebTest() throws IOException {
		super();
	}
	
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception{
		initialization();
		//Check if parameter passed from TestNG is 'firefox'
		//String browserName=prop.getProperty("browser");
		
		if(browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", projectPath+"/chromedriver");
			driver=new ChromeDriver();
		}else if(browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", projectPath+"/geckodriver");
			driver=new FirefoxDriver();
		}else if(browser.equals("safari"))
		{
			driver=new SafariDriver();
		}else if(browser.equals("opera"))
		{
			driver=new OperaDriver();
		}
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(com.saalchallenge.util.TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(com.saalchallenge.util.TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		
		log.info("Enter the URL");
		driver.get(prop.getProperty("url"));
		htmlReporter = new ExtentHtmlReporter("Extent.html");
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
	}



	@Test
	public void signInTest() {
		
		extent.attachReporter(htmlReporter);
		test= extent.createTest("SignIn Test", "This test is to check the login");
		test.log(Status.INFO, "This TEST has been started(status, details)");

		signInObj=new SigninPage();
		createAcc=new CreateAccountPage();
		lginPg=new LoginPage();
		myAccount=new AccountPage();
				
		signInObj.signin();
		logger.info("Sign In Button is clicked");
		test.pass("Sign In Button is clicked");

		lginPg.enter_email();
		logger.info("Entering Email Id to Create New Account");
		test.pass("Entering Email Id to Create New Account");
		
		lginPg.click_btn_create_account();
		logger.info("Clicking on Create Account Button");
		test.pass("Clicking on Create Account Button");
		
		createAcc.click_radio_btn_gender();
		logger.info("Selecting Gender Option");
		test.pass("Selecting Gender Option");

		createAcc.enter_name_and_pwd();
		logger.info("Entering Name and password details");
		test.pass("Entering Name and password details");

		createAcc.select_drd_day();
		logger.info("Selecting date from the dropdown");
		test.pass("Selecting date from the dropdown");

		createAcc.select_drd_month();
		logger.info("Selecting month from the dropdown");
		test.pass("Selecting month from the dropdown");

		createAcc.select_drd_year();
		logger.info("Selecting year from the dropdown");
		test.pass("Selecting year from the dropdown");

		createAcc.enter_all_address_details();
		logger.info("Entering all the address details such as such as addr, city, state, ph.no, comapny etc");
		test.pass("Entering all the address details such as such as addr, city, state, ph.no, comapny etc");

		createAcc.click_btn_submit();
		logger.info("Click on Submit button");
		test.pass("Click on Submit button");

		String get_txt_heading = myAccount.get_heading_label();
		logger.info("Fetching the heading of the page");
		test.pass("Fetching the heading of the page");

		String get_fullname = myAccount.get_fullname_label();
		logger.info("Fetching the fullname of the user");
		test.pass("Fetching the fullname of the user");

		Assert.assertEquals(myAccount.lbl_my_account_heading(), get_txt_heading, "Account Heading is not same");
		logger.info("Validating if the Heading of the page is right");
		test.pass("Validating if the Heading of the page is right");

		Assert.assertEquals(get_fullname, createAcc.name()+" "+createAcc.surname(), "Name and Surname is not same");
		logger.info("Validating if the Full Name of the user is correct");
		test.pass("Validating if the Full Name of the user is correct");

		Assert.assertEquals(myAccount.get_txt_account_info(), myAccount.lbl_txt_account_info(), "Account Info, text is not displayed");
		logger.info("Validating if the Account Information is displayed correctly");
		test.pass("Validating if the Account Information is displayed correctly");

		Assert.assertTrue(myAccount.is_disp_logout(), "Logout button is not displayed");
		logger.info("Validating if the logout button is displayed");
		test.pass("Validating if the logout button is displayed");

		Assert.assertTrue(myAccount.my_account_in_url(), "controller=my-account is not displayed in URL");
		logger.info("Validating if the 'controller=my-account' is appended in page URL");
		test.pass("Validating if the 'controller=my-account' is appended in page URL");
	}

	@Test
	public void logInTest() {
		extent.attachReporter(htmlReporter);
		test = extent.createTest("Login Test", "This test is to check the login");
		test.log(Status.INFO, "This TEST has been started(status, details)");

		signInObj=new SigninPage();
		createAcc=new CreateAccountPage();
		lginPg=new LoginPage();
		myAccount=new AccountPage();		

		signInObj.signin();
		logger.info("Sign In Button is clicked");
		test.pass("Sign In Button is clicked");

		lginPg.login();
		logger.info("LogIn is completed by entering username and password");
		test.pass("LogIn is completed by entering username and password");

		String get_txt_heading = myAccount.get_heading_label();
		logger.info("Fetching the Heading after successfull login");
		test.pass("Fetching the Heading after successfull login");

		String get_fullname = myAccount.get_fullname_label();
		logger.info("Fetching the Fullname of the sign in user");
		test.pass("Fetching the Fullname of the sign in user");

		Assert.assertEquals(myAccount.lbl_my_account_heading(), get_txt_heading, "Account Heading is not same");
		logger.info("Validating if the Heading displayed is correct");
		test.pass("Validating if the Heading displayed is correct");

		Assert.assertEquals(myAccount.get_fullname(), get_fullname, "User Name is not same");
		logger.info("Validating if the Fullname displayed is correct");	
		test.pass("Validating if the Fullname displayed is correct");

		Assert.assertEquals(myAccount.get_txt_account_info(), myAccount.lbl_txt_account_info(), "Account Info, text is not displayed");
		logger.info("Fetching & Validating if the Account Information is displayed correctly");	
		test.pass("Fetching & Validating if the Account Information is displayed correctly");

		Assert.assertTrue(myAccount.is_disp_logout(), "Logout button is not displayed");
		logger.info("Validating the logout button is displayed or not");	
		test.pass("Validating the logout button is displayed or not");

		Assert.assertTrue(myAccount.my_account_in_url(), "controller=my-account is not displayed in URL");
		logger.info("Validating if the 'controller=my-account' is appended in page URL");
		test.pass("Validating if the 'controller=my-account' is appended in page URL");

	}

	@Test
	public void checkoutTest() throws Exception {		
		extent.attachReporter(htmlReporter);
		test = extent.createTest("Checkout Test", "This test is to check the login");
		test.log(Status.INFO, "This TEST has been started");
		
		signInObj=new SigninPage();
		
		lginPg=new LoginPage();
		myAccount=new AccountPage();
		cartPg=new CartPage();
		womenClothing=new WomenClothingPage();
		

		signInObj.signin();
		logger.info("Sign In Button is clicked");
		test.pass("Sign In Button is clicked");

		lginPg.login();
		logger.info("LogIn is completed by entering username and password");
		test.pass("LogIn is completed by entering username and password");
		
		myAccount.click_btn_women();		
		logger.info("Click on Women button");
		test.pass("Click on Women button");
		
		womenClothing.select_dress();
		logger.info("Selecting the dress");
		test.pass("Selecting the dress");
		
		womenClothing.click_btn_submit();
		logger.info("Clicking on Submit button for checkout");
		test.pass("Clicking on Submit button for checkout");

		womenClothing.click_btn_proceed_checkout();	
		logger.info("Clicking on Checkout button to proceed");
		test.pass("Clicking on Checkout button to proceed");

		cartPg.click_proceed_checkout();
		logger.info("Clicking on Checkout button to proceed in the next page again");
		test.pass("Clicking on Checkout button to proceed in the next page again");

		cartPg.click_process_address();
		logger.info("Clicking on Next step button to process address");
		test.pass("Clicking on Next step button to process address");

		cartPg.chk_terms_conditions();
		logger.info("Selecting the Terms and conditions checkbox");
		test.pass("Selecting the Terms and conditions checkbox");

		cartPg.click_process_shipping();
		logger.info("Clicking on next to complete the shipping step");
		test.pass("Clicking on next to complete the shipping step");

		cartPg.click_btn_pay_by_bank_wire();
		logger.info("Select button - Pay by Bank Wire");
		test.pass("Select button - Pay by Bank Wire");

		cartPg.click_btn_confirm_order();	
		logger.info("Click on Confirm Order Button");
		test.pass("Click on Confirm Order Button");

		String get_txt_heading = cartPg.get_heading_label();
		logger.info("Fetching the text Order Confirmation");
		test.pass("Fetching the text Order Confirmation");

		Assert.assertEquals(cartPg.lbl_order_confirmation(), get_txt_heading,  "Order Confirmation text is not displayed");	
		logger.info("Verifying text in application if its ORDER Confirmation");
		test.pass("Verifying text in application if its ORDER Confirmation");

		Assert.assertTrue(cartPg.is_disp_shipping_step4(), "Shipping Step4 is not displayed");
		logger.info("Validating if the shipping section is displayed");
		test.pass("Validating if the shipping section is displayed");

		Assert.assertTrue(cartPg.is_disp_conformation_step_end(), "Confirmation Step End section is not displayed");
		logger.info("Validating if the Confirmation Step End section is displayed");
		test.pass("Validating if the Confirmation Step End section is displayed");

		Assert.assertEquals(cartPg.get_txt_order_confirmation(), cartPg.lbl_txt_order_confirmation(), "Order Completion Text is not displayed");
		logger.info("Verifying text ORDER Confirmation is displayed");
		test.pass("Verifying text ORDER Confirmation is displayed");

		Assert.assertTrue(cartPg.order_confirmation_in_url(), "The URL doesnt contains 'order-confirmation'");
		logger.info("Validating if the 'order-confirmation' is appended in page URL");
		test.pass("Validating if the 'order-confirmation' is appended in page URL");
	}
	
	@AfterTest
	public void tearDown(ITestResult result) {
		
		if(ITestResult.FAILURE==result.getStatus())
        {
			test.log(Status.FAIL, result.getThrowable());
            try 
            {
                TakesScreenshot ts=(TakesScreenshot)driver;
                File source=ts.getScreenshotAs(OutputType.FILE);
                String timestamp = String.valueOf(new Date().getTime());
                FileUtils.copyFile(source, new File("./ScreenShots/"+result.getName()+ timestamp +".png"));          
            } 
            catch (Exception e)
            {
                System.out.println("Exception while taking screenshot "+e.getMessage());
            }               
        }
		
		
		//To quit the Browser
		driver.quit();
		//Calling Flush writes Everything to the Log File
		extent.flush();
	}
}
