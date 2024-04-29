package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.user.UserShoppingCartUI;

public class UserShoppingCartPageObject extends BasePage {
	WebDriver driver;

	UserShoppingCartPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserProductDetailObject clickEditButtonByProductName(String productName) {
		waitForElementClickable(driver, UserShoppingCartUI.DYNAMIC_EDIT_BUTTON_BY_PRODUCT_NAME, productName);
		clickToElement(driver, UserShoppingCartUI.DYNAMIC_EDIT_BUTTON_BY_PRODUCT_NAME, productName);
		return PageGeneratorManager.getUserProductDetailPage(driver);
	}
	
	public void clickRemoveButtonByProductName(String productName) {
		waitForElementClickable(driver, UserShoppingCartUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME, productName);
		clickToElement(driver, UserShoppingCartUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME, productName);
	}

	public void changeProductQuantityByProductName(String value, String productName) {
		waitForElementVisible(driver, UserShoppingCartUI.DYNAMIC_QUANTITY_TEXTBOX_BY_PRODUCT_NAME, productName);
		sendkeyToElement(driver, UserShoppingCartUI.DYNAMIC_QUANTITY_TEXTBOX_BY_PRODUCT_NAME, value, productName);
	}
	
	public String getSubTotalPriceByProductName(String lenovoPCName) {
		waitForElementVisible(driver, UserShoppingCartUI.DYNAMIC_SUBTOTAL_BY_PRODUCT_NAME, lenovoPCName);
		return getElementText(driver, UserShoppingCartUI.DYNAMIC_SUBTOTAL_BY_PRODUCT_NAME, lenovoPCName);
	}

	public boolean isShippingMethodDisplay() {
		waitForElementVisible(driver, UserShoppingCartUI.SHIPPING_METHOD_IN_ESTIMATE_SHIPPING_POPUP);
		return isElementDisplayed(driver, UserShoppingCartUI.SHIPPING_METHOD_IN_ESTIMATE_SHIPPING_POPUP);
	}

	public boolean isShippingMethodUndisplay() {
		waitForElementInvisible(driver, UserShoppingCartUI.SHIPPING_METHOD_IN_ESTIMATE_SHIPPING_POPUP);
		return isElementUndisplayed(driver, UserShoppingCartUI.SHIPPING_METHOD_IN_ESTIMATE_SHIPPING_POPUP);
	}

}