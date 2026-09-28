package vn.edu.vtiacademy.test;

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
import vn.edu.vtiacademy.pages.EmployeePageFactory;

public class PageFactoryBaseTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(PageFactoryBaseTest.class);
  private static WebUI webUI;

  protected EmployeePageFactory objEmployeesPage;

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
    objEmployeesPage = new EmployeePageFactory(webUI);

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
}
