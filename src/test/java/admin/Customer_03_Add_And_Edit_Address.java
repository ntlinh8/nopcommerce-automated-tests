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

public class Customer_03_Add_And_Edit_Address extends BaseTest{
	private static WebDriver driver;
	private AdminLoginPageObject loginPage;
	private AdminDashboardPageObject dashboardPage;
	private AdminCustomerPageObject customerPage;
	private static String adminEmail, adminPassword;
	private String firstName, lastName, email, password, dateOfBirth, companyName, customerRole, adminComment;
	private String firstNameEdit, lastNameEdit, emailEdit, companyNameEdit;
	private String city, address1, address2, zipcode, faxNumber, phoneNumber;
	private String cityEdit, address1Edit, address2Edit, zipcodeEdit, faxNumberEdit, phoneNumberEdit;
	
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
		city = "Ho Chi Minh City";
		address1 = "No. 1, Nguyen Hue Street";
		address2 = "No. 11, Nguyen Hue Street";
		zipcode = "550000";
		faxNumber = "123";
		phoneNumber = "0123123123";
		
		firstNameEdit = "Edit Elon";
		lastNameEdit = "Edit Musk";
		emailEdit = "Edit" + email;
		companyNameEdit = "Edit Automation Group";
		cityEdit = "Edit Ho Chi Minh City";
		address1Edit = "Edit No. 1, Nguyen Hue Street";
		address2Edit = "Edit No. 11, Nguyen Hue Street";
		zipcodeEdit = "500000";
		faxNumberEdit = "456";
		phoneNumberEdit = "0234234234";
		
		
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
	public void Customer_01_Add_Address() {
		log.info("Customer 01 - Step 1: Search customer with company");
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
		
		log.info("Customer 01 - Step 2: Verify customer display in the list");
		verifyEquals(customerPage.getCustomerNumber(), "1");
		verifyTrue(customerPage.isCustomerInformationDisplayWithEmailAndName("Guest", firstName + " " + lastName));
		
		log.info("Customer 01 - Step 3: Click Edit button");
		customerPage.clickButtonLinkByLabelInAdminPage(driver, "Edit");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Customer 01 - Step 4: Click Address menu");
		customerPage.clickMenuByLabel("Addresses");
		customerPage.clickButtonByLabelInAdminPage(driver, "Add new address");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Customer 01 - Step 5: Enter address information");
		customerPage.inputToTextboxById(driver, firstName, "Address_FirstName");
		customerPage.inputToTextboxById(driver, lastName, "Address_LastName");
		customerPage.inputToTextboxById(driver, email, "Address_Email");
		customerPage.inputToTextboxById(driver, companyName, "Address_Company");
		customerPage.selectDropdownByName(driver, "Address.CountryId", "Viet Nam");
		customerPage.inputToTextboxById(driver, city, "Address_City");
		customerPage.inputToTextboxById(driver, address1, "Address_Address1");
		customerPage.inputToTextboxById(driver, address2, "Address_Address2");
		customerPage.inputToTextboxById(driver, zipcode, "Address_ZipPostalCode");
		customerPage.inputToTextboxById(driver, phoneNumber, "Address_PhoneNumber");
		customerPage.inputToTextboxById(driver, faxNumber, "Address_FaxNumber");
		customerPage.clickButtonByLabelInAdminPage(driver, "Save");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Customer 01 - Step 6: Verify add address successful message");
		verifyEquals(customerPage.getAdminAlertMessage(driver), "\r\n" + "The new address has been added successfully.");
		customerPage.clickToAdminCloseAlertMessage(driver);
		
		log.info("Customer 01 - Step 7: Verify address information correct");
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_FirstName"), firstName);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_LastName"), lastName);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_Email"), email);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_Company"), companyName);
		verifyEquals(customerPage.getSelectedItemFromDropdownByName(driver, "Address.CountryId"), "Viet Nam");
		verifyEquals(customerPage.getSelectedItemFromDropdownByName(driver, "Address.StateProvinceId"), "Other");
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_City"), city);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_Address1"), address1);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_Address2"), address2);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_ZipPostalCode"), zipcode);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_PhoneNumber"), phoneNumber);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_FaxNumber"), faxNumber);
		
		log.info("Customer 01 - Step 8: Back to customer detail page");
		customerPage.clickButtonLinkByLabelInAdminPage(driver, "back to customer details");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Customer 01 - Step 9: Scroll to Address information and verify");
		customerPage.scrollToMenuByLabel("Addresses");
		verifyEquals(customerPage.getAddressInformation(),
				firstName + " " + lastName + " " + email + " " + phoneNumber + " " + faxNumber + "\n" + companyName
						+ "\n" + address1 + "\n" + address2 + "\n" + city + "," + zipcode + "Viet Nam\nEdit Delete");

	}
	
	@Test
	public void Customer_02_Edit_Address() {
		log.info("Customer 02 - Step 1: Click edit address button");
		customerPage.clickButtonLinkByLabelInAdminPage(driver, "Edit");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Customer 02 - Step 2: Change the address information");
		customerPage.inputToTextboxById(driver, firstNameEdit, "Address_FirstName");
		customerPage.inputToTextboxById(driver, lastNameEdit, "Address_LastName");
		customerPage.inputToTextboxById(driver, emailEdit, "Address_Email");
		customerPage.inputToTextboxById(driver, companyNameEdit, "Address_Company");
		customerPage.inputToTextboxById(driver, address1Edit, "Address_Address1");
		customerPage.inputToTextboxById(driver, address2Edit, "Address_Address2");
		customerPage.inputToTextboxById(driver, zipcodeEdit, "Address_ZipPostalCode");
		customerPage.inputToTextboxById(driver, phoneNumberEdit, "Address_PhoneNumber");
		customerPage.inputToTextboxById(driver, faxNumberEdit, "Address_FaxNumber");
		customerPage.clickButtonByLabelInAdminPage(driver, "Save");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Customer 02 - Step3: Verify update address successful message");
		verifyEquals(customerPage.getAdminAlertMessage(driver), "\r\n" + "The address has been updated successfully.");
		customerPage.clickToAdminCloseAlertMessage(driver);
		
		log.info("Customer 02 - Step 4: Verify address information correct");
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_FirstName"), firstNameEdit);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_LastName"), lastNameEdit);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_Email"), emailEdit);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_Company"), companyNameEdit);
		verifyEquals(customerPage.getSelectedItemFromDropdownByName(driver, "Address.CountryId"), "Viet Nam");
		verifyEquals(customerPage.getSelectedItemFromDropdownByName(driver, "Address.StateProvinceId"), "Other");
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_City"), cityEdit);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_Address1"), address1Edit);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_Address2"), address2Edit);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_ZipPostalCode"), zipcodeEdit);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_PhoneNumber"), phoneNumberEdit);
		verifyEquals(customerPage.getValueAttributFromTextboxByID(driver, "Address_FaxNumber"), faxNumberEdit);
		
		log.info("Customer 02 - Step 5: Back to customer detail page");
		customerPage.clickButtonLinkByLabelInAdminPage(driver, "back to customer details");
		verifyTrue(customerPage.isPageLoadedSuccess(driver));
		
		log.info("Customer 02 - Step 6: Scroll to Address information and verify");
		customerPage.scrollToMenuByLabel("Addresses");
		verifyEquals(customerPage.getAddressInformation(),
				firstNameEdit + " " + lastNameEdit + " " + emailEdit + " " + phoneNumberEdit + " " + faxNumberEdit
						+ "\n" + companyNameEdit + "\n" + address1Edit + "\n" + address2Edit + "\n" + cityEdit + ","
						+ zipcodeEdit + "Viet Nam\nEdit Delete");

	}
	
	@Test
	public void Customer_03_Delete_Address() {
		log.info("Customer 03 - Step 1: Click delete address button");
		customerPage.clickButtonLinkByLabelInAdminPage(driver, "Delete");
		
		log.info("Customer 03 - Step 2: Accept alert delete address");
		customerPage.acceptAlert(driver);
		
		log.info("Customer 03 - Step 3: Verify no data avaiable in table text displays");
		verifyEquals(customerPage.getNoAddressInTableMessage(), "No data available in table");
		
	}
	

	@AfterClass
	public void afterClass() {
		closeBrowserDriver("local");
	}
}
