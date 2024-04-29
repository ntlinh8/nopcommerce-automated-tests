package interfaces.admin;

public class AdminAbstractPageUI {
	public static final String ADMIN_ALERT_MESSAGE = "xpath=//div[contains(@class, 'alert')]";
	public static final String ADMIN_CLOSE_ALERT_MESSAGE = ADMIN_ALERT_MESSAGE + "//button";
	public static final String DYNAMIC_BUTTON_LINK = "xpath=//a[contains(string(),'%s')]";
	public static final String DYNAMIC_BUTTON = "xpath=//button[contains(string(),'%s')]";
	public static final String DYNAMIC_LEFT_MENU_LINK_BY_LABEL = "xpath=//li[@class='nav-item has-treeview']/a//p[contains(text(),'%s')]/preceding-sibling::i";
	public static final String DYNAMIC_SELECTED_LEFT_MENU_LINK_BY_LABEL = "xpath=//li[@class='nav-item has-treeview menu-is-opening menu-open']/a//p[contains(text(),'%s')]/preceding-sibling::i";
	public static final String DYNAMIC_SUB_MENU_LINK_BY_LABEL = "xpath=//li[@class='nav-item has-treeview menu-is-opening menu-open']//ul[@class='nav nav-treeview']//p[contains(text(), '%s')]";
}