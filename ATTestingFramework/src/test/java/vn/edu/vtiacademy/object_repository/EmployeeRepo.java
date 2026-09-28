package vn.edu.vtiacademy.object_repository;

public class EmployeeRepo {

  // object repositories
  public static final String BTN_ADD = "//button[@id='addNewRecordButton']";
  public static final String REGISTRATION_FORM_TXT_FIRST_NAME = "//input[@id='firstName']";
  public static final String REGISTRATION_FORM_TXT_LAST_NAME = "//input[@id='lastName']";
  public static final String REGISTRATION_FORM_TXT_EMAIL = "//input[@id='userEmail']";
  public static final String REGISTRATION_FORM_TXT_AGE = "//input[@id='age']";
  public static final String REGISTRATION_FORM_TXT_SALARY = "//input[@id='salary']";
  public static final String REGISTRATION_FORM_TXT_DEPARTMENT = "//input[@id='department']";
  public static final String REGISTRATION_FORM_BTN_SUBMIT = "//button[@id='submit']";

  public static final String EMPLOYEE_TABLE_LBL_FIRST_NAMES = "//tr/td[1]";
  public static final String EMPLOYEE_TABLE_LBL_LAST_NAMES = "//tr/td[2]";
  public static final String EMPLOYEE_TABLE_LBL_AGES = "//tr/td[3]";
  public static final String EMPLOYEE_TABLE_LBL_EMAILS = "//tr/td[4]";
  public static final String EMPLOYEE_TABLE_LBL_SALARIES = "//tr/td[5]";
  public static final String EMPLOYEE_TABLE_LBL_DEPARTMENTS = "//tr/td[6]";
  public static final String EMPLOYEE_TABLE_BTN_EDITS = "//div[@class='action-buttons']/span[starts-with(@id,'edit-record')]";
  public static final String EMPLOYEE_TABLE_BTN_DELETES = "//div[@class='action-buttons']/span[starts-with(@id,'delete-record')]";

  public static final String EMPLOYEE_TABLE_BTN_ACTIONS = "//tr//div[@class='action-buttons']";
}
