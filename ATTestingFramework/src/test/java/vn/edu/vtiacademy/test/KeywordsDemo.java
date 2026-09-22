package vn.edu.vtiacademy.test;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import vn.edu.vtiacademy.common.keywords.WebUI;

public class KeywordsDemo {

  private static WebUI webUI;

  @BeforeTest
  public static void setup() {
    webUI = new WebUI();
    webUI.openBrowser("Chrome");
  }

//  @BeforeEach
//  void setUp() {
//
//  }

  @Test(enabled = false)
  public void web_browser_commands_demo() throws InterruptedException {
    webUI.navigateTo("https://demoqa.com/webtables");
//    webUI.navigateTo("https://demoqa.com/webtables");
    webUI.maximizeWindow();
    Thread.sleep(10000);
    webUI.getTitle();
    webUI.getCurrentUrl();
    webUI.getPageSource();
    webUI.navigateTo("https://vnexpress.net");
//    Thread.sleep(10000);
    webUI.back();
//    Thread.sleep(10000);
    webUI.forward();
//    Thread.sleep(10000);
    webUI.refresh();
//    Thread.sleep(10000);
  }

  @Test
  public void web_elements_commands_demo() {
    webUI.navigateTo("https://demoqa.com/text-box");
    webUI.maximizeWindow();
    webUI.delayInSeconds(5);
    webUI.inputText("//input[@id='userName']", "John Doe");
    webUI.delayInSeconds(5);
    webUI.copyText("//input[@id='userName']");
    webUI.pasteText("//input[@id='userEmail']");
    webUI.delayInSeconds(5);
    webUI.clearText("//input[@id='userName']");
    webUI.delayInSeconds(5);
    webUI.navigateTo("https://demoqa.com/buttons");
    webUI.delayInSeconds(5);
    webUI.click("//button[normalize-space()='Click Me']");
    webUI.delayInSeconds(5);
  }


  @Test
  public void verify_elements_commands_demo() {
    webUI.navigateTo("https://demoqa.com/text-box");
    webUI.maximizeWindow();
    webUI.delayInSeconds(5);
    webUI.inputText("//input[@id='userName']", "John Doe");
    webUI.delayInSeconds(5);
    webUI.inputText("//input[@id='userEmail']", "john.doe@mailinator.com");
    webUI.delayInSeconds(5);
    webUI.submit("//button[@id='submit']");
    webUI.delayInSeconds(5);
    assertTrue(webUI.verifyElementContainsText("//p[@id='name']", "John Doe"));
  }


  @Test
  public void validate_full_name_text_box_edited() {
    webUI.navigateTo("https://demoqa.com/text-box");
    webUI.maximizeWindow();
    webUI.inputText("//input[@id='userName']", "John Doe");
    String actualValue = webUI.getAttributeValue("//input[@id='userName']", "value");
    assertEquals("John Doe", actualValue);
    webUI.clearText("//input[@id='userName']");
    actualValue = webUI.getAttributeValue("//input[@id='userName']", "value");
    assertEquals("", actualValue);
  }

  @Test
  public void validate_full_name_text_box_edited_02() {
    webUI.navigateTo("https://demoqa.com/text-box");
    webUI.maximizeWindow();
    webUI.inputText("//input[@id='userName']", "John Doe");
    assertTrue(webUI.verifyElementAttributeValue("//input[@id='userName']", "value", "John Does"));
    webUI.clearText("//input[@id='userName']");
    assertTrue(webUI.verifyElementAttributeValue("//input[@id='userName']", "value", ""));
  }

  @Test
  public void validate_email_text_box_with_wrong_email() {
    webUI.navigateTo("https://demoqa.com/text-box");
    webUI.maximizeWindow();
    webUI.inputText("css:input[id='userEmail']", "JohnDoe");
    webUI.click("//button[@id='submit']");
    webUI.waitForCssValue("//input[@id='userEmail']", "border", "1px solid rgb(255, 0, 0)", 40);
    assertTrue(webUI.verifyCssValue("//input[@id='userEmail']", "border", "1px solid rgb(255, 0, 0)"));
  }

  @Test
  public void validate_select_or_deselect_options() {
    webUI.navigateTo("https://demoqa.com/select-menu");
    webUI.maximizeWindow();
    webUI.selectOptionByIndex("//select[@id='oldSelectMenu']", 5);
    assertTrue(webUI.verifyOptionSelectedByIndex("//select[@id='oldSelectMenu']", 5));
    webUI.delayInSeconds(5);
    webUI.selectOptionByValue("//select[@id='oldSelectMenu']", "1");
    assertTrue(webUI.verifyOptionSelectedByValue("//select[@id='oldSelectMenu']", "1"));
    webUI.delayInSeconds(5);
    webUI.selectOptionByText("//select[@id='oldSelectMenu']", "Indigo");
    assertTrue(webUI.verifyOptionSelectedByText("//select[@id='oldSelectMenu']", "Indigo"));
    webUI.delayInSeconds(5);
    webUI.selectAllOptions("//select[@id='cars']");
    assertTrue(webUI.verifyAllOptionsSelected("//select[@id='cars']"));
    webUI.delayInSeconds(5);
    webUI.deselectOptionByIndex("//select[@id='cars']", 2);
    assertTrue(webUI.verifyOptionNotSelectedByIndex("//select[@id='cars']", 2));
    webUI.delayInSeconds(5);
    webUI.deselectAllOptions("//select[@id='cars']");
    assertTrue(webUI.verifyAllOptionsNotSelected("//select[@id='cars']"));
    webUI.delayInSeconds(5);
  }

