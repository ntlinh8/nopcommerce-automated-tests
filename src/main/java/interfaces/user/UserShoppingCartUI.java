package interfaces.user;

public class UserShoppingCartUI {
	public static final String DYNAMIC_EDIT_BUTTON_BY_PRODUCT_NAME = UserAbstractPageUI.DYNAMIC_PRODUCT_NAME_IN_TABLE + "/following-sibling::div[@class='edit-item']/a";
	public static final String DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME = UserAbstractPageUI.DYNAMIC_PRODUCT_NAME_IN_TABLE + "/parent::td/following-sibling::td[@class='remove-from-cart']/button";
	public static final String DYNAMIC_QUANTITY_TEXTBOX_BY_PRODUCT_NAME = UserAbstractPageUI.DYNAMIC_PRODUCT_NAME_IN_TABLE + "/parent::td/following-sibling::td[@class='quantity']/input";
	public static final String DYNAMIC_SUBTOTAL_BY_PRODUCT_NAME = UserAbstractPageUI.DYNAMIC_PRODUCT_NAME_IN_TABLE + "/parent::td/following-sibling::td[@class='subtotal']/span";
	public static final String SHIPPING_METHOD_IN_ESTIMATE_SHIPPING_POPUP = "xpath=//div[@class='shipping-options-body']";
}
