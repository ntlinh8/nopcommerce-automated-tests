package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.user.UserCompareProductUI;

public class UserCompareProduct extends BasePage {
	WebDriver driver;

	UserCompareProduct(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isClearListButtonDipslay() {
		waitForElementVisible(driver, UserCompareProductUI.CLEAR_LIST_BUTTON);
		return isElementDisplayed(driver, UserCompareProductUI.CLEAR_LIST_BUTTON);
	}

	public boolean isSheetTextDisplayByRowClassAndColumnIndex(String rowClassName, String columnIndex, String expectedValue) {
		waitForElementVisible(driver, UserCompareProductUI.DYNAMIC_PRODUCT_NAME_TEXT, rowClassName, columnIndex, expectedValue);
		return isElementDisplayed(driver, UserCompareProductUI.DYNAMIC_PRODUCT_NAME_TEXT, rowClassName, columnIndex, expectedValue);
	}

	public boolean isSheetTextUndisplayByRowClassAndColumnIndex(String rowClassName, String columnIndex, String expectedValue) {
		waitForElementInvisible(driver, UserCompareProductUI.DYNAMIC_PRODUCT_NAME_TEXT, rowClassName, columnIndex, expectedValue);
		return isElementUndisplayed(driver, UserCompareProductUI.DYNAMIC_PRODUCT_NAME_TEXT, rowClassName, columnIndex, expectedValue);
	}

	public void clickClearListButton() {
		waitForElementClickable(driver, UserCompareProductUI.CLEAR_LIST_BUTTON);
		clickToElement(driver, UserCompareProductUI.CLEAR_LIST_BUTTON);
	}

	public boolean isButtonDisplayByRowClassAndColumnIndex(String rowClassName, String columnIndex, String buttonName) {
		waitForElementVisible(driver, UserCompareProductUI.DYNAMIC_BUTTON_BY_BUTTON_NAME, rowClassName, columnIndex, buttonName);
		return isElementDisplayed(driver, UserCompareProductUI.DYNAMIC_BUTTON_BY_BUTTON_NAME, rowClassName, columnIndex, buttonName);
	}

	public boolean isPriceDisplayByRowClassAndColumnIndex(String rowClassName, String columnIndex, String price) {
		waitForElementVisible(driver, UserCompareProductUI.DYNAMIC_PRICE_TEXT, rowClassName, columnIndex, price);
		return isElementDisplayed(driver, UserCompareProductUI.DYNAMIC_PRICE_TEXT, rowClassName, columnIndex, price);
	}

}