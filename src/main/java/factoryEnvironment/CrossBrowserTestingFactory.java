package factoryEnvironment;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import common.GlobalConstants;

public class CrossBrowserTestingFactory implements EnvironmentFactory{
	WebDriver driver;
	String osName;
	String browserName;
	
	public CrossBrowserTestingFactory(String osName, String browserName) {
		this.osName = osName;
		this.browserName = browserName;
	}
	
	@Override
	public WebDriver getBrowserDriver() {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("browserName", browserName);
		capability.setCapability("platform", osName);
		capability.setCapability("record_video", "true");
		capability.setCapability("name", "Run on " + osName + " and " + browserName + " with the lastest version");
		if(osName.contains("Windows")) {
			capability.setCapability("screenResolution", "1920x1080");
		}else {
			capability.setCapability("screenResolution", "2560x1600");
		}
		capability.setCapability("bitbar_apiKey", GlobalConstants.CROSS_AUTOMATE_KEY);
		try {
			driver = new RemoteWebDriver(new URL(GlobalConstants.CROSS_URL), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}

}
