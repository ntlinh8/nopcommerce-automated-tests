package user;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import pageObject.user.PageGeneratorManager;
import pageObject.user.UserHomePageObject;
import pageObject.user.UserRegisterPageObject;

@Epic("Regrestion Test")
@Feature("Precondition for all test suites")
public class Common_Register_NewAccount extends BaseTest{
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private String confirmPassword;
	public static String EMAIL, PASSWORD, FIRSTNAME, LASTNAME;
	public static Set<Cookie> ALLCookies;
	@Parameters({"browser", "servername"})
	@BeforeTest
	public void beforeClass(String browserName, String serverName) {
		driver = createBrowserDriver(browserName, serverName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		FIRSTNAME = "Elon";
		LASTNAME = "Musk";
		EMAIL = "elonmusk" + getRandomNumber() + "@gmail.com";
		PASSWORD = "Abc13579";
		confirmPassword = "Abc13579";
		log.info("Pre-condtion - Step 1: Click to register link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Pre-condtion - Step 2: Enter the first name");
		registerPage.inputToTextboxById(driver, FIRSTNAME, "FirstName");
		
		log.info("Pre-condtion - Step 3: Enter the last name");
		registerPage.inputToTextboxById(driver, LASTNAME, "LastName");
		
		log.info("Pre-condtion - Step 4: Enter the email");
		registerPage.inputToTextboxById(driver, EMAIL, "Email");
		
		log.info("Pre-condtion - Step 5: Enter the password");
		registerPage.inputToTextboxById(driver, PASSWORD, "Password");
		
		log.info("Pre-condtion - Step 6: Enter the confirm password");
		registerPage.inputToTextboxById(driver, confirmPassword, "ConfirmPassword");
		
		log.info("Pre-condtion - Step 7: Click to Register button");
		registerPage.clickToRegisterButton();
		
		log.info("Pre-condtion - Step 8: Verify the register success message displays");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Pre-condtion - Step 9: Back to Home page");
		homePage = registerPage.clickToContinueButton();
		
		ALLCookies = homePage.getCookies(driver);
		log.info(ALLCookies);
		
		closeBrowserDriver("local");
	}
	
}