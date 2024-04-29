package common;

import java.io.File;
import java.time.Duration;

public class GlobalConstants {

	public static final String PORTAL_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_URL = "https://admin-demo.nopcommerce.com/";
	
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	
	public static final String UPLOAD_FILE_PATH_FOLDER = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWNLOAD_FILE_PATH_FOLDER = PROJECT_PATH + File.separator + "downloadFiles" + File.separator;
	public static final String BROWSER_LOG_FILE_PATH_FOLDER = PROJECT_PATH + File.separator + "browserLogs" + File.separator;
	public static final String BROWSER_EXTENSION_PATH_FOLDER = PROJECT_PATH + File.separator + "browserExtentions" + File.separator;
	
	public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	public static final String AUTOIT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";
	public static final String REPORT_SCREENSHOT = PROJECT_PATH + File.separator + "ReportNGImage" + File.separator;
	public static final String EXTENT_PATH = PROJECT_PATH + File.separator + "ExtentReport" + File.separator;
	public static final String RESOURCE_PATH = PROJECT_PATH + File.separator + "resources" + File.separator;
	
	public static final String DB_DEV_URL = "32.18.195.23:9860";
	public static final String DB_DEV_USER = "ntlinh";
	public static final String DB_DEV_PASSWORD = "Abc123!@#";
	public static final String DB_TEST_URL = "32.18.195.23:9860";
	public static final String DB_TEST_USER = "ntlinh";
	public static final String DB_TEST_PASSWORD = "Abc123!@#";
	
	public static final Duration SHORT_TIMEOUT = Duration.ofSeconds(10);
	public static final Duration LONG_TIMEOUT = Duration.ofSeconds(30);
	public static final Duration RETRY_TEST_FAIL = Duration.ofSeconds(3);
	
	public static final String BROWSER_STACK_USERNAME = "thulinh_Z4r3Gi";
	public static final String BROWSER_STACK_AUTOMATE_KEY = "NF42SYseWaxn2ngtwxih";
	public static final String BROWSER_STACK_URL = "https://" + BROWSER_STACK_USERNAME + ":" + BROWSER_STACK_AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	public static final String SOURCELAB_USERNAME = "oauth-thuylinh2409nguyen-d52d3";
	public static final String SOURCELAB_AUTOMATE_KEY = "d56dbdeb-f41d-4ec0-9359-4b6c9d97b09b";
	public static final String SOURCELAB_URL = "https://" + SOURCELAB_USERNAME + ":" + SOURCELAB_AUTOMATE_KEY + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
	
	public static final String CROSS_USERNAME = "thuylinh2409nguyen@gmail.com".replaceAll("@", "%40");
	public static final String CROSS_AUTOMATE_KEY = "OyU49IyY69gnjTsFG8GW0snnrxAHGc4T";
	public static final String CROSS_URL = "https://eu-desktop-hub.bitbar.com/wd/hub";
	
	public static final String LAMBDA_USERNAME = "thuylinh2409nguyen";
	public static final String LAMBDA_AUTOMATE_KEY = "ZxdiJKMv9lAWrlUf0XIjMk87yKtAMRVJcj1EB3Hollb3QfX6I9";
	public static final String LAMBDA_URL = "https://" + LAMBDA_USERNAME + ":" + LAMBDA_AUTOMATE_KEY + "@hub.lambdatest.com/wd/hub";
}