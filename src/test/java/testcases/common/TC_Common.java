package testcases.common;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import browser.BrowserType;
import wrappers.GenericHandlers;

public class TC_Common extends GenericHandlers {

	public BrowserType browser;
	
	
	@BeforeMethod
	public void setUp() {
		invokeApp(browser);
	}
	
	@AfterMethod
	public void tearDown() {
		closeBrowser();
	}
}
