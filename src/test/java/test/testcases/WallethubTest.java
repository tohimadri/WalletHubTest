package test.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import configreader.ObjectRepository;
import configreader.PropertyFileReader;
import pages.wallethub.WallethubCompanyPage;
import testcases.common.TC_Common;
import utility.UtilityClass;

public class WallethubTest extends TC_Common {

	static final String reviewMessage= UtilityClass.getReviewMessage();
	
	@Parameters({"url"})
	@BeforeClass
	public void setData(String currentUrl) {
		ObjectRepository.reader = new PropertyFileReader();
		browser = ObjectRepository.reader.getBrowser();
		url = currentUrl;
	}

	@Test
	public void createNewReview() {
		String uName = ObjectRepository.reader.getUsername();
		String pwd = ObjectRepository.reader.getPassword();
		
		WallethubCompanyPage walletHub = new WallethubCompanyPage(driver)
		.goToReviewSection()
		.hoverOverStar()
		.clickInsuranceDropdown()
		.selectHealthInsurance()
		.writeReview(reviewMessage)
		.clickSubmitReview()
		.clickLoginLink()
		.enterUsername(uName)
		.enterPassword(pwd)
		.clickLoginBtn();
		
		String reviewComment = walletHub.getReviewComments();
		Assert.assertEquals(reviewComment.trim(), reviewMessage.trim());
	}
}