  @Test
  public void validate_to_handle_windows() {
    webUI.navigateTo("https://demoqa.com/browser-windows");
    webUI.maximizeWindow();
    webUI.click("//button[@id='tabButton']");
    webUI.delayInSeconds(5);
    webUI.switchToWindowByIndex(1);
    assertTrue(webUI.verifyElementText("//h1[@id='sampleHeading']", "This is a sample page"));
    webUI.closeWindowByIndex(1);
    webUI.delayInSeconds(5);
    webUI.switchToWindowByIndex(0);
    webUI.delayInSeconds(5);
    webUI.click("//button[@id='windowButton']");
    webUI.delayInSeconds(5);
  }

  private static String TXT_SELECT_VALUE = "//div[@id='withOptGroup']//div[contains(@class,'placeholder') or contains(@class,'singleValue')]/following-sibling::div"; //
  private static String LBL_SELECT_VALUE = "//div[@id='withOptGroup']//div[contains(@class,'placeholder') or contains(@class,'singleValue')]";
  private static String DDL_SELECT_OPTIONS = "//div[@role='option' and starts-with(@id, 'react-select') and  contains(@id,'-option-')]";

  @Test
  public void select_value_in_dropdown_with_new_style() {
    webUI.navigateTo("https://demoqa.com/select-menu");
    webUI.maximizeWindow();
    webUI.click(TXT_SELECT_VALUE);
    webUI.delayInSeconds(5);
    selectOptionValue("Group 2, option 2");
    webUI.delayInSeconds(5);
    assertTrue(webUI.verifyElementText(LBL_SELECT_VALUE, "Group 2, option 2"));
    webUI.click(TXT_SELECT_VALUE);
    selectOptionValue("Group 1, option 1");
    webUI.delayInSeconds(5);
    assertTrue(webUI.verifyElementText(LBL_SELECT_VALUE, "Group 1, option 1"));
  }

  @Test
  public void validate_double_click_button() {
    webUI.navigateTo("https://demoqa.com/buttons");
    webUI.maximizeWindow();
    webUI.doubleClick("//button[@id='doubleClickBtn']");
    assertTrue(webUI.verifyElementText("//p[@id='doubleClickMessage']", "You have done a double click"));
  }

  @Test
  public void validate_mouse_over_button() {
    webUI.navigateTo("https://demoqa.com/tool-tips");
    webUI.maximizeWindow();
    webUI.mouseOver("//button[@id='toolTipButton']");
    webUI.waitForElementVisible("//div[@id='buttonToolTip']//div[contains(@class,'tooltip-inner')]");
    assertTrue(webUI.verifyElementText("//div[@id='buttonToolTip']//div[contains(@class,'tooltip-inner')]",
        "You hovered over the Button"));
  }

  @Test
  public void validate_right_click_button() {
    webUI.navigateTo("https://demoqa.com/buttons");
    webUI.maximizeWindow();
    webUI.rightClick("//button[@id='rightClickBtn']");
    assertTrue(webUI.verifyElementText("//p[@id='rightClickMessage']", "You have done a right click"));
  }

  @Test
  public void validate_drag_and_drop() {
    webUI.navigateTo("https://demoqa.com/droppable");
    webUI.maximizeWindow();
    webUI.delayInSeconds(2);
    webUI.dragAndDrop("//div[@id='draggable']", "//div[@id='droppable']");
    webUI.delayInSeconds(3);
    assertTrue(webUI.verifyElementText("//div[@id='droppable']", "Dropped!"));
  }

  @Test
  public void validate_scroll_to_element_and_click() {
    webUI.navigateTo("https://dantri.com.vn/");
    webUI.maximizeWindow();
    String articleLink = "//a[contains(text(),'Bộ Nội vụ nói về khả năng tiếp tục sáp nhập xã, ph')]";
    webUI.scrollToElementAtPageCenter(articleLink);
    webUI.delayInSeconds(5);
    webUI.click(articleLink);
    webUI.waitForElementPresent("//h1[@data-slot='title']");
    assertTrue(webUI.getCurrentUrl().contains("bo-noi-vu-noi-ve-kha-nang-tiep-tuc-sap-nhap-xa-phuong"));
  }

  private void selectOptionValue(String option) {
    List<WebElement> lblSelectOptions = webUI.findWebElements(DDL_SELECT_OPTIONS);
    for (WebElement lblSelectOption: lblSelectOptions) {
      if(webUI.verifyElementText(lblSelectOption, option)) {
        webUI.click(lblSelectOption);
        break;
      }
    }
  }

//  @AfterEach
//  public void tearDown() {
//    webUI.closeBrowser();
//  }

  @AfterTest
  public static void tearDown() {
    webUI.closeBrowser();
  }
}
