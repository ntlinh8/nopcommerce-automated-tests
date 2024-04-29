package user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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
import pageObject.user.UserCheckoutPageObject;
import pageObject.user.UserConfirmOrderDetailPageObject;
import pageObject.user.UserHomePageObject;
import pageObject.user.UserLoginPageObject;
import pageObject.user.UserMyAccountObject;
import pageObject.user.UserOrderPageObject;
import pageObject.user.UserProductDetailObject;
import pageObject.user.UserProductListPageObject;
import pageObject.user.UserShoppingCartPageObject;
@Epic("Regrestion Test")
@Feature("Order")
public class Topic_07_Order extends BaseTest{
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserProductListPageObject productListPage;
	private UserProductDetailObject productDetailPage;
	private UserShoppingCartPageObject shoppingCartPage;
	private UserCheckoutPageObject checkoutPageObject;
	private UserConfirmOrderDetailPageObject confirmOrderDetailPage;
	private UserMyAccountObject myAccountPage;
	private UserOrderPageObject orderPage;
	
	private String buildPCProductName, processor1, processor2, ram1, ram2, hdd1, hdd2, os1, os2;
	private String software1, software2, software3, unitPrice1, unitPrice2, quantity, subPrice1, subPrice2;
	private String giftWrapping; 
	private String lenovoPCName, macbookAppleProduct, asusLaptopProduct;
	private String city, address, country, zipcode, phoneNumber, paymentMethod1, paymentMethod2, shippingMethod1, shippingMethod2, orderID;
	
