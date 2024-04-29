package user;

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
import pageObject.user.PageGeneratorManager;
import pageObject.user.UserHomePageObject;
import pageObject.user.UserRegisterPageObject;

@Epic("Regrestion Test")
@Feature("Register for user")
public class Topic_01_Register extends BaseTest{
	WebDriver driver;
	UserHomePageObject homePage;
	UserRegisterPageObject registerPage;
	String firstName, lastName, email, password, confirmPassword;
	
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
	}

	@Description("Register with empty data")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Register_01_Empty_Data() {
		log.info("Register 01 - Step 1: Click to register link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Register 01 - Step 2: Click to register button");
		registerPage.clickToRegisterButton();
		
		log.info("Register 01 - Step 3: Verify error message displays");
		verifyEquals(registerPage.getErrorMessageOfTextboxByID(driver, "FirstName-error"), "First name is required.");
		verifyEquals(registerPage.getErrorMessageOfTextboxByID(driver, "LastName-error"), "Last name is required.");
		verifyEquals(registerPage.getErrorMessageOfTextboxByID(driver, "Email-error"), "Email is required.");
		verifyEquals(registerPage.getErrorMessageOfTextboxByID(driver, "Password-error"), "Password is required.");
		verifyEquals(registerPage.getErrorMessageOfTextboxByID(driver, "ConfirmPassword-error"), "Password is required.");
	}

	@Description("Register with valid data")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Register_02_Invalid_Email() {
		homePage.openRegisterPage();
		
		log.info("Register 02 - Step 1: Enter the invalid email");
		registerPage.inputToTextboxById(driver, "@gmail.com", "Email");
		
		log.info("Register 02 - Step 2: Click to the register button");
		registerPage.clickToRegisterButton();
		
		log.info("Register 02 - Step 3: Verify the error message is \"Wrong email");
		verifyEquals(registerPage.getErrorMessageOfTextboxByID(driver, "Email-error"), "Wrong email");
	}
	
	@Description("Register with valid information")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void Register_03_Valid_Information() {
		homePage.openRegisterPage();
		
		log.info("Register 03 - Step 1: Enter the valid information");
		registerPage.inputToTextboxById(driver, firstName, "FirstName");
		registerPage.inputToTextboxById(driver, lastName, "LastName");
		registerPage.inputToTextboxById(driver, email, "Email");
		registerPage.inputToTextboxById(driver, password, "Password");
		registerPage.inputToTextboxById(driver, confirmPassword, "ConfirmPassword");
		
		log.info("Register 03 - Step 2: Click to Register button");
		registerPage.clickToRegisterButton();
		
		log.info("Register 03 - Step 3: Verify the register success message displays");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Register 03 - Step 4: Back to Home page");
		homePage = registerPage.clickToContinueButton();
	}
	
	@Description("Register with exist email")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Register_04_Exist_Email() {
		homePage.openRegisterPage();
		
		log.info("Register 04 - Step 1: Click to register link");
		registerPage =  homePage.clickToRegisterLink();
		
		log.info("Register 04 - Step 2: Enter the valid information with existing email");
		registerPage.inputToTextboxById(driver, firstName, "FirstName");
		registerPage.inputToTextboxById(driver, lastName, "LastName");
		registerPage.inputToTextboxById(driver, email, "Email");
		registerPage.inputToTextboxById(driver, password, "Password");
		registerPage.inputToTextboxById(driver, confirmPassword, "ConfirmPassword");
		
		log.info("Register 04 - Step 3: Click to Register button");
		registerPage.clickToRegisterButton();
		
		log.info("Register 04 - Step 4: Verify The specified email already exists message display");
		verifyEquals(registerPage.getExistEmailMessage(), "The specified email already exists");
	}
	
	@Description("Register with password length less 6 charaters")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Register_05_Password_Length_Less_6() {
		homePage.openRegisterPage();
		
		log.info("Register 05 - Step 1: Enter the valid information with password length less 6 charactors");
		registerPage.inputToTextboxById(driver, firstName, "FirstName");
		registerPage.inputToTextboxById(driver, lastName, "LastName");
		registerPage.inputToTextboxById(driver, email, "Email");
		registerPage.inputToTextboxById(driver, "abc12", "Password");
		registerPage.inputToTextboxById(driver, "abc12", "ConfirmPassword");
		
		log.info("Register 05 - Step 2: Click to Register button");
		registerPage.clickToRegisterButton();
		
		log.info("Register 05 - Step 3: Verify password error message display");
		verifyEquals(registerPage.getErrorMessageOfTextboxByID(driver, "Password-error"), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	
	@Description("Register with confirm passwork not match password")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Register_06_Confirm_Password_Not_Match_Password() {
		homePage.openRegisterPage();
		
		log.info("Register 06 - Step 1: Enter the valid information with confirm password not match with password");
		registerPage.inputToTextboxById(driver, firstName, "FirstName");
		registerPage.inputToTextboxById(driver, lastName, "LastName");
		registerPage.inputToTextboxById(driver, email, "Email");
		registerPage.inputToTextboxById(driver, "abc124", "Password");
		registerPage.inputToTextboxById(driver, "abc125", "ConfirmPassword");
		
		log.info("Register 06 - Step 2: Click to Register button");
		registerPage.clickToRegisterButton();
		
		log.info("Register 06 - Step 3: Verify confirm password error message display");
		verifyEquals(registerPage.getErrorMessageOfTextboxByID(driver, "ConfirmPassword-error"), "The password and confirmation password do not match.");
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowserDriver("local");
	}
}
