package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.user.UserMyProductReviewUI;

public class UserMyProductReviewObject extends BasePage{
	WebDriver driver;

	UserMyProductReviewObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getReviewTitle() {
		waitForElementVisible(driver, UserMyProductReviewUI.REVIEW_TITLE_TEXT);
		return getElementText(driver, UserMyProductReviewUI.REVIEW_TITLE_TEXT);
	}

	public String getReviewContent() {
		waitForElementVisible(driver, UserMyProductReviewUI.REVIEW_CONTENT_TEXT);
		return getElementText(driver, UserMyProductReviewUI.REVIEW_CONTENT_TEXT);
	}
	
}
