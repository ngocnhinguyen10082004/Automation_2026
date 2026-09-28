package vn.edu.vtiacademy.pages;

import io.qameta.allure.Step;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import vn.edu.vtiacademy.common.keywords.WebUI;
import vn.edu.vtiacademy.model.Employee;

public class EmployeePageFactory {

  private final WebUI webUI;

  @FindBy(xpath = "//button[@id='addNewRecordButton']")
  private WebElement btnAdd;

  @FindBy(xpath = "//input[@id='firstName']")
  private WebElement registrationFormTxtFirstName;
  @FindBy(xpath = "//input[@id='lastName']")
  private WebElement registrationFormTxtLastName;
  @FindBy(xpath = "//input[@id='userEmail']")
  private WebElement registrationFormTxtEmail;
  @FindBy(xpath = "//input[@id='age']")
  private WebElement registrationFormTxtAge;
  @FindBy(xpath = "//input[@id='salary']")
  private WebElement registrationFormTxtSalary;
  @FindBy(xpath = "//input[@id='department']")
  private WebElement registrationFormTxtDepartment;
  @FindBy(xpath = "//button[@id='submit']")
  private WebElement registrationFormBtnSubmit;

  @FindBy(xpath = "//tr/td[1]")
  private List<WebElement> employeeTableLblFirstNames;
  @FindBy(xpath = "//tr/td[2]")
  private List<WebElement> employeeTableLblLastNames;
  @FindBy(xpath = "//tr/td[3]")
  private List<WebElement> employeeTableLblAges;
  @FindBy(xpath = "//tr/td[4]")
  private List<WebElement> employeeTableLblEmails;
  @FindBy(xpath = "//tr/td[5]")
  private List<WebElement> employeeTableLblSalaries;
  @FindBy(xpath = "//tr/td[6]")
  private List<WebElement> employeeTableLblDepartments;
  @FindBy(xpath = "//div[@class='action-buttons']/span[starts-with(@id,'edit-record')]")
  private List<WebElement> employeeTableBtnEdits;
  @FindBy(xpath = "//div[@class='action-buttons']/span[starts-with(@id,'delete-record')]")
  private List<WebElement> employeeTableBtnDeletes;
  @FindBy(xpath = "//tr//div[@class='action-buttons']")
  private List<WebElement> employeeTableBtnActions;

  public EmployeePageFactory(WebUI webUI) {
    this.webUI = webUI;
    this.webUI.usePageFactory(this,30);
  }

  @Step("Click Add button")
  private void clickAddButton() {
    webUI.takeScreenshotWithHighlight(btnAdd);
    webUI.click(btnAdd);
    webUI.delayInSeconds(3);
    webUI.takeScreenshot();
  }

  @Step("Input First Name: {0}")
  private void inputFirstName(String firstName) {
    webUI.inputText(registrationFormTxtFirstName, firstName);
    webUI.takeScreenshotWithHighlight(registrationFormTxtFirstName);
  }

  @Step("Input Last Name: {0}")
  private void inputLastName(String lastName) {
    webUI.inputText(registrationFormTxtLastName, lastName);
    webUI.takeScreenshotWithHighlight(registrationFormTxtLastName);
  }

  @Step("Input Email: {0}")
  private void inputEmail(String email) {
    webUI.inputText(registrationFormTxtEmail, email);
    webUI.takeScreenshotWithHighlight(registrationFormTxtEmail);
  }

  @Step("Input Age: {0}")
  private void inputAge(String age) {
    webUI.inputText(registrationFormTxtAge, age);
    webUI.takeScreenshotWithHighlight(registrationFormTxtAge);
  }

  @Step("Input Salary: {0}")
  private void inputSalary(String salary) {
    webUI.inputText(registrationFormTxtSalary, salary);
    webUI.takeScreenshotWithHighlight(registrationFormTxtSalary);
  }

  @Step("Input Department: {0}")
  private void inputDepartment(String department) {
    webUI.inputText(registrationFormTxtDepartment, department);
    webUI.takeScreenshotWithHighlight(registrationFormTxtDepartment);
  }

  @Step("Click Submit button")
  private void clickSubmitButton() {
    webUI.takeScreenshotWithHighlight(registrationFormBtnSubmit);
    webUI.click(registrationFormBtnSubmit);
    if (registrationFormBtnSubmit.isDisplayed()) {
      webUI.click(registrationFormBtnSubmit);
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
    for (WebElement lblFirstName : employeeTableLblFirstNames) {
      if (webUI.verifyElementText(lblFirstName, firstName)) {
        webUI.takeScreenshotWithHighlight(lblFirstName);
        return true;
      }
    }
    return false;
  }

  @Step("Should not show first name '{0}' in Employee table")
  public boolean shouldNotSeeFirstNameInEmployeeTable(String firstName) {
    for (WebElement lblFirstName : employeeTableLblFirstNames) {
      if (webUI.verifyElementText(lblFirstName, firstName)) {
        webUI.takeScreenshot();
        return false;
      }
    }
    return true;
  }

  @Step("Should show last name '{0}' in Employee table")
  public boolean shouldSeeLastNameInEmployeeTable(String lastName) {
    for (WebElement lblLastName : employeeTableLblLastNames) {
      if (webUI.verifyElementText(lblLastName, lastName)) {
        webUI.takeElementScreenshot(lblLastName);
        return true;
      }
    }
    return false;
  }

  @Step("Should not show last name '{0}' in Employee table")
  public boolean shouldNotSeeLastNameInEmployeeTable(String lastName) {
    for (WebElement lblLastName : employeeTableLblLastNames) {
      if (webUI.verifyElementText(lblLastName, lastName)) {
        return false;
      }
    }
    return true;
  }

  @Step("Edit email '{0}'")
  public void clickEditButtonOfEmail(String email) {
    for (int i = 0; i < employeeTableLblEmails.size(); i++) {
      if (webUI.verifyElementText(employeeTableLblEmails.get(i), email)) {
        webUI.clickOffset(employeeTableBtnActions.get(i), -24, 0);
        break;
      }
    }
  }

  @Step("Delete email '{0}'")
  public void clickDeleteButtonOfEmail(String email) {
    for (int i = 0; i < employeeTableLblEmails.size(); i++) {
      if (webUI.verifyElementText(employeeTableLblEmails.get(i), email)) {
        webUI.clickOffset(employeeTableBtnActions.get(i), 0, 0);
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
