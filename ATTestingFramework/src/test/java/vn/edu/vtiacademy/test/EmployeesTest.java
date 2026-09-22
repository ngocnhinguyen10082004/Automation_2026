package vn.edu.vtiacademy.test;

import static org.testng.Assert.assertTrue;

import io.qameta.allure.Step;
import java.lang.reflect.Method;
import java.util.List;
import net.datafaker.Faker;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import vn.edu.vtiacademy.common.keywords.WebUI;
import vn.edu.vtiacademy.model.Employee;

public class EmployeesTest {

  private static final String EMPLOYEES_URL = "https://demoqa.com/webtables";
  private static final String BTN_ADD = "//button[@id='addNewRecordButton']";
  private static final String REGISTRATION_FORM_TXT_FIRST_NAME = "//input[@id='firstName']";
  private static final String REGISTRATION_FORM_TXT_LAST_NAME = "//input[@id='lastName']";
  private static final String REGISTRATION_FORM_TXT_EMAIL = "//input[@id='userEmail']";
  private static final String REGISTRATION_FORM_TXT_AGE = "//input[@id='age']";
  private static final String REGISTRATION_FORM_TXT_SALARY = "//input[@id='salary']";
  private static final String REGISTRATION_FORM_TXT_DEPARTMENT = "//input[@id='department']";
  private static final String REGISTRATION_FORM_BTN_SUBMIT = "//button[@id='submit']";

  private static final String EMPLOYEE_TABLE_LBL_FIRST_NAMES = "//tr/td[1]";
  private static final String EMPLOYEE_TABLE_LBL_LAST_NAMES = "//tr/td[2]";
  private static final String EMPLOYEE_TABLE_LBL_AGES = "//tr/td[3]";
  private static final String EMPLOYEE_TABLE_LBL_EMAILS = "//tr/td[4]";
  private static final String EMPLOYEE_TABLE_LBL_SALARIES = "//tr/td[5]";
  private static final String EMPLOYEE_TABLE_LBL_DEPARTMENTS = "//tr/td[6]";
  private static final String EMPLOYEE_TABLE_BTN_EDITS = "//div[@class='action-buttons']/span[starts-with(@id,'edit-record')]";
  private static final String EMPLOYEE_TABLE_BTN_DELETES = "//div[@class='action-buttons']/span[starts-with(@id,'delete-record')]";

