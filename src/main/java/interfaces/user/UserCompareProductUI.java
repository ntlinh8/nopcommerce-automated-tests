package interfaces.user;

public class UserCompareProductUI {
	public static final String DYNAMIC_SHEET_BY_ROW_CLASS_AND_COLUMN_INDEX = "xpath=//table[@class='compare-products-table']/tbody/tr[@class='%s']/td[%s]";
	public static final String DYNAMIC_PRODUCT_NAME_TEXT = DYNAMIC_SHEET_BY_ROW_CLASS_AND_COLUMN_INDEX + "/a[text()='%s']";
	public static final String DYNAMIC_BUTTON_BY_BUTTON_NAME = DYNAMIC_SHEET_BY_ROW_CLASS_AND_COLUMN_INDEX + "/button[text()='%s']";
	public static final String DYNAMIC_PRICE_TEXT = DYNAMIC_SHEET_BY_ROW_CLASS_AND_COLUMN_INDEX + "[text()='%s']";
	public static final String CLEAR_LIST_BUTTON = "xpath=//a[@class='clear-list']";
}
