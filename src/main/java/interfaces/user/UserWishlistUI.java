package interfaces.user;

public class UserWishlistUI {
	public static final String INDEX_OF_COLUMN_BY_LABEL = "xpath=//table[@class='cart']//th[text()='%s']/preceding-sibling::th";
	public static final String DYNAMIC_SHEET_BY_COLUMN_LABEL_AND_ROW_INDEX = "xpath=//table[@class='cart']/tbody/tr[%s]/td[%s]";
	public static final String SHARE_WISHLIST_LINK = "xpath=//div[@class='share-info']/a";
	public static final String DYNAMIC_SELECT_CHECKBOX_BY_PRODUCT_NAME = "xpath=//div[@class='page wishlist-page']//a[text()='%s']/parent::td/preceding-sibling::td[@class='add-to-cart']/input";
	public static final String DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME = "xpath=//div[@class='page wishlist-page']//a[text()='%s']/parent::td/following-sibling::td[@class='remove-from-cart']/button";
}
