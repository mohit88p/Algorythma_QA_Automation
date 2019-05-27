package com.saal.challenge;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.saalchallanges.pages.SigninPage;
import com.saalchallenge.base.TestBase;

@Listeners(com.saalchallenge.base.TestNGListeners.class)

public class WebTest extends TestBase {

	String existingUserEmail = "hf_challenge_1234567@hf12345.com";
	String existingUserPassword = "12345678";
	SigninPage signInObj;

	public WebTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		htmlReporter = new ExtentHtmlReporter("Extent.html");
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@Test
	public void signInTest() {

		signInObj=new SigninPage();
		signInObj.signin();
		Random random = new Random();
		int randomValue = random.nextInt(900) + 100;
		System.out.println(randomValue);
		String email = "hf_challenge_" + randomValue + "@gmail.com";
		String name = "Firstname";
		String surname = "Lastname";
		driver.findElement(By.id("email_create")).sendKeys(email);
		driver.findElement(By.id("SubmitCreate")).click();
		driver.findElement(By.id("id_gender2")).click();
		System.out.println("helo");
		driver.findElement(By.id("customer_firstname")).sendKeys(name);
		driver.findElement(By.id("customer_lastname")).sendKeys(surname);
		driver.findElement(By.id("passwd")).sendKeys("Qwerty");
		Select select = new Select(driver.findElement(By.id("days")));
		select.selectByValue("1");
		select = new Select(driver.findElement(By.id("months")));
		select.selectByValue("1");
		select = new Select(driver.findElement(By.id("years")));
		select.selectByValue("2000");
		driver.findElement(By.id("company")).sendKeys("Company");
		driver.findElement(By.id("address1")).sendKeys("Qwerty, 123");
		driver.findElement(By.id("address2")).sendKeys("zxcvb");
		driver.findElement(By.id("city")).sendKeys("Qwerty");
		select = new Select(driver.findElement(By.id("id_state")));
		select.selectByVisibleText("Colorado");
		driver.findElement(By.id("postcode")).sendKeys("12345");
		driver.findElement(By.id("other")).sendKeys("Qwerty");
		driver.findElement(By.id("phone")).sendKeys("12345123123");
		driver.findElement(By.id("phone_mobile")).sendKeys("12345123123");
		driver.findElement(By.id("alias")).sendKeys("hf");
		driver.findElement(By.id("submitAccount")).click();

		WebElement heading= driver.findElement(By.cssSelector("h1"));


		assertEquals(heading.getText(), "MY ACCOUNT");
		assertEquals(driver.findElement(By.className("account")).getText(), name + " " + surname);
		assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
		assertTrue(driver.findElement(By.className("logout")).isDisplayed());
		assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
	}

	@Test(priority=2)
	public void logInTest() {
		String fullName = "Vikas Kumar";
		driver.findElement(By.className("login")).click();

		driver.findElement(By.id("email")).sendKeys(existingUserEmail);
		driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
		driver.findElement(By.id("SubmitLogin")).click();
		WebElement heading =driver.findElement(By.cssSelector("h1"));

		assertEquals("MY ACCOUNT", heading.getText());
		assertEquals(fullName, driver.findElement(By.className("account")).getText());
		assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
		assertTrue(driver.findElement(By.className("logout")).isDisplayed());
		assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
	}

	@Test(priority=3)
	public void checkoutTest() {

		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys(existingUserEmail);
		driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
		System.out.println("Click on pass");
		driver.findElement(By.id("SubmitLogin")).click();
		System.out.println("Click on pass123");
		driver.findElement(By.linkText("Women")).click();
		System.out.println("Click on pass1234");

		driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
		System.out.println("Click on pass88");
		//driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
		System.out.println("Click on pass00");
		driver.findElement(By.name("Submit")).click();
		System.out.println("Click on pass99");
		driver.findElement(By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")).click();
		driver.findElement(By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")).click();
		driver.findElement(By.name("processAddress")).click();
		driver.findElement(By.id("uniform-cgv")).click();

		driver.findElement(By.name("processCarrier")).click();
		driver.findElement(By.className("bankwire")).click();
		driver.findElement(By.xpath("//*[@id='cart_navigation']/button")).click();

		WebElement heading =driver.findElement(By.cssSelector("h1"));

		assertEquals("ORDER CONFIRMATION", heading.getText());
		assertTrue(driver.findElement(By.xpath("//li[@class='step_done step_done_last four']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//li[@id='step_end' and @class='step_current last']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//*[@class='cheque-indent']/strong")).getText().contains("Your order on My Store is complete."));
		assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		
		if(ITestResult.FAILURE==result.getStatus())
        {
			testEvent.log(Status.FAIL, result.getThrowable());
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
