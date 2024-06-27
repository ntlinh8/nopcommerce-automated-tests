# NopCommerce Automated Tests

This project originated from my previous repository where I explored various design concepts. You can view the original project at https://github.com/ntlinh8/hybrid-framework-nopcommerce/.

## Table of Contents

- [Introduction](#introduction)
- [Setup](#setup)
- [Techstacks Details](#techstacks-details)
- [Running Tests](#running-tests)
  
## Introduction

Powered by Java, Selenium with TestNG, and AllureReport, this project aims to validate functionalities including Signup, Login, Order, Search, and Sorting for https://demo.nopcommerce.com/

Techstacks used:
- Java, Maven with Selenium, TestNG, AllureReport libraries
- Page Object Model
- Dynamic Locator
- Custom reports
- Fake data

## Techstacks Details:
1. Page Object Model

Why did I choose the Page Object Model instead of the Page Factory pattern?
- With the large actions and locators for each page, merging them into one file is unfriendly to developers because the length of the file will be too long and will take many time to maintain (when scrolling up and down)
- With the Page Factory pattern, the page object with elements will be inited when initing pages. But it will take a long time to find all the elements (including unnecessary elements for this case). After reloading pages, the page object and elements will be reloaded and it can cause the StaleElementException

Analyze Page Object Model
- BasePage.java: contains all wrapped methods of Selenium used for all projects. These methods are used in the page object classes
- BaseTest.java: contains all methods used in TestClass (test case level). It consists of:
  a getWebDriver method: support to get the appropriate driver for each browser, support to run in headless mode, and multiple environments
  verifying methods: consist of verifyTrue(), verifyFalse(), verifyEqual() methods used to verify actions in test cases
  if you want to run on the cloud platform (BrowserStack or SourceLab), it'll be set on this file
- Interface folder: contains locators divided for each page. 
- PageObject folder: contains page object classes. These classes inherit the BasePage and use these BasePage methods and the appropriate locators (in the Interface folder) to implement the specific actions for each page. In this folder, I have PageGeneratorManager to manage the definition of all classes.
- TestScript: contains test classes. These classes inherit BaseTest and it's implemented by actions in PageObject classes

2. Dynamic Locator
Instead of using locators with the same template like
//p[text()='Product']
//p[text()='Desktop']
I created the template locator: //p[text()='%s'] and injected the suitable label in the test case into this template to create the complete locator
====
This action is customed in these methods of BasePage and applied for the entire project

3. Custom reports: using Allure Report customed for capture the screen before the failure occurs
4. Fake data: Using FakerLibary to fake data
(In addition, I can custom get data from Excel or JSON files)

## Setup

To set up the automated testing environment, follow these steps:

1. **Clone the Repository**: Clone this repository to your local machine using the following command:
```
git clone https://github.com/ntlinh8/nopcommerce-automated-tests.git
```
2. **Install Dependencies**: Install the necessary dependencies for running the tests. This may include:
- Testing frameworks (e.g., TestNG, AllureReport)
- Selenium WebDriver
- Any other required packages or libraries

3. **Configure Test Environment**: Modify any configuration files or settings to specify the URL of the NopCommerce instance to be tested, as well as any other environment-specific configurations.

4. **Set Up Test Data**: Ensure that the test environment has the necessary test data (e.g., sample products, user accounts) for executing the tests effectively.

## Running Tests

To execute the automated tests, follow these steps:

1. **Build Solution**: Build the solution to ensure that all necessary files and dependencies are compiled correctly.

2. **Run Tests**: Use the testing framework's test runner to execute the tests. Depending on the framework used (e.g., TestNG, MSTest), this may involve running a specific command or using an integrated development environment (IDE) to run the tests.

3. **Review Test Results**: After the tests have completed execution, review the test results to identify any failures or issues encountered during the testing process.