  private static final String EMPLOYEE_TABLE_BTN_ACTIONS = "//tr//div[@class='action-buttons']";

  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeesTest.class);
  private static WebUI webUI;

  private static Employee employee;
  private static Employee editedEmployee;

  @BeforeSuite(alwaysRun = true)
  public void beforeSuite() {
    LOGGER.info("===============================Before Suite========================");
    webUI = new WebUI();
  }

  @BeforeTest(alwaysRun = true)
  @Parameters({"browser", "url"})
  public void beforeTest(String browser, String url) {
    LOGGER.info("===============================Before Test========================");
    webUI.openBrowser(browser, url);
    webUI.maximizeWindow();
  }

  @BeforeMethod(alwaysRun = true)
  public static void setup(Method method) {
    LOGGER.info("===============================Start {}========================", method.getName());
  }

  @AfterMethod(alwaysRun = true)
  public static void tearDown(Method method) {
    LOGGER.info("===============================End {}========================", method.getName());
  }

  @AfterTest(alwaysRun = true)
  public void afterTest() {
    webUI.closeBrowser();
    LOGGER.info("===============================After Test==========================");
  }

  @AfterSuite(alwaysRun = true)
  public void afterSuite() {
    LOGGER.info("===============================After Suite========================");
  }

  @DataProvider(name = "newEmployeeDataProvider")
  public Object[][] newEmployeeDataProvider() {
    Faker faker = new Faker();
    return new Object[][]{
        {Employee.Builder.aEmployee()
            .withFirstName(faker.name().firstName())
            .withLastName(faker.name().lastName())
            .withEmail(faker.internet().emailAddress())
            .withAge(String.valueOf(faker.number().numberBetween(24, 55)))
            .withSalary(String.valueOf(faker.number().numberBetween(1000, 5000)))
            .withDepartment(faker.job().title())
            .build()},
        {Employee.Builder.aEmployee()
            .withFirstName(faker.name().firstName())
            .withLastName(faker.name().lastName())
            .withEmail(faker.internet().emailAddress())
            .withAge(String.valueOf(faker.number().numberBetween(24, 55)))
            .withSalary(String.valueOf(faker.number().numberBetween(1000, 5000)))
            .withDepartment(faker.job().title())
            .build()},
    };
  }

  @Test(groups = {"smoke", "regression"}, dataProvider = "newEmployeeDataProvider")
  public void EM001_create_a_new_employee_successfully(Employee employee) {
    SoftAssert softAssert = new SoftAssert();
    addNewEmployee(employee);
    softAssert.assertTrue(shouldSeeFirstNameInEmployeeTable("Hoa"), "Should see first name '" + employee.getFirstName() + "' in Employee Table");
    softAssert.assertTrue(shouldSeeLastNameInEmployeeTable(employee.getLastName()));
    softAssert.assertAll();
  }

  @Test(groups = {"smoke", "regression"})
  public void EM002_edit_a_existed_employee_successfully() {
//    webUI.delayInSeconds(5);
    Faker faker = new Faker();
    employee = new Employee(faker.name().firstName(), faker.name().lastName(),
        faker.internet().emailAddress(), String.valueOf(faker.number().numberBetween(24, 55)),
        String.valueOf(faker.number().numberBetween(1000, 5000)), faker.job().title());
    addNewEmployee(employee);

    editedEmployee = new Employee(faker.name().firstName(), faker.name().lastName(),
        faker.internet().emailAddress(), String.valueOf(faker.number().numberBetween(24, 55)),
        String.valueOf(faker.number().numberBetween(1000, 5000)), faker.job().title());
    editEmployeeWithEmail(employee.getEmail(), editedEmployee);
//    webUI.delayInSeconds(5);
    assertTrue(shouldSeeFirstNameInEmployeeTable(editedEmployee.getFirstName()));
    assertTrue(shouldSeeLastNameInEmployeeTable(editedEmployee.getLastName()));
  }

  @Test(groups = {"regression"})
  public void EM003_delete_a_existed_employee_successfully() {
//    webUI.delayInSeconds(5);
    deleteEmployeeWithEmail(editedEmployee.getEmail());
//    webUI.delayInSeconds(5);
    assertTrue(shouldNotSeeFirstNameInEmployeeTable(editedEmployee.getFirstName()));
    assertTrue(shouldNotSeeLastNameInEmployeeTable(editedEmployee.getLastName()));
  }

  @Step("Click Add button")
  private void clickAddButton() {
    webUI.takeScreenshotWithHighlight(BTN_ADD);
    webUI.click(BTN_ADD);
    webUI.delayInSeconds(3);
    webUI.takeScreenshot();
  }

  @Step("Input First Name: {0}")
  private void inputFirstName(String firstName) {
    webUI.inputText(REGISTRATION_FORM_TXT_FIRST_NAME, firstName);
    webUI.takeScreenshotWithHighlight(REGISTRATION_FORM_TXT_FIRST_NAME);
  }

  @Step("Input Last Name: {0}")
  private void inputLastName(String lastName) {
    webUI.inputText(REGISTRATION_FORM_TXT_LAST_NAME, lastName);
    webUI.takeScreenshotWithHighlight(REGISTRATION_FORM_TXT_LAST_NAME);
  }

  @Step("Input Email: {0}")
  private void inputEmail(String email) {
    webUI.inputText(REGISTRATION_FORM_TXT_EMAIL, email);
    webUI.takeScreenshotWithHighlight(REGISTRATION_FORM_TXT_EMAIL);
  }

  @Step("Input Age: {0}")
  private void inputAge(String age) {
    webUI.inputText(REGISTRATION_FORM_TXT_AGE, age);
    webUI.takeScreenshotWithHighlight(REGISTRATION_FORM_TXT_AGE);
  }

  @Step("Input Salary: {0}")
  private void inputSalary(String salary) {
    webUI.inputText(REGISTRATION_FORM_TXT_SALARY, salary);
    webUI.takeScreenshotWithHighlight(REGISTRATION_FORM_TXT_SALARY);
  }

  @Step("Input Department: {0}")
  private void inputDepartment(String department) {
    webUI.inputText(REGISTRATION_FORM_TXT_DEPARTMENT, department);
    webUI.takeScreenshotWithHighlight(REGISTRATION_FORM_TXT_DEPARTMENT);
  }

  @Step("Click Submit button")
  private void clickSubmitButton() {
    webUI.takeScreenshotWithHighlight(REGISTRATION_FORM_BTN_SUBMIT);
    webUI.click(REGISTRATION_FORM_BTN_SUBMIT);
    webUI.delayInSeconds(3);
    webUI.takeScreenshot();
  }

  @Step("Add new employee")
  private void addNewEmployee(String firstName, String lastName, String email, String age,
      String salary, String department) {
    clickAddButton();
    inputFirstName(firstName);
    inputLastName(lastName);
    inputEmail(email);
    inputAge(age);
    inputSalary(salary);
    inputDepartment(department);
    clickSubmitButton();
  }

  @Step("Add new employee: {0}")
  private void addNewEmployee(Employee employee) {
    clickAddButton();
    inputFirstName(employee.getFirstName());
    inputLastName(employee.getLastName());
    inputEmail(employee.getEmail());
    inputAge(employee.getAge());
    inputSalary(employee.getSalary());
    inputDepartment(employee.getDepartment());
    clickSubmitButton();
  }

  @Step("Should show first name '{0}' in Employee table")
  private boolean shouldSeeFirstNameInEmployeeTable(String firstName) {
    List<WebElement> lblFirstNames = webUI.findWebElements(EMPLOYEE_TABLE_LBL_FIRST_NAMES);
    for (WebElement lblFirstName : lblFirstNames) {
      if (webUI.verifyElementText(lblFirstName, firstName)) {
        webUI.takeScreenshotWithHighlight(lblFirstName);
        return true;
      }
    }
    return false;
  }

  @Step("Should not show first name '{0}' in Employee table")
  private boolean shouldNotSeeFirstNameInEmployeeTable(String firstName) {
    List<WebElement> lblFirstNames = webUI.findWebElements(EMPLOYEE_TABLE_LBL_FIRST_NAMES);
    for (WebElement lblFirstName : lblFirstNames) {
      if (webUI.verifyElementText(lblFirstName, firstName)) {
        webUI.takeScreenshot();
        return false;
      }
    }
    return true;
  }

  @Step("Should show last name '{0}' in Employee table")
  private boolean shouldSeeLastNameInEmployeeTable(String lastName) {
    List<WebElement> lblFirstNames = webUI.findWebElements(EMPLOYEE_TABLE_LBL_LAST_NAMES);
    for (WebElement lblFirstName : lblFirstNames) {
      if (webUI.verifyElementText(lblFirstName, lastName)) {
        webUI.takeElementScreenshot(lblFirstName);
        return true;
      }
    }
    return false;
  }

  @Step("Should not show last name '{0}' in Employee table")
  private boolean shouldNotSeeLastNameInEmployeeTable(String lastName) {
    List<WebElement> lblFirstNames = webUI.findWebElements(EMPLOYEE_TABLE_LBL_LAST_NAMES);
    for (WebElement lblFirstName : lblFirstNames) {
      if (webUI.verifyElementText(lblFirstName, lastName)) {
        return false;
      }
    }
    return true;
  }

  @Step("Edit email '{0}'")
  private void clickEditButtonOfEmail(String email) {
    List<WebElement> lblEmails = webUI.findWebElements(EMPLOYEE_TABLE_LBL_EMAILS);
//    List<WebElement> btnEdits = webUI.findWebElements(EMPLOYEE_TABLE_BTN_EDITS);
    List<WebElement> btnActions = webUI.findWebElements(EMPLOYEE_TABLE_BTN_ACTIONS);
    for (int i = 0; i < lblEmails.size(); i++) {
      if (webUI.verifyElementText(lblEmails.get(i), email)) {
        webUI.clickOffset(btnActions.get(i), -24, 0);
        break;
      }
    }
  }

  @Step("Delete email '{0}'")
  private void clickDeleteButtonOfEmail(String email) {
    List<WebElement> lblEmails = webUI.findWebElements(EMPLOYEE_TABLE_LBL_EMAILS);
//    List<WebElement> btnDeletes = webUI.findWebElements(EMPLOYEE_TABLE_BTN_DELETES);
    List<WebElement> btnActions = webUI.findWebElements(EMPLOYEE_TABLE_BTN_ACTIONS);
    for (int i = 0; i < lblEmails.size(); i++) {
      if (webUI.verifyElementText(lblEmails.get(i), email)) {
        webUI.clickOffset(btnActions.get(i), 0, 0);
        break;
      }
    }
  }

  private void editEmployeeWithEmail(String oldEmail, String newFirstName, String newLastName,
      String newEmail, String newAge, String newSalary, String newDepartment) {
    clickEditButtonOfEmail(oldEmail);
    inputFirstName(newFirstName);
    inputLastName(newLastName);
    inputEmail(newEmail);
    inputAge(newAge);
    inputSalary(newSalary);
    inputDepartment(newDepartment);
    clickSubmitButton();
  }

  private void editEmployeeWithEmail(String oldEmail, Employee employee) {
    clickEditButtonOfEmail(oldEmail);
    inputFirstName(employee.getFirstName());
    inputLastName(employee.getLastName());
    inputEmail(editedEmployee.getEmail());
    inputAge(employee.getAge());
    inputSalary(employee.getSalary());
    inputDepartment(employee.getDepartment());
    clickSubmitButton();
  }

  private void deleteEmployeeWithEmail(String email) {
    clickDeleteButtonOfEmail(email);
  }

}
