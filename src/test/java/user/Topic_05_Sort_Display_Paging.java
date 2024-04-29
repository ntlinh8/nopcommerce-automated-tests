package user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import pageObject.user.PageGeneratorManager;
import pageObject.user.UserHomePageObject;
import pageObject.user.UserProductListPageObject;

public class Topic_05_Sort_Display_Paging extends BaseTest{
	WebDriver driver;
	UserHomePageObject homePage;
	UserProductListPageObject productListPage;
	
	@Parameters({"browser", "servername"})
	@BeforeClass
	public void beforeClass(String browserName, String serverName) {
		driver = createBrowserDriver(browserName, serverName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Pre-condition - Step 1: Hover to the menu");
		homePage.hoverToMenuItemByLabel(driver, "Computers");
		
		log.info("Pre-condition - Step 2: Click to the submenu");
		productListPage = homePage.clickToSubmenuInHeaderMenuByLabel(driver, "Computers", "Notebooks");
		
		log.info("Pre-condition - Step 3: Click to the product name");
		verifyEquals(productListPage.getCurrentPageTitle(driver), "Notebooks");
	}
	
	@Test
	public void Sort_01_Name_From_A_To_Z() throws Exception {
		log.info("Sort 01 - Step 1: Select sort product by name: A to Z");
		productListPage.selectDropdownByName(driver, "products-orderby", "Name: A to Z");
		
		log.info("Sort 01 - Step 2: Verify products were sorted");
		verifyTrue(productListPage.isProductNameWasSortedByLabel("Name: A to Z"));
	}
	
	@Test
	public void Sort_02_Name_From_Z_To_A() throws Exception {
		log.info("Sort 02 - Step 1: Select sort product by name: Z to A");
		productListPage.selectDropdownByName(driver, "products-orderby", "Name: Z to A");
		
		log.info("Sort 02 - Step 2: Verify products were sorted");
		verifyTrue(productListPage.isProductNameWasSortedByLabel("Name: Z to A"));
	}
	
	@Test
	public void Sort_03_Price_Low_To_High() throws Exception {
		log.info("Sort 03 - Step 1: Select sort product by price: Low to High");
		productListPage.selectDropdownByName(driver, "products-orderby", "Price: Low to High");
		
		log.info("Sort 03 - Step 2: Verify products were sorted");
		verifyTrue(productListPage.isProductNameWasSortedByPrice("Price: Low to High"));
	}
	
	@Test
	public void Sort_04_Price_High_To_Low() throws Exception {
		log.info("Sort 04 - Step 1: Select sort product by price: High to Low");
		productListPage.selectDropdownByName(driver, "products-orderby", "Price: High to Low");
		
		log.info("Sort 04 - Step 2: Verify products were sorted");
		verifyTrue(productListPage.isProductNameWasSortedByPrice("Price: High to Low"));
	}
	
	@Test
	public void Sort_05_Display_With_3_Items() throws Exception {
		log.info("Sort 05 - Step 1: Select mode display with 3 products");
		productListPage.selectDropdownByName(driver, "products-pagesize", "3");
		
		log.info("Sort 05 - Step 2: Verify the product number is less 3");
		verifyTrue(productListPage.getDisplayedProductNumber() <= 3);
		
		log.info("Sort 05 - Step 3: Verify the current page index is 1");
		verifyEquals(productListPage.getCurrentPagingNumber(), "1");
		
		log.info("Sort 05 - Step 4: Verify the next page display");
		verifyTrue(productListPage.isPagingButtonDisplayByClass("next-page"));
		
		log.info("Sort 05 - Step 5: Click to individual page");
		productListPage.clickToPagingButtonByID("individual-page");
		
		log.info("Sort 05 - Step 6: Click to current page is 2");
		verifyEquals(productListPage.getCurrentPagingNumber(), "2");
		
		log.info("Sort 05 - Step 7: Verify the previous page display");
		verifyTrue(productListPage.isPagingButtonDisplayByClass("previous-page"));
	}
	
	@Test
	public void Sort_06_Display_With_6_Items(){
		log.info("Sort 06 - Step 1: Select mode display with 6 products");
		productListPage.selectDropdownByName(driver, "products-pagesize", "6");
		
		log.info("Sort 06 - Step 2: Verify the product number is less 6");
		verifyTrue(productListPage.getDisplayedProductNumber() <= 6);
		
		log.info("Sort 06 - Step 3: Verify the paging button undisplays");
		verifyTrue(productListPage.isPagingButtonUndisplayed());
	}
	
	@Test
	public void Sort_07_Display_With_9_Items() throws Exception {
		log.info("Sort 7 - Step 1: Select mode display with 9 products");
		productListPage.selectDropdownByName(driver, "products-pagesize", "9");
		
		log.info("Sort 07 - Step 2: Verify the product number is less 9");
		verifyTrue(productListPage.getDisplayedProductNumber() <= 9);
		
		log.info("Sort 07 - Step 3: Verify the paging button undisplays");
		verifyTrue(productListPage.isPagingButtonUndisplayed());
	}

	@AfterClass
	public void afterClass() {
		closeBrowserDriver("local");
	}
}