/**
 * 
 */
package com.saalchallenge.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.logging.log4j.LogManager;

/**
 * @author mohitpuri
 *
 */
public class TestBase {
	
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties prop;
	public static String projectPath=System.getProperty("user.dir");
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest testEvent;
	public static Logger log= LogManager.getLogger();
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	
	public TestBase()
	{
		try {
			prop=new Properties();
			FileInputStream ip=new FileInputStream(projectPath+"/config.properties");
			
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public static void initialization()
	{
		
		String browserName=prop.getProperty("browser");
		
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", projectPath+"/chromedriver");
			driver=new ChromeDriver();
		}else if(browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", projectPath+"/geckodriver");
			driver=new FirefoxDriver();
		}else if(browserName.equals("safari"))
		{
			driver=new SafariDriver();
		}else if(browserName.equals("opera"))
		{
			driver=new OperaDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(com.saalchallenge.util.TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(com.saalchallenge.util.TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		log.info("Enter the URL");
		driver.get(prop.getProperty("url"));
		
	}

}
