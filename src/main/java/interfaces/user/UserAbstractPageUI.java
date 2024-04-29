package interfaces.user;

public class UserAbstractPageUI {
	public static final String DYNAMIC_PAGE_IN_MY_ACCOUNT_AREA = "xpath=//a[text()='%s']";
	public static final String DYNAMIC_FOOTER_LINK = "xpath=//div[@class='footer-upper']//a[text()='%s']";
	public static final String DYNAMIC_HEADER_LINK_BY_CLASS = "xpath=//a[@class='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String DYNAMIC_TEXTAREA_BY_ID = "xpath=//textarea[@id='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "xpath=//select[@name='%s']";
	public static final String DYNAMIC_ERROR_MESSAGE_ID = "xpath=//span[@id='%s']";
	public static final String USER_ALERT_MESSAGE = "xpath=//div[@class='bar-notification success']/p[@class='content']";
	public static final String USER_CLOSE_ALERT_MESSAGE = "xpath=//div[@class='bar-notification success']/span[@class='close']";
	public static final String DYNAMIC_BUTTON_BY_LABEL = "xpath=//button[contains(text(),'%s')]";
	public static final String DYNAMIC_BUTTON_BY_CLASS = "xpath=//button[contains(@class,'%s')]";
	public static final String DYNAMIC_BUTTON_LINK_BY_LABEL = "xpath=//a[contains(text(),'%s')]";
	public static final String DYNAMIC_PRODUCT_NAME_IN_TABLE = "xpath=//a[@class='product-name' and text()='%s']";
	public static final String HEADER_MENU_BUTTON = "xpath=//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]";
	public static final String SUBMENU_BUTTON = "xpath=//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]/following-sibling::ul[@class='sublist first-level']//a[contains(text(),'%s')]";
	public static final String DYNAMIC_PRODUCT_TITLE = "xpath=//a[text()='%s']";
	public static final String DYNAMIC_BUTTON_IN_PRODUCT_ITEM_BY_PRODUCT_NAME = DYNAMIC_PRODUCT_TITLE + "//button[text()='%s']";
	public static final String TABLE_BODY_DATA = "xpath=//div[@class='%s']//div[@class='page-body']";
	public static final String DYNAMIC_CHECKBOX_RADIO_BUTTON_BY_LABEL = "xpath=//label[contaisn(text(),'%s')]/preceding-sibling::input";
	public static final String DYNAMIC_CHECKBOX_RADIO_BUTTON_BY_ID = "xpath=//input[@id='%s']";
	public static final String MINI_SHOPPING_CART = "xpath=//div[@class='mini-shopping-cart']";
	public static final String DYNAMIC_PRODUCT_NAME_IN_MINI_CART = MINI_SHOPPING_CART + "//div[@class='name']/a";
	public static final String ADD_PRODUCT_MESSAGE = MINI_SHOPPING_CART + "//div[@class='count']";
	public static final String PRODUCT_ATTRIBUTE = MINI_SHOPPING_CART + "//div[@class='attributes']";
	public static final String PRODUCT_UNIT_PRICE = MINI_SHOPPING_CART + "//div[@class='price']/span";
	public static final String PRODUCT_QUANTITY = MINI_SHOPPING_CART + "//div[@class='quantity']/span";
	public static final String PRODUCT_SUB_TOTAL = MINI_SHOPPING_CART + "//div[@class='totals']/strong";
	
}
