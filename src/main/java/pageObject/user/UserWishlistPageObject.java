package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.user.UserWishlistUI;

public class UserWishlistPageObject extends BasePage {
	WebDriver driver;

	UserWishlistPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getSheetTextByColumnLabelAndRowIndex(String columnLabel, String rowIndex) {
		waitForElementVisible(driver, UserWishlistUI.INDEX_OF_COLUMN_BY_LABEL, columnLabel);
		int columnIndex = getElementSize(driver, UserWishlistUI.INDEX_OF_COLUMN_BY_LABEL, columnLabel) + 1;
		return getElementText(driver, UserWishlistUI.DYNAMIC_SHEET_BY_COLUMN_LABEL_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
	}

	public void clickToShareInformationLink() {
		waitForElementClickable(driver, UserWishlistUI.SHARE_WISHLIST_LINK);
		clickToElement(driver, UserWishlistUI.SHARE_WISHLIST_LINK);
	}

	public void clickToCheckboxByProductName(String productName) {
		waitForElementClickable(driver, UserWishlistUI.DYNAMIC_SELECT_CHECKBOX_BY_PRODUCT_NAME, productName);
		clickToElement(driver, UserWishlistUI.DYNAMIC_SELECT_CHECKBOX_BY_PRODUCT_NAME, productName);
	}
	
	public void clickToRemoveButtonByProductName(String productName) {
		waitForElementClickable(driver, UserWishlistUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME, productName);
		clickToElement(driver, UserWishlistUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME, productName);
	}

}