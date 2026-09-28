package vn.edu.vtiacademy.pages;

import io.qameta.allure.Step;
import java.util.List;
import org.openqa.selenium.WebElement;
import vn.edu.vtiacademy.common.keywords.WebUI;
import vn.edu.vtiacademy.model.Employee;
import vn.edu.vtiacademy.object_repository.EmployeeRepo;

public class EmployeesPage extends BasePage{

  public EmployeesPage(WebUI webUi) {
    super(webUi);
    setRepo(EmployeesPage.class.getSimpleName()); // EmployeesPage.class.getSimpleName() = "EmployeesPage"
  }

  // methods to interact with web elements
  @Step("Click Add button")
  private void clickAddButton() {
    webUI.takeScreenshotWithHighlight(findTestObject("BTN_ADD"));
    webUI.click(findTestObject("BTN_ADD"));
    webUI.delayInSeconds(3);
    webUI.takeScreenshot();
  }

  @Step("Input First Name: {0}")
  private void inputFirstName(String firstName) {
    webUI.inputText(findTestObject("REGISTRATION_FORM_TXT_FIRST_NAME"), firstName);
    webUI.takeScreenshotWithHighlight(findTestObject("REGISTRATION_FORM_TXT_FIRST_NAME"));
  }

  @Step("Input Last Name: {0}")
  private void inputLastName(String lastName) {
    webUI.inputText(findTestObject("REGISTRATION_FORM_TXT_LAST_NAME"), lastName);
    webUI.takeScreenshotWithHighlight(findTestObject("REGISTRATION_FORM_TXT_LAST_NAME"));
  }

  @Step("Input Email: {0}")
  private void inputEmail(String email) {
    webUI.inputText(findTestObject("REGISTRATION_FORM_TXT_EMAIL"), email);
    webUI.takeScreenshotWithHighlight(findTestObject("REGISTRATION_FORM_TXT_EMAIL"));
  }

  @Step("Input Age: {0}")
  private void inputAge(String age) {
    webUI.inputText(findTestObject("REGISTRATION_FORM_TXT_AGE"), age);
    webUI.takeScreenshotWithHighlight(findTestObject("REGISTRATION_FORM_TXT_AGE"));
  }

  @Step("Input Salary: {0}")
  private void inputSalary(String salary) {
    webUI.inputText(findTestObject("REGISTRATION_FORM_TXT_SALARY"), salary);
    webUI.takeScreenshotWithHighlight(findTestObject("REGISTRATION_FORM_TXT_SALARY"));
  }

  @Step("Input Department: {0}")
  private void inputDepartment(String department) {
    webUI.inputText(findTestObject("REGISTRATION_FORM_TXT_DEPARTMENT"), department);
    webUI.takeScreenshotWithHighlight(findTestObject("REGISTRATION_FORM_TXT_DEPARTMENT"));
  }

  @Step("Click Submit button")
  private void clickSubmitButton() {
    webUI.takeScreenshotWithHighlight(findTestObject("REGISTRATION_FORM_BTN_SUBMIT"));
    webUI.click(findTestObject("REGISTRATION_FORM_BTN_SUBMIT"));
    if(webUI.verifyElementVisible(findTestObject("REGISTRATION_FORM_BTN_SUBMIT"))) {
      webUI.click(findTestObject("REGISTRATION_FORM_BTN_SUBMIT"));
    }
    webUI.delayInSeconds(3);
    webUI.takeScreenshot();
  }

  @Step("Add new employee")
  public void addNewEmployee(String firstName, String lastName, String email, String age,
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
  public void addNewEmployee(Employee employee) {
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
  public boolean shouldSeeFirstNameInEmployeeTable(String firstName) {
    List<WebElement> lblFirstNames = webUI.findWebElements(findTestObject("EMPLOYEE_TABLE_LBL_FIRST_NAMES"));
    for (WebElement lblFirstName : lblFirstNames) {
      if (webUI.verifyElementText(lblFirstName, firstName)) {
        webUI.takeScreenshotWithHighlight(lblFirstName);
        return true;
      }
    }
    return false;
  }

  @Step("Should not show first name '{0}' in Employee table")
  public boolean shouldNotSeeFirstNameInEmployeeTable(String firstName) {
    List<WebElement> lblFirstNames = webUI.findWebElements(findTestObject("EMPLOYEE_TABLE_LBL_FIRST_NAMES"));
    for (WebElement lblFirstName : lblFirstNames) {
      if (webUI.verifyElementText(lblFirstName, firstName)) {
        webUI.takeScreenshot();
        return false;
      }
    }
    return true;
  }

  @Step("Should show last name '{0}' in Employee table")
  public boolean shouldSeeLastNameInEmployeeTable(String lastName) {
    List<WebElement> lblFirstNames = webUI.findWebElements(findTestObject("EMPLOYEE_TABLE_LBL_LAST_NAMES"));
    for (WebElement lblFirstName : lblFirstNames) {
      if (webUI.verifyElementText(lblFirstName, lastName)) {
        webUI.takeElementScreenshot(lblFirstName);
        return true;
      }
    }
    return false;
  }

  @Step("Should not show last name '{0}' in Employee table")
  public boolean shouldNotSeeLastNameInEmployeeTable(String lastName) {
    List<WebElement> lblFirstNames = webUI.findWebElements(findTestObject("EMPLOYEE_TABLE_LBL_LAST_NAMES"));
    for (WebElement lblFirstName : lblFirstNames) {
      if (webUI.verifyElementText(lblFirstName, lastName)) {
        return false;
      }
    }
    return true;
  }

  @Step("Edit email '{0}'")
  public void clickEditButtonOfEmail(String email) {
    List<WebElement> lblEmails = webUI.findWebElements(findTestObject("EMPLOYEE_TABLE_LBL_EMAILS"));
//    List<WebElement> btnEdits = webUI.findWebElements(EMPLOYEE_TABLE_BTN_EDITS);
    List<WebElement> btnActions = webUI.findWebElements(findTestObject("EMPLOYEE_TABLE_BTN_ACTIONS"));
    for (int i = 0; i < lblEmails.size(); i++) {
      if (webUI.verifyElementText(lblEmails.get(i), email)) {
        webUI.clickOffset(btnActions.get(i), -24, 0);
        break;
      }
    }
  }

  @Step("Delete email '{0}'")
  public void clickDeleteButtonOfEmail(String email) {
    List<WebElement> lblEmails = webUI.findWebElements(findTestObject("EMPLOYEE_TABLE_LBL_EMAILS"));
//    List<WebElement> btnDeletes = webUI.findWebElements(EMPLOYEE_TABLE_BTN_DELETES);
    List<WebElement> btnActions = webUI.findWebElements(findTestObject("EMPLOYEE_TABLE_BTN_ACTIONS"));
    for (int i = 0; i < lblEmails.size(); i++) {
      if (webUI.verifyElementText(lblEmails.get(i), email)) {
        webUI.clickOffset(btnActions.get(i), 0, 0);
        break;
      }
    }
  }

  public void editEmployeeWithEmail(String oldEmail, String newFirstName, String newLastName,
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

  public void editEmployeeWithEmail(String oldEmail, Employee employee) {
    clickEditButtonOfEmail(oldEmail);
    inputFirstName(employee.getFirstName());
    inputLastName(employee.getLastName());
    inputEmail(employee.getEmail());
    inputAge(employee.getAge());
    inputSalary(employee.getSalary());
    inputDepartment(employee.getDepartment());
    clickSubmitButton();
  }

  public void deleteEmployeeWithEmail(String email) {
    clickDeleteButtonOfEmail(email);
  }
}
