package pageObject.user;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	static public UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}
	
	static public UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}
	
	static public UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}
	
	static public UserMyAccountObject getUserMyAccountPage(WebDriver driver) {
		return new UserMyAccountObject(driver);
	}
	
	static public UserAddProductReviewObject getUserAddProductReviewPage(WebDriver driver) {
		return new UserAddProductReviewObject(driver);
	}
	
	static public UserAddAddressesObject getUserAddAddressPage(WebDriver driver) {
		return new UserAddAddressesObject(driver);
	}
	
	static public UserChangePasswordPageObject getUserChangePasswordPage(WebDriver driver) {
		return new UserChangePasswordPageObject(driver);
	}
	
	static public UserCustomerInformationPageObject getUserCustomerInformationPage(WebDriver driver) {
		return new UserCustomerInformationPageObject(driver);
	}
	
	static public UserMyProductReviewObject getUserMyProductReviewPage(WebDriver driver) {
		return new UserMyProductReviewObject(driver);
	}
	
	static public UserProductDetailObject getUserProductDetailPage(WebDriver driver) {
		return new UserProductDetailObject(driver);
	}
	
	static public UserSearchPageObject getUserSearchPage(WebDriver driver) {
		return new UserSearchPageObject(driver);
	}
	
	static public UserProductListPageObject getUserProductListPage(WebDriver driver) {
		return new UserProductListPageObject(driver);
	}
	
	static public UserWishlistPageObject getUserWishlistPage(WebDriver driver) {
		return new UserWishlistPageObject(driver);
	}
	
	static public UserShoppingCartPageObject getShoppingCartPage(WebDriver driver) {
		return new UserShoppingCartPageObject(driver);
	}
	
	static public UserCompareProduct getCompareProductPage(WebDriver driver) {
		return new UserCompareProduct(driver);
	}
	
	static public UserRecentlyViewedProductPageObject getRecentlyViewedProductPage(WebDriver driver) {
		return new UserRecentlyViewedProductPageObject(driver);
	}
	
	static public UserCheckoutPageObject getCheckoutPage(WebDriver driver) {
		return new UserCheckoutPageObject(driver);
	}
	
	static public UserConfirmOrderDetailPageObject getConfirmOrderDetailPage(WebDriver driver) {
		return new UserConfirmOrderDetailPageObject(driver);
	}
	
	static public UserOrderPageObject getOrderPageObject(WebDriver driver) {
		return new UserOrderPageObject(driver);
	}
}
