package user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObject.user.PageGeneratorManager;
import pageObject.user.UserCompareProduct;
import pageObject.user.UserHomePageObject;
import pageObject.user.UserLoginPageObject;
import pageObject.user.UserProductDetailObject;
import pageObject.user.UserProductListPageObject;
import pageObject.user.UserRecentlyViewedProductPageObject;
import pageObject.user.UserShoppingCartPageObject;
import pageObject.user.UserWishlistPageObject;
@Epic("Regrestion Test")
@Feature("WishList, Compare and RecentView")
public class Topic_06_Wishlist_Compare_RecentView extends BaseTest{
	WebDriver driver;
	UserHomePageObject homePage;
	UserLoginPageObject loginPage;
	UserProductListPageObject productListPage;
	UserProductDetailObject productDetailPage;
	UserWishlistPageObject wishlistPage;
	UserShoppingCartPageObject shoppingCartPage;
	UserCompareProduct compareProductPage;
	UserRecentlyViewedProductPageObject	recentlyViewedProductPage;
	
	String macbookAppleProduct, asusLaptopProduct, adobePhotoshopSoftware, nikonCamera, nikeFloralShoes;
	@Parameters({"browser", "servername"})
	@BeforeClass
	public void beforeClass(String browserName, String serverName) {
		driver = createBrowserDriver(browserName, serverName);
		macbookAppleProduct = "Apple MacBook Pro 13-inch";
		asusLaptopProduct = "Asus N551JK-XO076H Laptop";
		adobePhotoshopSoftware = "Adobe Photoshop CS4";
		nikonCamera = "Nikon D5500 DSLR";
		nikeFloralShoes = "Nike Floral Roshe Customized Running Shoes";
		
		homePage = PageGeneratorManager.getUserHomePage(driver);
		log.info("Pre-condition - Step 1: Click to Login link to clear data");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Pre-condition - Step 2: Input correct email");
		loginPage.inputToTextboxById(driver, Common_Register_NewAccount.EMAIL, "Email");
		
		log.info("Pre-condition - Step 3: Input correct password");
		loginPage.inputToTextboxById(driver, Common_Register_NewAccount.PASSWORD, "Password");
		
		log.info("Pre-condition - Step 4: Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Pre-condition - Step 5: Verify the my account page is displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		log.info("Pre-condition - Step 6: Hover to Computers menu");
		homePage.hoverToMenuItemByLabel(driver, "Computers");
		
		log.info("Pre-condition - Step 7: Click to Notebooks submenu in Computers menu");
		productListPage = homePage.clickToSubmenuInHeaderMenuByLabel(driver, "Computers", "Notebooks");
		
		log.info("Pre-condition - Step 8: Verify current page is Notebooks");
		verifyEquals(productListPage.getCurrentPageTitle(driver), "Notebooks");
		
		log.info("Pre-condition - Step 9: Click to product name");
		productDetailPage = productListPage.clickToProductTitle(driver, macbookAppleProduct);
	}
	
	@Description("Add product to wish list")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void WishlistCompareRecent_01_Add_Product_To_Wishlist(){
		log.info("WishlistCompareRecent 01 - Step 1: Click Add to wishlist button");
		productDetailPage.clickToButtonByTextAndProductName("Add to wishlist");
		
		log.info("WishlistCompareRecent 01 - Step 2: Verify add success message display");
		verifyEquals(productDetailPage.getUserAlertMessage(driver), "The product has been added to your wishlist");
		
		log.info("WishlistCompareRecent 01 - Step 3: Click close toast message button");
		productDetailPage.clickToUserCloseAlertMessageButton(driver);
		
		log.info("WishlistCompareRecent 01 - Step 4: Click to Wishlist header link");
		productDetailPage.clickToHeaderLinkByClass(driver, "ico-wishlist");
		wishlistPage = PageGeneratorManager.getUserWishlistPage(driver);
		
		log.info("WishlistCompareRecent 01 - Step 5: Verify product name is exist in table");
		verifyTrue(wishlistPage.isProductExistInTableByProductName(driver, macbookAppleProduct));
		
		log.info("WishlistCompareRecent 01 - Step 6: Click share information link");
		wishlistPage.clickToShareInformationLink();
		
		log.info("WishlistCompareRecent 01 - Step 7: Verify the wishlist of user was shared");
		verifyEquals(wishlistPage.getCurrentPageTitle(driver), "Wishlist of " + Common_Register_NewAccount.FIRSTNAME + " " + Common_Register_NewAccount.LASTNAME);
	}
	
	@Description("Add product to cart from wish list")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void WishlistCompareRecent_02_Add_Product_To_Cart_From_Wishlist(){
		log.info("WishlistCompareRecent 02 - Step 1: Back to my wishlist page");
		wishlistPage.backToPage(driver);
		
		log.info("WishlistCompareRecent 02 - Step 2: Verify current page is Wishlist");
		verifyEquals(productListPage.getCurrentPageTitle(driver), "Wishlist");
		
		log.info("WishlistCompareRecent 02 - Step 3: Select checkbox by product name");
		wishlistPage.clickToCheckboxByProductName(macbookAppleProduct);
		
		log.info("WishlistCompareRecent 02 - Step 4: Click Add to cart button");
		wishlistPage.clickButtonByLabel(driver, "Add to cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		
		log.info("WishlistCompareRecent 02 - Step 5: Verify current page is Shopping cart");
		verifyEquals(shoppingCartPage.getCurrentPageTitle(driver), "Shopping cart");
		
		log.info("WishlistCompareRecent 02 - Step 6: Veridy product name is exist in table");
		verifyTrue(shoppingCartPage.isProductExistInTableByProductName(driver, macbookAppleProduct));
		
		log.info("WishlistCompareRecent 02 - Step 7: Click to Wishlist header link");
		shoppingCartPage.clickToHeaderLinkByClass(driver, "ico-wishlist");
		wishlistPage = PageGeneratorManager.getUserWishlistPage(driver);
		
		log.info("WishlistCompareRecent 02 - Step 8: Veridy product name is NOT exist in table");
		verifyTrue(shoppingCartPage.isProductNotExistInTableByProductName(driver, macbookAppleProduct));
	}
	
	@Description("Remove product from wishlist")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void WishlistCompareRecent_03_Remove_Product_From_Wishlist(){
		log.info("WishlistCompareRecent 03 - Step 1: Add product to Wishlist");
		shoppingCartPage.hoverToMenuItemByLabel(driver, "Computers");
		productListPage = shoppingCartPage.clickToSubmenuInHeaderMenuByLabel(driver, "Computers", "Notebooks");
		productDetailPage = productListPage.clickToProductTitle(driver, macbookAppleProduct);
		productDetailPage.clickToButtonByTextAndProductName("Add to wishlist");
		verifyEquals(productDetailPage.getUserAlertMessage(driver), "The product has been added to your wishlist");
		productDetailPage.clickToUserCloseAlertMessageButton(driver);
		productDetailPage.clickToHeaderLinkByClass(driver, "ico-wishlist");
		wishlistPage = PageGeneratorManager.getUserWishlistPage(driver);
		verifyTrue(wishlistPage.isProductExistInTableByProductName(driver, macbookAppleProduct));
		
		log.info("WishlistCompareRecent 03 - Step 2: Click remove button by product name");
		wishlistPage.clickToRemoveButtonByProductName(macbookAppleProduct);
		
		log.info("WishlistCompareRecent 03 - Step 3: Verify product is NOT exist in table");
		verifyTrue(shoppingCartPage.isProductNotExistInTableByProductName(driver, macbookAppleProduct));
		
		log.info("WishlistCompareRecent 03 - Step 3: Verify body table displays with message: The wishlis is empty!");
		verifyEquals(wishlistPage.getBodyTableData(driver, "page wishlist-page"), "The wishlist is empty!");
	}
	
	@Description("Add product to compare")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void WishlistCompareRecent_04_Add_Product_To_Compare(){
		log.info("WishlistCompareRecent 04 - Step 1: Go to product list page");
		wishlistPage.hoverToMenuItemByLabel(driver, "Computers");
		productListPage = wishlistPage.clickToSubmenuInHeaderMenuByLabel(driver, "Computers", "Notebooks");
		
		log.info("WishlistCompareRecent 04 - Step 2: Click add to compare list with macbook product");
		productListPage.clickToButtonInProductItemByProductName(driver, macbookAppleProduct, "Add to compare list");
		
		log.info("WishlistCompareRecent 04 - Step 3: Verify add success message display");
		verifyEquals(productDetailPage.getUserAlertMessage(driver), "The product has been added to your wishlist");
		
		log.info("WishlistCompareRecent 04 - Step 4: Click close toast message button");
		productDetailPage.clickToUserCloseAlertMessageButton(driver);
		
		log.info("WishlistCompareRecent 04 - Step 6: Click add to compare list with asus product");
		productListPage.clickToButtonInProductItemByProductName(driver, asusLaptopProduct, "Add to compare list");
		
		log.info("WishlistCompareRecent 04 - Step 7: Verify add success message display");
		verifyEquals(productDetailPage.getUserAlertMessage(driver), "The product has been added to your wishlist");
		
		log.info("WishlistCompareRecent 04 - Step 8: Click close toast message button");
		productDetailPage.clickToUserCloseAlertMessageButton(driver);
		
		log.info("WishlistCompareRecent 04 - Step 9: Click Compare products list footer link");
		productDetailPage.clickToFooterLink(driver, "Compare products list");
		compareProductPage = PageGeneratorManager.getCompareProductPage(driver);
		
		log.info("WishlistCompareRecent 04 - Step 10: Verify the product compare list display");
		verifyTrue(compareProductPage.isButtonDisplayByRowClassAndColumnIndex("remove-product", "2", "Remove"));
		verifyTrue(compareProductPage.isButtonDisplayByRowClassAndColumnIndex("remove-product", "3", "Remove"));
		verifyTrue(compareProductPage.isSheetTextDisplayByRowClassAndColumnIndex("product-name", "2", asusLaptopProduct));
		verifyTrue(compareProductPage.isSheetTextDisplayByRowClassAndColumnIndex("product-name", "3", macbookAppleProduct));
		verifyTrue(compareProductPage.isPriceDisplayByRowClassAndColumnIndex("product-price", "2", "$1,500.00"));
		verifyTrue(compareProductPage.isPriceDisplayByRowClassAndColumnIndex("product-price", "3", "$1,800.00"));
		verifyTrue(compareProductPage.isClearListButtonDipslay());
		
		log.info("WishlistCompareRecent 04 - Step 11: Click to clear button");
		compareProductPage.clickClearListButton();
		
		log.info("WishlistCompareRecent 04 - Step 12: Verify the no items message display");
		verifyEquals(compareProductPage.getBodyTableData(driver, "page compare-products-page"), "You have no items to compare.");
		
		log.info("WishlistCompareRecent 04 - Step 13: Verify the product name undisplay");
		verifyTrue(compareProductPage.isSheetTextUndisplayByRowClassAndColumnIndex("product-name", "3", macbookAppleProduct));
		verifyTrue(compareProductPage.isSheetTextUndisplayByRowClassAndColumnIndex("product-name", "2", asusLaptopProduct));
	}
	
	@Description("Recently Viewed Product")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void WishlistCompareRecent_05_Recently_Viewed_Product(){
		log.info("WishlistCompareRecent 05 - Step 1: Open Macbook detail page");
		compareProductPage.hoverToMenuItemByLabel(driver, "Computers");
		productListPage = compareProductPage.clickToSubmenuInHeaderMenuByLabel(driver, "Computers", "Notebooks");
		productDetailPage = productListPage.clickToProductTitle(driver, macbookAppleProduct);
		
		log.info("WishlistCompareRecent 05 - Step 2: Open Asus detail page");
		productDetailPage.hoverToMenuItemByLabel(driver, "Computers");
		productListPage = productDetailPage.clickToSubmenuInHeaderMenuByLabel(driver, "Computers", "Notebooks");
		productDetailPage = productListPage.clickToProductTitle(driver, asusLaptopProduct);
		
		log.info("WishlistCompareRecent 05 - Step 3: Open Adobe detail page");
		productDetailPage.hoverToMenuItemByLabel(driver, "Computers");
		productListPage = productDetailPage.clickToSubmenuInHeaderMenuByLabel(driver, "Computers", "Software");
		productDetailPage = productListPage.clickToProductTitle(driver, adobePhotoshopSoftware);
		
		log.info("WishlistCompareRecent 05 - Step 4: Open Nikon detail page");
		productDetailPage.hoverToMenuItemByLabel(driver, "Electronics");
		productListPage = productDetailPage.clickToSubmenuInHeaderMenuByLabel(driver, "Electronics", "Camera & photo");
		productDetailPage = productListPage.clickToProductTitle(driver, nikonCamera);
		
		log.info("WishlistCompareRecent 05 - Step 5: Open Nike shoes detail page");
		productDetailPage.hoverToMenuItemByLabel(driver, "Apparel");
		productListPage = productDetailPage.clickToSubmenuInHeaderMenuByLabel(driver, "Apparel", "Shoes");
		productDetailPage = productListPage.clickToProductTitle(driver, nikeFloralShoes);
		
		log.info("WishlistCompareRecent 05 - Step 6: Click Recently viewed products footer link");
		productDetailPage.clickToFooterLink(driver, "Recently viewed products");
		recentlyViewedProductPage = PageGeneratorManager.getRecentlyViewedProductPage(driver);
		
		log.info("WishlistCompareRecent 05 - Step 7: Verify current page is Recently viewed products");
		verifyEquals(recentlyViewedProductPage.getCurrentPageTitle(driver), "Recently viewed products");
		
		log.info("WishlistCompareRecent 05 - Step 8: Verify the product display in the Recently viewed product page");
		verifyTrue(recentlyViewedProductPage.isProductDisplayByProductName(driver, adobePhotoshopSoftware));
		verifyTrue(recentlyViewedProductPage.isProductDisplayByProductName(driver, nikonCamera));
		verifyTrue(recentlyViewedProductPage.isProductDisplayByProductName(driver, nikeFloralShoes));
		verifyTrue(recentlyViewedProductPage.isProductDisplayByProductName(driver, macbookAppleProduct));
		verifyTrue(recentlyViewedProductPage.isProductDisplayByProductName(driver, asusLaptopProduct));
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowserDriver("local");
	}

}