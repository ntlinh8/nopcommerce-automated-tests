package interfaces.admin;

public class AdminProductPageUI {
	public static final String DYNAMIC_COLUMN_INDEX_BY_LABEL = "xpath=//th[text()='%s']/preceding-sibling::th";
	public static final String DYNAMIC_SHEET_BY_ROW_INDEX_AND_COLUMN_LABEL = "xpath=//table[@id='products-grid']/tbody/tr[%s]/td[%s]";
	public static final String PUBLISH_PRODUCT_BY_PRODUCT_NAME_AND_COLUMN_INDEX = "xpath=//table[@id='products-grid']//td[text()='%s']/parent::tr/td[%s]";
	public static final String NO_ITEM_MESSAGE = "xpath=//table[@id='products-grid']//td[@class='dataTables_empty']";
}