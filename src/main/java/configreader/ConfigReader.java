package configreader;

import browser.BrowserType;

public interface ConfigReader {

	public BrowserType getBrowser();
	public String getWalletHubUsername();
	public String getWalletHubPwd();
	public String getFacebookUname();
	public String getFacebookPwd();
	public int getPageLoadTimeOut();
	public int getImplicitWait();
	public int getExplicitWait();
	public long getPollingTimeInMilliSecond();
}
