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
import pageObject.user.UserLoginPageObject;
import pageObject.user.UserRegisterPageObject;
@Epic("Regrestion Test")
@Feature("Login for User")
public class Topic_02_Login extends BaseTest {
	WebDriver driver;
	UserHomePageObject homePage;
	UserLoginPageObject loginPage;
	UserRegisterPageObject registerPage;
	String firstName, lastName, email, password, confirmPassword, incorrectPassword;
	
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
		incorrectPassword = "123123123";
		
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
	}

	@Description("Login with empty data")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Login_01_Empty_Data() {
		log.info("Login 01 - Step 1: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login 01 - Step 2: Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Login 01 - Step 3: Verify the email error message displays");
		verifyEquals(loginPage.getErrorMessageOfTextboxByID(driver, "Email-error"), "Please enter your email");
	}

	@Description("Login with invalid email")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Login_02_Invalid_Email() {
		homePage.openLoginPage();
	
		log.info("Login 02 - Step 1: Click to Login link to clear data");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login 02 - Step 2: Input invalid email to email textbox");
		loginPage.inputToTextboxById(driver, "@gmail.com", "Email");
		
		log.info("Login 02 - Step 3: Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Login 02 - Step 4: Verify the email error message displays");
		verifyEquals(loginPage.getErrorMessageOfTextboxByID(driver, "Email-error"), "Wrong email");
		
	}
	
	@Description("Login with email not register")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Login_03_Email_Not_Register() {
		homePage.openLoginPage();
		
		log.info("Login 03 - Step 1: Click to Login link to clear data");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login 03 - Step 2: Input email which was not registered in textbox");
		loginPage.inputToTextboxById(driver, "elonmusk" + getRandomNumber() + "@gmail.com", "Email");
		loginPage.inputToTextboxById(driver, password, "Password");
		
		log.info("Login 03 - Step 3: Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Login 03 - Step 4: Verify the email not found message displays");
		verifyEquals(loginPage.getEmailNotFoundMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	
	@Description("Login with correct email and no passsword")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Login_04_Correct_Email_And_No_Password() {
		homePage.openLoginPage();
		
		log.info("Login 04 - Step 1: Click to Login link to clear data");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login 04 - Step 2: Input correct email and keep the password field is empty");
		loginPage.inputToTextboxById(driver, email, "Email");
		
		log.info("Login 04 - Step 3: Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Login 04 - Step 4: Verify the email not found message displays");
		verifyEquals(loginPage.getEmailNotFoundMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Description("Login with correct email and incorrect password")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Login_05_Correct_Email_And_Incorrect_Password() {
		homePage.openLoginPage();
		
		log.info("Login 05 - Step 1: Click to Login link to clear data");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login 05 - Step 2: Input correct email and incorrect password");
		loginPage.inputToTextboxById(driver, email, "Email");
		loginPage.inputToTextboxById(driver, incorrectPassword, "Password");
		
		
		log.info("Login 05 - Step 3: Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Login 05 - Step 4: Verify the email not found message displays");
		verifyEquals(loginPage.getEmailNotFoundMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Description("Login with correct email and correct password")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Login_06_Correct_Email_And_Correct_Password() {
		homePage.openLoginPage();
		
		log.info("Login 06 - Step 1: Click to Login link to clear data");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login 06 - Step 2: Input correct email and correct password");
		loginPage.inputToTextboxById(driver, email, "Email");
		loginPage.inputToTextboxById(driver, password, "Password");
		
		log.info("Login 06 - Step 3: Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Login 06 - Step 4: Verify the email not found message displays");
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		closeBrowserDriver("local");
	}
}
