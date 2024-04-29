package user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import pageObject.user.PageGeneratorManager;
import pageObject.user.UserAddAddressesObject;
import pageObject.user.UserAddProductReviewObject;
import pageObject.user.UserChangePasswordPageObject;
import pageObject.user.UserCustomerInformationPageObject;
import pageObject.user.UserHomePageObject;
import pageObject.user.UserLoginPageObject;
import pageObject.user.UserMyAccountObject;
import pageObject.user.UserMyProductReviewObject;
import pageObject.user.UserProductDetailObject;
import pageObject.user.UserProductListPageObject;
import pageObject.user.UserRegisterPageObject;

public class Topic_03_My_Account extends BaseTest{
	WebDriver driver;
	UserHomePageObject homePage;
	UserLoginPageObject loginPage;
	UserRegisterPageObject registerPage;
	UserMyAccountObject myaccountPage;
	UserCustomerInformationPageObject customerInformationPage;
	UserAddAddressesObject addressesPage;
	UserChangePasswordPageObject changePasswordPage;
	UserProductListPageObject productListPage;
	UserProductDetailObject productDetailPage;
	UserAddProductReviewObject addProductReviewPage;
	UserMyProductReviewObject myProductReviewPage;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, email, password, confirmPassword, country, city, address1, address2, portalCode, phoneNumber, faxNumber;
	String newFirstName, newLastName, newEmail, companyName, gender, dayOfBirth, monthOfBirth, yearOfBirth, newPassword;
	String reviewTitle, reviewContent;
	
