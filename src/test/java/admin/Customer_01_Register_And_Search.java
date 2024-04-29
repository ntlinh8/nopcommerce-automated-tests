package admin;

import org.openqa.selenium.WebDriver;
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
import pageObject.admin.AdminCustomerPageObject;
import pageObject.admin.AdminDashboardPageObject;
import pageObject.admin.AdminLoginPageObject;
import pageObject.admin.PageGeneratorManager;

@Epic("Regrestion Test")
@Feature("Register And Search")
public class Customer_01_Register_And_Search extends BaseTest{
	private static WebDriver driver;
	private AdminLoginPageObject loginPage;
	private AdminDashboardPageObject dashboardPage;
	private AdminCustomerPageObject customerPage;
	private static String adminEmail, adminPassword;
	private String firstName, lastName, email, password, dateOfBirth, companyName, customerRole, adminComment;
	
	@Parameters({"browser", "servername"})
	@BeforeClass
	public void beforeClass(String browserName, String serverName) {
		driver = createBrowserDriver(browserName, serverName);
		adminEmail = "admin@yourstore.com";
		adminPassword = "admin";
		firstName = "Elon";
		lastName = "Musk";
		email = "elonmusk" + getRandomNumber() + "@gmail.com";
		password = "Abc13579";
		dateOfBirth = "12/29/2021";
		companyName = "Automation Group";
		customerRole = "Guests";
		adminComment = "This is the new admin of the page";
		
		loginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		log.info("Pre-condition - Step 1: Login with admin account"); 
		dashboardPage = loginPage.loginWithAdminAccount(adminEmail, adminPassword);
		verifyTrue(dashboardPage.isPageLoadedSuccess(driver));
		
		log.info("Pre-condition - Step 2: Verify the dashboard page is displayed");
		verifyTrue(dashboardPage.isDashboardHeaderDisplayed());
	}
	
