package vn.edu.vtiacademy.test;

import static org.testng.Assert.assertTrue;

import net.datafaker.Faker;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import vn.edu.vtiacademy.model.Employee;

public class DataDrivenExcel_EmployeesTest extends ExcelBaseTest {

  private static Employee employee;
  private static Employee editedEmployee;

  public DataDrivenExcel_EmployeesTest() {
    super();
    setDataFile(DataDrivenExcel_EmployeesTest.class.getSimpleName());
    setSheetName(DataDrivenExcel_EmployeesTest.class.getSimpleName());
  }


  @Test(groups = {"smoke", "regression"})
  public void EM001_create_a_new_employee_successfully() {
    employee = Employee.Builder.aEmployee()
        .withFirstName(findTestData(1, 1))
        .withLastName(findTestData(1, 2))
        .withEmail(findTestData(1, 3))
        .withAge(findTestData(1, 4))
        .withSalary(findTestData(1, 5))
        .withDepartment(findTestData(1, 6))
        .build();
    // Arrange
    SoftAssert softAssert = new SoftAssert();

    //Act
    objEmployeesPage.addNewEmployee(employee);

    //Assert
    softAssert.assertTrue(
        objEmployeesPage.shouldSeeFirstNameInEmployeeTable(employee.getFirstName()),
        "Should see first name '" + employee.getFirstName() + "' in Employee Table");
    softAssert.assertTrue(
        objEmployeesPage.shouldSeeLastNameInEmployeeTable(employee.getLastName()));
    softAssert.assertAll();
  }

  @Test(groups = {"smoke", "regression"})
  public void EM001_create_a_new_employee_successfully_01() {
    employee = Employee.Builder.aEmployee()
        .withFirstName(findTestData("EM001").get(0).get("firstName"))
        .withLastName(findTestData("EM001").get(0).get("lastName"))
        .withEmail(findTestData("EM001").get(0).get("email"))
        .withAge(findTestData("EM001").get(0).get("age"))
        .withSalary(findTestData("EM001").get(0).get("salary"))
        .withDepartment(findTestData("EM001").get(0).get("department"))
        .build();
    // Arrange
    SoftAssert softAssert = new SoftAssert();

    //Act
    objEmployeesPage.addNewEmployee(employee);

    //Assert
    softAssert.assertTrue(
        objEmployeesPage.shouldSeeFirstNameInEmployeeTable(employee.getFirstName()),
        "Should see first name '" + employee.getFirstName() + "' in Employee Table");
    softAssert.assertTrue(
        objEmployeesPage.shouldSeeLastNameInEmployeeTable(employee.getLastName()));
    softAssert.assertAll();
  }

//  @Test(groups = {"smoke", "regression"})
//  public void EM002_edit_a_existed_employee_successfully() {
//    // Arrange
//    Faker faker = new Faker();
//    employee = new Employee(faker.name().firstName(), faker.name().lastName(),
//        faker.internet().emailAddress(), String.valueOf(faker.number().numberBetween(24, 55)),
//        String.valueOf(faker.number().numberBetween(1000, 5000)), faker.job().title());
//
//    editedEmployee = new Employee(faker.name().firstName(), faker.name().lastName(),
//        faker.internet().emailAddress(), String.valueOf(faker.number().numberBetween(24, 55)),
//        String.valueOf(faker.number().numberBetween(1000, 5000)), faker.job().title());
//    // Act
//    objEmployeesPage.addNewEmployee(employee);
//
//    // Assert
//    objEmployeesPage.editEmployeeWithEmail(employee.getEmail(), editedEmployee);
//    assertTrue(objEmployeesPage.shouldSeeFirstNameInEmployeeTable(editedEmployee.getFirstName()));
//    assertTrue(objEmployeesPage.shouldSeeLastNameInEmployeeTable(editedEmployee.getLastName()));
//  }
//
//  @Test(groups = {"regression"})
//  public void EM003_delete_a_existed_employee_successfully() {
//    objEmployeesPage.deleteEmployeeWithEmail(editedEmployee.getEmail());
//    assertTrue(
//        objEmployeesPage.shouldNotSeeFirstNameInEmployeeTable(editedEmployee.getFirstName()));
//    assertTrue(objEmployeesPage.shouldNotSeeLastNameInEmployeeTable(editedEmployee.getLastName()));
//  }
}
