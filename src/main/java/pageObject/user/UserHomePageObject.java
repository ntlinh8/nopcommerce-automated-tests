package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import common.GlobalConstants;
import interfaces.user.UserHomePageUI;
import io.qameta.allure.Step;

public class UserHomePageObject extends BasePage {
	WebDriver driver;
	
	UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Click to Register link")
	public UserRegisterPageObject clickToRegisterLink() {
		waitForElementVisible(driver, UserHomePageUI.REGISTER_LINK);
		clickToElement(driver, UserHomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getUserRegisterPage(driver);
	}
	
	public UserLoginPageObject clickToLoginLink() {
		waitForElementVisible(driver, UserHomePageUI.LOGIN_LINK);
		clickToElement(driver, UserHomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}
	
	public UserMyAccountObject clickToMyAccountLink() {
		waitForElementVisible(driver, UserHomePageUI.MYACCOUNT_LINK);
		clickToElement(driver, UserHomePageUI.MYACCOUNT_LINK);
		return PageGeneratorManager.getUserMyAccountPage(driver);
	}
	
	@Step("My Dashboard page displayed")
	public boolean isMyAccountLinkDisplayed() {
		return isElementDisplayed(driver, UserHomePageUI.MYACCOUNT_LINK);
	}

	public void inputKeywordToSearchTextbox(String keyword) {
		waitForElementVisible(driver, UserHomePageUI.SEARCH_TEXTBOX);
		sendkeyToElement(driver, UserHomePageUI.SEARCH_TEXTBOX, keyword);
	}

	public UserProductListPageObject clickToSearchButton() {
		waitForElementClickable(driver, UserHomePageUI.SEARCH_BUTTON);
		clickToElement(driver, UserHomePageUI.SEARCH_BUTTON);
		return PageGeneratorManager.getUserProductListPage(driver);
	}
	
	public void openSearchPage() {
		openPageUrl(driver, GlobalConstants.PORTAL_URL + "search");
		SleepInSecond(2);
	}
	
	public void openRegisterPage() {
		openPageUrl(driver, GlobalConstants.PORTAL_URL + "register");
		SleepInSecond(2);
	}
	
	public void openLoginPage() {
		openPageUrl(driver, GlobalConstants.PORTAL_URL + "login");
		SleepInSecond(2);
	}

}