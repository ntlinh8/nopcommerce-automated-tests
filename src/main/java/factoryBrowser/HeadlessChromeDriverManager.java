package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HeadlessChromeDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions optionsChrome = new ChromeOptions();
		optionsChrome.addArguments("-headless");
		optionsChrome.addArguments("window-size=1920x1080");
		return new ChromeDriver(optionsChrome);
	}

}
