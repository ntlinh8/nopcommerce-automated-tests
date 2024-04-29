package factoryBrowser;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import common.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.firefoxdriver().setup();
		FirefoxProfile profile = new FirefoxProfile();
		File fileFf = new File(GlobalConstants.BROWSER_EXTENSION_PATH_FOLDER + "\\google_translate.xpi");
		profile.addExtension(fileFf);
		FirefoxOptions optionsFirefoxEx = new FirefoxOptions();
		optionsFirefoxEx.setProfile(profile);
		return new FirefoxDriver();
	}

} 