package test.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import configreader.ObjectRepository;
import configreader.PropertyFileReader;
import pages.facebook.FacebookLoginPage;
import testcases.common.TC_Common;

public class FacebookTest extends TC_Common {

	@Parameters({"url"})
	@BeforeClass
	public void setData(String currentUrl) {
		ObjectRepository.reader = new PropertyFileReader();
		browser = ObjectRepository.reader.getBrowser();
		url = currentUrl;
	}
	
	@Test
	public void createFacebookPost() {
		String uName = ObjectRepository.reader.getFacebookUname();
		String pwd = ObjectRepository.reader.getFacebookPwd();
		
		new FacebookLoginPage(driver)
			.enterUsername(uName)
			.enterPassword(pwd)
			.clickLogin()
			.writeNewPost("Hello World")
			.clickPost();
	}
}
