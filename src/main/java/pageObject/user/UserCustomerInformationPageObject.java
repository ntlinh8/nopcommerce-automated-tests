package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.user.UserCustomerInformationUI;

public class UserCustomerInformationPageObject extends BasePage{
	WebDriver driver;

	UserCustomerInformationPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToGenderRadio() {
		waitForElementVisible(driver, UserCustomerInformationUI.FEMALE_GENDER_RADIO_BUTTON);
		clickToElement(driver, UserCustomerInformationUI.FEMALE_GENDER_RADIO_BUTTON);
	}
	
	public void clickToSaveButton() {
		waitForElementVisible(driver, UserCustomerInformationUI.SAVE_BUTTON);
		clickToElement(driver, UserCustomerInformationUI.SAVE_BUTTON);
	}

	public String getToastMessage() {
		waitForElementVisible(driver, UserCustomerInformationUI.TOAST_MESSAGE);
		return getElementText(driver, UserCustomerInformationUI.TOAST_MESSAGE);
	}

	public boolean isFemaleGender() {
		waitForElementVisible(driver, UserCustomerInformationUI.FEMALE_GENDER_RADIO_BUTTON);
		return isElementSelected(driver, UserCustomerInformationUI.FEMALE_GENDER_RADIO_BUTTON);
	}

	
}
