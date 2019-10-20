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

public class WallethubJoinPage {

	private static final Logger log = LoggerHandler.getLogger(WallethubJoinPage.class);

	private GenericHandlers handlers;
	private WaitHandler wait;
	WebDriver driver;
	
	@FindBy(name="em")
	WebElement username;
	
	@FindBy(name="pw")
	WebElement password;
	
	@FindBy(css="button[class='btn blue center reg-tabs-bt touch-element-cl']")
	WebElement login;
	
	
	public WallethubJoinPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		handlers = new GenericHandlers(this.driver);
		wait =  new WaitHandler(this.driver);
	}
	
	public WallethubCompanyPage login(String uname, String pwd) {
		log.info("Login into Wallet hub portal");
		wait.waitForElementToBeClickable(ObjectRepository.reader.getExplicitWait(), username);
		handlers.enterData(username, uname);
		handlers.enterData(password, pwd);
		handlers.clickElement(login);
		return new WallethubCompanyPage(this.driver);
	}
}
