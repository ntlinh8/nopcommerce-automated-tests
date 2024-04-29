package factoryEnvironment;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import common.GlobalConstants;

public class BrowserstackFactory implements EnvironmentFactory{
	WebDriver driver;
	String osName;
	String osVersion;
	String browserName;
	String browserVersion;
	
	public BrowserstackFactory(String osName, String osVersion, String browserName, String browserVersion) {
		this.osName = osName;
		this.osVersion = osVersion;
		this.browserName = browserName;
		this.browserVersion = browserVersion;
	}
	
	@Override
	public WebDriver getBrowserDriver() {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("os", osName);
		capability.setCapability("os_version", osVersion);
		capability.setCapability("browser", browserName);
		capability.setCapability("browser_version", browserVersion);
		capability.setCapability("browserstack.debug", "true");
		capability.setCapability("resolution", "1920x1080");
		capability.setCapability("name", "Run on " + osName + " and " + browserName + " with version " + browserVersion);
		try {
			driver = new RemoteWebDriver(new URL(GlobalConstants.BROWSER_STACK_URL), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}