package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.user.UserCheckoutPageUI;

public class UserCheckoutPageObject extends BasePage {
	WebDriver driver;

	UserCheckoutPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getPaymentInfomation() {
		waitForElementVisible(driver, UserCheckoutPageUI.PAYMENT_INFORMATION);
		return getElementText(driver, UserCheckoutPageUI.PAYMENT_INFORMATION);
	}


}
