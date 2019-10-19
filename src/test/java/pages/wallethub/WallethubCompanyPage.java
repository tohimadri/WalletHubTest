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

public class WallethubCompanyPage {

	private static final Logger log = LoggerHandler.getLogger(WallethubCompanyPage.class);
	private GenericHandlers handlers;
	private WaitHandler wait;
	WebDriver driver;
	
	@FindBy(xpath="//span[@class='nav-txt'][contains(text(),'Reviews')]")
	WebElement review;
	
	@FindBy(xpath="(//review-star[@class='rvs-svg']//*[4])[1]")
	WebElement rating;
	
	@FindBy(xpath="//article[@class='rvtab-citem rvtab-item-user ng-enter-element']//div[@class='rvtab-ci-content with-links text-select']")
	WebElement reviewComments;
	
	public WallethubCompanyPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		handlers = new GenericHandlers(this.driver);
		wait = new WaitHandler(this.driver);
	}
	
	public WallethubCompanyPage goToReviewSection() {
		log.info("Go to review section");
		handlers.clickElement(review);
		return this;
	}
	
	public String getReviewComments() {
		log.info("Verifying review comments");
		wait.waitForElementToBeVisible(reviewComments, ObjectRepository.reader.getExplicitWait());
		return handlers.getText(reviewComments);
	}
	
	public WallethubNewReview hoverOverStar() {
		log.info("Selecting rating");
		wait.waitForElementToBeVisible(rating, ObjectRepository.reader.getExplicitWait());
		handlers.mouseOver(rating);
		handlers.clickElement(rating);
		return new WallethubNewReview(this.driver);
	}
}
