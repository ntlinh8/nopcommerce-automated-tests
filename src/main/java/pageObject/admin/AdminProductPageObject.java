package pageObject.admin;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.admin.AdminProductPageUI;

public class AdminProductPageObject extends BasePage{
	WebDriver driver;

	AdminProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getSheetByColumnLabelAndRowIndex(String columnLabel, String rowIndex) {
		waitForAllElementVisible(driver, AdminProductPageUI.DYNAMIC_COLUMN_INDEX_BY_LABEL, columnLabel);
		int columnIndex = getElementSize(driver, AdminProductPageUI.DYNAMIC_COLUMN_INDEX_BY_LABEL, columnLabel) + 1;
		waitForElementVisible(driver, AdminProductPageUI.DYNAMIC_SHEET_BY_ROW_INDEX_AND_COLUMN_LABEL, rowIndex, String.valueOf(columnIndex));
		return getElementText(driver, AdminProductPageUI.DYNAMIC_SHEET_BY_ROW_INDEX_AND_COLUMN_LABEL, rowIndex, String.valueOf(columnIndex));
	}

	public boolean isPublishedProductByName(String lenovoPC) {
		waitForAllElementVisible(driver, AdminProductPageUI.DYNAMIC_COLUMN_INDEX_BY_LABEL, "Published");
		int columnIndex = getElementSize(driver, AdminProductPageUI.DYNAMIC_COLUMN_INDEX_BY_LABEL, "Published") + 1;
		waitForElementVisible(driver, AdminProductPageUI.PUBLISH_PRODUCT_BY_PRODUCT_NAME_AND_COLUMN_INDEX, lenovoPC, String.valueOf(columnIndex));
		return isElementDisplayed(driver, AdminProductPageUI.PUBLISH_PRODUCT_BY_PRODUCT_NAME_AND_COLUMN_INDEX, lenovoPC, String.valueOf(columnIndex));
	}

	public String getNoItemMessage() {
		waitForElementVisible(driver, AdminProductPageUI.NO_ITEM_MESSAGE);
		return getElementText(driver, AdminProductPageUI.NO_ITEM_MESSAGE);
	}
	
	
}