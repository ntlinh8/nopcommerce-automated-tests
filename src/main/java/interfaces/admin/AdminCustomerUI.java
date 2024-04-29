package interfaces.admin;

public class AdminCustomerUI {
	public static final String DYNAMIC_MENU_LABEL = "xpath=//div[@class='card-title' and contains(string(),'%s')]";
	public static final String SEARCH_BUTTON = "css=button#search-customers";
	public static final String CUSTOMER_NUMBER = "xpath=//table[@id='customers-grid']/tbody/tr";
	public static final String CUSTOMER_INFOR_ROW_BY_EMAIL_AND_FULLNAME = "xpath=//table[@id='customers-grid']/tbody//td[text()='%s']/parent::tr//td[text()='%s']";
	public static final String CUSTOMER_ROLE_PARENT_DROPDOWN = "xpath=//ul[@id='SelectedCustomerRoleIds_taglist']/parent::div";
	public static final String CUSTOMER_ROLE_CHILD_DROPDOWN = "xpath=//ul[@id='SelectedCustomerRoleIds_listbox']/li";
	public static final String DELETE_CUSTOMER_ROLE_BY_LABEL = "xpath=//ul[@id='SelectedCustomerRoleIds_taglist']//span[text()='%s']/following-sibling::span";
	public static final String DYNAMIC_SELECTED_CUSTOM_ROLE_OPTION= "xpath=//ul[@id='SelectedCustomerRoleIds_taglist']//span[text()='%s']";
	public static final String ADDRESS_INFORMATION= "xpath=//table[@id='customer-addresses-grid']/tbody/tr";
	public static final String NO_ADDRESS_IN_TABLE_MESSAGE= "xpath=//table[@id='customer-addresses-grid']/tbody//td[@class='dataTables_empty']";
}