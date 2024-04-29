package interfaces.user;

public class UserSearchPageUI {
	public static final String SEARCH_TEXTBOX = "xpath=//input[@class='search-text']";
	public static final String SEARCH_ADVANCED_CHECKBOX = "xpath=//input[@id='advs']";
	public static final String SEARCH_BUTTON = "xpath=//button[contains(@class, 'search-button')]";
	public static final String CATEGORY_DROPDOWN = "xpath=//div[@id='advanced-search-block']//select[@id='cid']";
	public static final String AUTO_SEARCH_SUB_CATEGORY_CHECKBOX = "xpath=//input[@id='isc']";
	public static final String MANUFACTURER_DROPDOWN = "xpath=//div[@id='advanced-search-block']//select[@id='mid']";
	public static final String SEARCH_IN_PRODUCT_DESCRIPTION_CHECKBOX= "xpath=//input[@id='isc']";
	public static final String WARNING_MESSAGE = "xpath=//div[@class='products-wrapper']/div";
	public static final String PRODUCT_ITEM = "xpath=//div[@class='product-item']";
	public static final String PRODUCT_TITLE = "xpath=//h2[@class='product-title']/a[text()='****']";
}
