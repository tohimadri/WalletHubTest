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

public class WallethubNewReview {

	private static final Logger log = LoggerHandler.getLogger(WallethubNewReview.class);
	
	private GenericHandlers handlers;
	private WaitHandler wait;
	WebDriver driver;
	
	@FindBy(css="h4.wrev-prd-name")
	WebElement insuranceCompany;
	
	@FindBy(xpath="//span[contains(text(),'Select...')]")
	WebElement dropDownList;
	
	@FindBy(xpath="//li[contains(text(),'Health Insurance')]")
	WebElement healthInsurance;
	
	@FindBy(css="textarea.textarea.wrev-user-input.validate")
	WebElement review;
	
	@FindBy(css="div[class='sbn-action semi-bold-font btn fixed-w-c tall']")
	WebElement submit;
	
	public WallethubNewReview(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		handlers = new GenericHandlers(this.driver);
		wait = new WaitHandler(this.driver);
	}
	
	public String getCompanyText() {
		log.info("Verifying Company Text");
		wait.waitForElementToBeVisible(insuranceCompany, ObjectRepository.reader.getExplicitWait());
		return handlers.getText(insuranceCompany);
	}
	
	public WallethubNewReview clickInsuranceDropdown() {
		log.info("Clicking insurance list dropdown");
		handlers.clickElement(dropDownList);
		return this;
	}
	
	public WallethubNewReview selectHealthInsurance() {
		log.info("Selecting Health Insurance");
		handlers.clickElement(healthInsurance);
		return this;
	}
	
	public WallethubNewReview writeReview(String reviewMsg) {
		log.info("Writing review comment");
		handlers.enterData(review, reviewMsg);
		return this;
	}
	
	public WallethubLoginPage clickSubmitReview() {
		log.info("Submitting review");
		handlers.clickElement(submit);
		return new WallethubLoginPage(this.driver);
	}
}