package admin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import pageObject.admin.AdminCustomerPageObject;
import pageObject.admin.AdminDashboardPageObject;
import pageObject.admin.AdminLoginPageObject;
import pageObject.admin.PageGeneratorManager;

public class Customer_02_Edit_Customer_Information extends BaseTest{
	private static WebDriver driver;
	private AdminLoginPageObject loginPage;
	private AdminDashboardPageObject dashboardPage;
	private AdminCustomerPageObject customerPage;
	private static String adminEmail, adminPassword;
	private String firstName, lastName, email, password, dateOfBirth, companyName, customerRole, adminComment;
	private String firstNameEdit, lastNameEdit, emailEdit, dateOfBirthEdit, companyNameEdit, adminCommentEdit;
	
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
		
		firstNameEdit = "Edit Elon";
		lastNameEdit = "Edit Musk";
		emailEdit = "Edit" + email;
		dateOfBirthEdit = "11/28/2021";
		companyNameEdit = "Edit Automation Group";
		adminCommentEdit = "Edit This is the new admin of the page";
		
		loginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		log.info("Pre-condition - Step 1: Login with admin account"); 
		dashboardPage = loginPage.loginWithAdminAccount(adminEmail, adminPassword);
		verifyTrue(dashboardPage.isPageLoadedSuccess(driver));
		
		log.info("Pre-condition - Step 2: Verify the dashboard page is displayed");
		verifyTrue(dashboardPage.isDashboardHeaderDisplayed());
		
		log.info("Pre-condition - Step 3: Click to Customers menu");
		dashboardPage.clickToLeftMenuByLabel(driver, "Customers");
		
		log.info("Pre-condition - Step 4: Verify the Customers menu is select");
		verifyTrue(dashboardPage.isSelectedLeftMenuByLabel(driver, "Customers"));
		
		log.info("Pre-condition - Step 5: Click to Customers sub menu");
		dashboardPage.clickToSubLeftMenuByLabel(driver, "Customers");
		customerPage = PageGeneratorManager.getAdminCustomerPage(driver);
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Pre-condition - Step 6: Click add new customer button");
		customerPage.clickButtonLinkByLabelInAdminPage(driver, "Add new");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Pre-condition - Step 7: Entering the customer information");
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
		
		log.info("Pre-condition - Step 8: Click save and continue button");
		customerPage.clickButtonByLabelInAdminPage(driver, "Save and Continue Edit");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Pre-condition - Step 9: Verify alert add new customer successful");
		verifyEquals(customerPage.getAdminAlertMessage(driver), "\r\n" + "The new customer has been added successfully.");
		customerPage.clickToAdminCloseAlertMessage(driver);
		
		log.info("Pre-condition - Step 10: Verify user information was added correct");
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
		
		log.info("Pre-condition - Step 11: Click back to customer list page");
		customerPage.clickButtonLinkByLabelInAdminPage(driver, "back to customer list");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
	}
	
	
	@Test
	public void Customer_01_Edit_Customer_Information() {
		log.info("Edit Information 01 - Step 1: Search customer with company");
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
		
		log.info("Edit Information 01 - Step 2: Verify customer display in the list");
		verifyEquals(customerPage.getCustomerNumber(), "1");
		verifyTrue(customerPage.isCustomerInformationDisplayWithEmailAndName("Guest", firstName + " " + lastName));
		
		log.info("Edit Information 01 - Step 3: Click Edit button");
		customerPage.clickButtonLinkByLabelInAdminPage(driver, "Edit");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Edit Information 01 - Step 4: Enter new customer information");
		customerPage.inputToTextboxById(driver, emailEdit, "Email");
		customerPage.inputToTextboxById(driver, firstNameEdit, "FirstName");
		customerPage.inputToTextboxById(driver, lastNameEdit, "LastName");
		customerPage.inputToTextboxById(driver, dateOfBirthEdit, "DateOfBirth");
		customerPage.inputToTextboxById(driver, companyNameEdit, "Company");
		customerPage.inputToTextAreaById(driver, adminCommentEdit, "AdminComment");
		
		log.info("Edit Information 01 - Step 5: Click save and continue button");
		customerPage.clickButtonByLabelInAdminPage(driver, "Save");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Edit Information 01 - Step 6: Verify alert add new customer successful");
		verifyEquals(customerPage.getAdminAlertMessage(driver), "\r\n" + "The customer has been updated successfully.");
		customerPage.clickToAdminCloseAlertMessage(driver);
		
		log.info("Edit Information 01 - Step 7: Seach customer with new information");
		customerPage.inputToTextboxById(driver, emailEdit, "SearchEmail");
		customerPage.inputToTextboxById(driver, firstNameEdit, "SearchFirstName");
		customerPage.inputToTextboxById(driver, lastNameEdit, "SearchLastName");
		customerPage.inputToTextboxById(driver, companyNameEdit, "SearchCompany");
		customerPage.selectDropdownByName(driver, "SearchMonthOfBirth", "11");
		customerPage.selectDropdownByName(driver, "SearchDayOfBirth", "28");
		customerPage.clickDeleteCustomerRoleByLabel("Registered");
		customerPage.selectCustomerRole(customerRole);
		customerPage.clickButtonByLabelInAdminPage(driver, "Search");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Edit Information 02 - Step 8: Verify customer display in the list");
		verifyEquals(customerPage.getCustomerNumber(), "1");
		verifyTrue(customerPage.isCustomerInformationDisplayWithEmailAndName("Guest", firstNameEdit + " " + lastNameEdit));
		
	}
	

	@AfterClass
	public void afterClass() {
		closeBrowserDriver("local");
	}
}
