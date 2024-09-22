
# Web Automation Framework

## Overview
This Web Automation Framework is designed using the **Page Object Model (POM)** structure, with an emphasis on reusability and scalability. The framework is built with **Java**, **Selenium WebDriver** for browser automation, and **TestNG** for test execution and reporting. Maven is used for managing dependencies and builds.

## Project Structure

### 1. `pom.xml`
The **Maven Project Object Model (POM)** file is the backbone of the build automation. It defines the project’s dependencies (e.g., Selenium, TestNG), build plugins, and configurations. Key dependencies are:
- **Selenium WebDriver**: Automates browser interactions.
- **TestNG**: Manages test execution and reporting.
- **Other utilities** for logging, reporting, and handling test data.

### 2. `testng.xml`
This file configures TestNG to define:
- **Test suites and cases**: Organizes test methods under logical groups.
- **Execution order**: Specifies how tests are executed, including parallel execution.

### 3. `src/main/java/pages/`
This directory contains the **Page Object Model (POM)** classes. Each web page in your application is represented as a class that encapsulates the page’s elements and actions.

- **`LoginPage.java`**: Contains web elements and methods for interacting with the login page (e.g., entering username, password, clicking login button).

### 4. `src/main/java/utils/`
This folder consists of reusable utility classes to aid the automation process. It includes:
- **`SeleniumUtils.java`**: General helper methods for interacting with web elements, waiting conditions, handling alerts, etc.
- **`RandomUtils.java`**: A utility class for generating random test data, such as email addresses, phone numbers, and strings.
- **`ButtonActions.java`**: Encapsulates common actions for button interactions like clicking and verification.
- **`ScrollingMethods.java`**: Handles scrolling actions, with methods to scroll up, down, or to specific elements.
- **`Validations.java`**: Contains methods for validating UI elements and comparing expected versus actual results.

### 5. `src/test/java/test_data_files/`
This directory stores external test data, making the framework data-driven. Key files include:
- **`email_configuration.properties`**: A configuration file that stores email server credentials and other settings for email-based tests.
- **`email_body.README`**: Documentation or template for email-related testing data.

### 6. `.gitignore`
Specifies the files and directories that should not be tracked by Git, such as Maven’s `target/` folder and sensitive configuration files.

## How to Run the Tests

### Pre-requisites
Ensure that you have the following installed on your machine:
- **Java (JDK 8 or later)**
- **Maven**
- **WebDriver binaries** (managed through Maven dependencies).

### Execution
You can run the tests using either Maven or TestNG:
1. **Run via Maven**:
   In the terminal, navigate to the project directory and run:
   ```bash
   mvn test
   ```
2. **Run via TestNG**:
   You can execute the `testng.xml` file directly from your IDE by right-clicking it and selecting "Run."

### Reports
Test execution results are available as HTML reports under the `target/reports/` folder.

## Contribution
If you find this framework helpful or would like to contribute:
- **Give this repo a ⭐ on GitHub to show your support!**

Feel free to raise issues, submit pull requests, or suggest improvements.



## License
This project is licensed under the MIT License. You are free to use, modify, and distribute this code, provided that the original copyright notice and permission notice are included in all copies or substantial portions of the software. For more details, please refer to the LICENSE file.

---
This framework is flexible, scalable, and designed to be extended. Happy Testing!
