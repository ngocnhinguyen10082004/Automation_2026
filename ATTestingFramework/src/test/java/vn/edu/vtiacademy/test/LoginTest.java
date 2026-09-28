package vn.edu.vtiacademy.test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import vn.edu.vtiacademy.common.keywords.WebUI;
import vn.edu.vtiacademy.common.pom.LoginPage;

public class LoginTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(LoginTest.class);
  private WebUI webUI;
  private LoginPage loginPage;

  @BeforeMethod(alwaysRun = true)
  public void setUp() {
    LOGGER.info(" START TEST");
    webUI = new WebUI();
    webUI.openBrowser("Chrome");
    loginPage = new LoginPage(webUI);
    loginPage.navigateToLoginPage();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    LOGGER.info("END TEST");
    webUI.closeBrowser();
  }

  @Test(priority = 1)
  public void TC001_LoginWithBlankUserId() {
    LOGGER.info("TC001: Login with blank User ID");

    loginPage.inputUserId("");
    loginPage.inputPassword("UmyYqQe");
    loginPage.clickLoginButton();

    SoftAssert softAssert = new SoftAssert();
    boolean isErrorDisplayed = loginPage.verifyUserIdErrorMessage("User-ID must not be blank");
    softAssert.assertTrue(isErrorDisplayed, " Error message'User-ID must not be blank' should be display ");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void TC002_LoginWithBlankPassword() {
    LOGGER.info(" TC002: Login with blank Password ");

    loginPage.inputUserId("mngr579831");
    loginPage.inputPassword("");
    loginPage.clickLoginButton();

    SoftAssert softAssert = new SoftAssert();
    boolean isErrorDisplayed = loginPage.verifyPasswordErrorMessage("Password must not be blank");
    softAssert.assertTrue(isErrorDisplayed, "Error message 'Password must not be blank' should be displayed");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void TC003_LoginWithValidCredentials() {
    LOGGER.info(" TC003: Login with valid credentials ");
    loginPage.login("mngr579831", "UmyYqQe");
    SoftAssert softAssert = new SoftAssert();
    boolean isManagerPageDisplayed = loginPage.isManagerPageDisplayed();
    softAssert.assertTrue(isManagerPageDisplayed, "Manager page with 'Manger Id:' should be displayed");
    softAssert.assertAll();
  }
}