package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import interfaces.user.UserProductDetailUI;

public class UserProductDetailObject extends BasePage{
	WebDriver driver;

	UserProductDetailObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserAddProductReviewObject clickToAddYourReviewLink() {
		waitForElementVisible(driver, UserProductDetailUI.ADD_YOUR_REVIEWS_LINK);
		clickToElement(driver, UserProductDetailUI.ADD_YOUR_REVIEWS_LINK);
		return PageGeneratorManager.getUserAddProductReviewPage(driver);
	}

	public void clickToButtonByTextAndProductName(String labelText) {
		waitForElementVisible(driver, UserProductDetailUI.DYNAMIC_BUTTON_BY_LABEL, labelText);
		clickToElement(driver, UserProductDetailUI.DYNAMIC_BUTTON_BY_LABEL, labelText);
	}


	





	

}
