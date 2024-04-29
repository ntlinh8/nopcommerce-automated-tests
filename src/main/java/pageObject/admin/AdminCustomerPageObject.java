package pageObject.admin;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.admin.AdminCustomerUI;

public class AdminCustomerPageObject extends BasePage{
	WebDriver driver;
	
	AdminCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, AdminCustomerUI.SEARCH_BUTTON);
		clickToElement(driver, AdminCustomerUI.SEARCH_BUTTON);
	}

	public boolean isCustomerInformationDisplayWithEmailAndName(String email, String fullName) {
		waitForElementVisible(driver, AdminCustomerUI.CUSTOMER_INFOR_ROW_BY_EMAIL_AND_FULLNAME, email, fullName);
		return isElementDisplayed(driver, AdminCustomerUI.CUSTOMER_INFOR_ROW_BY_EMAIL_AND_FULLNAME, email, fullName);
	}

	public void selectCustomerRole(String customerRole) {
		SelectCustomDropDown(driver, AdminCustomerUI.CUSTOMER_ROLE_PARENT_DROPDOWN, AdminCustomerUI.CUSTOMER_ROLE_CHILD_DROPDOWN, customerRole);
	}

	public boolean isCustomRoleExist(String customerRole) {
		waitForElementVisible(driver, AdminCustomerUI.DYNAMIC_SELECTED_CUSTOM_ROLE_OPTION, customerRole);
		return isElementDisplayed(driver, AdminCustomerUI.DYNAMIC_SELECTED_CUSTOM_ROLE_OPTION, customerRole);
	}

	public void clickDeleteCustomerRoleByLabel(String optionLabel) {
		waitForElementClickable(driver, AdminCustomerUI.DELETE_CUSTOMER_ROLE_BY_LABEL, optionLabel);
		clickToElement(driver, AdminCustomerUI.DELETE_CUSTOMER_ROLE_BY_LABEL, optionLabel);
	}

	public String getCustomerNumber() {
		waitForElementVisible(driver, AdminCustomerUI.CUSTOMER_NUMBER);
		return String.valueOf(getElementSize(driver, AdminCustomerUI.CUSTOMER_NUMBER));
	}
	
	public void scrollToMenuByLabel(String menuLabel) {
		scrollToElementOnTop(driver, AdminCustomerUI.DYNAMIC_MENU_LABEL, menuLabel);
		SleepInSecond(3);
	}
	
	public void clickMenuByLabel(String menuLabel) {
		scrollToMenuByLabel(menuLabel);
//		waitForElementClickable(driver, AdminCustomerUI.DYNAMIC_MENU_LABEL, menuLabel);
//		clickToElement(driver, AdminCustomerUI.DYNAMIC_MENU_LABEL, menuLabel);
		SleepInSecond(2);
		
	}

	public String getAddressInformation() {
		waitForElementVisible(driver, AdminCustomerUI.ADDRESS_INFORMATION);
		return getElementText(driver, AdminCustomerUI.ADDRESS_INFORMATION);
	}

	public String getNoAddressInTableMessage() {
		waitForElementVisible(driver, AdminCustomerUI.NO_ADDRESS_IN_TABLE_MESSAGE);
		return getElementText(driver, AdminCustomerUI.NO_ADDRESS_IN_TABLE_MESSAGE);
	}

}