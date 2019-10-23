package wrappers;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import browser.BrowserType;
import configreader.ObjectRepository;
import logger.LoggerHandler;
import utility.ResourceHandler;
import utility.UtilityClass;

public class GenericHandlers {

	private static final Logger log = LoggerHandler.getLogger(GenericHandlers.class);

	public static WebDriver driver;
	protected static Properties prop;
	
	public String url;

	public GenericHandlers() {
		
	}

	public GenericHandlers(WebDriver driver) {
		this.driver = driver;
	}

	public void unloadObjects() {
		prop = null;
	}

	/**
	 * This method will launch the browser in local machine and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * @param url - The url with http or https
	 * 
	 */
	public void invokeApp(BrowserType browser) {
		invokeApp(browser,false);
	}

	/**
	 * This method will launch the browser in grid node (if remote) and maximise the browser and set the
	 * wait for 30 seconds and load the url 
	 * @param url - The url with http or https
	 * 
	 */
	public void invokeApp(BrowserType browser, boolean bRemote) {

		log.info("Launching " + browser + " browser");
		String osName = System.getProperty("os.name");
		try {

			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(browser.toString());

			if (osName.contains("Window")) {
				dc.setPlatform(Platform.WINDOWS);
			} else if (osName.contains("Mac")) {
				dc.setPlatform(Platform.MAC);
			}

			switch (browser) {
			case Chrome:
				if (osName.contains("Window")) {
					System.setProperty("webdriver.chrome.driver", ResourceHandler.getResourcePath("\\resources\\drivers\\chromedriver.exe"));
				} else if (osName.contains("Mac")) {
					System.setProperty("webdriver.chrome.driver", ResourceHandler.getResourcePath("\\resources\\drivers\\chromedriver"));
				}
				//Pass the argument 1 to allow and 2 to block the notification alert
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				 
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);
				break;
			case Firefox:
				if (osName.contains("Window")) {
					System.setProperty("webdriver.gecko.driver", ResourceHandler.getResourcePath("\\resources\\drivers\\geckodriver.exe"));
				} else if (osName.contains("Mac")) {
					System.setProperty("webdriver.gecko.driver", ResourceHandler.getResourcePath("\\resources\\drivers\\geckodriver"));
				}
				driver = new FirefoxDriver();
				break;
			case Iexplorer:
				break;
			default:
				throw new RuntimeException("Provided browser name is not correct");
			}
		
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(ObjectRepository.reader.getImplicitWait(), TimeUnit.SECONDS);
			driver.get(url);
		} catch (Exception e) {
			log.error("The browser:" + browser + " could not be launched");
			log.error(e.getStackTrace());
		}
		log.info("The browser:" + browser + " launched successfully");
	}

	/**
	 * This method will enter the value to the text field
	 * @param element
	 * @param data
	 */
	public void enterData(WebElement element, String data) {
		try {
			//element.clear();
			element.sendKeys(data);
			log.info("The data: "+data+" entered successfully in field");
		} catch (NoSuchElementException e) {
			log.error("The data: "+data+" could not be entered in the field");
			log.error(e.getStackTrace());
		} catch (Exception e) {
			log.error("Unknown exception occured while entering "+data+" in the field");
			log.error(e.getStackTrace());
		}
	}

	/**
	 * This method will close current browser window
	 */
	public void closeBrowser() {
		try {
			if (driver!=null) {
				driver.close();
			}
		} catch (Exception e) {
			log.error("The browser could not be closed.");
			log.error(e.getStackTrace());
		}
	}

	/**
	 * This method clicks the WebElement
	 * @param element
	 */
	public void clickElement(WebElement element) {
		try{
			log.info("The element : "+element+" is clicked.");
			element.click();
		} catch (Exception e) {
			log.error("The element : "+element+" could not be clicked.");
			log.error(e.getStackTrace());
		}
	}

	/**
	 * This method will mouse over on the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be moused over
	 */
	public void mouseOver(WebElement element) {
		try{
			new Actions(driver).moveToElement(element).build().perform();
		} catch (Exception e) {
			log.error("The mouse over by xpath : "+element+" could not be performed.");
			log.error(e.getStackTrace());
		}
		log.info("The mouse over by xpath : "+element+" is performed.");
	}

	/**
	 * This method will return the text of the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element
	 */
	public String getText(WebElement element){
		String bReturn = "";
		try{
			return element.getText();
		} catch (Exception e) {
			log.error("The element with xpath: "+element+" could not be found.");
			log.error(e.getStackTrace());
		}
		return bReturn; 
	}
}