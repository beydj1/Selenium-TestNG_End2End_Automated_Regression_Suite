# Selenium-TestNG_End2End_Automated_Regression_Suite

Enterprise-style automated regression suite designed for end-to-end functional testing using:

- **Java 17**
- **Maven**
- **Selenium 4+**
- **TestNG**
- **Page Object Model + PageFactory**

## Project Structure

```text
src
├── main
│   ├── java/com/automation/framework
│   │   ├── config
│   │   ├── core
│   │   ├── pages
│   │   └── utils
│   └── resources
│       ├── env.properties
│       └── data.properties
└── test
    ├── java/com/automation/framework/tests
    └── resources/testng.xml
```

## Run Tests

```bash
mvn clean test
```

Optional external properties override:

```bash
mvn clean test -Denv.file=/path/to/env.properties -Ddata.file=/path/to/data.properties
```
