package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;

public class UserMyAccountObject extends BasePage {
	WebDriver driver;

	UserMyAccountObject(WebDriver driver) {
		this.driver = driver;
	}

}