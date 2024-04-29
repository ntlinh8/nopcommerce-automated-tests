package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.user.UserAddressesUI;

public class UserAddAddressesObject extends BasePage{
	WebDriver driver;

	UserAddAddressesObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickAddNewButton() {
		waitForElementClickable(driver, UserAddressesUI.ADD_NEW_BUTTON);
		clickToElement(driver, UserAddressesUI.ADD_NEW_BUTTON);
	}

	public String getMessageByClass(WebDriver driver, String classname) {
		waitForElementVisible(driver, UserAddressesUI.DYNAMIC_MESSAGE_BY_CLASS, classname);
		return getElementText(driver, UserAddressesUI.DYNAMIC_MESSAGE_BY_CLASS, classname);
	}

	public String getToastMessage() {
		waitForElementVisible(driver, UserAddressesUI.TOAST_MESSAGE);
		return getElementText(driver, UserAddressesUI.TOAST_MESSAGE);
	}

	public void clickToCloseToastMessageButton() {
		waitForElementClickable(driver, UserAddressesUI.CLOSE_TOAST_MESSAGE_BUTTON);
		clickToElement(driver, UserAddressesUI.CLOSE_TOAST_MESSAGE_BUTTON);
	}

	public void clickSaveButton() {
		waitForElementClickable(driver, UserAddressesUI.SAVE_BUTTON);
		clickToElement(driver, UserAddressesUI.SAVE_BUTTON);
	}
}
