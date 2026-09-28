package vn.edu.vtiacademy.test;

import static org.testng.Assert.assertTrue;

import net.datafaker.Faker;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import vn.edu.vtiacademy.model.Employee;

public class POM_EmployeesTest extends BaseTest {

  private static Employee employee;
  private static Employee editedEmployee;

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
  public void EM002_edit_a_existed_employee_successfully() {
    // Arrange
    Faker faker = new Faker();
    employee = new Employee(faker.name().firstName(), faker.name().lastName(),
        faker.internet().emailAddress(), String.valueOf(faker.number().numberBetween(24, 55)),
        String.valueOf(faker.number().numberBetween(1000, 5000)), faker.job().title());

    editedEmployee = new Employee(faker.name().firstName(), faker.name().lastName(),
        faker.internet().emailAddress(), String.valueOf(faker.number().numberBetween(24, 55)),
        String.valueOf(faker.number().numberBetween(1000, 5000)), faker.job().title());
    // Act
    objEmployeesPage.addNewEmployee(employee);

    // Assert
    objEmployeesPage.editEmployeeWithEmail(employee.getEmail(), editedEmployee);
    assertTrue(objEmployeesPage.shouldSeeFirstNameInEmployeeTable(editedEmployee.getFirstName()));
    assertTrue(objEmployeesPage.shouldSeeLastNameInEmployeeTable(editedEmployee.getLastName()));
  }

  @Test(groups = {"regression"})
  public void EM003_delete_a_existed_employee_successfully() {
    objEmployeesPage.deleteEmployeeWithEmail(editedEmployee.getEmail());
    assertTrue(
        objEmployeesPage.shouldNotSeeFirstNameInEmployeeTable(editedEmployee.getFirstName()));
    assertTrue(objEmployeesPage.shouldNotSeeLastNameInEmployeeTable(editedEmployee.getLastName()));
  }
}
