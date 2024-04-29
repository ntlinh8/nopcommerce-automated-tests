package interfaces.user;

public class UserOrderPageUI {
	public static final String DYNAMIC_ORDER_ID = "xpath=//strong[text()='Order Number: %s']";
	public static final String DYNAMIC_DETAIL_BUTTON_BY_ORDER_ID = DYNAMIC_ORDER_ID + "/parent::div/following-sibling::div[@class='buttons']/button";
	public static final String DYNAMIC_QUANTITY_BY_PRODUCT_NAME = UserAbstractPageUI.DYNAMIC_PRODUCT_TITLE + "/parent::em/parent::td/following-sibling::td[@class='quantity']/span";
	public static final String DYNAMIC_SUBTOTAL_BY_PRODUCT_NAME = UserAbstractPageUI.DYNAMIC_PRODUCT_TITLE + "/parent::em/parent::td/following-sibling::td[@class='subtotal']/span";
	public static final String DYNAMIC_SKU_BY_PRODUCT_NAME = UserAbstractPageUI.DYNAMIC_PRODUCT_TITLE + "/parent::em/parent::td/preceding-sibling::td[@class='sku']/span";
	public static final String DYNAMIC_CART_TOTAL_ROW_BY_LABEL = "xpath=//table[@class='cart-total']/tbody//label[contains(text(), '%s')]/parent::td/following-sibling::td/span";
	public static final String CART_OPTION = "xpath=//div[@class='section options']/div";
	public static final String BILLING_ADDRESS_INFOR_BY_CLASS = "xpath=//div[@class='billing-info-wrap']//li[@class='%s']";
	public static final String SHIPPING_ADDRESS_INFOR_BY_CLASS = "xpath=//div[@class='shipping-info-wrap']//li[@class='%s']";
}
