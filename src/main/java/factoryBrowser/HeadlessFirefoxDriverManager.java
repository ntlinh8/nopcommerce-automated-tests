package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HeadlessFirefoxDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions optionsFirefox = new FirefoxOptions();
		optionsFirefox.addArguments("-headless");
		optionsFirefox.addArguments("window-size=1920x1080");
		return new FirefoxDriver(optionsFirefox);
	}

}
