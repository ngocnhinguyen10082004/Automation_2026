package vn.edu.vtiacademy.test;

import com.jayway.jsonpath.JsonPath;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import vn.edu.vtiacademy.common.keywords.WebUI;
import vn.edu.vtiacademy.pages.EmployeesPage;

public class BaseTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
  private static WebUI webUI;

  private final static String DATA_FOLDER =
      System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
          + File.separator + "resources" + File.separator + "data";
  //"/Users/tuyenluu/training-workspace/VTI_AT_202602/ATTestingFramework/src/test/resources/data";

  private String dataFile;

  protected EmployeesPage objEmployeesPage;

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

  private String getDataFile() {
    return dataFile;
  }

  public void setDataFile(String dataFile) {
    this.dataFile = DATA_FOLDER + File.separator + dataFile + ".json";
  }

  public String findTestData(String testDataName) {
    File file = new File(getDataFile());
    try {
      String dataValue = JsonPath.read(file, "$." + testDataName).toString();
      return dataValue;
    } catch (IOException e) {
      LOGGER.error("Failed to find test object '{}' in '{}'. Root cause: {}", testDataName, getDataFile(), e.getMessage());
    }
    return null;
  }
}
