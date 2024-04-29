package common;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogEntries;
import org.testng.Assert;
import org.testng.Reporter;

import factoryBrowser.BrowserList;
import factoryBrowser.ChromeDriverManager;
import factoryBrowser.EdgeDriverManager;
import factoryBrowser.FirefoxDriverManager;
import factoryBrowser.HeadlessChromeDriverManager;
import factoryBrowser.HeadlessFirefoxDriverManager;
import factoryEnvironment.BrowserstackFactory;
import factoryEnvironment.EnvironmentList;
import factoryEnvironment.GridFactory;
import factoryEnvironment.LocalFactory;

public class BaseTest {
	
	private WebDriver driver;
	protected final Log log;
	
	public BaseTest() {
		log = LogFactory.getLog(getClass());
	}
	
	public WebDriver getDriverInstance() {
		return this.driver;
	}
	
	public WebDriver createBrowserDriver(String browserName, String serverName) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		switch (browser) {
		case FIREFOX:
			driver = new FirefoxDriverManager().getBrowserDriver();
			break;
		case CHROME:
			driver = new ChromeDriverManager().getBrowserDriver();
			break;
		case EDGE_CHROMIUM:
			driver = new EdgeDriverManager().getBrowserDriver();
			break;
		case H_CHROME:
			driver = new HeadlessChromeDriverManager().getBrowserDriver();
			break;
		case H_FIREFOX:
			driver = new HeadlessFirefoxDriverManager().getBrowserDriver();
			break;
		default:
			throw new RuntimeException("Browser Name Invalid");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT);
		driver.manage().window().maximize();
		driver.get(getEnvironmentServerName(serverName));
		return driver;
	}
	
	protected WebDriver createBrowserDriverWithCloudEnvironment(String serverName, String runEnvironmentName, String browserName, String browserVersion, String osName, String osVersion, String ipAddress, String portNumber) {
		switch (runEnvironmentName) {
		case "local":
			driver = new LocalFactory(browserName).getBrowserDriver();
			break;
		case "grid":
			driver = new GridFactory(browserName, ipAddress, portNumber).getBrowserDriver();
			break;
		case "browserStack":
			driver = new BrowserstackFactory(osName, osVersion, browserName, browserVersion).getBrowserDriver();
			break;
		default:
			driver = new LocalFactory(browserName).getBrowserDriver();
			break;
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT);
		driver.manage().window().maximize();
		driver.get(getEnvironmentServerName(serverName));
		return driver;
	}
	
	private String getEnvironmentServerName(String serverName) {
		String url = null;
		EnvironmentList env = EnvironmentList.valueOf(serverName.toUpperCase());
		switch (env) {
		case ADMIN:
			url = GlobalConstants.ADMIN_URL;
			break;
		case USER:
			url = GlobalConstants.PORTAL_URL;
			break;
		default:
			throw new RuntimeException("Please input correct the environment name!");
		}
		return url;
	}
	
	protected int getRandomNumber() {
		Random rand = new Random();
		int randomNumber = rand.nextInt(99999);
		return randomNumber;
	}
	
	protected void SleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected boolean verifyTrue(boolean condition) {
		boolean isPassed = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			isPassed = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return isPassed;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean isPassed = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			isPassed = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return isPassed;
	}

	protected boolean verifyEquals(String actual, String expected) {
		boolean isPassed = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			isPassed = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return isPassed;
	}
	
	protected boolean verifyEquals(Object actual, Object expected) {
		boolean isPassed = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			isPassed = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return isPassed;
	}
	
	protected void closeBrowserDriver(String envName) {
		if(envName.toLowerCase().contains("local") || envName.toLowerCase().contains("grid")) {
			String cmd = null;
			try {
				String osName = System.getProperty("os.name").toLowerCase();
				log.info("OS name = " + osName);

				String driverInstanceName = driver.toString().toLowerCase();
				log.info("Driver instance name = " + driverInstanceName);

				String browserDriverName = null;

				if (driverInstanceName.contains("chrome")) {
					browserDriverName = "chromedriver";
				} else if (driverInstanceName.contains("internetexplorer")) {
					browserDriverName = "IEDriverServer";
				} else if (driverInstanceName.contains("firefox")) {
					browserDriverName = "geckodriver";
				} else if (driverInstanceName.contains("edge")) {
					browserDriverName = "msedgedriver";
				} else if (driverInstanceName.contains("opera")) {
					browserDriverName = "operadriver";
				} else {
					browserDriverName = "safaridriver";
				}

				if (osName.contains("window")) {
					cmd = "taskkill /F /IM " + browserDriverName + ".exe";
				} else {
					cmd = "pkill " + browserDriverName;
				}

				if (driver != null) {
					driver.manage().deleteAllCookies();
					driver.quit();
				}
			} catch (Exception e) {
				log.info(e.getMessage());
			} finally {
				try {
					Process process = Runtime.getRuntime().exec(cmd);
					process.waitFor();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	protected void showBrowserConsoleLogs(WebDriver driver) {
		System.out.println(driver.toString());
		if(driver.toString().contains("chrome") || driver.toString().contains("edge")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry logging : logList) {
				if(logging.getLevel().toString().toLowerCase().contains("error")) {
					log.info("----------" + logging.getLevel().toString() + "----------\n" + logging.getMessage());
				}
			}
		}
		
		
		
	}
}