package interfaces.user;

public class UserAddressesUI {
	public static final String ADD_NEW_BUTTON = "xpath=//button[contains(@class, 'add-address-button')]";
	public static final String SAVE_BUTTON = "xpath=//button[contains(@class, 'save-address-button')]";
	public static final String TOAST_MESSAGE = "xpath=//div[@id='bar-notification']//p";
	public static final String CLOSE_TOAST_MESSAGE_BUTTON = "xpath=//div[@id='bar-notification']//span[@class='close']";
	public static final String DYNAMIC_MESSAGE_BY_CLASS = "xpath=//li[@class='%s']";
}
