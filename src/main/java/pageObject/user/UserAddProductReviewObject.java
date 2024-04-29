package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.user.UserAddProductReviewUI;

public class UserAddProductReviewObject extends BasePage{
	WebDriver driver;

	UserAddProductReviewObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSubmitButton() {
		waitForElementClickable(driver, UserAddProductReviewUI.SUBMIT_REVIEW_BUTTON);
		clickToElement(driver, UserAddProductReviewUI.SUBMIT_REVIEW_BUTTON);
	}

	public String getAddReviewSuccessMessage() {
		waitForElementVisible(driver, UserAddProductReviewUI.ADD_REVIEW_SUCCESS_MESSAGE);
		return getElementText(driver, UserAddProductReviewUI.ADD_REVIEW_SUCCESS_MESSAGE);
	}
	
}
