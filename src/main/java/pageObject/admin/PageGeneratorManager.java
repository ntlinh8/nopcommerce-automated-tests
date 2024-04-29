package pageObject.admin;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	static public AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}
	
	static public AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}
	
	static public AdminCustomerPageObject getAdminCustomerPage(WebDriver driver) {
		return new AdminCustomerPageObject(driver);
	}
	
	static public AdminProductPageObject getAdminProductPagePage(WebDriver driver) {
		return new AdminProductPageObject(driver);
	}
	
}