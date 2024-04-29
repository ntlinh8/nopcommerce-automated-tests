package factoryBrowser;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import common.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.chromedriver().setup();
//		File fileCh = new File(GlobalConstants.BROWSER_EXTENSION_PATH_FOLDER + "\\google_translate.crx");
		ChromeOptions optionsChromeEx = new ChromeOptions();
//		optionsChromeEx.addExtensions(fileCh);
		optionsChromeEx.addArguments("--incognito");
		return new ChromeDriver(optionsChromeEx);
	}

}
