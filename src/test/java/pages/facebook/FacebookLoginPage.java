package pages.facebook;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import logger.LoggerHandler;
import wrappers.GenericHandlers;
import wrappers.WaitHandler;

public class FacebookLoginPage {

	private static final Logger log = LoggerHandler.getLogger(FacebookLoginPage.class);
	private GenericHandlers handlers;
	private WaitHandler wait;
	WebDriver driver;
	
	@FindBy(id="email")
	WebElement username;
	
	@FindBy(id="pass")
	WebElement password;
	
	@FindBy(css="input[type='submit']")
	WebElement logIn;
	
	public FacebookLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		handlers = new GenericHandlers(this.driver);
		wait = new WaitHandler(this.driver);
	}
	
	public FacebookLoginPage enterUsername(String uName) {
		log.info("Entering username");
		handlers.enterData(username, uName);
		return this;
	}
	
	public FacebookLoginPage enterPassword(String pwd) {
		log.info("Entering password");
		handlers.enterData(password, pwd);
		return this;
	}
	
	public FacebookPostPage clickLogin() {
		log.info("Clicking login button");
		handlers.clickElement(logIn);
		return new FacebookPostPage(this.driver);
	}
}
