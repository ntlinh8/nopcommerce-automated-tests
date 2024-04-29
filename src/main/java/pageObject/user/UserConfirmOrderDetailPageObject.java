package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.user.UserConfirmOrderPageUI;

public class UserConfirmOrderDetailPageObject extends BasePage {
	WebDriver driver;

	UserConfirmOrderDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getBillingAddressById(String fieldClassName) {
		waitForElementVisible(driver, UserConfirmOrderPageUI.BILLING_ADDRESS_INFOR_BY_CLASS, fieldClassName);
		return getElementText(driver, UserConfirmOrderPageUI.BILLING_ADDRESS_INFOR_BY_CLASS, fieldClassName);
	}

	public String getShippingAddressById(String fieldClassName) {
		waitForElementVisible(driver, UserConfirmOrderPageUI.SHIPPING_ADDRESS_INFOR_BY_CLASS, fieldClassName);
		return getElementText(driver, UserConfirmOrderPageUI.SHIPPING_ADDRESS_INFOR_BY_CLASS, fieldClassName);
	}

	public String getProductSkuByProductName(String productName) {
		waitForElementVisible(driver, UserConfirmOrderPageUI.DYNAMIC_SKU_BY_PRODUCT_NAME, productName);
		return getElementText(driver, UserConfirmOrderPageUI.DYNAMIC_SKU_BY_PRODUCT_NAME, productName);
	}
	
	public String getSubTotalPriceByProductName(String productName) {
		waitForElementVisible(driver, UserConfirmOrderPageUI.DYNAMIC_SUBTOTAL_BY_PRODUCT_NAME, productName);
		return getElementText(driver, UserConfirmOrderPageUI.DYNAMIC_SUBTOTAL_BY_PRODUCT_NAME, productName);
	}
	public String getQuantityByProductName(String productName) {
		waitForElementVisible(driver, UserConfirmOrderPageUI.DYNAMIC_QUANTITY_BY_PRODUCT_NAME, productName);
		return getElementText(driver, UserConfirmOrderPageUI.DYNAMIC_QUANTITY_BY_PRODUCT_NAME, productName);
	}

	public String getCartOption() {
		waitForElementVisible(driver, UserConfirmOrderPageUI.CART_OPTION);
		return getElementText(driver, UserConfirmOrderPageUI.CART_OPTION);
	}

	public String getRowOfCartTotalTableByClass(String rowClassName) {
		waitForElementVisible(driver, UserConfirmOrderPageUI.DYNAMIC_CART_TOTAL_ROW_BY_CLASS_NAME, rowClassName);
		return getElementText(driver, UserConfirmOrderPageUI.DYNAMIC_CART_TOTAL_ROW_BY_CLASS_NAME, rowClassName);
	}

	public String getOrderStatusMessage() {
		waitForElementVisible(driver, UserConfirmOrderPageUI.ORDER_SUCCESS_TITLE_MESSAGE);
		return getElementText(driver, UserConfirmOrderPageUI.ORDER_SUCCESS_TITLE_MESSAGE);
	}

	public String getOrderNumberText() {
		waitForElementVisible(driver, UserConfirmOrderPageUI.ORDER_NUMBER_TEXT);
		String orderNumberMessage = getElementText(driver, UserConfirmOrderPageUI.ORDER_NUMBER_TEXT);
		return orderNumberMessage.substring(orderNumberMessage.length()-4, orderNumberMessage.length());
	}

}