	@Description("Create new customer")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Customer_01_Create_New_Customer() {
		log.info("Product 01 - Step 1: Click to Customers menu");
		dashboardPage.clickToLeftMenuByLabel(driver, "Customers");
		
		log.info("Product 01 - Step 2: Verify the Customers menu is select");
		verifyTrue(dashboardPage.isSelectedLeftMenuByLabel(driver, "Customers"));
		
		log.info("Product 01 - Step 3: Click to Customers sub menu");
		dashboardPage.clickToSubLeftMenuByLabel(driver, "Customers");
		customerPage = PageGeneratorManager.getAdminCustomerPage(driver);
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Product 01 - Step 4: Click add new customer button");
		customerPage.clickButtonLinkByLabelInAdminPage(driver, "Add new");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Product 01 - Step 5: Entering the customer information");
		customerPage.inputToTextboxById(driver, email, "Email");
		customerPage.inputToTextboxById(driver, password, "Password");
		customerPage.inputToTextboxById(driver, firstName, "FirstName");
		customerPage.inputToTextboxById(driver, lastName, "LastName");
		customerPage.checkToCheckboxRadioButtonById(driver, "Gender_Male");
		customerPage.inputToTextboxById(driver, dateOfBirth, "DateOfBirth");
		customerPage.inputToTextboxById(driver, companyName, "Company");
		customerPage.clickDeleteCustomerRoleByLabel("Registered");
		customerPage.selectCustomerRole(customerRole);
		customerPage.checkToCheckboxRadioButtonById(driver, "Active");
		customerPage.inputToTextAreaById(driver, adminComment, "AdminComment");
		
		log.info("Product 01 - Step 6: Click save and continue button");
		customerPage.clickButtonByLabelInAdminPage(driver, "Save and Continue Edit");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Product 01 - Step 7: Verify alert add new customer successful");
		verifyEquals(customerPage.getAdminAlertMessage(driver), "\r\n" + "The new customer has been added successfully.");
		customerPage.clickToAdminCloseAlertMessage(driver);
		
		log.info("Product 01 - Step 8: Verify user information was added correct");
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Email"), email);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Password"), password);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "FirstName"), firstName);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "LastName"), lastName);
		verifyTrue(customerPage.isCheckboxRadioCheckedById(driver, "Gender_Male"));
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "DateOfBirth"), dateOfBirth);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Company"), companyName);
		verifyTrue(customerPage.isCustomRoleExist(customerRole));
		verifyTrue(customerPage.isCheckboxRadioCheckedById(driver, "Active"));
		verifyEquals(customerPage.getValueAttributFromTextareaByID(driver, "AdminComment"), adminComment);
		
		log.info("Product 01 - Step 9: Click back to customer list page");
		customerPage.clickButtonLinkByLabelInAdminPage(driver, "back to customer list");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Product 01 - Step 10: Search customer with role");
		customerPage.clickDeleteCustomerRoleByLabel("Registered");
		customerPage.selectCustomerRole(customerRole);
		customerPage.clickButtonByLabelInAdminPage(driver, "Search");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Product 01 - Step 11: Verify customer display in the list");
		verifyTrue(customerPage.isCustomerInformationDisplayWithEmailAndName("Guest", firstName + " " + lastName));
	}
	
	@Description("Search Customer with Email")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Customer_02_Search_Customer_With_Email() {
		log.info("Product 02 - Step 1: Search customer with email");
		customerPage.refreshCurrentPage(driver);
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		customerPage.inputToTextboxById(driver, email, "SearchEmail");
		customerPage.clickDeleteCustomerRoleByLabel("Registered");
		customerPage.selectCustomerRole(customerRole);
		customerPage.clickButtonByLabelInAdminPage(driver, "Search");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Product 02 - Step 2: Verify customer display in the list");
		verifyEquals(customerPage.getCustomerNumber(), "1");
		verifyTrue(customerPage.isCustomerInformationDisplayWithEmailAndName("Guest", firstName + " " + lastName));
	}
	
	@Description("Search Customer With Firstname and LastName")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Customer_03_Search_Customer_With_FirstName_And_LastName() {
		log.info("Product 03 - Step 1: Search customer with first name and last name");
		customerPage.refreshCurrentPage(driver);
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		customerPage.inputToTextboxById(driver, firstName, "SearchFirstName");
		customerPage.inputToTextboxById(driver, lastName, "SearchLastName");
		customerPage.clickDeleteCustomerRoleByLabel("Registered");
		customerPage.selectCustomerRole(customerRole);
		customerPage.clickButtonByLabelInAdminPage(driver, "Search");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Product 03 - Step 2: Verify customer display in the list");
		verifyEquals(customerPage.getCustomerNumber(), "1");
		verifyTrue(customerPage.isCustomerInformationDisplayWithEmailAndName("Guest", firstName + " " + lastName));
	}
	
	@Description("Search Customer with Company")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Customer_04_Search_Customer_With_Company() {
		log.info("Product 04 - Step 1: Search customer with company");
		customerPage.refreshCurrentPage(driver);
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		customerPage.inputToTextboxById(driver, companyName, "SearchCompany");
		customerPage.clickDeleteCustomerRoleByLabel("Registered");
		customerPage.selectCustomerRole(customerRole);
		customerPage.clickButtonByLabelInAdminPage(driver, "Search");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Product 04 - Step 2: Verify customer display in the list");
		verifyEquals(customerPage.getCustomerNumber(), "1");
		verifyTrue(customerPage.isCustomerInformationDisplayWithEmailAndName("Guest", firstName + " " + lastName));
	}
	
	@Description("Search Customer with full data")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Customer_05_Search_Customer_With_FullData() {
		log.info("Product 05 - Step 1: Search customer with company");
		customerPage.refreshCurrentPage(driver);
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		customerPage.inputToTextboxById(driver, email, "SearchEmail");
		customerPage.inputToTextboxById(driver, firstName, "SearchFirstName");
		customerPage.inputToTextboxById(driver, lastName, "SearchLastName");
		customerPage.inputToTextboxById(driver, companyName, "SearchCompany");
		customerPage.selectDropdownByName(driver, "SearchMonthOfBirth", "12");
		customerPage.selectDropdownByName(driver, "SearchDayOfBirth", "29");
		customerPage.clickDeleteCustomerRoleByLabel("Registered");
		customerPage.selectCustomerRole(customerRole);
		customerPage.clickButtonByLabelInAdminPage(driver, "Search");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Product 05 - Step 2: Verify customer display in the list");
		verifyEquals(customerPage.getCustomerNumber(), "1");
		verifyTrue(customerPage.isCustomerInformationDisplayWithEmailAndName("Guest", firstName + " " + lastName));
	}

	@AfterClass
	public void afterClass() {
		closeBrowserDriver("local");
	}
}
