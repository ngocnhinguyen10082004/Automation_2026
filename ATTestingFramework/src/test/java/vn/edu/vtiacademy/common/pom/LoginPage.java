package vn.edu.vtiacademy.common.pom;
import io.qameta.allure.Step;
import vn.edu.vtiacademy.common.keywords.WebUI;

public class LoginPage {
  private static final String GURU99_LOGIN_URL = "https://demo.guru99.com/V4/index.php";
  private static final String TXT_USER_ID = "/html/body/form/table/tbody/tr[1]/td[2]/input";
  private static final String TXT_PASSWORD = "/html/body/form/table/tbody/tr[2]/td[2]/input";
  private static final String BTN_LOGIN = "/html/body/form/table/tbody/tr[3]/td[2]/input[1]";
  private static final String BTN_RESET = "/html/body/form/table/tbody/tr[3]/td[2]/input[2]";

  // Error messages locators
  private static final String LBL_USER_ID_ERROR = "//label[@id='message23']";
  private static final String LBL_PASSWORD_ERROR = "//label[@id='message18']";
  // Manager page indicator
  private static final String MANAGER_TITLE = "//marquee[@class='heading3']";

  // ===== WebUI instance =====
  private WebUI webUI;


  public LoginPage(WebUI webUI) {
    this.webUI = webUI;
  }

  // ===== PAGE NAVIGATION =====
  @Step("Navigate to Guru99 Login Site")
  public void navigateToLoginPage() {
    webUI.navigateTo(GURU99_LOGIN_URL);
    webUI.delayInSeconds(2);
  }

  // ===== INPUT ACTIONS =====
  @Step("Input User ID: {0}")
  public void inputUserId(String userId) {
    webUI.inputText(TXT_USER_ID, userId);
    webUI.takeScreenshotWithHighlight(TXT_USER_ID);
  }

  @Step("Input Password: {0}")
  public void inputPassword(String password) {
    webUI.inputText(TXT_PASSWORD, password);
    webUI.takeScreenshotWithHighlight(TXT_PASSWORD);
  }

  // ===== BUTTON ACTIONS =====
  @Step("Click Login Button")
  public void clickLoginButton() {
    webUI.click(BTN_LOGIN);
    webUI.delayInSeconds(2);
    webUI.takeScreenshot();
  }

  @Step("Click Reset Button")
  public void clickResetButton() {
    webUI.click(BTN_RESET);
    webUI.delayInSeconds(1);
    webUI.takeScreenshot();
  }

  // ===== VERIFICATION METHODS =====
  @Step("Verify User ID Error Message: {0}")
  public boolean verifyUserIdErrorMessage(String expectedMessage) {
    webUI.takeScreenshot();
    return webUI.verifyElementText(LBL_USER_ID_ERROR, expectedMessage);
  }

  @Step("Verify Password Error Message: {0}")
  public boolean verifyPasswordErrorMessage(String expectedMessage) {
    webUI.takeScreenshot();
    return webUI.verifyElementText(LBL_PASSWORD_ERROR, expectedMessage);
  }

  @Step("Verify Manager Page is displayed")
  public boolean isManagerPageDisplayed() {
    webUI.delayInSeconds(2);
    webUI.takeScreenshot();
    return webUI.verifyElementText(MANAGER_TITLE, "Manger Id");
  }

  // ===== COMBINED ACTIONS =====
  @Step("Login with User ID: {0} and Password: {1}")
  public void login(String userId, String password) {
    inputUserId(userId);
    inputPassword(password);
    clickLoginButton();
  }

  @Step("Clear all fields")
  public void clearAllFields() {
    clickResetButton();
  }
}