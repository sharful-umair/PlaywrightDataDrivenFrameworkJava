# 🚀 Playwright Data-Driven Framework with Java

A robust automation testing framework built using **Playwright Java** with support for **Data-Driven Testing** through Excel files.


## 🧰 Tech Stack

- 🎭 **Playwright Java** – Fast and reliable browser automation  
- 📗 **Apache POI** – Read/write Excel data  
- 📊 **Extent Reports** – Detailed HTML reports  
- 🔍 **TestNG** – Flexible test execution with assertions and parallelism  
- ⚙️ **Maven** – Build and dependency management  
- 📜 **Log4j** – Logging mechanism  
- 🧪 **Data-Driven Testing** – Run tests with dynamic inputs  

---

## 📁 Project Structure

PlaywrightDataDrivenFrameworkJava/
├── src/<br />
│   ├── main/<br />
│   │   └── java/<br />
│   │       ├── base/<br />
│   │       │   └── BaseTest.java               # Common setup/teardown logic<br />
│   │       ├── config/<br />
│   │       │   └── ConfigReader.java           # Reads config from properties file<br />
│   │       ├── pages/<br />
│   │       │   ├── LoginPage.java              # Page Object for login<br />
│   │       │   ├── DashboardPage.java          # Page Object for dashboard<br />
│   │       │   └── ...                         # Other page objects<br />
│   │       ├── utils/<br />
│   │       │   ├── ExcelUtils.java             # Excel data reader<br />
│   │       │   ├── PlaywrightFactory.java      # Browser/context setup<br />
│   │       │   └── TestDataProvider.java       # DataProvider for TestNG<br />
│   │       └── constants/<br />
│   │           └── AppConstants.java           # Static constants (timeouts, URLs)<br />
│
│   └── test/<br />
│       └── java/<br />
│           ├── tests/<br />
│           │   ├── LoginTest.java              # Test class using TestNG<br />
│           │   └── DashboardTest.java          # Another test class<br />
│           └── runners/<br />
│               └── TestRunner.java             # Optional: custom runner logic<br />
│
├── config/<br />
│   └── config.properties                       # Environment-specific configs<br />
│
├── testng.xml                                   # Test suite definition<br />
├── pom.xml                                      # Maven dependencies & plugins<br />
├── README.md                                    # Project overview & usage<br />
└── logs/                                        # (Optional) Log output directory<br />

---

## ✅ Features

✅ Java-based Playwright automation  
✅ Data-driven test execution using Excel  
✅ Page Object Model (POM) structure  
✅ HTML reports with ExtentReports  
✅ Logging using Log4j  
✅ TestNG framework for flexible test execution  
✅ Easily integrable with CI tools like Jenkins  

---

## 🚀 Getting Started

### Prerequisites

- Java 11 or later  
- Maven 3.6+  
- Chrome/Firefox browser  
- IDE (IntelliJ / Eclipse)

---

### 🔧 Installation

1. **Clone the Repository:**

```bash
git clone https://github.com/sharful-umair/PlaywrightDataDrivenFrameworkJava.git
cd PlaywrightDataDrivenFrameworkJava
````

2. **Install Dependencies:**

```bash
mvn clean install
```

---

## 🧪 Running Tests

To execute all test cases:

```bash
mvn test
```

To run a specific test class:

```bash
mvn -Dtest=ClassName test
```

---

## 📊 Generate Reports

After test execution, view the generated report at:

```
/test-output/Extent.html
```

Logs can be found at:

```
/src/test/resources/logs/applog.txt
```

---

## 📄 Test Data Handling

* Data is maintained in `testdata/testdata.xlsx`
* Read dynamically using Apache POI
* Used in tests via `@DataProvider` annotations in TestNG

---

## 🔄 Continuous Integration

The framework supports integration with:

* ✅ Jenkins
* ✅ GitHub Actions
* ✅ Azure DevOps
* ✅ GitLab CI

For Jenkins, configure a Maven job and point to `pom.xml`.

---

## 🤝 Contributing

Contributions are welcome!

* Fork the repository
* Create a feature branch
* Submit a pull request

For significant changes, open an issue first to discuss your ideas.

---

## 👨‍💻 Author

**Sharful Umair**
🔗 [GitHub](https://github.com/sharful-umair)
🔗 [LinkedIn](https://linkedin.com/in/sharfulumair)

