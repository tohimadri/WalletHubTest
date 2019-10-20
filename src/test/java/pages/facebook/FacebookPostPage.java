package pages.facebook;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import wrappers.GenericHandlers;
import wrappers.WaitHandler;

public class FacebookPostPage {

	private static final Logger log = LoggerHandler.getLogger(FacebookPostPage.class);
	private GenericHandlers handlers;
	private WaitHandler wait;
	WebDriver driver;
	
	@FindBy(css="div._1p1t")
	WebElement newPost;
	
	@FindBy(css="div#pagelet_composer")
	WebElement postDialog;
	
	@FindBy(xpath="//div[@id='pagelet_composer']//following::div[@class='_1p1v']")
	WebElement writePost;
	
	@FindBy(css="button[data-testid='react-composer-post-button']")
	WebElement postBtn;
	
	public FacebookPostPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		handlers = new GenericHandlers(this.driver);
		wait = new WaitHandler(this.driver);
	}
	
	public FacebookPostPage writeNewPost(String post) {
		log.info("Clicking on new post text area");
		wait.waitForElementToBeClickable(ObjectRepository.reader.getExplicitWait(), newPost);
		handlers.clickElement(newPost);
		wait.waitForElementToBeVisible(postDialog, 30);
		wait.waitForElementToBeClickable(ObjectRepository.reader.getExplicitWait(), writePost);
		handlers.enterData(writePost, post);
		return this;
	}
	
	public void clickPost() {
		log.info("Click Post button");
		wait.waitForElementToBeClickable(30, postBtn);
		handlers.clickElement(postBtn);
	}
}
