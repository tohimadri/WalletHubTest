package configreader;

import browser.BrowserType;

public interface ConfigReader {

	public BrowserType getBrowser();
	public String getUsername();
	public String getPassword();
	public int getPageLoadTimeOut();
	public int getImplicitWait();
	public int getExplicitWait();
	public long getPollingTimeInMilliSecond();
}
