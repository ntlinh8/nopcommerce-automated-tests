package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.user.UserSearchPageUI;

public class UserSearchPageObject extends BasePage {
	WebDriver driver;

	UserSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToSearchKeywordTextbox(String keyword) {
		waitForElementVisible(driver, UserSearchPageUI.SEARCH_TEXTBOX);
		sendkeyToElement(driver, UserSearchPageUI.SEARCH_TEXTBOX, keyword);
	}

	public void checkToAdvancedSearchCheckbox() {
		waitForElementVisible(driver, UserSearchPageUI.SEARCH_ADVANCED_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, UserSearchPageUI.SEARCH_ADVANCED_CHECKBOX);
	}
	
	public void uncheckToAdvancedSearchCheckbox() {
		waitForElementVisible(driver, UserSearchPageUI.SEARCH_ADVANCED_CHECKBOX);
		uncheckToDefaultCheckbox(driver, UserSearchPageUI.SEARCH_ADVANCED_CHECKBOX);
	}

	public void selectCategory(String categoryName) {
		waitForElementVisible(driver, UserSearchPageUI.CATEGORY_DROPDOWN);
		selectItemDefaultDropdown(driver, UserSearchPageUI.CATEGORY_DROPDOWN, categoryName);
	}

	public void checkToAutoSearchSubCategoryCheckbox() {
		waitForElementVisible(driver, UserSearchPageUI.AUTO_SEARCH_SUB_CATEGORY_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, UserSearchPageUI.AUTO_SEARCH_SUB_CATEGORY_CHECKBOX);
	}
	
	public void uncheckToAutoSearchSubCategoryCheckbox() {
		waitForElementVisible(driver, UserSearchPageUI.AUTO_SEARCH_SUB_CATEGORY_CHECKBOX);
		uncheckToDefaultCheckbox(driver, UserSearchPageUI.AUTO_SEARCH_SUB_CATEGORY_CHECKBOX);
	}

	public void selectManufacturer(String manufacturerName) {
		waitForElementVisible(driver, UserSearchPageUI.MANUFACTURER_DROPDOWN);
		selectItemDefaultDropdown(driver, UserSearchPageUI.MANUFACTURER_DROPDOWN, manufacturerName);
	}

	public void clickToSearchInProductDescriptionCheckbox() {
		waitForElementVisible(driver, UserSearchPageUI.SEARCH_IN_PRODUCT_DESCRIPTION_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, UserSearchPageUI.SEARCH_IN_PRODUCT_DESCRIPTION_CHECKBOX);
	}

	public void clickToSearchButton() {
		waitForElementVisible(driver, UserSearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, UserSearchPageUI.SEARCH_BUTTON);
	}

	public String getWarningMessage() {
		waitForElementVisible(driver, UserSearchPageUI.WARNING_MESSAGE);
		return getElementText(driver, UserSearchPageUI.WARNING_MESSAGE);
	}

	public String getResultItemCount() {
		waitForElementVisible(driver, UserSearchPageUI.PRODUCT_ITEM);
		return String.valueOf(getElementSize(driver, UserSearchPageUI.PRODUCT_ITEM));
	}

	public boolean isProductNameDisplay(String productName) {
		String productLocator = UserSearchPageUI.PRODUCT_TITLE.replace("****", productName);
		return isElementDisplayed(driver, productLocator);
	}
	
}