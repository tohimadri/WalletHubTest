package pages.wallethub;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import wrappers.GenericHandlers;
import wrappers.WaitHandler;

public class WallethubLoginPage {

	private static final Logger log = LoggerHandler.getLogger(WallethubNewReview.class);

	private GenericHandlers handlers;
	private WaitHandler wait;
	WebDriver driver;

	@FindBy(xpath = "//a[text()='Login']")
	WebElement loginLink;
	
	@FindBy(css="span[class='brgm-button brgm-signup']")
	WebElement login;

	@FindBy(name = "em")
	WebElement emailId;

	@FindBy(name = "pw1")
	WebElement password;

	@FindBy(css = "button[class='btn blue touch-element-cl']")
	WebElement loginBtn;

	public WallethubLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		handlers = new GenericHandlers(this.driver);
		wait =  new WaitHandler(this.driver);
	}

	public WallethubLoginPage clickLoginLink() {
		log.info("Clicking Login Link");
		wait.waitForElementToBeClickable(ObjectRepository.reader.getExplicitWait(), loginLink);
		handlers.clickElement(loginLink);
		return this;
	}

	public WallethubJoinPage clickLogin() {
		log.info("Clicking Login");
		handlers.clickElement(login);
		return new WallethubJoinPage(this.driver);
	}
	
	public WallethubLoginPage enterUsername(String email) {
		log.info("Entering username");
		wait.waitForElementToBeClickable(ObjectRepository.reader.getExplicitWait(), emailId);
		handlers.enterData(emailId, email);
		return this;
	}

	public WallethubLoginPage enterPassword(String pwd) {
		log.info("Entering password");
		wait.waitForElementToBeClickable(ObjectRepository.reader.getExplicitWait(), password);
		handlers.enterData(password, pwd);
		return this;
	}

	public WallethubCompanyPage clickLoginBtn() {
		log.info("Clicking Login Button");
		wait.waitForElementToBeClickable(ObjectRepository.reader.getExplicitWait(), loginBtn);
		handlers.clickElement(loginBtn);
		return new WallethubCompanyPage(this.driver);
	}
}