	@Parameters({"browser", "servername"})
	@BeforeClass
	public void beforeClass(String browserName, String serverName) {
		driver = createBrowserDriver(browserName, serverName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		firstName = "Elon";
		lastName = "Musk";
		email = "elonmusk" + getRandomNumber() + "@gmail.com";
		password = "Abc13579";
		confirmPassword = "Abc13579";
		country = "Viet Nam";
		city = "Ha Noi";
		address1 = "No.1 Pham Van Bach Street";
		address2 = "No.10 Duy Tan Street";
		portalCode = "550000";
		phoneNumber = "0123456789";
		faxNumber = "0123456789";
		
		newFirstName = "Automation";
		newLastName = "FC";
		newEmail = "automation" +getRandomNumber() + "@gmail.com";
		companyName = "AutoFC";
		gender = "Female";
		dayOfBirth = "10";
		monthOfBirth = "October";
		yearOfBirth = "1996";
		newPassword = "111111";
		
		// for My_Account_04_Product_Review
		reviewTitle = "Review Title " + getRandomNumber();
		reviewContent = "Review content Review Content" + getRandomNumber();
		
		// Pre-condition
		log.info("Pre-condition - Step 1: Enter the valid information");
		registerPage = homePage.clickToRegisterLink();
		log.info("Pre-condition - Step 2: Enter the valid information");
		registerPage.inputToTextboxById(driver, firstName, "FirstName");
		registerPage.inputToTextboxById(driver, lastName, "LastName");
		registerPage.inputToTextboxById(driver, email, "Email");
		registerPage.inputToTextboxById(driver, password, "Password");
		registerPage.inputToTextboxById(driver, confirmPassword, "ConfirmPassword");
		log.info("Pre-condition - Step 3: Click to Register button");
		registerPage.clickToRegisterButton();
		log.info("Pre-condition - Step 4: Verify the register success message displays");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		log.info("Pre-condition - Step 5: Back to Home page");
		homePage = registerPage.clickToContinueButton();
		log.info("Pre-condition - Step 6: Click to Login link to clear data");
		loginPage = homePage.clickToLoginLink();
		log.info("Pre-condition - Step 7: Input correct email and correct password");
		loginPage.inputToTextboxById(driver, email, "Email");
		loginPage.inputToTextboxById(driver, password, "Password");
		log.info("Pre-condition - Step 8: Click to Login button");
		homePage = loginPage.clickToLoginButton();
		log.info("Pre-condition - Step 9: Verify the home page displays");
		verifyTrue(homePage.isMyAccountLinkDisplayed());
		log.info("=============================");
	}
	
	@Test
	public void My_Account_01_Customer_Information() {
		log.info("My Account 01 - Step 1: Click to My Account link");
		myaccountPage = homePage.clickToMyAccountLink();
		customerInformationPage = myaccountPage.clickToCustomerInfoTab(driver);
		
		log.info("My Account 01 - Step 2: Change the customer information and click save button");
		customerInformationPage.clickToGenderRadio();
		customerInformationPage.inputToTextboxById(driver, newFirstName, "FirstName");
		customerInformationPage.inputToTextboxById(driver, newLastName, "LastName");
		customerInformationPage.selectDropdownByName(driver, dayOfBirth, "DateOfBirthDay");
		customerInformationPage.selectDropdownByName(driver, monthOfBirth, "DateOfBirthMonth");
		customerInformationPage.selectDropdownByName(driver, yearOfBirth, "DateOfBirthYear");
		customerInformationPage.inputToTextboxById(driver, newEmail, "Email");
		customerInformationPage.inputToTextboxById(driver, companyName, "Company");
		customerInformationPage.clickToSaveButton();
		
		log.info("My Account 01 - Step 3: Verify changing the customer information successful");
		verifyEquals(customerInformationPage.getToastMessage(), "The customer info has been updated successfully.");
		verifyTrue(customerInformationPage.isFemaleGender());
		verifyEquals(customerInformationPage.getValueAttributFromTextboxByID(driver, "FirstName"), newFirstName);
		verifyEquals(customerInformationPage.getValueAttributFromTextboxByID(driver, "LastName"), newLastName);
		verifyEquals(customerInformationPage.getSelectedItemFromDropdownByName(driver, "DateOfBirthDay"), dayOfBirth);
		verifyEquals(customerInformationPage.getSelectedItemFromDropdownByName(driver, "DateOfBirthMonth"), monthOfBirth);
		verifyEquals(customerInformationPage.getSelectedItemFromDropdownByName(driver, "DateOfBirthYear"), yearOfBirth);
		verifyEquals(customerInformationPage.getValueAttributFromTextboxByID(driver, "Email"), newEmail);
		verifyEquals(customerInformationPage.getValueAttributFromTextboxByID(driver, "Company"), companyName);
		log.info("=============================");
	}
	
	@Test
	public void My_Account_02_Add_New_Addresses() {
		log.info("My Account 02 - Step 1: Click to Addresses tab");
		// myaccountPage = homePage.clickToMyAccountLink();
		addressesPage = myaccountPage.clickToAddAddressesTab(driver);
		addressesPage.clickAddNewButton();
		
		log.info("My Account 02 - Step 2: Enter the customer address");
		addressesPage.inputToTextboxById(driver, newFirstName, "Address_FirstName");
		addressesPage.inputToTextboxById(driver, newLastName, "Address_LastName");
		addressesPage.inputToTextboxById(driver, newEmail, "Address_Email");
		addressesPage.inputToTextboxById(driver, companyName, "Address_Company");
		addressesPage.selectDropdownByName(driver, country, "Address_CountryId");
		addressesPage.selectDropdownByName(driver, "Other", "Address_StateProvinceId");
		addressesPage.inputToTextboxById(driver, city, "Address_City");
		addressesPage.inputToTextboxById(driver, address1, "Address_Address1");
		addressesPage.inputToTextboxById(driver, address2, "Address_Address2");
		addressesPage.inputToTextboxById(driver, portalCode, "Address_ZipPostalCode");
		addressesPage.inputToTextboxById(driver, phoneNumber, "Address_PhoneNumber");
		addressesPage.inputToTextboxById(driver, faxNumber, "Address_FaxNumber");
		addressesPage.clickSaveButton();
		
		log.info("My Account 02 - Step 3: Verify add new address process successful");
		verifyEquals(addressesPage.getToastMessage(), "The new address has been added successfully.");
		verifyTrue(addressesPage.getMessageByClass(driver, "name").contains(newFirstName + " " + newLastName));
		verifyTrue(addressesPage.getMessageByClass(driver, "phone").contains(phoneNumber));
		verifyTrue(addressesPage.getMessageByClass(driver, "fax").contains(faxNumber));
		verifyTrue(addressesPage.getMessageByClass(driver, "address1").contains(address1));
		verifyTrue(addressesPage.getMessageByClass(driver, "city-state-zip").contains(city +", " + portalCode));
		verifyTrue(addressesPage.getMessageByClass(driver, "country").contains(country));
		addressesPage.clickToCloseToastMessageButton();
		log.info("=============================");
	}
	
	@Test
	public void My_Account_03_Change_Password() {
		log.info("My Account 03 - Step 1: Click to Change password tab");
		// myaccountPage = homePage.clickToMyAccountLink();
		changePasswordPage = myaccountPage.clickToChangePasswordTab(driver);
		
		log.info("My Account 03 - Step 2: Enter the old password and new password");
		changePasswordPage.inputToTextboxById(driver, password, "OldPassword");
		changePasswordPage.inputToTextboxById(driver, newPassword, "NewPassword");
		changePasswordPage.inputToTextboxById(driver, newPassword, "ConfirmNewPassword");
		changePasswordPage.clickToChangePasswordButton();
		
		log.info("My Account 03 - Step 3: Verify the changing password process successful");
		verifyEquals(changePasswordPage.getToastMessage(), "Password was changed");
		changePasswordPage.clickToCloseToastMessageButton();
		
		log.info("My Account 03 - Step 4: Verify login successful with new account after log out");
		SleepInSecond(2);
		homePage = changePasswordPage.clickToLogOutLink(driver);
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToTextboxById(driver, newEmail, "Email");
		loginPage.inputToTextboxById(driver, newPassword, "Password");
		homePage = loginPage.clickToLoginButton();
		verifyTrue(homePage.isMyAccountLinkDisplayed());
		log.info("=============================");
	}
	
	@Test
	public void My_Account_04_Product_Review() {
		log.info("My Account 04 - Step 1: Search Product By Product Title");
		homePage.inputKeywordToSearchTextbox("apple");
		productListPage = homePage.clickToSearchButton();
		
		log.info("My Account 04 - Step 2: Open Product Detail Page");
		productDetailPage = productListPage.clickToProductTitle(driver, "Apple MacBook Pro 13-inch");
		
		log.info("My Account 04 - Step 3: Click Add Your Review Link");
		addProductReviewPage = productDetailPage.clickToAddYourReviewLink();
		
		log.info("My Account 04 - Step 4: Enter the new review for the product");
		addProductReviewPage.inputToTextboxById(driver, reviewTitle, "AddProductReview_Title");
		addProductReviewPage.inputToTextAreaById(driver, reviewContent, "AddProductReview_ReviewText");
		addProductReviewPage.clickToSubmitButton();
		
		log.info("My Account 04 - Step 5: Verify add new review successfully");
		verifyTrue(addProductReviewPage.getAddReviewSuccessMessage().contains("Product review is successfully added."));
		
		log.info("My Account 04 - Step 6: Open My Product Review Page");
		myaccountPage = homePage.clickToMyAccountLink();
		myProductReviewPage = myaccountPage.clickToMyProductReviewTab(driver);
		
		log.info("My Account 04 - Step 7: Verify new review occurs in the list review");
		verifyEquals(myProductReviewPage.getReviewTitle(), reviewTitle);
		verifyEquals(myProductReviewPage.getReviewContent(), reviewContent);
		log.info("=============================");
	}

	public void SleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowserDriver("local");
	}
}