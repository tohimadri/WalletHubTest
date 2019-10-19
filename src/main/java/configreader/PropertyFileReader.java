package configreader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;

import browser.BrowserType;
import utility.ResourceHandler;

public class PropertyFileReader implements ConfigReader {

	private Properties prop;

	public PropertyFileReader() {
		prop = new Properties();
		try {
			prop.load(ResourceHandler.getResourcePathInputStream("\\resources\\configfile\\" + "config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public BrowserType getBrowser() {
		return BrowserType.valueOf(prop.getProperty("Browser"));
	}
	
	@Override
	public int getPageLoadTimeOut() {
		return Integer.parseInt(prop.getProperty("PageLoadTimeOut"));
	}

	@Override
	public int getImplicitWait() {
		return Integer.parseInt(prop.getProperty("ImplicitWait"));
	}

	@Override
	public int getExplicitWait() {
		return Integer.parseInt(prop.getProperty("ExplicitWait"));
	}

	@Override
	public long getPollingTimeInMilliSecond() {
		return Long.parseLong(prop.getProperty("PollingTimeInMilliSecond"));
	}
	
	@Override
	public String getUsername() {
		return prop.getProperty("Username");
	}

	@Override
	public String getPassword() {
		return prop.getProperty("Password");
	}
	
	public Level getLoggerLevel() {
		switch (prop.getProperty("Logger.Level")) {
		case "DEBUG":
			return Level.DEBUG;
		case "INFO":
			return Level.INFO;
		case "WARN":
			return Level.WARN;
		case "ERROR":
			return Level.ERROR;
		case "FATAL":
			return Level.FATAL;
		}
		return Level.ALL;
	}

}