	@Parameters({"browser", "servername"})
	@BeforeClass
	public void beforeClass(String browserName, String serverName) {
		driver = createBrowserDriver(browserName, serverName);
		buildPCProductName = "Build your own computer";
		lenovoPCName = "Lenovo IdeaCentre 600 All-in-One PC";
		macbookAppleProduct = "Apple MacBook Pro 13-inch";
		asusLaptopProduct = "Asus N551JK-XO076H Laptop";
		
		processor1 = "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]";
		ram1 = "8GB [+$60.00]";
		hdd1 = "400 GB [+$100.00]";
		os1 = "Vista Premium [+$60.00]";
		software1 = "Microsoft Office [+$50.00]";
		software2 = "Acrobat Reader [+$10.00]";
		software3 = "Total Commander [+$5.00]";
		unitPrice1 = "$1,500.00";
		quantity = "1";
		subPrice1 = "$1,500.00";
		
		processor2 = "2.2 GHz Intel Pentium Dual-Core E2200";
		ram2 = "4GB [+$20.00]";
		hdd2 = "320 GB";
		os2 = "Vista Home [+$50.00]";
		unitPrice2 = "$1,320.00";
		subPrice2 = "$1,320.00";
		giftWrapping = "Yes [+$10.00]";
		
		city = "Ho Chi Minh City";
		address = "No. 225, Dong Khoi Street";
		country = "Viet Nam";
		zipcode = "550000";
		phoneNumber = "0123123123";
		paymentMethod1 = "Check / Money Order";
		paymentMethod2 = "Credit Card";
		shippingMethod1 = "Ground ($0.00)";
		shippingMethod2 = "2nd Day Air ($0.00)";
		
		homePage = PageGeneratorManager.getUserHomePage(driver);
		log.info("Pre-condition - Step 1: Click to Login link to clear data");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Pre-condition - Step 2: Input correct email");
		loginPage.inputToTextboxById(driver, Common_Register_NewAccount.EMAIL, "Email");
		
		log.info("Pre-condition - Step 3: Input correct password");
		loginPage.inputToTextboxById(driver, Common_Register_NewAccount.PASSWORD, "Password");
		
		log.info("Pre-condition - Step 4: Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Pre-condition - Step 5: Verify the my account page is displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}
	
	@Description("Add Product to Cart")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Order_01_Add_Product_To_Cart(){
		log.info("Order 01 - Step 1: Hover on Coputer menu");
		homePage.hoverToMenuItemByLabel(driver, "Computers");
		
		log.info("Order 01 - Step 2: Click on Desktop submenu");
		productListPage = homePage.clickToSubmenuInHeaderMenuByLabel(driver, "Computers", "Desktops");
		
		log.info("Order 01 - Step 3: Verify the current page is Desktops");
		verifyEquals(productListPage.getCurrentPageTitle(driver), "Desktops");
		
		log.info("Order 01 - Step 4: Click to Build your own computer");
		productDetailPage = productListPage.clickToProductTitle(driver, buildPCProductName);
		
		log.info("Order 01 - Step 5: Select option for your computer");
		productDetailPage.selectDropdownByName(driver, "product_attribute_1", processor1);
		productDetailPage.selectDropdownByName(driver, "product_attribute_2", ram1);
		productDetailPage.checkToCheckboxRadioButtonByLabel(driver, hdd1);
		productDetailPage.checkToCheckboxRadioButtonByLabel(driver, os1);
		productDetailPage.checkToCheckboxRadioButtonByLabel(driver, software1);
		productDetailPage.checkToCheckboxRadioButtonByLabel(driver, software2);
		productDetailPage.checkToCheckboxRadioButtonByLabel(driver, software3);
		
		log.info("Order 01 - Step 6: Click Add to cart button");
		productDetailPage.clickButtonByLabel(driver, "Add to cart");
		
		log.info("Order 01 - Step 7: Verify the add to cart success message display");
		verifyEquals(productDetailPage.getUserAlertMessage(driver), "The product has been added to your shopping cart");
		
		log.info("Order 01 - Step 8: Click to close button");
		productDetailPage.clickToUserCloseAlertMessageButton(driver);
		
		log.info("Order 01 - Step 9: Hover the Shopping Cart header menu");
		productDetailPage.hoverToHeaderLinkByClass(driver, "ico-cart");
		
		log.info("Order 01 - Step 10: Verify the product was added");
		verifyTrue(productDetailPage.isMiniShoppingCartDisplay(driver));
		verifyEquals(productDetailPage.getAddProductMessageDisplay(driver), "There are 1 item(s) in your cart.");
		verifyEquals(productDetailPage.getProductNameInMiniCart(driver), buildPCProductName);
		verifyEquals(productDetailPage.getAttributeOfProduct(driver), "Processor: " + processor1 + "\nRAM: " + ram1 + "\nHDD: " + hdd1 + "\nOS: " + os1 + "\nSoftware: " + software1 + "\nSoftware: " + software2 + "\nSoftware: " + software3);
		verifyEquals(productDetailPage.getProductUnitPrice(driver), unitPrice1);
		verifyEquals(productDetailPage.getProductQuantity(driver), quantity);
		verifyEquals(productDetailPage.getSubPrice(driver), subPrice1);
	}
	
	@Description("Edit Product in Shopping cart")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Order_02_Edit_Product_In_Shopping_Cart(){
		log.info("Order 02 - Step 1: Click to Shopping cart header link");
		productDetailPage.clickToHeaderLinkByClass(driver, "ico-cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		
		log.info("Order 02 - Step 2: Verify the current page is Shopping cart");
		verifyEquals(shoppingCartPage.getCurrentPageTitle(driver), "Shopping cart");
		
		log.info("Order 02 - Step 3: Click to Edit button");
		productDetailPage = shoppingCartPage.clickEditButtonByProductName(buildPCProductName);
		
		log.info("Order 02 - Step 4: Select option for your computer");
		productDetailPage.selectDropdownByName(driver, "product_attribute_1", processor2);
		productDetailPage.selectDropdownByName(driver, "product_attribute_2", ram2);
		productDetailPage.checkToCheckboxRadioButtonByLabel(driver, hdd2);
		productDetailPage.checkToCheckboxRadioButtonByLabel(driver, os2);
		productDetailPage.checkToCheckboxRadioButtonByLabel(driver, software1);
		productDetailPage.uncheckToCheckboxButtonByLabel(driver, software2);
		productDetailPage.uncheckToCheckboxButtonByLabel(driver, software3);
		
		log.info("Order 02 - Step 5: Click Update button");
		productDetailPage.clickButtonByLabel(driver, "Update");
		
		log.info("Order 02 - Step 6: Verify the add to cart success message display");
		verifyEquals(productDetailPage.getUserAlertMessage(driver), "The product has been added to your shopping cart");
		
		log.info("Order 01 - Step 7: Click to close button");
		productDetailPage.clickToUserCloseAlertMessageButton(driver);
		
		log.info("Order 02 - Step 8: Hover the Shopping Cart header menu");
		productDetailPage.hoverToHeaderLinkByClass(driver, "ico-cart");
		
		log.info("Order 02 - Step 9: Verify the product was updated with correct data");
		verifyTrue(productDetailPage.isMiniShoppingCartDisplay(driver));
		verifyEquals(productDetailPage.getAddProductMessageDisplay(driver), "There are 1 item(s) in your cart.");
		verifyEquals(productDetailPage.getProductNameInMiniCart(driver), buildPCProductName);
		verifyEquals(productDetailPage.getAttributeOfProduct(driver), "Processor: " + processor2 + "\nRAM: " + ram2 + "\nHDD: " + hdd2 + "\nOS: " + os2 + "\nSoftware: " + software1);
		verifyEquals(productDetailPage.getProductUnitPrice(driver), unitPrice2);
		verifyEquals(productDetailPage.getProductQuantity(driver), quantity);
		verifyEquals(productDetailPage.getSubPrice(driver), subPrice2);
	}
	
	@Description("Remove product in Shopping Cart")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Order_03_Remove_Product_In_Shopping_Cart(){
		log.info("Order 03 - Step 1: Click to Shopping cart header link");
		productDetailPage.clickToHeaderLinkByClass(driver, "ico-cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		
		log.info("Order 03 - Step 2: Verify the current page is Shopping cart");
		verifyEquals(shoppingCartPage.getCurrentPageTitle(driver), "Shopping cart");
		
		log.info("Order 03 - Step 3: Click to Remove button");
		shoppingCartPage.clickRemoveButtonByProductName(buildPCProductName);
		
		log.info("Order 03 - Step 4: Verify the Product not exist in the Shopping Cart page");
		verifyTrue(shoppingCartPage.isProductNotExistInTableByProductName(driver, buildPCProductName));
		
		log.info("Order 03 - Step 5: Verify body table displays with message: Your Shopping Cart is empty!");
		verifyEquals(shoppingCartPage.getBodyTableData(driver, "page shopping-cart-page"), "Your Shopping Cart is empty!");
	}
	
	@Description("Update Shopping Cart")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Order_04_Update_Shopping_Cart(){
		log.info("Order 04 - Step 1: Hover on Coputer menu");
		shoppingCartPage.hoverToMenuItemByLabel(driver, "Computers");
		
		log.info("Order 04 - Step 2: Click on Desktop submenu");
		productListPage = shoppingCartPage.clickToSubmenuInHeaderMenuByLabel(driver, "Computers", "Desktops");
		
		log.info("Order 04 - Step 3: Verify the current page is Desktops");
		verifyEquals(productListPage.getCurrentPageTitle(driver), "Desktops");
		
		log.info("Order 04 - Step 4: Click to Lenovo IdedPC");
		productDetailPage = productListPage.clickToProductTitle(driver, lenovoPCName);
		
		log.info("Order 04 - Step 5: Click Add to cart button");
		productDetailPage.clickButtonByLabel(driver, "Add to cart");
		
		log.info("Order 04 - Step 6: Verify the add to cart success message display");
		verifyEquals(productDetailPage.getUserAlertMessage(driver), "The product has been added to your shopping cart");
		
		log.info("Order 04 - Step 7: Click to close button");
		productDetailPage.clickToUserCloseAlertMessageButton(driver);
		
		log.info("Order 04 - Step 8: Click to Shopping cart header link");
		productDetailPage.clickToHeaderLinkByClass(driver, "ico-cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		
		log.info("Order 04 - Step 9: Verify the product exist in the Shopping Cart page");
		verifyTrue(shoppingCartPage.isProductExistInTableByProductName(driver, lenovoPCName));
		
		log.info("Order 04 - Step 10: Change product quantity");
		shoppingCartPage.changeProductQuantityByProductName("5", lenovoPCName);
		
		log.info("Order 04 - Step 11: Click update shopping cart button");
		shoppingCartPage.clickButtonByLabel(driver, "Update shopping cart");
		
		log.info("Order 04 - Step 12: Verify the total price is correct");
		verifyEquals(shoppingCartPage.getSubTotalPriceByProductName(lenovoPCName), "$2,500.00");
	}
	
	@Description("Payment method by cheque")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Order_05_Payment_Method_By_Cheque(){
		log.info("Order 05 - Step 1: Hover on Coputer menu");
		shoppingCartPage.hoverToMenuItemByLabel(driver, "Computers");
		
		log.info("Order 05 - Step 2: Click on Notebook submenu");
		productListPage = shoppingCartPage.clickToSubmenuInHeaderMenuByLabel(driver, "Computers", "Notebooks");
		
		log.info("Order 05 - Step 3: Verify the current page is Notebooks");
		verifyEquals(productListPage.getCurrentPageTitle(driver), "Notebooks");
		
		log.info("Order 05 - Step 4: Click to Macbook");
		productDetailPage = productListPage.clickToProductTitle(driver, macbookAppleProduct);
		
		log.info("Order 05 - Step 5: Click Add to cart button");
		productDetailPage.clickButtonByLabel(driver, "Add to cart");
		
		log.info("Order 05 - Step 6: Verify the add to cart success message display");
		verifyEquals(productDetailPage.getUserAlertMessage(driver), "The product has been added to your shopping cart");
		
		log.info("Order 05 - Step 7: Click to close button");
		productDetailPage.clickToUserCloseAlertMessageButton(driver);
		
		log.info("Order 05 - Step 8: Click to Shopping cart header link");
		productDetailPage.clickToHeaderLinkByClass(driver, "ico-cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		
		log.info("Order 05 - Step 9: Verify the product exist in the Shopping Cart page");
		verifyTrue(shoppingCartPage.isProductExistInTableByProductName(driver, macbookAppleProduct));
		
		log.info("Order 05 - Step 10: Select gift wrapping");
		shoppingCartPage.selectDropdownByName(driver, "checkout_attribute_1", giftWrapping);
		
		log.info("Order 05 - Step 11: Estimate shipping cost");
		shoppingCartPage.clickButtonLinkByLabel(driver, "Estimate shipping");
		shoppingCartPage.selectDropdownByName(driver, "CountryId", country);
		shoppingCartPage.inputToTextboxById(driver, zipcode, "ZipPostalCode");
		verifyTrue(shoppingCartPage.isShippingMethodDisplay());
		SleepInSecond(2);
		shoppingCartPage.clickButtonByLabel(driver, "Apply");
		verifyTrue(shoppingCartPage.isShippingMethodUndisplay());
		
		log.info("Order 05 - Step 12: Select agree checkbox");
		shoppingCartPage.checkToCheckboxRadioButtonByLabel(driver, "I agree with the terms of service and I adhere to them unconditionally");
		
		log.info("Order 05 - Step 13: Click checkout button");
		shoppingCartPage.clickButtonByLabel(driver, "Checkout");
		checkoutPageObject = PageGeneratorManager.getCheckoutPage(driver);
		
		log.info("Order 05 - Step 14: Enter the billing information");
		checkoutPageObject.checkToCheckboxRadioButtonByLabel(driver, "Ship to the same address");
		checkoutPageObject.inputToTextboxById(driver, Common_Register_NewAccount.FIRSTNAME, "BillingNewAddress_FirstName");
		checkoutPageObject.inputToTextboxById(driver, Common_Register_NewAccount.LASTNAME, "BillingNewAddress_LastName");
		checkoutPageObject.inputToTextboxById(driver, Common_Register_NewAccount.EMAIL, "BillingNewAddress_Email");
		checkoutPageObject.selectDropdownByName(driver, "BillingNewAddress.CountryId", country);
		checkoutPageObject.inputToTextboxById(driver, city, "BillingNewAddress_City");
		checkoutPageObject.inputToTextboxById(driver, address, "BillingNewAddress_Address1");
		checkoutPageObject.inputToTextboxById(driver, zipcode, "BillingNewAddress_ZipPostalCode");
		checkoutPageObject.inputToTextboxById(driver, phoneNumber, "BillingNewAddress_PhoneNumber");
		
		log.info("Order 05 - Step 15: Click Continue button");
		checkoutPageObject.clickButtonByClass(driver, "new-address-next-step-button");
		
		log.info("Order 05 - Step 16: Select the shipping method");
		checkoutPageObject.checkToCheckboxRadioButtonByLabel(driver, shippingMethod1);
		
		log.info("Order 05 - Step 17: Click Continue button");
		checkoutPageObject.clickButtonByClass(driver, "shipping-method-next-step-button");
		
		log.info("Order 05 - Step 18: Select the payment method");
		checkoutPageObject.checkToCheckboxRadioButtonByLabel(driver, paymentMethod1);
		
		log.info("Order 05 - Step 19: Click Continue button");
		checkoutPageObject.clickButtonByClass(driver, "payment-method-next-step-button");
		
		log.info("Order 05 - Step 20: Verify payment information");
		verifyEquals(checkoutPageObject.getPaymentInfomation(), "Mail Personal or Business Check, Cashier's Check or money order to:\n\nNOP SOLUTIONS\nyour address here,\nNew York, NY 10001\nUSA\nNotice that if you pay by Personal or Business Check, your or\ner may be held for up to 10 days after we receive your check to allow enough time for the check to clear. If you want us to ship faster upon receipt of your payment, then we recommend your send a money order or Cashier's check.\nP.S. You can edit this text from admin panel.");
		
		log.info("Order 05 - Step 21: Click Continue button");
		checkoutPageObject.clickButtonByClass(driver, "payment-info-next-step-button");
		confirmOrderDetailPage = PageGeneratorManager.getConfirmOrderDetailPage(driver);
		
		log.info("Order 05 - Step 22: Verify the billing address information");
		verifyEquals(confirmOrderDetailPage.getBillingAddressById("address1"), address);
		verifyEquals(confirmOrderDetailPage.getBillingAddressById("city-state-zip"), city + "," + zipcode);
		verifyEquals(confirmOrderDetailPage.getBillingAddressById("country"), country);
		verifyEquals(confirmOrderDetailPage.getBillingAddressById("payment-method"), "Payment Method: " + paymentMethod1);
		
		log.info("Order 05 - Step 23: Verify the shipping address information");
		verifyEquals(confirmOrderDetailPage.getShippingAddressById("address1"), address);
		verifyEquals(confirmOrderDetailPage.getShippingAddressById("city-state-zip"), city + "," + zipcode);
		verifyEquals(confirmOrderDetailPage.getShippingAddressById("country"), country);
		verifyEquals(confirmOrderDetailPage.getShippingAddressById("shipping-method"), "Shipping Method: " + shippingMethod1);
		
		log.info("Order 05 - Step 24: Verify the product information");
		verifyTrue(confirmOrderDetailPage.isProductExistInTableByProductName(driver, macbookAppleProduct));
		verifyEquals(confirmOrderDetailPage.getProductSkuByProductName(macbookAppleProduct), "AP_MBP_13");
		verifyEquals(confirmOrderDetailPage.getSubTotalPriceByProductName(macbookAppleProduct), "$3,600.00");
		verifyEquals(confirmOrderDetailPage.getQuantityByProductName(macbookAppleProduct), "2");
		
		log.info("Order 05 - Step 25: Verify the gift wrapping");
		verifyEquals(confirmOrderDetailPage.getCartOption(), "Gift wrapping: " + giftWrapping);
		
		log.info("Order 05 - Step 26: Verify the total cost");
		verifyEquals(confirmOrderDetailPage.getRowOfCartTotalTableByClass("order-subtotal"), "$3,610.00");
		verifyEquals(confirmOrderDetailPage.getRowOfCartTotalTableByClass("shipping-cost"), "$0.00");
		verifyEquals(confirmOrderDetailPage.getRowOfCartTotalTableByClass("tax-value"), "$0.00");
		verifyEquals(confirmOrderDetailPage.getRowOfCartTotalTableByClass("order-total"), "$3,610.00");
		
		log.info("Order 05 - Step 27: Click Confirm button");
		checkoutPageObject.clickButtonByLabel(driver, "Confirm");
		
		log.info("Order 05 - Step 28: Verify the order successful message");
		verifyEquals(confirmOrderDetailPage.getCurrentPageTitle(driver), "Thank you");
		verifyEquals(confirmOrderDetailPage.getOrderStatusMessage(), "Your order has been successfully processed!");
		
		log.info("Order 05 - Step 29: Get order id");
		orderID = confirmOrderDetailPage.getOrderNumberText();
		
		log.info("Order 05 - Step 30: Click Continue button");
		checkoutPageObject.clickButtonByLabel(driver, "Continue");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Order 05 - Step 31: Click my account header link");
		homePage.clickToHeaderLinkByClass(driver, "ico-account");
		myAccountPage = PageGeneratorManager.getUserMyAccountPage(driver);
		
		log.info("Order 05 - Step 32: Click Order in the left menu");
		myAccountPage.openPageInMyAccountArea(driver, "Orders");
		orderPage = PageGeneratorManager.getOrderPageObject(driver);
		
		log.info("Order 05 - Step 33: Verify the order in the Order page");
		verifyTrue(orderPage.isOrderDisplayByOrderID(orderID));
		
		log.info("Order 05 - Step 34: Click Detail button to show the detail information of the order");
		orderPage.clickToDetailOrderByOrderID(orderID);
		
		log.info("Order 05 - Step 35: Verify the billing address");
		verifyEquals(orderPage.getBillingAddressById("address1"), address);
		verifyEquals(orderPage.getBillingAddressById("city-state-zip"), city + "," + zipcode);
		verifyEquals(orderPage.getBillingAddressById("country"), country);
		verifyEquals(orderPage.getBillingAddressById("payment-method"), "Payment Method: " + paymentMethod1);
		
		log.info("Order 05 - Step 36: Verify the shipping address");
		verifyEquals(orderPage.getShippingAddressById("address1"), address);
		verifyEquals(orderPage.getShippingAddressById("city-state-zip"), city + "," + zipcode);
		verifyEquals(orderPage.getShippingAddressById("country"), country);
		verifyEquals(orderPage.getShippingAddressById("shipping-method"), "Shipping Method: " + shippingMethod1);
		
		log.info("Order 05 - Step 37: Verify the product information");
		verifyTrue(orderPage.isProductDisplayByProductName(driver, macbookAppleProduct));
		
		log.info("Order 05 - Step 38: Verify gift wrapping");
		verifyEquals(orderPage.getCartOption(), "Gift wrapping: " + giftWrapping);
		
		log.info("Order 05 - Step 39: Verify total cost");
		verifyEquals(orderPage.getRowOfCartTotalTableByClass("Sub-Total"), "$3,610.00");
		verifyEquals(orderPage.getRowOfCartTotalTableByClass("Shipping"), "$0.00");
		verifyEquals(orderPage.getRowOfCartTotalTableByClass("Tax"), "$0.00");
		verifyEquals(orderPage.getRowOfCartTotalTableByClass("Order Total"), "$3,610.00");
	}
	
	@Description("Payment method by cart")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Order_06_Payment_Method_By_Cart(){
		log.info("Order 06 - Step 1: Hover on Coputer menu");
		orderPage.hoverToMenuItemByLabel(driver, "Computers");
		
		log.info("Order 06 - Step 2: Click on Notebooks submenu");
		productListPage = orderPage.clickToSubmenuInHeaderMenuByLabel(driver, "Computers", "Notebooks");
		
		log.info("Order 06 - Step 3: Verify the current page is Notebooks");
		verifyEquals(productListPage.getCurrentPageTitle(driver), "Notebooks");
		
		log.info("Order 06 - Step 4: Click to Asus notebook");
		productDetailPage = productListPage.clickToProductTitle(driver, asusLaptopProduct);
		
		log.info("Order 06 - Step 5: Click Add to cart button");
		productDetailPage.clickButtonByLabel(driver, "Add to cart");
		
		log.info("Order 06 - Step 6: Verify the add to cart success message display");
		verifyEquals(productDetailPage.getUserAlertMessage(driver), "The product has been added to your shopping cart");
		
		log.info("Order 06 - Step 7: Click to close button");
		productDetailPage.clickToUserCloseAlertMessageButton(driver);
		
		log.info("Order 06 - Step 8: Click to Shopping cart header link");
		productDetailPage.clickToHeaderLinkByClass(driver, "ico-cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		
		log.info("Order 06 - Step 9: Verify the product exist in the Shopping Cart page");
		verifyTrue(shoppingCartPage.isProductExistInTableByProductName(driver, asusLaptopProduct));
		
		log.info("Order 06 - Step 10: Select gift wrapping");
		shoppingCartPage.selectDropdownByName(driver, "checkout_attribute_1", giftWrapping);
		
		log.info("Order 06 - Step 11: Estimate shipping cost");
		shoppingCartPage.clickButtonLinkByLabel(driver, "Estimate shipping");
		shoppingCartPage.selectDropdownByName(driver, "CountryId", country);
		shoppingCartPage.inputToTextboxById(driver, zipcode, "ZipPostalCode");
		verifyTrue(shoppingCartPage.isShippingMethodDisplay());
		SleepInSecond(2);
		shoppingCartPage.clickButtonByLabel(driver, "Apply");
		verifyTrue(shoppingCartPage.isShippingMethodUndisplay());
		
		log.info("Order 06 - Step 12: Select agree checkbox");
		shoppingCartPage.checkToCheckboxRadioButtonByLabel(driver, "I agree with the terms of service and I adhere to them unconditionally");
		
		log.info("Order 06 - Step 13: Click checkout button");
		shoppingCartPage.clickButtonByLabel(driver, "Checkout");
		checkoutPageObject = PageGeneratorManager.getCheckoutPage(driver);
		
		log.info("Order 06 - Step 14: Enter the billing information");
		checkoutPageObject.checkToCheckboxRadioButtonByLabel(driver, "Ship to the same address");
		checkoutPageObject.inputToTextboxById(driver, Common_Register_NewAccount.FIRSTNAME, "BillingNewAddress_FirstName");
		checkoutPageObject.inputToTextboxById(driver, Common_Register_NewAccount.LASTNAME, "BillingNewAddress_LastName");
		checkoutPageObject.inputToTextboxById(driver, Common_Register_NewAccount.EMAIL, "BillingNewAddress_Email");
		checkoutPageObject.selectDropdownByName(driver, "BillingNewAddress.CountryId", country);
		checkoutPageObject.inputToTextboxById(driver, city, "BillingNewAddress_City");
		checkoutPageObject.inputToTextboxById(driver, address, "BillingNewAddress_Address1");
		checkoutPageObject.inputToTextboxById(driver, zipcode, "BillingNewAddress_ZipPostalCode");
		checkoutPageObject.inputToTextboxById(driver, phoneNumber, "BillingNewAddress_PhoneNumber");
		
		log.info("Order 06 - Step 15: Click Continue button");
		checkoutPageObject.clickButtonByClass(driver, "new-address-next-step-button");
		
		log.info("Order 06 - Step 16: Select the shipping method");
		checkoutPageObject.checkToCheckboxRadioButtonByLabel(driver, shippingMethod2);
		
		log.info("Order 06 - Step 17: Click Continue button");
		checkoutPageObject.clickButtonByClass(driver, "shipping-method-next-step-button");
		
		log.info("Order 06 - Step 18: Select the payment method");
		checkoutPageObject.checkToCheckboxRadioButtonByLabel(driver, paymentMethod2);
		checkoutPageObject.clickButtonByClass(driver, "payment-method-next-step-button");
		
		log.info("Order 06 - Step 19: Enter the card information");
		checkoutPageObject.selectDropdownByName(driver, "CreditCardType", "Master card");
		checkoutPageObject.inputToTextboxById(driver, "ELON MUSK", "CardholderName");
		checkoutPageObject.inputToTextboxById(driver, "5555555555554444", "CardNumber");
		checkoutPageObject.selectDropdownByName(driver, "ExpireMonth", "10");
		checkoutPageObject.selectDropdownByName(driver, "ExpireYear", "2024");
		checkoutPageObject.inputToTextboxById(driver, "145", "CardCode");
		checkoutPageObject.clickButtonByClass(driver, "payment-info-next-step-button");
		confirmOrderDetailPage = PageGeneratorManager.getConfirmOrderDetailPage(driver);
		
		log.info("Order 06 - Step 20: Verify the billing address information");
		verifyEquals(confirmOrderDetailPage.getBillingAddressById("address1"), address);
		verifyEquals(confirmOrderDetailPage.getBillingAddressById("city-state-zip"), city + "," + zipcode);
		verifyEquals(confirmOrderDetailPage.getBillingAddressById("country"), country);
		verifyEquals(confirmOrderDetailPage.getBillingAddressById("payment-method"), "Payment Method: " + paymentMethod2);
		
		log.info("Order 06 - Step 21: Verify the shipping address information");
		verifyEquals(confirmOrderDetailPage.getShippingAddressById("address1"), address);
		verifyEquals(confirmOrderDetailPage.getShippingAddressById("city-state-zip"), city + "," + zipcode);
		verifyEquals(confirmOrderDetailPage.getShippingAddressById("country"), country);
		verifyEquals(confirmOrderDetailPage.getShippingAddressById("shipping-method"), "Shipping Method: " + shippingMethod2);
		
		log.info("Order 06 - Step 22: Verify the product information");
		verifyTrue(confirmOrderDetailPage.isProductExistInTableByProductName(driver, asusLaptopProduct));
		verifyEquals(confirmOrderDetailPage.getProductSkuByProductName(asusLaptopProduct), "AS_551_LP");
		verifyEquals(confirmOrderDetailPage.getSubTotalPriceByProductName(asusLaptopProduct), "$1,510.00");
		verifyEquals(confirmOrderDetailPage.getQuantityByProductName(asusLaptopProduct), "1");
		
		log.info("Order 06 - Step 23: Verify the gift wrapping");
		verifyEquals(confirmOrderDetailPage.getCartOption(), "Gift wrapping: " + giftWrapping);
		
		log.info("Order 06 - Step 24: Verify the total cost");
		verifyEquals(confirmOrderDetailPage.getRowOfCartTotalTableByClass("order-subtotal"), "$1,510.00");
		verifyEquals(confirmOrderDetailPage.getRowOfCartTotalTableByClass("shipping-cost"), "$0.00");
		verifyEquals(confirmOrderDetailPage.getRowOfCartTotalTableByClass("tax-value"), "$0.00");
		verifyEquals(confirmOrderDetailPage.getRowOfCartTotalTableByClass("order-total"), "$1,510.00");
		
		log.info("Order 06 - Step 25: Click Confirm button");
		checkoutPageObject.clickButtonByLabel(driver, "Confirm");
		
		log.info("Order 06 - Step 26: Verify the order successful message");
		verifyEquals(confirmOrderDetailPage.getCurrentPageTitle(driver), "Thank you");
		verifyEquals(confirmOrderDetailPage.getOrderStatusMessage(), "Your order has been successfully processed!");
		
		log.info("Order 06 - Step 27: Get order id");
		orderID = confirmOrderDetailPage.getOrderNumberText();
		
		log.info("Order 06 - Step 28: Click Continue button");
		checkoutPageObject.clickButtonByLabel(driver, "Continue");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Order 06 - Step 29: Click my account header link");
		homePage.clickToHeaderLinkByClass(driver, "ico-account");
		myAccountPage = PageGeneratorManager.getUserMyAccountPage(driver);
		
		log.info("Order 06 - Step 30: Click Order in the left menu");
		myAccountPage.openPageInMyAccountArea(driver, "Orders");
		orderPage = PageGeneratorManager.getOrderPageObject(driver);
		
		log.info("Order 06 - Step 31: Verify the order in the Order page");
		verifyTrue(orderPage.isOrderDisplayByOrderID(orderID));
		
		log.info("Order 06 - Step 32: Click Detail button to show the detail information of the order");
		orderPage.clickToDetailOrderByOrderID(orderID);
		
		log.info("Order 06 - Step 33: Verify the billing address");
		verifyEquals(orderPage.getBillingAddressById("address1"), address);
		verifyEquals(orderPage.getBillingAddressById("city-state-zip"), city + "," + zipcode);
		verifyEquals(orderPage.getBillingAddressById("country"), country);
		verifyEquals(orderPage.getBillingAddressById("payment-method"), "Payment Method: " + paymentMethod2);
		
		log.info("Order 06 - Step 34: Verify the shipping address");
		verifyEquals(orderPage.getShippingAddressById("address1"), address);
		verifyEquals(orderPage.getShippingAddressById("city-state-zip"), city + "," + zipcode);
		verifyEquals(orderPage.getShippingAddressById("country"), country);
		verifyEquals(orderPage.getShippingAddressById("shipping-method"), "Shipping Method: " + shippingMethod2);
		
		log.info("Order 06 - Step 35: Verify the product information");
		verifyTrue(orderPage.isProductDisplayByProductName(driver, asusLaptopProduct));
		
		log.info("Order 06 - Step 36: Verify gift wrapping");
		verifyEquals(orderPage.getCartOption(), "Gift wrapping: " + giftWrapping);
		
		log.info("Order 05 - Step 37: Verify total cost");
		verifyEquals(orderPage.getRowOfCartTotalTableByClass("Sub-Total"), "$1,510.00");
		verifyEquals(orderPage.getRowOfCartTotalTableByClass("Shipping"), "$0.00");
		verifyEquals(orderPage.getRowOfCartTotalTableByClass("Tax"), "$0.00");
		verifyEquals(orderPage.getRowOfCartTotalTableByClass("Order Total"), "$1,510.00");
	}
	
	@Description("Reorder product")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Order_07_ReOrder_Product(){
		log.info("Order 07 - Step 1: Click re-order button");
		orderPage.clickButtonByLabel(driver, "Re-order");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		
		log.info("Order 07 - Step 2: Verify the current page is Notebooks");
		verifyEquals(productListPage.getCurrentPageTitle(driver), "Notebooks");
		
		log.info("Order 07 - Step 3: Verify the product display in the shopping cart");
		verifyTrue(shoppingCartPage.isProductExistInTableByProductName(driver, asusLaptopProduct));
		
		log.info("Order 07 - Step 4: Change the quantity");
		shoppingCartPage.changeProductQuantityByProductName("10", asusLaptopProduct);
		
		log.info("Order 07 - Step 5: Select agree checkbox");
		shoppingCartPage.checkToCheckboxRadioButtonByLabel(driver, "I agree with the terms of service and I adhere to them unconditionally");
		
		log.info("Order 07 - Step 6: Click checkout button");
		shoppingCartPage.clickButtonByLabel(driver, "Checkout");
		checkoutPageObject = PageGeneratorManager.getCheckoutPage(driver);
		
		log.info("Order 07 - Step 7: Delete the old information");
		checkoutPageObject.checkToCheckboxRadioButtonByLabel(driver, "Ship to the same address");
		checkoutPageObject.clickButtonByLabel(driver, "Delete");
		
		log.info("Order 07 - Step 8: Enter the new billing information");
		checkoutPageObject.inputToTextboxById(driver, "Automation", "BillingNewAddress_FirstName");
		checkoutPageObject.inputToTextboxById(driver, "Group", "BillingNewAddress_LastName");
		checkoutPageObject.inputToTextboxById(driver, "Automationgroup@gmail.com", "BillingNewAddress_Email");
		checkoutPageObject.selectDropdownByName(driver, "BillingNewAddress.CountryId", country);
		checkoutPageObject.inputToTextboxById(driver, city, "BillingNewAddress_City");
		checkoutPageObject.inputToTextboxById(driver, address, "BillingNewAddress_Address1");
		checkoutPageObject.inputToTextboxById(driver, zipcode, "BillingNewAddress_ZipPostalCode");
		checkoutPageObject.inputToTextboxById(driver, phoneNumber, "BillingNewAddress_PhoneNumber");
		
		log.info("Order 07 - Step 8: Click Continue button");
		checkoutPageObject.clickButtonByClass(driver, "new-address-next-step-button");
		
		log.info("Order 07 - Step 9: Select the shipping method");
		checkoutPageObject.checkToCheckboxRadioButtonByLabel(driver, shippingMethod2);
		
		log.info("Order 07 - Step 10: Click Continue button");
		checkoutPageObject.clickButtonByClass(driver, "shipping-method-next-step-button");
		
		log.info("Order 07 - Step 11: Select the payment method");
		checkoutPageObject.checkToCheckboxRadioButtonByLabel(driver, paymentMethod2);
		checkoutPageObject.clickButtonByClass(driver, "payment-method-next-step-button");
		
		log.info("Order 07 - Step 12: Enter the card information");
		checkoutPageObject.selectDropdownByName(driver, "CreditCardType", "Master card");
		checkoutPageObject.inputToTextboxById(driver, "ELON MUSK", "CardholderName");
		checkoutPageObject.inputToTextboxById(driver, "5555555555554444", "CardNumber");
		checkoutPageObject.selectDropdownByName(driver, "ExpireMonth", "10");
		checkoutPageObject.selectDropdownByName(driver, "ExpireYear", "2024");
		checkoutPageObject.inputToTextboxById(driver, "145", "CardCode");
		checkoutPageObject.clickButtonByClass(driver, "payment-info-next-step-button");
		confirmOrderDetailPage = PageGeneratorManager.getConfirmOrderDetailPage(driver);
		
		log.info("Order 07 - Step 13: Verify the billing address information");
		verifyEquals(confirmOrderDetailPage.getBillingAddressById("address1"), address);
		verifyEquals(confirmOrderDetailPage.getBillingAddressById("city-state-zip"), city + "," + zipcode);
		verifyEquals(confirmOrderDetailPage.getBillingAddressById("country"), country);
		verifyEquals(confirmOrderDetailPage.getBillingAddressById("payment-method"), "Payment Method: " + paymentMethod2);
		
		log.info("Order 07 - Step 14: Verify the shipping address information");
		verifyEquals(confirmOrderDetailPage.getShippingAddressById("address1"), address);
		verifyEquals(confirmOrderDetailPage.getShippingAddressById("city-state-zip"), city + "," + zipcode);
		verifyEquals(confirmOrderDetailPage.getShippingAddressById("country"), country);
		verifyEquals(confirmOrderDetailPage.getShippingAddressById("shipping-method"), "Shipping Method: " + shippingMethod2);
		
		log.info("Order 07 - Step 15: Verify the product information");
		verifyTrue(confirmOrderDetailPage.isProductExistInTableByProductName(driver, asusLaptopProduct));
		verifyEquals(confirmOrderDetailPage.getProductSkuByProductName(asusLaptopProduct), "AS_551_LP");
		verifyEquals(confirmOrderDetailPage.getSubTotalPriceByProductName(asusLaptopProduct), "$15,100.00");
		verifyEquals(confirmOrderDetailPage.getQuantityByProductName(asusLaptopProduct), "10");
		
		log.info("Order 07 - Step 16: Verify the gift wrapping");
		verifyEquals(confirmOrderDetailPage.getCartOption(), "Gift wrapping: " + giftWrapping);
		
		log.info("Order 07 - Step 17: Verify the total cost");
		verifyEquals(confirmOrderDetailPage.getRowOfCartTotalTableByClass("order-subtotal"), "$15,100.00");
		verifyEquals(confirmOrderDetailPage.getRowOfCartTotalTableByClass("shipping-cost"), "$0.00");
		verifyEquals(confirmOrderDetailPage.getRowOfCartTotalTableByClass("tax-value"), "$0.00");
		verifyEquals(confirmOrderDetailPage.getRowOfCartTotalTableByClass("order-total"), "$15,100.00");
		
		log.info("Order 07 - Step 18: Click Confirm button");
		checkoutPageObject.clickButtonByLabel(driver, "Confirm");
		
		log.info("Order 07 - Step 19: Verify the order successful message");
		verifyEquals(confirmOrderDetailPage.getCurrentPageTitle(driver), "Thank you");
		verifyEquals(confirmOrderDetailPage.getOrderStatusMessage(), "Your order has been successfully processed!");
		
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowserDriver("local");
	}

}