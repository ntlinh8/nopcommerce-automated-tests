package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.user.UserChangePasswordUI;

public class UserChangePasswordPageObject extends BasePage{
	WebDriver driver;

	UserChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToChangePasswordButton() {
		waitForElementClickable(driver, UserChangePasswordUI.CHANGE_PASSWORD_BUTTON);
		clickToElement(driver, UserChangePasswordUI.CHANGE_PASSWORD_BUTTON);
	}

	public String getToastMessage() {
		waitForElementVisible(driver, UserChangePasswordUI.TOAST_MESSAGE);
		return getElementText(driver, UserChangePasswordUI.TOAST_MESSAGE);
	}

	public void clickToCloseToastMessageButton() {
		waitForElementClickable(driver, UserChangePasswordUI.CLOSE_TOAST_MESSAGE_BUTTON);
		clickToElement(driver, UserChangePasswordUI.CLOSE_TOAST_MESSAGE_BUTTON);
	}
	
}