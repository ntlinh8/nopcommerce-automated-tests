# NopCommerce Automated Tests (Split from https://github.com/ntlinh8/hybrid-framework-nopcommerce/)

This repository contains automated tests for NopCommerce, an open-source e-commerce platform built with ASP.NET Core. These tests are designed to validate the functionality and performance of various features within NopCommerce.

## Table of Contents

- [Introduction](#introduction)
- [Setup](#setup)
- [Running Tests](#running-tests)
- [Contributing](#contributing)
- [License](#license)

## Introduction

NopCommerce is a widely-used e-commerce solution that provides a flexible and customizable platform for building online stores. Automated tests play a crucial role in ensuring that the application functions as expected across different scenarios and environments.

This repository contains a suite of automated tests developed using a testing framework (e.g., Selenium, TestNG) to interact with the NopCommerce web application. These tests cover various aspects of the application, including:

- User authentication and authorization
- Product management
- Shopping cart functionality
- Checkout process

## Setup

To set up the automated testing environment, follow these steps:

1. **Clone the Repository**: Clone this repository to your local machine using the following command:

git clone https://github.com/ntlinh8/nopcommerce-automated-tests.git

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

## Contributing

Contributions to this repository are welcome! If you find any bugs, issues, or areas for improvement in the automated tests, feel free to submit a pull request with your changes. Please ensure that your contributions adhere to the existing coding standards and practices.

If you're unsure about how to contribute, you can also open an issue to discuss potential enhancements or changes with the repository maintainers.

## License

This repository is licensed under the [MIT License](LICENSE), which allows for both personal and commercial use of the codebase. See the [LICENSE](LICENSE) file for more information.
