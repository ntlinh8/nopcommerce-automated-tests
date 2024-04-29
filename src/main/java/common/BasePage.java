package common;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.admin.AdminAbstractPageUI;
import interfaces.user.UserAbstractPageUI;
import interfaces.user.UserProductListUI;
import pageObject.user.PageGeneratorManager;
import pageObject.user.UserAddAddressesObject;
import pageObject.user.UserChangePasswordPageObject;
import pageObject.user.UserCustomerInformationPageObject;
import pageObject.user.UserHomePageObject;
import pageObject.user.UserMyProductReviewObject;
import pageObject.user.UserProductDetailObject;
import pageObject.user.UserProductListPageObject;


public class BasePage {
	private Duration longTimeout = Duration.ofSeconds(30);
	private Duration shortTimeout = Duration.ofSeconds(5);
	
	static public BasePage getBasePageObject() {
		return new BasePage();
	}
	
	private void overrideGlobalTimeOut(WebDriver driver, Duration timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut);
	}
	
	private By getByLocator(String locator) {
		By by = null;
		if(locator.startsWith("id") || locator.startsWith("Id") || locator.startsWith("ID")) {
			by =By.id(locator.substring(3));
		}else if(locator.startsWith("name") || locator.startsWith("Name") || locator.startsWith("NAME")) {
			by =By.name(locator.substring(5));
		}else if(locator.startsWith("class") || locator.startsWith("Class") || locator.startsWith("CLASS")) {
			by =By.className(locator.substring(6));
		}else if(locator.startsWith("css") || locator.startsWith("Css") || locator.startsWith("CSS")) {
			by =By.cssSelector(locator.substring(4));
		}else if(locator.startsWith("xpath") || locator.startsWith("XPath") || locator.startsWith("XPATH")) {
			by =By.xpath(locator.substring(6));
		}else {
			throw new RuntimeException("Locator type is not supported!");
		}
		return by;
	}
	
	private String getDynamicXpath(String xpathLocator, String... values) {
		if(xpathLocator.startsWith("xpath") || xpathLocator.startsWith("XPath") || xpathLocator.startsWith("XPATH")) {
			xpathLocator = String.format(xpathLocator, (Object[]) values);
		}
		return xpathLocator;
	}
	
	public void openPageUrl(WebDriver driver, String url) {
		driver.get(url);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	public Set<String> getWindowHandles(WebDriver driver){
		return driver.getWindowHandles();
	}
	
	public void switchToWindowByID(WebDriver driver, String windowId) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(windowId)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	public void switchToWindowByTitle(WebDriver driver, String tabTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			if (driver.getTitle().equals(tabTitle)) {
				break;
			}
		}
	}
	
	public void closeAllTabWindowParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}
	
	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(getDynamicXpath(locator)));
	}
	
	public List<WebElement> getWebElements(WebDriver driver, String locator){
		return driver.findElements(getByLocator(locator));
	}
	
	public Set<Cookie> getCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setCookieAndReloadPage(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
		SleepInSecond(3);
		refreshCurrentPage(driver);
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}
	
	public void clickToElement(WebDriver driver, String locator, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locator, dynamicValues)).click();
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String textValue) {
		WebElement element = getWebElement(driver, locator);
		element.clear();
		element.sendKeys(textValue);
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locator, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
	
	public void sendKeyToDateTextbox(WebDriver driver, String locator, String textValue) {
		removeAttributeInDOM(driver, locator, "type");
		SleepInSecond(2);
		waitForElementVisible(driver, locator);
		sendkeyToElement(driver, locator, textValue);
	}
	
	public void selectItemDefaultDropdown(WebDriver driver, String locator, String textItem) {
		Select select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(textItem);
	}
	
	public void selectItemDefaultDropdown(WebDriver driver, String locator, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
		select.selectByVisibleText(textItem);
	}
	
	public void SelectCustomDropDown(WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
		clickToElement(driver, parentLocator);
		waitForAllElementPresence(driver, childLocator);
		List<WebElement> speedDropdownItems = getWebElements(driver, childLocator);
		for (WebElement item : speedDropdownItems) {
			if(item.getText().trim().equals(expectedItem)) {
				item.click();
				break;
			}
		}
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String locator, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedTextItem) {
		getWebElement(driver, parentXpath).click();
		SleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
		List<WebElement> speedDropdownItems = driver.findElements(getByLocator(childXpath));
		for (WebElement item : speedDropdownItems) {
			if(item.getText().trim().equals(expectedTextItem)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
				SleepInSecond(1);
				item.click();
				break;
			}
		}
	}
	
	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).getAttribute(attributeName);
	}
	
	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}
	
	public String getElementText(WebDriver driver, String locator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).getText();
	}
	
	public String getElementCSSValue(WebDriver driver, String locator, String propertyName) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}
	
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, String locator) {
		return getWebElements(driver, locator).size();
	}
	
	public int getElementSize(WebDriver driver, String locator, String... dynamicValues) {
		return getWebElements(driver, getDynamicXpath(locator, dynamicValues)).size();
	}
	
	public void checkToDefaultCheckboxRadio(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if(!isElementSelected(driver, locator)) {
			element.click();
		}
	}
	
	public void checkToDefaultCheckboxRadio(WebDriver driver, String locator, String...dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locator, dynamicValues));
		if(!isElementSelected(driver, getDynamicXpath(locator, dynamicValues))) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if(isElementSelected(driver, locator)) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String locator, String...dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locator, dynamicValues));
		if(isElementSelected(driver, getDynamicXpath(locator, dynamicValues))) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).isDisplayed();
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeOut(driver, shortTimeout);
		List<WebElement> elements = getWebElements(driver, locator);
		overrideGlobalTimeOut(driver, longTimeout);
		if(elements.size() == 0) {
			return true;
		}else if(elements.size() != 0 && !elements.get(0).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locator, String... dynamicValues) {
		overrideGlobalTimeOut(driver, shortTimeout);
		List<WebElement> elements = getWebElements(driver, getDynamicXpath(locator, dynamicValues));
		overrideGlobalTimeOut(driver, longTimeout);
		if(elements.size() == 0) {
			return true;
		}else if(elements.size() != 0 && !elements.get(0).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isElementEnable(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).isSelected();
	}
	
	public void switchToFrameIFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locator) {
		new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locator,  String... dynamicValues) {
		new Actions(driver).moveToElement(getWebElement(driver, getDynamicXpath(locator, dynamicValues))).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		new Actions(driver).sendKeys(getWebElement(driver, locator), key).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... dynamicValues) {
		new Actions(driver).sendKeys(getWebElement(driver, getDynamicXpath(locator, dynamicValues)), key).perform();
	}
	
	public void doubleClickToElement(WebDriver driver, String locator) {
		new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
	}
	
	public void doubleClickToElement(WebDriver driver, String locator, String... dynamicValues) {
		new Actions(driver).doubleClick(getWebElement(driver, getDynamicXpath(locator, dynamicValues))).perform();
	}
	
	public void rightClickToElement(WebDriver driver, String locator) {
		new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
	}
	
	public void dragAndDrop(WebDriver driver, String sourceXpath, String targetXpath) {
		new Actions(driver).dragAndDrop(getWebElement(driver, sourceXpath), getWebElement(driver, targetXpath)).perform();
	}
	
	public void sendKeyboardToElement(WebDriver driver, String locator, String key) {
		new Actions(driver).sendKeys(getWebElement(driver, locator), key).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void hightlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		SleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}
	
	public void hightlightElement(WebDriver driver, String locator, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicXpath(locator, dynamicValues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		SleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", getWebElement(driver, locator));
	}
	
	public void clickToElementByJS(WebDriver driver, String locator, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
	}

	public void scrollToElementOnTop(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}
	
	public void scrollToElementOnTop(WebDriver driver, String locator, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
	}
	
	public void scrollToElementOnDown(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
	}
	
	public void scrollToElementOnDown(WebDriver driver, String locator, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}
	
	public void addAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getWebElement(driver, locator));
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}
	
	public String getElementAttributeByJS(WebDriver driver, String locator, String attributeName) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0]." + attributeName, getWebElement(driver, locator));
	}
	
	public boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, locator));
		return status;
	}
	
	public boolean isImageLoaded(WebDriver driver, String locator, String...dynamicValues) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
		return status;
	}

	
	public boolean isPageLoadedSuccess(WebDriver driver) {
		SleepInSecond(3);
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active ===0);");
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	public void waitForElementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}
	
	public void waitForElementVisible(WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locator, dynamicValues))));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locator, dynamicValues))));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideGlobalTimeOut(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
		overrideGlobalTimeOut(driver, longTimeout);
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideGlobalTimeOut(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locator, dynamicValues))));
		overrideGlobalTimeOut(driver, longTimeout);
	}
	
	public void waitForAllElementInvisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideGlobalTimeOut(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElement(driver, locator)));
		overrideGlobalTimeOut(driver, longTimeout);
	}
	
	public void waitForElementClickable(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locator, dynamicValues))));
	}
	
	public void waitForElementPresence(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
	}
	
	public void waitForElementPresence(WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByLocator(getDynamicXpath(locator, dynamicValues))));
	}
	
	public void waitForAllElementPresence(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
	}
	
	public void waitForAllElementPresence(WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locator, dynamicValues))));
	}
	
	public void uploadMultipleFiles(WebDriver driver, String locator, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FILE_PATH_FOLDER;
		String fullFilePaths = "";
		for (String fileName : fileNames) {
			fullFilePaths = fullFilePaths + filePath + fileName + "\n";
		}
		fullFilePaths.trim();
		getWebElement(driver, locator).sendKeys(fullFilePaths);
	}
	
	public void SleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// Apply in level dynamic locator
	public BasePage openPageInMyAccountArea(WebDriver driver, String pageName) {
		String leftMenuLocator = String.format(UserAbstractPageUI.DYNAMIC_PAGE_IN_MY_ACCOUNT_AREA, pageName);
		waitForElementVisible(driver, leftMenuLocator);
		clickToElement(driver, leftMenuLocator);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInformationPage(driver);
		case "Addresses":
			return PageGeneratorManager.getUserAddAddressPage(driver);
		case "Change password":
			return PageGeneratorManager.getUserChangePasswordPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewPage(driver);
		default:
			return null;
		}
	}
	
	// Apply only Level_07_Switch_Page
	public UserCustomerInformationPageObject clickToCustomerInfoTab(WebDriver driver) {
		openPageInMyAccountArea(driver, "Customer info");
		return PageGeneratorManager.getUserCustomerInformationPage(driver);
	}
	
	// Apply only Level_07_Switch_Page
	public UserAddAddressesObject clickToAddAddressesTab(WebDriver driver) {
		openPageInMyAccountArea(driver, "Addresses");
		return PageGeneratorManager.getUserAddAddressPage(driver);
	}

	// Apply only Level_07_Switch_Page
	public UserChangePasswordPageObject clickToChangePasswordTab(WebDriver driver) {
		openPageInMyAccountArea(driver, "Change password");
		return PageGeneratorManager.getUserChangePasswordPage(driver);
	}

	// Apply only Level_07_Switch_Page
	public UserMyProductReviewObject clickToMyProductReviewTab(WebDriver driver) {
		openPageInMyAccountArea(driver, "My product reviews");
		return PageGeneratorManager.getUserMyProductReviewPage(driver);
	}
	
	public void clickToFooterLink(WebDriver driver, String footerLabel) {
		scrollToBottomPage(driver);
		waitForElementVisible(driver, UserAbstractPageUI.DYNAMIC_FOOTER_LINK, footerLabel);
		clickToElement(driver, UserAbstractPageUI.DYNAMIC_FOOTER_LINK, footerLabel);
	}

	public UserHomePageObject clickToLogOutLink(WebDriver driver) {
		clickToHeaderLinkByClass(driver, "ico-logout");
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
	public void clickToHeaderLinkByClass(WebDriver driver, String className) {
		scrollToElementOnTop(driver, UserAbstractPageUI.DYNAMIC_HEADER_LINK_BY_CLASS, className);
		waitForElementVisible(driver, UserAbstractPageUI.DYNAMIC_HEADER_LINK_BY_CLASS, className);
		clickToElement(driver, UserAbstractPageUI.DYNAMIC_HEADER_LINK_BY_CLASS, className);
	}
	
	public void hoverToHeaderLinkByClass(WebDriver driver, String className) {
		scrollToElementOnTop(driver, UserAbstractPageUI.DYNAMIC_HEADER_LINK_BY_CLASS, className);
		waitForElementVisible(driver, UserAbstractPageUI.DYNAMIC_HEADER_LINK_BY_CLASS, className);
		hoverMouseToElement(driver, UserAbstractPageUI.DYNAMIC_HEADER_LINK_BY_CLASS, className);
	}
	
	// Apply Pattern Object
	/**
	 * Input value to textbox by id of textbox
	 * @param driver
	 * @param value
	 * @param textboxId
	 * @author ntlinh8
	 */
	public void inputToTextboxById(WebDriver driver, String value, String textboxId) {
		waitForElementVisible(driver, UserAbstractPageUI.DYNAMIC_TEXTBOX_BY_ID, textboxId);
		sendkeyToElement(driver, UserAbstractPageUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxId);
	}
	
	public void inputToTextAreaById(WebDriver driver, String value, String textareaId) {
		waitForElementVisible(driver, UserAbstractPageUI.DYNAMIC_TEXTAREA_BY_ID, textareaId);
		sendkeyToElement(driver, UserAbstractPageUI.DYNAMIC_TEXTAREA_BY_ID, value, textareaId);
	}
	
	public String getValueAttributFromTextboxByID(WebDriver driver, String textboxId) {
		waitForElementVisible(driver, UserAbstractPageUI.DYNAMIC_TEXTBOX_BY_ID, textboxId);
		return getElementAttribute(driver, UserAbstractPageUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxId);
	}
	
	public String getValueAttributFromTextareaByID(WebDriver driver, String textareaId) {
		waitForElementVisible(driver, UserAbstractPageUI.DYNAMIC_TEXTAREA_BY_ID, textareaId);
		return getElementAttribute(driver, UserAbstractPageUI.DYNAMIC_TEXTAREA_BY_ID, "value", textareaId);
	}
	
	public String getErrorMessageOfTextboxByID(WebDriver driver, String ID) {
		waitForElementVisible(driver, UserAbstractPageUI.DYNAMIC_ERROR_MESSAGE_ID, ID);
		return getElementText(driver, UserAbstractPageUI.DYNAMIC_ERROR_MESSAGE_ID, ID);
	}
	
	public void selectDropdownByName(WebDriver driver, String option, String dropdownName) {
		waitForElementVisible(driver, UserAbstractPageUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownName);
		selectItemDefaultDropdown(driver, UserAbstractPageUI.DYNAMIC_DROPDOWN_BY_NAME, option, dropdownName);
	}
	
	public String getSelectedItemFromDropdownByName(WebDriver driver, String dropdownName) {
		waitForElementVisible(driver, UserAbstractPageUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownName);
		return getSelectedItemDefaultDropdown(driver, UserAbstractPageUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownName);
	}
	
	public String getCurrentPageTitle(WebDriver driver) {
		waitForElementVisible(driver, UserProductListUI.PRODUCT_TITLE_PAGE);
		return getElementText(driver, UserProductListUI.PRODUCT_TITLE_PAGE);
	}
	
	public String getUserAlertMessage(WebDriver driver) {
		waitForElementVisible(driver, UserAbstractPageUI.USER_ALERT_MESSAGE);
		return getElementText(driver, UserAbstractPageUI.USER_ALERT_MESSAGE);
	}
	
	public void clickToUserCloseAlertMessageButton(WebDriver driver) {
		waitForElementClickable(driver, UserAbstractPageUI.USER_CLOSE_ALERT_MESSAGE);
		clickToElement(driver, UserAbstractPageUI.USER_CLOSE_ALERT_MESSAGE);
		waitForElementInvisible(driver, UserAbstractPageUI.USER_CLOSE_ALERT_MESSAGE);
	}
	
	public void clickButtonByLabel(WebDriver driver, String buttonLabel) {
		waitForElementClickable(driver, UserAbstractPageUI.DYNAMIC_BUTTON_BY_LABEL, buttonLabel);
		clickToElement(driver, UserAbstractPageUI.DYNAMIC_BUTTON_BY_LABEL, buttonLabel);
	}
	
	public void clickButtonByClass(WebDriver driver, String buttonClassName) {
		waitForElementClickable(driver, UserAbstractPageUI.DYNAMIC_BUTTON_BY_CLASS, buttonClassName);
		clickToElement(driver, UserAbstractPageUI.DYNAMIC_BUTTON_BY_CLASS, buttonClassName);
	}
	
	public void clickButtonLinkByLabel(WebDriver driver, String linkLabel) {
		waitForElementClickable(driver, UserAbstractPageUI.DYNAMIC_BUTTON_LINK_BY_LABEL, linkLabel);
		clickToElement(driver, UserAbstractPageUI.DYNAMIC_BUTTON_LINK_BY_LABEL, linkLabel);
	}
	
	public boolean isProductExistInTableByProductName(WebDriver driver, String productName) {
		waitForElementVisible(driver, UserAbstractPageUI.DYNAMIC_PRODUCT_NAME_IN_TABLE, productName);
		return isElementDisplayed(driver, UserAbstractPageUI.DYNAMIC_PRODUCT_NAME_IN_TABLE, productName);
	}
	
	public boolean isProductNotExistInTableByProductName(WebDriver driver, String productName) {
		waitForElementInvisible(driver, UserAbstractPageUI.DYNAMIC_PRODUCT_NAME_IN_TABLE, productName);
		return isElementUndisplayed(driver, UserAbstractPageUI.DYNAMIC_PRODUCT_NAME_IN_TABLE, productName);
	}
	
	public void hoverToMenuItemByLabel(WebDriver driver, String menuLabel) {
		waitForElementClickable(driver, UserAbstractPageUI.HEADER_MENU_BUTTON, menuLabel);
		hoverMouseToElement(driver, UserAbstractPageUI.HEADER_MENU_BUTTON, menuLabel);
	}

	public UserProductListPageObject clickToSubmenuInHeaderMenuByLabel(WebDriver driver, String menuLabel, String submenuLabel) {
		waitForElementClickable(driver, UserAbstractPageUI.SUBMENU_BUTTON, menuLabel, submenuLabel);
		clickToElement(driver, UserAbstractPageUI.SUBMENU_BUTTON, menuLabel, submenuLabel);
		return PageGeneratorManager.getUserProductListPage(driver);
	}
	
	public void clickToButtonInProductItemByProductName(WebDriver driver, String productName, String buttonLabel) {
		waitForElementClickable(driver, UserAbstractPageUI.DYNAMIC_BUTTON_IN_PRODUCT_ITEM_BY_PRODUCT_NAME, productName, buttonLabel);
		clickToElement(driver, UserAbstractPageUI.DYNAMIC_BUTTON_IN_PRODUCT_ITEM_BY_PRODUCT_NAME, productName, buttonLabel);
	}
	
	public String getBodyTableData(WebDriver driver, String className) {
		waitForElementVisible(driver, UserAbstractPageUI.TABLE_BODY_DATA, className);
		return getElementText(driver, UserAbstractPageUI.TABLE_BODY_DATA, className);
	}
	
	public UserProductDetailObject clickToProductTitle(WebDriver driver, String productTitle) {
		waitForElementClickable(driver, UserAbstractPageUI.DYNAMIC_PRODUCT_TITLE, productTitle);
		clickToElement(driver, UserAbstractPageUI.DYNAMIC_PRODUCT_TITLE, productTitle);
		return PageGeneratorManager.getUserProductDetailPage(driver);
	}
	
	public boolean isProductDisplayByProductName(WebDriver driver, String productTitle) {
		waitForElementVisible(driver, UserAbstractPageUI.DYNAMIC_PRODUCT_TITLE, productTitle);
		return isElementDisplayed(driver, UserAbstractPageUI.DYNAMIC_PRODUCT_TITLE, productTitle);
	}
	
	public void checkToCheckboxRadioButtonByLabel(WebDriver driver, String optionLabel) {
		waitForElementClickable(driver, UserAbstractPageUI.DYNAMIC_CHECKBOX_RADIO_BUTTON_BY_LABEL, optionLabel);
		checkToDefaultCheckboxRadio(driver, UserAbstractPageUI.DYNAMIC_CHECKBOX_RADIO_BUTTON_BY_LABEL, optionLabel);
	}
	
	public void uncheckToCheckboxButtonByLabel(WebDriver driver, String optionLabel) {
		waitForElementClickable(driver, UserAbstractPageUI.DYNAMIC_CHECKBOX_RADIO_BUTTON_BY_LABEL, optionLabel);
		uncheckToDefaultCheckbox(driver, UserAbstractPageUI.DYNAMIC_CHECKBOX_RADIO_BUTTON_BY_LABEL, optionLabel);
	}
	public void checkToCheckboxRadioButtonById(WebDriver driver, String optionId) {
		waitForElementClickable(driver, UserAbstractPageUI.DYNAMIC_CHECKBOX_RADIO_BUTTON_BY_ID, optionId);
		checkToDefaultCheckboxRadio(driver, UserAbstractPageUI.DYNAMIC_CHECKBOX_RADIO_BUTTON_BY_ID, optionId);
	}
	
	public void uncheckToCheckboxButtonById(WebDriver driver, String optionId) {
		waitForElementClickable(driver, UserAbstractPageUI.DYNAMIC_CHECKBOX_RADIO_BUTTON_BY_ID, optionId);
		uncheckToDefaultCheckbox(driver, UserAbstractPageUI.DYNAMIC_CHECKBOX_RADIO_BUTTON_BY_ID, optionId);
	}
	
	public boolean isCheckboxRadioCheckedById(WebDriver driver, String optionId) {
		waitForElementVisible(driver, UserAbstractPageUI.DYNAMIC_CHECKBOX_RADIO_BUTTON_BY_ID, optionId);
		return isElementSelected(driver, UserAbstractPageUI.DYNAMIC_CHECKBOX_RADIO_BUTTON_BY_ID, optionId);
	}
	
	public boolean isCheckboxRadioCheckedByLabel(WebDriver driver, String optionLabel) {
		waitForElementVisible(driver, UserAbstractPageUI.DYNAMIC_CHECKBOX_RADIO_BUTTON_BY_LABEL, optionLabel);
		return isElementSelected(driver, UserAbstractPageUI.DYNAMIC_CHECKBOX_RADIO_BUTTON_BY_LABEL, optionLabel);
	}
	
	public boolean isMiniShoppingCartDisplay(WebDriver driver) {
		waitForElementVisible(driver, UserAbstractPageUI.MINI_SHOPPING_CART);
		return isElementDisplayed(driver, UserAbstractPageUI.MINI_SHOPPING_CART);
	}
	
	public String getAddProductMessageDisplay(WebDriver driver) {
		waitForElementVisible(driver, UserAbstractPageUI.DYNAMIC_PRODUCT_NAME_IN_MINI_CART);
		return getElementText(driver, UserAbstractPageUI.DYNAMIC_PRODUCT_NAME_IN_MINI_CART);
	}
	
	public String getProductNameInMiniCart(WebDriver driver) {
		waitForElementVisible(driver, UserAbstractPageUI.ADD_PRODUCT_MESSAGE);
		return getElementText(driver, UserAbstractPageUI.ADD_PRODUCT_MESSAGE);
	}
	
	public String getAttributeOfProduct(WebDriver driver) {
		waitForElementVisible(driver, UserAbstractPageUI.PRODUCT_ATTRIBUTE);
		return getElementText(driver, UserAbstractPageUI.PRODUCT_ATTRIBUTE);
	}
	
	public String getProductUnitPrice(WebDriver driver) {
		waitForElementVisible(driver, UserAbstractPageUI.PRODUCT_UNIT_PRICE);
		return getElementText(driver, UserAbstractPageUI.PRODUCT_UNIT_PRICE);
	}
	
	public String getProductQuantity(WebDriver driver) {
		waitForElementVisible(driver, UserAbstractPageUI.PRODUCT_QUANTITY);
		return getElementText(driver, UserAbstractPageUI.PRODUCT_QUANTITY);
	}
	
	public String getSubPrice(WebDriver driver) {
		waitForElementVisible(driver, UserAbstractPageUI.PRODUCT_SUB_TOTAL);
		return getElementText(driver, UserAbstractPageUI.PRODUCT_SUB_TOTAL);
	}
	
	// Admin
	public void clickToLeftMenuByLabel(WebDriver driver, String label) {
		waitForElementClickable(driver, AdminAbstractPageUI.DYNAMIC_LEFT_MENU_LINK_BY_LABEL, label);
		doubleClickToElement(driver, AdminAbstractPageUI.DYNAMIC_LEFT_MENU_LINK_BY_LABEL, label);
	}

	public boolean isSelectedLeftMenuByLabel(WebDriver driver, String label) {
		waitForElementVisible(driver, AdminAbstractPageUI.DYNAMIC_SELECTED_LEFT_MENU_LINK_BY_LABEL, label);
		return isElementDisplayed(driver, AdminAbstractPageUI.DYNAMIC_SELECTED_LEFT_MENU_LINK_BY_LABEL, label);
	}

	public void clickToSubLeftMenuByLabel(WebDriver driver, String label) {
		waitForElementClickable(driver, AdminAbstractPageUI.DYNAMIC_SUB_MENU_LINK_BY_LABEL, label);
		doubleClickToElement(driver, AdminAbstractPageUI.DYNAMIC_SUB_MENU_LINK_BY_LABEL, label);
	}
	
	public void clickButtonByLabelInAdminPage(WebDriver driver, String label) {
		waitForElementClickable(driver, AdminAbstractPageUI.DYNAMIC_BUTTON, label);
		clickToElement(driver, AdminAbstractPageUI.DYNAMIC_BUTTON, label);
	}
	
	public void clickButtonLinkByLabelInAdminPage(WebDriver driver, String label) {
		waitForElementClickable(driver, AdminAbstractPageUI.DYNAMIC_BUTTON_LINK, label);
		clickToElement(driver, AdminAbstractPageUI.DYNAMIC_BUTTON_LINK, label);
	}
	
	public String getAdminAlertMessage(WebDriver driver) {
		waitForElementVisible(driver, AdminAbstractPageUI.ADMIN_ALERT_MESSAGE);
		return getElementText(driver, AdminAbstractPageUI.ADMIN_ALERT_MESSAGE);
	}
	
	public void clickToAdminCloseAlertMessage(WebDriver driver) {
		waitForElementClickable(driver, AdminAbstractPageUI.ADMIN_CLOSE_ALERT_MESSAGE);
		clickToElement(driver, AdminAbstractPageUI.ADMIN_CLOSE_ALERT_MESSAGE);
		waitForElementInvisible(driver, AdminAbstractPageUI.ADMIN_CLOSE_ALERT_MESSAGE);
	}
	
}