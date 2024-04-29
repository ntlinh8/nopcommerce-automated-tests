package pageObject.admin;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage{
	WebDriver driver;
	
	AdminDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isDashboardHeaderDisplayed() {
		waitForElementVisible(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
		return isElementDisplayed(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
	}

	
}