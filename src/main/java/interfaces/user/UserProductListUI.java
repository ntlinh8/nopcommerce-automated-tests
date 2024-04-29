package interfaces.user;

public class UserProductListUI {
	public static final String ALL_PRODUCT_TITLE = "xpath=//h2[@class='product-title']/a";
	public static final String ALL_PRODUCT_PRICE = "xpath=//div[@class='prices']/span";
	public static final String PRODUCT_TITLE_PAGE = "xpath=//div[@class='page-title']/h1";
	public static final String PRODUCT_SORT_DROPDOWN = "css=select#products-orderby";
	public static final String PAGING_BUTTON = "xpath=//div[@class='pager']";
	public static final String DYNAMIC_PAGING_BY_CLASS = PAGING_BUTTON + "//li[@class='%s']";
}
