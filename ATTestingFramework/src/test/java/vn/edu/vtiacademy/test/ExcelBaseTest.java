package vn.edu.vtiacademy.test;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import vn.edu.vtiacademy.common.helpers.ExcelHelper;
import vn.edu.vtiacademy.common.helpers.FileHelper;
import vn.edu.vtiacademy.common.keywords.WebUI;
import vn.edu.vtiacademy.pages.EmployeesPage;

public class ExcelBaseTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExcelBaseTest.class);
  private final static String DATA_FOLDER =
      System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
          + File.separator + "resources" + File.separator + "data";
  private static WebUI webUI;
  //"/Users/tuyenluu/training-workspace/VTI_AT_202602/ATTestingFramework/src/test/resources/data";
  protected EmployeesPage objEmployeesPage;
  private String dataFile;
  private String sheetName;

  @BeforeMethod(alwaysRun = true)
  public static void setup(Method method) {
    LOGGER.info("===============================Start {}========================",
        method.getName());
  }

  @AfterMethod(alwaysRun = true)
  public static void tearDown(Method method) {
    LOGGER.info("===============================End {}========================", method.getName());
  }

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
    objEmployeesPage = new EmployeesPage(webUI);
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

  private String getDataFile() {
    return dataFile;
  }

  public void setDataFile(String dataFile) {
    this.dataFile = DATA_FOLDER + File.separator + dataFile + ".xlsx";
  }

  private String getSheetName() {
    return sheetName;
  }

  public void setSheetName(String sheetName) {
    this.sheetName = sheetName;
  }

  public String findTestData(int rowIndex, int columnIndex) {
    XSSFWorkbook workbook = ExcelHelper.getWorkbook(getDataFile());
    XSSFSheet sheet = ExcelHelper.getSheet(workbook, getSheetName());
    try {
      String dataValue = ExcelHelper.getCellValue(sheet, rowIndex, columnIndex);
      return dataValue;
    } catch (Exception e) {
      LOGGER.error(
          "Failed to find test data from row index '{}', column index '{}' in excel file '{}'. Root cause: {}",
          rowIndex, columnIndex, getDataFile(), e.getMessage());
    }
    return null;
  }

  public List<HashMap<String, String>> findTestData(String testCaseId) {
    LOGGER.info("Finding test data of test case '{}' in sheet name '{}' of file '{}'",
        testCaseId, getSheetName(), getDataFile());
    List<HashMap<String, String>> cellValues = FileHelper.getExcelData(getDataFile(),
        getSheetName(), testCaseId);
    LOGGER.info("Found '{}' test data of test case id '{}' in sheet name '{}' of file '{}'", cellValues.size(), testCaseId,
        getSheetName(), getDataFile());
    return cellValues;
  }
}
