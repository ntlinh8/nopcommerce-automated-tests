package pageObject.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.BasePage;
import interfaces.user.UserProductListUI;

public class UserProductListPageObject extends BasePage{
	WebDriver driver;

	UserProductListPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectSortByLabel(String optionLabel) {
		waitForElementVisible(driver, UserProductListUI.PRODUCT_SORT_DROPDOWN);
		selectItemDefaultDropdown(driver, UserProductListUI.PRODUCT_SORT_DROPDOWN, optionLabel);
	}

	public boolean isProductNameWasSortedByLabel(String optionLabel) throws Exception {
		waitForElementVisible(driver, UserProductListUI.ALL_PRODUCT_TITLE);
		SleepInSecond(3);
		List<WebElement> productElementList = getWebElements(driver, UserProductListUI.ALL_PRODUCT_TITLE);
		List<String> productNameList = new ArrayList<>();
		for (WebElement productElement : productElementList) {
			productNameList.add(productElement.getText());
		}
		switch (optionLabel) {
		case "Name: A to Z":
			return isSortFromAToZ(productNameList);
		case "Name: Z to A":
			return isSortFromZToA(productNameList);
		default:
			throw new Exception("You entered the invalid option");
		}
	}
	
	public boolean isProductNameWasSortedByPrice(String optionLabel) throws Exception {
		waitForElementVisible(driver, UserProductListUI.ALL_PRODUCT_PRICE);
		SleepInSecond(3);
		List<WebElement> productElementList = getWebElements(driver, UserProductListUI.ALL_PRODUCT_PRICE);
		List<Float> productNameList = new ArrayList<Float>();
		for (WebElement productElement : productElementList) {
			productNameList.add(Float.parseFloat(productElement.getText().replace("$", "").trim()));
		}
		switch (optionLabel) {
		case "Price: Low to High":
			return isSortFromLowToHigh(productNameList);
		case "Price: High to Low":
			return isSortFromHighToLow(productNameList);
		default:
			throw new Exception("You entered the invalid option");
		}
	}
	
	private boolean isSortFromAToZ(List<String> productNameList) {
		List<String> productNameListAfterSorted = new ArrayList<String>();
		for (String product : productNameList) {
			productNameListAfterSorted.add(product);
		}
		Collections.sort(productNameListAfterSorted);
		return productNameListAfterSorted.equals(productNameList);
	}
	
	private boolean isSortFromZToA(List<String> productNameList) {
		List<String> productNameListAfterSorted = new ArrayList<String>();
		for (String product : productNameList) {
			productNameListAfterSorted.add(product);
		}
		Collections.sort(productNameListAfterSorted);
		Collections.reverse(productNameListAfterSorted);
	    return productNameListAfterSorted.equals(productNameList);
	}
	
	private boolean isSortFromLowToHigh(List<Float> productPriceList) {
		List<Float> productPriceListAfterSorted = new ArrayList<Float>();
		for (Float productPrice : productPriceList) {
			productPriceListAfterSorted.add(productPrice);
		}
		Collections.sort(productPriceListAfterSorted);
		return productPriceListAfterSorted.equals(productPriceList);
	}
	
	private boolean isSortFromHighToLow(List<Float> productPriceList) {
		List<Float> productPriceListAfterSorted = new ArrayList<Float>();
		for (Float productPrice : productPriceList) {
			productPriceListAfterSorted.add(productPrice);
		}
		Collections.sort(productPriceListAfterSorted);
		Collections.reverse(productPriceListAfterSorted);
		return productPriceListAfterSorted.equals(productPriceList);
		
	}

	public int getDisplayedProductNumber() {
		waitForElementVisible(driver, UserProductListUI.ALL_PRODUCT_TITLE);
		return getElementSize(driver, UserProductListUI.ALL_PRODUCT_TITLE);
	}


	public String getCurrentPagingNumber() {
		waitForElementVisible(driver, UserProductListUI.DYNAMIC_PAGING_BY_CLASS, "current-page");
		return getElementText(driver, UserProductListUI.DYNAMIC_PAGING_BY_CLASS, "current-page");
	}

	public boolean isPagingButtonDisplayByClass(String className) {
		waitForElementVisible(driver, UserProductListUI.DYNAMIC_PAGING_BY_CLASS, className);
		return isElementDisplayed(driver, UserProductListUI.DYNAMIC_PAGING_BY_CLASS, className);
	}

	public void clickToPagingButtonByID(String className) {
		waitForElementClickable(driver, UserProductListUI.DYNAMIC_PAGING_BY_CLASS, className);
		clickToElement(driver, UserProductListUI.DYNAMIC_PAGING_BY_CLASS, className);
	}

	public boolean isPagingButtonUndisplayed() {
		waitForElementInvisible(driver, UserProductListUI.PAGING_BUTTON);
		return isElementUndisplayed(driver, UserProductListUI.PAGING_BUTTON);
	}

}
