package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;

public class UserRecentlyViewedProductPageObject extends BasePage {
	WebDriver driver;

	UserRecentlyViewedProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
