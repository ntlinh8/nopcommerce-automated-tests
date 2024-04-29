package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.user.UserOrderPageUI;

public class UserOrderPageObject extends BasePage {
	WebDriver driver;

	UserOrderPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isOrderDisplayByOrderID(String orderID) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_ORDER_ID, orderID);
		return isElementDisplayed(driver, UserOrderPageUI.DYNAMIC_ORDER_ID, orderID);
	}
	public void clickToDetailOrderByOrderID(String orderID) {
		waitForElementClickable(driver, UserOrderPageUI.DYNAMIC_DETAIL_BUTTON_BY_ORDER_ID, orderID);
		clickToElement(driver, UserOrderPageUI.DYNAMIC_DETAIL_BUTTON_BY_ORDER_ID, orderID);
	}
	
	public String getBillingAddressById(String fieldClassName) {
		waitForElementVisible(driver, UserOrderPageUI.BILLING_ADDRESS_INFOR_BY_CLASS, fieldClassName);
		return getElementText(driver, UserOrderPageUI.BILLING_ADDRESS_INFOR_BY_CLASS, fieldClassName);
	}

	public String getShippingAddressById(String fieldClassName) {
		waitForElementVisible(driver, UserOrderPageUI.SHIPPING_ADDRESS_INFOR_BY_CLASS, fieldClassName);
		return getElementText(driver, UserOrderPageUI.SHIPPING_ADDRESS_INFOR_BY_CLASS, fieldClassName);
	}

	public String getProductSkuByProductName(String productName) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_SKU_BY_PRODUCT_NAME, productName);
		return getElementText(driver, UserOrderPageUI.DYNAMIC_SKU_BY_PRODUCT_NAME, productName);
	}
	
	public String getSubTotalPriceByProductName(String productName) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_SUBTOTAL_BY_PRODUCT_NAME, productName);
		return getElementText(driver, UserOrderPageUI.DYNAMIC_SUBTOTAL_BY_PRODUCT_NAME, productName);
	}
	public String getQuantityByProductName(String productName) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_QUANTITY_BY_PRODUCT_NAME, productName);
		return getElementText(driver, UserOrderPageUI.DYNAMIC_QUANTITY_BY_PRODUCT_NAME, productName);
	}

	public String getCartOption() {
		waitForElementVisible(driver, UserOrderPageUI.CART_OPTION);
		return getElementText(driver, UserOrderPageUI.CART_OPTION);
	}

	public String getRowOfCartTotalTableByClass(String label) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_CART_TOTAL_ROW_BY_LABEL, label);
		return getElementText(driver, UserOrderPageUI.DYNAMIC_CART_TOTAL_ROW_BY_LABEL, label);
	}

}