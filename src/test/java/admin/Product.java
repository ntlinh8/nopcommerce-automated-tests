package admin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import pageObject.admin.AdminDashboardPageObject;
import pageObject.admin.AdminLoginPageObject;
import pageObject.admin.AdminProductPageObject;
import pageObject.admin.PageGeneratorManager;

public class Product extends BaseTest{
	private static WebDriver driver;
	private AdminLoginPageObject loginPage;
	private AdminDashboardPageObject dashboardPage;
	private AdminProductPageObject productPage;
	public static String adminEmail, adminPassword;
	
	private String lenovoPC, sku, price, stockQuantity;
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = createBrowserDriver(browserName, url);
		adminEmail = "admin@yourstore.com";
		adminPassword = "admin";
		lenovoPC = "Lenovo IdeaCentre 600 All-in-One PC";
		sku = "LE_IC_600";
		price = "500";
		stockQuantity = "10000";
		
		loginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		log.info("Pre-condition - Step 1: Login with admin account"); 
		dashboardPage = loginPage.loginWithAdminAccount(adminEmail, adminPassword);
		verifyTrue(dashboardPage.isPageLoadedSuccess(driver));
		
		log.info("Pre-condition - Step 2: Verify the dashboard page is displayed");
		verifyTrue(dashboardPage.isDashboardHeaderDisplayed());
	}
	
	@Test
	public void Product_01_Search_With_Product_Name() {
		log.info("Product 01 - Step 1: Click to Catalog menu");
		dashboardPage.clickToLeftMenuByLabel(driver, "Catalog");
		
		log.info("Product 01 - Step 2: Verify the Catalog menu is select");
		verifyTrue(dashboardPage.isSelectedLeftMenuByLabel(driver, "Catalog"));
		
		log.info("Product 01 - Step 3: Click to Products sub menu");
		dashboardPage.clickToSubLeftMenuByLabel(driver, "Products");
		productPage = PageGeneratorManager.getAdminProductPagePage(driver);
		
		log.info("Product 01 - Step 4: Send product name");
		productPage.inputToTextboxById(driver, lenovoPC, "SearchProductName");
		
		log.info("Product 01 - Step 5: Click search button");
		productPage.clickButtonByClass(driver, "btn-primary btn-search");
		
		log.info("Product 01 - Step 6: Verify there are one item with correct information");
		verifyEquals(productPage.getSheetByColumnLabelAndRowIndex("Product name", "1"), lenovoPC);
		verifyEquals(productPage.getSheetByColumnLabelAndRowIndex("SKU", "1"), sku);
		verifyEquals(productPage.getSheetByColumnLabelAndRowIndex("Price", "1"), price);
		verifyEquals(productPage.getSheetByColumnLabelAndRowIndex("Stock quantity", "1"), stockQuantity);
		verifyTrue(productPage.isPublishedProductByName(lenovoPC));
	}
	
	@Test
	public void Product_02_Search_With_Product_Name_And_Parent_Category_And_Unchecked() {
		log.info("Product 02 - Step 1: Send product name");
		productPage.inputToTextboxById(driver, lenovoPC, "SearchProductName");
		
		log.info("Product 02 - Step 2: Select parent category");
		productPage.selectDropdownByName(driver, "SearchCategoryId", "Computers");
		
		log.info("Product 02 - Step 3: Uncheck search with subcategories");
		productPage.uncheckToCheckboxButtonById(driver, "SearchIncludeSubCategories");
		
		log.info("Product 02 - Step 4: Click search button");
		productPage.clickButtonByClass(driver, "btn-primary btn-search");
		
		log.info("Product 02 - Step 5: Verify no item in the table");
		verifyEquals(productPage.getNoItemMessage(), "No data available in table");
	}
	
	@Test
	public void Product_03_Search_With_Product_Name_And_Parent_Category_And_Checked() {
		log.info("Product 03 - Step 1: Send product name");
		productPage.inputToTextboxById(driver, lenovoPC, "SearchProductName");
		
		log.info("Product 03 - Step 2: Select parent category");
		productPage.selectDropdownByName(driver, "SearchCategoryId", "Computers");
		
		log.info("Product 03 - Step 3: Check search with subcategories");
		productPage.checkToCheckboxRadioButtonById(driver, "SearchIncludeSubCategories");
		
		log.info("Product 03 - Step 4: Click search button");
		productPage.clickButtonByClass(driver, "btn-primary btn-search");
		
		log.info("Product 03 - Step 5: Verify there are one item with correct information");
		verifyEquals(productPage.getSheetByColumnLabelAndRowIndex("Product name", "1"), lenovoPC);
		verifyEquals(productPage.getSheetByColumnLabelAndRowIndex("SKU", "1"), sku);
		verifyEquals(productPage.getSheetByColumnLabelAndRowIndex("Price", "1"), price);
		verifyEquals(productPage.getSheetByColumnLabelAndRowIndex("Stock quantity", "1"), stockQuantity);
		verifyTrue(productPage.isPublishedProductByName(lenovoPC));
	}
	
	@Test
	public void Product_04_Search_With_Product_Name_And_Child_Category_And_Unchecked() {
		log.info("Product 04 - Step 1: Send product name");
		productPage.inputToTextboxById(driver, lenovoPC, "SearchProductName");
		
		log.info("Product 04 - Step 2: Select parent category");
		productPage.selectDropdownByName(driver, "SearchCategoryId", "Computers >> Desktops");
		
		log.info("Product 04 - Step 3: Check search with subcategories");
		productPage.uncheckToCheckboxButtonById(driver, "SearchIncludeSubCategories");
		
		log.info("Product 04 - Step 4: Click search button");
		productPage.clickButtonByClass(driver, "btn-primary btn-search");
		
		log.info("Product 04 - Step 5: Verify there are one item with correct information");
		verifyEquals(productPage.getSheetByColumnLabelAndRowIndex("Product name", "1"), lenovoPC);
		verifyEquals(productPage.getSheetByColumnLabelAndRowIndex("SKU", "1"), sku);
		verifyEquals(productPage.getSheetByColumnLabelAndRowIndex("Price", "1"), price);
		verifyEquals(productPage.getSheetByColumnLabelAndRowIndex("Stock quantity", "1"), stockQuantity);
		verifyTrue(productPage.isPublishedProductByName(lenovoPC));
	}
	
	@Test
	public void Product_05_Search_With_Product_Name_And_Manufacturer() {
		log.info("Product 05 - Step 1: Send product name");
		productPage.inputToTextboxById(driver, lenovoPC, "SearchProductName");
		
		log.info("Product 05 - Step 2: Select parent category");
		productPage.selectDropdownByName(driver, "SearchCategoryId", "All");
		
		log.info("Product 05 - Step 3: Check search with subcategories");
		productPage.uncheckToCheckboxButtonById(driver, "SearchIncludeSubCategories");
		
		log.info("Product 05 - Step 4: Select parent category");
		productPage.selectDropdownByName(driver, "SearchManufacturerId", "Apple");
		
		log.info("Product 05 - Step 5: Click search button");
		productPage.clickButtonByClass(driver, "btn-primary btn-search");
		
		log.info("Product 05 - Step 6: Verify no item in the table");
		verifyEquals(productPage.getNoItemMessage(), "No data available in table");
	}
	
	@Test
	public void Product_06_Go_Directly_To_Product_Sku() {
		log.info("Product 06 - Step 1: Send product name");
		productPage.inputToTextboxById(driver, lenovoPC, "SearchProductName");
		
		log.info("Product 06 - Step 2: Select parent category");
		productPage.selectDropdownByName(driver, "SearchCategoryId", "All");
		
		log.info("Product 06 - Step 3: Check search with subcategories");
		productPage.uncheckToCheckboxButtonById(driver, "SearchIncludeSubCategories");
		
		log.info("Product 06 - Step 4: Select parent category");
		productPage.selectDropdownByName(driver, "SearchManufacturerId", "All");
		
		log.info("Product 06 - Step 5: Send product sku");
		productPage.inputToTextboxById(driver, sku, "GoDirectlyToSku");
		
		log.info("Product 06 - Step 5: Click search button");
		productPage.clickButtonByLabel(driver, "Go");
		
		log.info("Product 06 - Step 5: Verify switch to detail product successful");
		verifyEquals(productPage.getPageTitle(driver), "Edit product details / nopCommerce administration");
		
		log.info("Product 06 - Step 6: Verify the lenovo pc display in the product name");
		verifyEquals(productPage.getValueAttributFromTextboxByID(driver, "Name"), lenovoPC);
	}

	@AfterClass
	public void afterClass() {
		closeBrowserDriver("local");
	}
}
