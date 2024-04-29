package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.user.UserLoginPageUI;
import io.qameta.allure.Step;

public class UserLoginPageObject extends BasePage {
	WebDriver driver;

	UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Click to login button")
	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
	public String getEmailNotFoundMessage() {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_NOT_FOUND_MESSAGE);
		return getElementText(driver, UserLoginPageUI.EMAIL_NOT_FOUND_MESSAGE);
	}

}
