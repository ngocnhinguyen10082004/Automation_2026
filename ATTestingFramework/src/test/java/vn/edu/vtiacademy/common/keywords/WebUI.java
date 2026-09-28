package vn.edu.vtiacademy.common.keywords;

import io.qameta.allure.Attachment;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebUI {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebUI.class);
  private static final int DEFAULT_TIMEOUT = 30;
  private WebDriver driver;

  //chrome, Chrome, CHROME, chRome => CHROME
  public void openBrowser(String browserName, String... url) {
    try {
      LOGGER.info("Opening browser '{}'", browserName.toUpperCase());
      switch (browserName.toUpperCase()) {
        case "CHROME":
//          WebDriverManager.chromedriver().setup();
          ChromeOptions options = new ChromeOptions();
          options.addArguments("--remote-allow-origins=*");
          driver = new ChromeDriver(options); //open browser
          break;
        case "FIREFOX":
//          WebDriverManager.firefoxdriver().setup();
          driver = new FirefoxDriver(); //open browser
          break;
        case "SAFARI":
//          WebDriverManager.safaridriver().setup();
          driver = new SafariDriver(); //open browser
          break;
        case "CHROME_HEADLESS":
          ChromeOptions chromeHeadlessOptions = new ChromeOptions();
          chromeHeadlessOptions.addArguments("--remote-allow-origins=*");
          chromeHeadlessOptions.addArguments("--headless=new");
          driver = new ChromeDriver(chromeHeadlessOptions);
          break;
        case "FIREFOX_HEADLESS":
          FirefoxOptions firefoxHeadlessOptions = new FirefoxOptions();
          firefoxHeadlessOptions.addArguments("--headless");
          driver = new FirefoxDriver(firefoxHeadlessOptions);
          break;
      }
      LOGGER.info("Opened browser '{}' successfully", browserName.toUpperCase());
    } catch (Exception e) {
      LOGGER.error("Failed to open browser '{}'. Root cause: {}", browserName.toUpperCase(),
          e.getMessage());
    }

    String rawUrl = url.length > 0 ? url[0] : "";
    if (!rawUrl.isEmpty()) {
      try {
        LOGGER.info("Navigating to url '{}'", rawUrl);
        driver.get(rawUrl);
        LOGGER.info("Navigated to url '{}' successfully", rawUrl);
      } catch (Exception e) {
        LOGGER.error("Failed to navigate to url '{}'. Root cause: {}", rawUrl, e.getMessage());
      }
    }
  }

  public void usePageFactory(Object page, int timeout) {
    LOGGER.info("Using page factory");
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, timeout), page);
    LOGGER.info("Used page factory successfully");
  }

  public void closeBrowser() {
    try {
      LOGGER.info("Closing browser");
      driver.quit();
      LOGGER.info("Closed browser successfully");
    } catch (Exception e) {
      LOGGER.error("Failed to close browser. Root cause: {}", e.getMessage());
    }
  }

  public String getTitle() {
    try {
      LOGGER.info("Getting title of the page");
      String title = driver.getTitle();
      LOGGER.info("Title of the page is '{}'", title);
      return title;
    } catch (Exception e) {
      LOGGER.error("Failed to get title of the page. Root cause: {}", e.getMessage());
    }
    return null;
  }

  public String getCurrentUrl() {
    try {
      LOGGER.info("Getting current url");
      String url = driver.getCurrentUrl();
      LOGGER.info("Current url is '{}'", url);
      return url;
    } catch (Exception e) {
      LOGGER.error("Failed to get current url. Root cause: {}", e.getMessage());
    }
    return null;
  }

  public String getPageSource() {
    try {
      LOGGER.info("Getting page source");
      String source = driver.getPageSource();
      LOGGER.info("Page source is '{}'", source);
      return source;
    } catch (Exception e) {
      LOGGER.error("Failed to get page source. Root cause: {}", e.getMessage());
    }
    return null;
  }

  @Attachment(value = "Page screenshot", type = "image/png")
  public byte[] takeScreenshot() {
    try {
      LOGGER.info("Taking screenshot of the page");
      byte[] images = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      if (images.length > 0) {
        LOGGER.info("Took screenshot of the page successfully");
        return images;
      }
    } catch (Exception e) {
      LOGGER.error("Failed to take screenshot of the page. Root cause: {}", e.getMessage());
    }
    return null;
  }

  public String takeScreenshot(String filePath) {
    try {
      LOGGER.info("Taking screenshot of the page");
      File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      File destFile = new File(filePath);
      FileUtils.copyFile(srcFile, destFile);
      LOGGER.info("Took screenshot of the page and saved to '{}' successfully", filePath);
      return destFile.getAbsolutePath();
    } catch (Exception e) {
      LOGGER.error("Failed to take screenshot of the page. Root cause: {}", e.getMessage());
    }
    return null;
  }

  @Attachment(value = "Page screenshot", type = "image/png")
  public byte[] takeElementScreenshot(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Taking screenshot of web element located by '{}'", we);
      byte[] images = we.getScreenshotAs(OutputType.BYTES);
      if (images.length > 0) {
        LOGGER.info("Took screenshot of web element located by '{}' successfully",
            we);
        return images;
      }
    } catch (Exception e) {
      LOGGER.error("Failed to take screenshot of web element located by '{}'. Root cause: {}", we,
          e.getMessage());
    }
    return null;
  }

  @Attachment(value = "Page screenshot", type = "image/png")
  public byte[] takeElementScreenshot(WebElement we) {
    try {
      LOGGER.info("Taking screenshot of web element located by '{}'", we);
      byte[] images = we.getScreenshotAs(OutputType.BYTES);
      if (images.length > 0) {
        LOGGER.info("Took screenshot of web element located by '{}' successfully",
            we);
        return images;
      }
    } catch (Exception e) {
      LOGGER.error("Failed to take screenshot of web element located by '{}'. Root cause: {}", we,
          e.getMessage());
    }
    return null;
  }

  public String takeScreenshot(String locator, String filePath) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Taking screenshot of web element located by '{}'", we);
      File srcFile = we.getScreenshotAs(OutputType.FILE);
      File destFile = new File(filePath);
      FileUtils.copyFile(srcFile, destFile);
      LOGGER.info("Took screenshot of web element located by '{}' and saved to '{}' successfully",
          we, filePath);
      return destFile.getAbsolutePath();
    } catch (Exception e) {
      LOGGER.error("Failed to take screenshot of web element located by '{}'. Root cause: {}", we,
          e.getMessage());
    }
    return null;
  }

  @Attachment(value = "Page screenshot with highlighted element", type = "image/png")
  public byte[] takeScreenshotWithHighlight(String locator) {
    WebElement we = findWebElement(locator);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String originalStyle = we.getAttribute("style");
    try {
      LOGGER.info("Taking screenshot of the page with web element '{}' highlighted", we);
      js.executeScript("arguments[0].style.border='4px solid red';", we);
      byte[] images = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      if (images.length > 0) {
        LOGGER.info("Took screenshot of the page with web element '{}' highlighted successfully",
            we);
        return images;
      }
    } catch (Exception e) {
      LOGGER.error(
          "Failed to take screenshot of the page with web element '{}' highlighted. Root cause: {}",
          we, e.getMessage());
    } finally {
      js.executeScript("arguments[0].setAttribute('style', arguments[1]);", we, originalStyle);
    }
    return null;
  }

  @Attachment(value = "Page screenshot with highlighted element", type = "image/png")
  public byte[] takeScreenshotWithHighlight(WebElement we) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String originalStyle = we.getAttribute("style");
    try {
      LOGGER.info("Taking screenshot of the page with web element '{}' highlighted", we);
      js.executeScript("arguments[0].style.border='4px solid red';", we);
      byte[] images = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      if (images.length > 0) {
        LOGGER.info("Took screenshot of the page with web element '{}' highlighted successfully",
            we);
        return images;
      }
    } catch (Exception e) {
      LOGGER.error(
          "Failed to take screenshot of the page with web element '{}' highlighted. Root cause: {}",
          we, e.getMessage());
    } finally {
      js.executeScript("arguments[0].setAttribute('style', arguments[1]);", we, originalStyle);
    }
    return null;
  }

  public void refresh() {
    try {
      LOGGER.info("Refreshing the browser");
      driver.navigate().refresh();
      LOGGER.info("The browser is refreshed successfully");
    } catch (Exception e) {
      LOGGER.error("Failed to refresh the browser. Root cause: {}", e.getMessage());
    }
  }

  public void forward() {
    try {
      LOGGER.info("Forwarding the browser");
      driver.navigate().forward();
      LOGGER.info("The browser is forwarded successfully");
    } catch (Exception e) {
      LOGGER.error("Failed to forward the browser. Root cause: {}", e.getMessage());
    }
  }

  public void back() {
    try {
      LOGGER.info("Backing the browser");
      driver.navigate().back();
      LOGGER.info("The browser is backed successfully");
    } catch (Exception e) {
      LOGGER.error("Failed to back the browser. Root cause: {}", e.getMessage());
    }
  }

  public void navigateTo(String url) {
    try {
      LOGGER.info("Navigating to url '{}'", url);
      driver.navigate().to(url);
      LOGGER.info("Navigated to url '{}' successfully", url);
    } catch (Exception e) {
      LOGGER.error("Failed to navigate to url. Root cause: {}", e.getMessage());
    }
  }

  public void maximizeWindow() {
    try {
      LOGGER.info("Maximizing the window");
      driver.manage().window().maximize();
      LOGGER.info("Maximized the window successfully");
    } catch (Exception e) {
      LOGGER.error("Failed to maximize the window. Root cause: {}", e.getMessage());
    }
  }

  public void switchToWindowByTitle(String title) {
    try {
      LOGGER.info("Switching to window with title '{}'", title);
      for (String windowHandle : driver.getWindowHandles()) {
        driver.switchTo().window(windowHandle);
        if (driver.getTitle().equals(title)) {
          LOGGER.info("Switched to window with title '{}' successfully", title);
          return;
        }
      }
      LOGGER.error("Failed to find window with title '{}'", title);
    } catch (Exception e) {
      LOGGER.error("Failed to switch to window with title '{}'. Root cause: {}", title,
          e.getMessage());
    }
  }

  public void switchToWindowByUrl(String url) {
    try {
      LOGGER.info("Switching to window with url '{}'", url);
      for (String windowHandle : driver.getWindowHandles()) {
        driver.switchTo().window(windowHandle);
        if (driver.getCurrentUrl().equals(url)) {
          LOGGER.info("Switched to window with url '{}' successfully", url);
          return;
        }
      }
      LOGGER.error("Failed to find window with url '{}'", url);
    } catch (Exception e) {
      LOGGER.error("Failed to switch to window with url '{}'. Root cause: {}", url, e.getMessage());
    }
  }

  public void switchToWindowByIndex(int index) {
    try {
      LOGGER.info("Switching to window at index '{}'", index);
      List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
      String windowHandle = windowHandles.get(index);
      driver.switchTo().window(windowHandle);
      LOGGER.info("Switched to window at index '{}' successfully", index);
    } catch (Exception e) {
      LOGGER.error("Failed to switch to window at index '{}'. Root cause: {}", index,
          e.getMessage());
    }
  }

  public void closeWindowByUrl(String url) {
    try {
      LOGGER.info("Closing window with url '{}'", url);
      for (String windowHandle : driver.getWindowHandles()) {
        driver.switchTo().window(windowHandle);
        if (driver.getCurrentUrl().equals(url)) {
          driver.close();
          LOGGER.info("Closed window with url '{}' successfully", url);
          return;
        }
      }
      LOGGER.error("Failed to find window with url '{}'", url);
    } catch (Exception e) {
      LOGGER.error("Failed to close window with url '{}'. Root cause: {}", url, e.getMessage());
    }
  }

  public void closeWindowByTitle(String title) {
    try {
      LOGGER.info("Closing window with title '{}'", title);
      for (String windowHandle : driver.getWindowHandles()) {
        driver.switchTo().window(windowHandle);
        if (driver.getTitle().equals(title)) {
          driver.close();
          LOGGER.info("Closed window with title '{}' successfully", title);
          return;
        }
      }
      LOGGER.error("Failed to find window with title '{}'", title);
    } catch (Exception e) {
      LOGGER.error("Failed to close window with title '{}'. Root cause: {}", title, e.getMessage());
    }
  }

  public void closeWindowByIndex(int index) {
    try {
      LOGGER.info("Closing window at index '{}'", index);
      List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
      String windowHandle = windowHandles.get(index);
      driver.switchTo().window(windowHandle);
      driver.close();
      LOGGER.info("Closed window at index '{}' successfully", index);
    } catch (Exception e) {
      LOGGER.error("Failed to close window at index '{}'. Root cause: {}", index, e.getMessage());
    }
  }

  // //button[@id='submit'] -- use xpath as default
  // xpath://button[@id='submit'], css:button[id='submit'], id:submit, ...
  private By findBy(String locator) {
    String prefix = StringUtils.substringBefore(locator, ":");
    String locatorValue = StringUtils.substringAfter(locator, ":");
    switch (prefix.toLowerCase()) {
      case "css":
        return By.cssSelector(locatorValue);
      case "xpath":
        return By.xpath(locatorValue);
      case "id":
        return By.id(locatorValue);
      case "name":
        return By.name(locatorValue);
      case "class":
        return By.className(locatorValue);
      case "link":
        return By.linkText(locatorValue);
      case "partial link":
        return By.partialLinkText(locatorValue);
      default:
        return By.xpath(locator);
    }
  }

  public WebElement findWebElement(String locator, int... timeout) {
//    long startTime = 0;
//    long endTime = 0;
//    double totalTime = 0;
    int waitTime = timeout.length > 0 ? timeout[0] : DEFAULT_TIMEOUT;
    try {
      LOGGER.info("Finding web element located by '{}'", locator);
//      startTime = System.currentTimeMillis();
//      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//      Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30))
//          .pollingEvery(Duration.ofSeconds(5))
//          .ignoring(NoSuchElementException.class);
////      WebElement we = driver.findElement(findBy(locator));
//      WebElement we = wait.until(new Function<WebDriver, WebElement>() {
//        @Override
//        public WebElement apply(WebDriver webDriver) {
//          return driver.findElement(findBy(locator));
//        }
//      });
      Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
      WebElement we = wait.until(ExpectedConditions.presenceOfElementLocated(findBy(locator)));
//      endTime = System.currentTimeMillis();
      if (we != null) {
//        totalTime = (double) (endTime - startTime) / 1000;
//        LOGGER.info("Total time '{}' second(s)", totalTime);
        LOGGER.info("Found web element located by '{}'", locator);
        return we;
      }
    } catch (Exception e) {
//      endTime = System.currentTimeMillis();
//      totalTime = (double) (endTime - startTime) / 1000;
      LOGGER.error("Failed to find web element. Root cause: {}", e.getMessage());
    }
//    LOGGER.info("Total time '{}' second(s)", totalTime);
    return null;
  }

  public List<WebElement> findWebElements(String locator, int... timeout) {
    int waitTime = timeout.length > 0 ? timeout[0] : DEFAULT_TIMEOUT;
    try {
      LOGGER.info("Finding web elements located by '{}'", locator);
      Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
      List<WebElement> wes = wait.until(
          ExpectedConditions.presenceOfAllElementsLocatedBy(findBy(locator)));
      LOGGER.info("Found {} web elements located by '{}'", wes.size(), locator);
      return wes;
    } catch (Exception e) {
      LOGGER.error("Failed to find web elements located by '{}'. Root cause: {}", locator,
          e.getMessage());
    }
    return null;
  }

  public void inputText(String locator, String text) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Input text '{}' into web element '{}'", text, we);
      we.clear();
      we.sendKeys(text);
      LOGGER.info("Inputted text '{}' into web element '{}' successfully", text, we);
    } catch (Exception e) {
      LOGGER.error("Failed to input text '{}' into web element '{}'. Root cause: {}", text, we,
          e.getMessage());
    }
  }

  public void inputText(WebElement we, String text) {
    try {
      LOGGER.info("Input text '{}' into web element '{}'", text, we);
      we.clear();
      we.sendKeys(text);
      LOGGER.info("Inputted text '{}' into web element '{}' successfully", text, we);
    } catch (Exception e) {
      LOGGER.error("Failed to input text '{}' into web element '{}'. Root cause: {}", text, we,
          e.getMessage());
    }
  }

  public void clearText(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Clearing text into web element '{}'", we);
      we.clear();
      LOGGER.info("Text into web element '{}' is cleared successfully", we);
    } catch (Exception e) {
      LOGGER.error("Failed to clear text into web element '{}'. Root cause: {}", we,
          e.getMessage());
    }
  }

  public void delayInSeconds(int seconds) {
    try {
      Thread.sleep(seconds * 1000L);
      LOGGER.info("Delayed '{}' second(s) successfully", seconds);
    } catch (InterruptedException e) {
      LOGGER.error("Delay '{}' second(s) interrupted. Root cause: {}", seconds, e.getMessage());
    }
  }

  public void delayInMilliSeconds(int milliseconds) {
    try {
      Thread.sleep(milliseconds);
      LOGGER.info("Delayed '{}' millisecond(s) successfully", milliseconds);
    } catch (InterruptedException e) {
      LOGGER.error("Delay '{}' millisecond(s) interrupted. Root cause: {}", milliseconds,
          e.getMessage());
    }
  }

  public void click(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Clicking on web element '{}'", we);
      we.click();
      LOGGER.info("Clicked on web element '{}' successfully", we);
    } catch (Exception e) {
      LOGGER.error("Failed to click on web element '{}'. Root cause: {}", we, e.getMessage());
    }
  }

  public void click(WebElement we) {
    try {
      LOGGER.info("Clicking on web element '{}'", we);
      we.click();
      LOGGER.info("Clicked on web element '{}' successfully", we);
    } catch (Exception e) {
      LOGGER.error("Failed to click on web element '{}'. Root cause: {}", we, e.getMessage());
    }
  }

  public void doubleClick(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Double clicking on web element '{}'", we);
      Actions actions = new Actions(driver);
      actions.doubleClick(we).perform();
      LOGGER.info("Double clicked on web element '{}' successfully", we);
    } catch (Exception e) {
      LOGGER.error("Failed to double click on web element '{}'. Root cause: {}", we,
          e.getMessage());
    }
  }

  public void doubleClick(WebElement we) {
    try {
      LOGGER.info("Double clicking on web element '{}'", we);
      new Actions(driver).doubleClick(we).perform();
      LOGGER.info("Double clicked on web element '{}' successfully", we);
    } catch (Exception e) {
      LOGGER.error("Failed to double click on web element '{}'. Root cause: {}", we,
          e.getMessage());
    }
  }

  public void mouseOver(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Mouse over on web element '{}'", we);
      Actions actions = new Actions(driver);
      actions.moveToElement(we).perform();
      LOGGER.info("Mouse over on web element '{}' successfully", we);
    } catch (Exception e) {
      LOGGER.error("Failed to mouse over on web element '{}'. Root cause: {}", we, e.getMessage());
    }
  }

  public void mouseOver(WebElement we) {
    try {
      LOGGER.info("Mouse over on web element '{}'", we);
      new Actions(driver).moveToElement(we).perform();
      LOGGER.info("Mouse over on web element '{}' successfully", we);
    } catch (Exception e) {
      LOGGER.error("Failed to mouse over on web element '{}'. Root cause: {}", we, e.getMessage());
    }
  }

  public void rightClick(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Right clicking on web element '{}'", we);
      Actions actions = new Actions(driver);
      actions.contextClick(we).perform();
      LOGGER.info("Right clicked on web element '{}' successfully", we);
    } catch (Exception e) {
      LOGGER.error("Failed to right click on web element '{}'. Root cause: {}", we, e.getMessage());
    }
  }

  public void rightClick(WebElement we) {
    try {
      LOGGER.info("Right clicking on web element '{}'", we);
      new Actions(driver).contextClick(we).perform();
      LOGGER.info("Right clicked on web element '{}' successfully", we);
    } catch (Exception e) {
      LOGGER.error("Failed to right click on web element '{}'. Root cause: {}", we, e.getMessage());
    }
  }

  public void dragAndDrop(String sourceLocator, String targetLocator) {
    WebElement source = findWebElement(sourceLocator);
    WebElement target = findWebElement(targetLocator);
    try {
      LOGGER.info("Dragging web element '{}' and dropping onto web element '{}'", source, target);
//      new Actions(driver).clickAndHold(source)
//          .moveByOffset(1, 1)
//          .pause(Duration.ofMillis(200))
//          .moveToElement(target)
//          .pause(Duration.ofMillis(200))
//          .moveByOffset(1, 1)
//          .pause(Duration.ofMillis(200))
//          .release()
//          .perform();
      new Actions(driver).dragAndDrop(source, target).perform();
      LOGGER.info("Dragged web element '{}' and dropped onto web element '{}' successfully", source,
          target);
    } catch (Exception e) {
      LOGGER.error("Failed to drag web element '{}' and drop onto web element '{}'. Root cause: {}",
          source, target, e.getMessage());
    }
  }

  public void dragAndDrop(WebElement source, WebElement target) {
    try {
      LOGGER.info("Dragging web element '{}' and dropping onto web element '{}'", source, target);
//      new Actions(driver).clickAndHold(source)
//          .moveByOffset(1, 1)
//          .pause(Duration.ofMillis(200))
//          .moveToElement(target)
//          .pause(Duration.ofMillis(200))
//          .moveByOffset(1, 1)
//          .pause(Duration.ofMillis(200))
//          .release()
//          .perform();
      new Actions(driver).dragAndDrop(source, target).perform();
      LOGGER.info("Dragged web element '{}' and dropped onto web element '{}' successfully", source,
          target);
    } catch (Exception e) {
      LOGGER.error("Failed to drag web element '{}' and drop onto web element '{}'. Root cause: {}",
          source, target, e.getMessage());
    }
  }

  public void clickOffset(String locator, int xOffset, int yOffset) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Clicking on web element '{}' at offset ({}, {})", we, xOffset, yOffset);
      new Actions(driver).moveToElement(we, xOffset, yOffset).click().perform();
      LOGGER.info("Clicked on web element '{}' at offset ({}, {}) successfully", we, xOffset,
          yOffset);
    } catch (Exception e) {
      LOGGER.error("Failed to click on web element '{}' at offset ({}, {}). Root cause: {}", we,
          xOffset, yOffset, e.getMessage());
    }
  }

  public void clickOffset(WebElement we, int xOffset, int yOffset) {
    try {
      LOGGER.info("Clicking on web element '{}' at offset ({}, {})", we, xOffset, yOffset);
      new Actions(driver).moveToElement(we, xOffset, yOffset).click().perform();
      LOGGER.info("Clicked on web element '{}' at offset ({}, {}) successfully", we, xOffset,
          yOffset);
    } catch (Exception e) {
      LOGGER.error("Failed to click on web element '{}' at offset ({}, {}). Root cause: {}", we,
          xOffset, yOffset, e.getMessage());
    }
  }

  public void clickByJS(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Clicking on web element '{}' using javascript executor", we);
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", we);
      LOGGER.info("Clicked on web element '{}' using javascript executor successfully", we);
    } catch (Exception e) {
      LOGGER.error("Failed to click on web element '{}' using javascript executor. Root cause: {}",
          we, e.getMessage());
    }
  }

  public void clickByJS(WebElement we) {
    try {
      LOGGER.info("Clicking on web element '{}' using javascript executor", we);
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", we);
      LOGGER.info("Clicked on web element '{}' using javascript executor successfully", we);
    } catch (Exception e) {
      LOGGER.error("Failed to click on web element '{}' using javascript executor. Root cause: {}",
          we, e.getMessage());
    }
  }

  public Object executeJavascript(String script, Object... args) {
    try {
      LOGGER.info("Executing javascript '{}'", script);
      Object result = ((JavascriptExecutor) driver).executeScript(script, args);
      LOGGER.info("Executed javascript '{}' successfully", script);
      return result;
    } catch (Exception e) {
      LOGGER.error("Failed to execute javascript '{}'. Root cause: {}", script, e.getMessage());
    }
    return null;
  }

  public void scrollToElement(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Scrolling to web element '{}' using javascript executor", we);
      ((JavascriptExecutor) driver).executeScript(
          "arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'center'});",
          we);
      LOGGER.info("Scrolled to web element '{}' using javascript executor successfully", we);
    } catch (Exception e) {
      LOGGER.error("Failed to scroll to web element '{}' using javascript executor. Root cause: {}",
          we, e.getMessage());
    }
  }

  public void scrollToElement(WebElement we) {
    try {
      LOGGER.info("Scrolling to web element '{}' using javascript executor", we);
      ((JavascriptExecutor) driver).executeScript(
          "arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'center'});",
          we);
      LOGGER.info("Scrolled to web element '{}' using javascript executor successfully", we);
    } catch (Exception e) {
      LOGGER.error("Failed to scroll to web element '{}' using javascript executor. Root cause: {}",
          we, e.getMessage());
    }
  }

  public void scrollToElementAtPageCenter(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Scrolling web element '{}' to the center of the page using javascript executor",
          we);
      ((JavascriptExecutor) driver).executeScript(
          "var rect = arguments[0].getBoundingClientRect();"
              + "var scrollTop = window.pageYOffset + rect.top - (window.innerHeight / 2) + (rect.height / 2);"
              + "var scrollLeft = window.pageXOffset + rect.left - (window.innerWidth / 2) + (rect.width / 2);"
              + "window.scrollTo(scrollLeft, scrollTop);", we);
      LOGGER.info(
          "Scrolled web element '{}' to the center of the page using javascript executor successfully",
          we);
    } catch (Exception e) {
      LOGGER.error(
          "Failed to scroll web element '{}' to the center of the page using javascript executor. Root cause: {}",
          we, e.getMessage());
    }
  }

  public void scrollToElementAtPageCenter(WebElement we) {
    try {
      LOGGER.info("Scrolling web element '{}' to the center of the page using javascript executor",
          we);
      ((JavascriptExecutor) driver).executeScript(
          "var rect = arguments[0].getBoundingClientRect();"
              + "var scrollTop = window.pageYOffset + rect.top - (window.innerHeight / 2) + (rect.height / 2);"
              + "var scrollLeft = window.pageXOffset + rect.left - (window.innerWidth / 2) + (rect.width / 2);"
              + "window.scrollTo(scrollLeft, scrollTop);", we);
      LOGGER.info(
          "Scrolled web element '{}' to the center of the page using javascript executor successfully",
          we);
    } catch (Exception e) {
      LOGGER.error(
          "Failed to scroll web element '{}' to the center of the page using javascript executor. Root cause: {}",
          we, e.getMessage());
    }
  }

  public void copyText(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Copying text into web element '{}'", we);
      String os = System.getProperty("os.name");
      if (os.contains("Windows") || os.contains("Linux")) {
        we.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        we.sendKeys(Keys.chord(Keys.CONTROL, "c"));
      } else {
        we.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        we.sendKeys(Keys.chord(Keys.COMMAND, "c"));
      }

      LOGGER.info("Copied text into web element '{}' successfully", we);
    } catch (Exception e) {
      LOGGER.error("Failed to copy text into web element '{}'. Root cause: {}", we, e.getMessage());
    }
  }

  public void pasteText(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Pasting text into web element '{}'", we);
      String os = System.getProperty("os.name");
      if (os.contains("Windows") || os.contains("Linux")) {
        we.sendKeys(Keys.chord(Keys.CONTROL, "v"));
      } else {
        we.sendKeys(Keys.chord(Keys.COMMAND, "v"));
      }
      LOGGER.info("Pasted text into web element '{}' successfully", we);
    } catch (Exception e) {
      LOGGER.error("Failed to paste text into web element '{}'. Root cause: {}", we,
          e.getMessage());
    }
  }

  public boolean verifyElementVisible(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Verifying element '{}' visible", we);
      boolean visible = we.isDisplayed();
      if (visible) {
        LOGGER.info("Element '{}' is visible", we);
        return true;
      } else {
        LOGGER.error("Element '{}' is still invisible", we);
      }
    } catch (Exception e) {
      LOGGER.error("Failed to verify element '{}' visible. Root cause: {}", we, e.getMessage());
    }
    return false;
  }

  public boolean verifyElementInvisible(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Verifying element '{}' invisible ", we);
      boolean visible = we.isDisplayed();
      if (!visible) {
        LOGGER.info("Element '{}' is invisible", we);
        return true;
      } else {
        LOGGER.error("Element '{}' is still visible", we);
      }
    } catch (Exception e) {
      LOGGER.error("Failed to verify element '{}' invisible. Root cause: {}", we, e.getMessage());
    }
    return false;
  }

  public boolean verifyElementClickable(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Verifying element '{}' is clickable", we);
      boolean isEnable = we.isEnabled();
      if (isEnable) {
        LOGGER.info("Element '{}' is clickable", we);
        return true;
      } else {
        LOGGER.error("Element '{}' is not clickable", we);
      }
    } catch (Exception e) {
      LOGGER.error("Failed to verify element '{}' clickable. Root cause: {}", we, e.getMessage());
    }
    return false;
  }

  public boolean verifyElementNotClickable(String locator, String locator2) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Verifying element '{}' is not clickable", we);
      boolean isEnable = we.isEnabled();
      if (!isEnable) {
        LOGGER.info("Element '{}' is not clickable", we);
        return true;
      } else {
        LOGGER.error("Element '{}' is still clickable", we);
      }
    } catch (Exception e) {
      LOGGER.error("Failed to verify element '{}' is not clickable. Root cause: {}", we,
          e.getMessage());
    }
    return false;
  }

  public boolean verifyElementSelected(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Verifying element '{}' is selected", we);
      boolean isSelected = we.isSelected();
      if (isSelected) {
        LOGGER.info("Element '{}' is selected", we);
        return true;
      } else {
        LOGGER.error("Element '{}' is still not selected", we);
      }
    } catch (Exception e) {
      LOGGER.error("Failed to verify element '{}' is selected. Root cause: {}", we, e.getMessage());
    }
    return false;
  }

  public boolean verifyElementNotSelected(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Verifying element '{}' is not selected", we);
      boolean isSelected = we.isSelected();
      if (!isSelected) {
        LOGGER.info("Element '{}' is not selected", we);
        return true;
      } else {
        LOGGER.error("Element '{}' is still selected", we);
      }
    } catch (Exception e) {
      LOGGER.error("Failed to verify element '{}' is not selected. Root cause: {}", we,
          e.getMessage());
    }
    return false;
  }

  public boolean verifyElementText(String locator, String expectedText) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Verifying text of web element located by '{}'", we);
      String actualText = we.getText();
      if (actualText.equals(expectedText)) {
        LOGGER.info("Text of web element located by '{}' is '{}'", we, expectedText);
        return true;
      }
      LOGGER.error("Actual text of web element located by '{}' is '{}', not '{}'", we, actualText,
          expectedText);
    } catch (Exception e) {
      LOGGER.error("Failed to verify text of web element located by '{}'. Root cause: {}", we,
          e.getMessage());
    }
    return false;
  }

  public boolean verifyElementText(WebElement we, String expectedText) {
    try {
      LOGGER.info("Verifying text of web element located by '{}'", we);
      String actualText = we.getText();
      if (actualText.equals(expectedText)) {
        LOGGER.info("Text of web element located by '{}' is '{}'", we, expectedText);
        return true;
      }
      LOGGER.error("Actual text of web element located by '{}' is '{}', not '{}'", we, actualText,
          expectedText);
    } catch (Exception e) {
      LOGGER.error("Failed to verify text of web element located by '{}'. Root cause: {}", we,
          e.getMessage());
    }
    return false;
  }

  public boolean verifyElementContainsText(String locator, String expectedText) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Verifying text of web element located by '{}' contains '{}'", we, expectedText);
      String actualText = we.getText();
      if (actualText.contains(expectedText)) {
        LOGGER.info("Text of web element located by '{}' contains '{}'", we, expectedText);
        return true;
      }
      LOGGER.error("Actual text of web element located by '{}' is '{}', does not contain '{}'", we,
          actualText, expectedText);
    } catch (Exception e) {
      LOGGER.error(
          "Failed to verify text of web element located by '{}' contains text. Root cause: {}",
          we, e.getMessage());
    }
    return false;
  }

  public void submit(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Submit web element located by '{}'", we);
      we.submit();
      LOGGER.info("Submitted web element located by '{}' successfully", we);
    } catch (Exception e) {
      LOGGER.error("Failed to submit web element located by '{}'. Root cause: {}", we,
          e.getMessage());
    }
  }

  public String getText(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Getting text of web element located by '{}'", we);
      String text = we.getText();
      if (!text.isEmpty() || text != null) {
        LOGGER.info("Text of web element located by '{}' is empty", we);
        return text;
      }
    } catch (Exception e) {
      LOGGER.error("Failed to get text of  web element located by '{}'. Root cause: {}", we,
          e.getMessage());
    }
    return null;
  }

  public String getTagName(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Getting tag name of web element located by '{}'", we);
      String tagName = we.getTagName();
      if (!tagName.isEmpty()) {
        LOGGER.info("Tag name of web element located by '{}' is empty", we);
        return tagName;
      }
    } catch (Exception e) {
      LOGGER.error("Failed to get tag name of web element located by '{}'. Root cause: {}", we,
          e.getMessage());
    }
    return null;
  }

  public String getAttributeValue(String locator, String attributeName) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Getting attribute value of web element located by '{}'", we);
      String attributeValue = we.getAttribute(attributeName);
      LOGGER.info("Attribute value of attribute '{}' of web element located by '{}' is '{}'",
          attributeName, we, attributeValue);
      return attributeValue;
    } catch (Exception e) {
      LOGGER.error(
          "Failed to get attribute value of attribute '{}' of web element located by '{}'. Root cause: {}",
          attributeName, we, e.getMessage());
    }
    return null;
  }

  public boolean verifyElementAttributeValue(String locator, String attributeName,
      String expectedValue) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Verifying attribute value of attribute name '{}' of web element located by '{}'",
          attributeName, we);
      String attributeValue = we.getAttribute(attributeName);
      if (attributeValue.equals(expectedValue)) {
        LOGGER.info(
            "Actual attribute value '{}' and Expected attribute value '{}' of attribute name '{}' of web element located by '{}' are the same",
            attributeValue, expectedValue, attributeName, we);
        return true;
      }
      LOGGER.error(
          "Actual attribute value '{}' and Expected attribute value '{}' of attribute name '{}' of web element located by '{}' are not the same",
          attributeValue, expectedValue, attributeName, we);
    } catch (Exception e) {
      LOGGER.error(
          "Failed to verify attribute value of attribute name '{}' of web element located by '{}'. Root cause: {}",
          attributeName, locator, e.getMessage());
    }
    return false;
  }

  public String getCssValue(String locator, String cssName) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Getting css value of css name '{}' of web element located by '{}'", cssName, we);
      String cssValue = we.getCssValue(cssName);
      LOGGER.info("Css value of css name '{}' of web element located by '{}' is '{}'", cssName, we,
          cssValue);
      return cssValue;
    } catch (Exception e) {
      LOGGER.error(
          "Failed to get css value of css name '{}' of web element located by '{}'. Root cause: {}",
          cssName, we, e.getMessage());
    }
    return null;
  }

  public boolean verifyCssValue(String locator, String cssName, String expectedValue) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Verifying css value of css '{}' of web element located by '{}'", cssName, we);
      String cssValue = we.getCssValue(cssName);
      if (cssValue.equals(expectedValue)) {
        LOGGER.info(
            "Actual css value '{}' and Expected css value '{}' of css '{}' of web element located by '{}' are the same'",
            cssValue, expectedValue, cssName, locator);
        return true;
      }
      LOGGER.error(
          "Actual css value '{}' and Expected css value '{}' of css '{}' of web element located by '{}' are not the same'",
          cssValue, expectedValue, cssName, locator);
    } catch (Exception e) {
      LOGGER.error(
          "Failed to verify css value of css '{}' of web element located by '{}'. Root cause: {}'",
          cssName, locator, e.getMessage());
    }
    return false;
  }

  public int getElementWidth(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Getting width of web element located by '{}'", we);
      int width = we.getSize().getWidth();
      LOGGER.info("Width of web element located by '{}' is '{}'", we, width);
      return width;
    } catch (Exception e) {
      LOGGER.info("Failed to get width of web element located by '{}'. Root cause: {}", we,
          e.getMessage());
    }
    return -1;
  }

  public int getElementHeight(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Getting height of web element located by '{}'", we);
      int height = we.getSize().getHeight();
      LOGGER.info("Height of web element located by '{}' is '{}'", we, height);
    } catch (Exception e) {
      LOGGER.error("Failed to get height of web element located by '{}'. Root cause: {}", we,
          e.getMessage());
    }
    return -1;
  }

  public int getElementLeftPosition(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Getting left position of web element located by '{}'", we);
      int leftPosition = we.getLocation().getX();
      LOGGER.info("Left position of web element located by '{}' is '{}'", we, leftPosition);
      return leftPosition;
    } catch (Exception e) {
      LOGGER.error("Failed to get left position of web element located by '{}'. Root cause: {}", we,
          e.getMessage());
    }
    return -1;
  }

  public int getElementTopPosition(String locator) {
    WebElement we = findWebElement(locator);
    try {
      LOGGER.info("Getting top position of web element located by '{}'", we);
      int topPosition = we.getLocation().getY();
      LOGGER.info("Top position of web element located by '{}' is '{}'", we, topPosition);
      return topPosition;
    } catch (Exception e) {
      LOGGER.error("Failed to get top position of web element located by '{}'. Root cause: {}'", we,
          e.getMessage());
    }
    return -1;
  }

  public boolean waitForCssValue(String locator, String cssName, String expectedValue,
      int... timeout) {
    WebElement we = findWebElement(locator, timeout);
    int waitTime = timeout.length > 0 ? timeout[0] : DEFAULT_TIMEOUT;
    try {
      LOGGER.info("Waiting css value of css '{}' of web element located by '{}' to become '{}'",
          cssName, we, expectedValue);
      Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
      boolean found = wait.until(d -> expectedValue.equals(we.getCssValue(cssName)));
      if (found) {
        LOGGER.info(
            "Css value of css '{}' of web element located by '{}' is '{}' within '{}' second(s)",
            cssName, we, expectedValue, waitTime);
        return true;
      }
      LOGGER.error(
          "Css value of css '{}' of web element located by '{}' is not '{}' after '{}' second(s)",
          cssName, we, expectedValue, waitTime);
    } catch (Exception e) {
      LOGGER.error(
          "Failed to wait css value of css '{}' of web element located by '{}' to become '{}'. Root cause: {}",
          cssName, we, expectedValue, e.getMessage());
    }
    return false;
  }

  public boolean waitForElementPresent(String locator, int... timeOut) {
    int waitTime = timeOut.length > 0 ? timeOut[0] : DEFAULT_TIMEOUT;
    try {
      LOGGER.info("Waiting web element located by '{}' to be present within '{}' second(s)",
          locator, waitTime);
      Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
      WebElement we = wait.until(ExpectedConditions.presenceOfElementLocated(findBy(locator)));
      if (we != null) {
        LOGGER.info("Web element located by '{}' is present within '{}' second(s)", we, waitTime);
        return true;
      }
      LOGGER.error("Web element located by '{}' is not present within '{}' second(s)", we,
          waitTime);
    } catch (Exception e) {
      LOGGER.error("Failed to wait web element located by '{}' to be present. Root cause: {}",
          locator, e.getMessage());
    }
    return false;
  }

  public boolean waitForElementNotPresent(String locator, int... timeOut) {
    int waitTime = timeOut.length > 0 ? timeOut[0] : DEFAULT_TIMEOUT;
    try {
      LOGGER.info("Waiting web element located by '{}' not present within '{}' second(s)",
          locator, waitTime);
      Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
      boolean notPresent = wait.until(d -> d.findElements(findBy(locator)).isEmpty());
      if (notPresent) {
        LOGGER.info("Web element located by '{}' is not present within '{}' second(s)", locator,
            waitTime);
        return true;
      }
      LOGGER.error("Web element located by '{}' is present within '{}' second(s)", locator,
          waitTime);
    } catch (Exception e) {
      LOGGER.error("Failed to wait web element located by '{}' not present. Root cause: {}",
          locator, e.getMessage());
    }
    return false;
  }

  public boolean waitForElementVisible(String locator, int... timeOut) {
    int waitTime = timeOut.length > 0 ? timeOut[0] : DEFAULT_TIMEOUT;
    try {
      LOGGER.info("Waiting web element located by '{}' to be visible within '{}' second(s)",
          locator, waitTime);
      Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
      WebElement we = wait.until(ExpectedConditions.visibilityOfElementLocated(findBy(locator)));
      if (we != null) {
        LOGGER.info("Web element located by '{}' is visible within '{}' second(s)", we, waitTime);
        return true;
      }
      LOGGER.error("Web element located by '{}' is not visible within '{}' second(s)", we,
          waitTime);
    } catch (Exception e) {
      LOGGER.error("Failed to wait web element located by '{}' to be visible. Root cause: {}",
          locator, e.getMessage());
    }
    return false;
  }

  public boolean waitForElementInvisible(String locator, int... timeOut) {
    int waitTime = timeOut.length > 0 ? timeOut[0] : DEFAULT_TIMEOUT;
    try {
      LOGGER.info("Waiting web element located by '{}' not invisible within '{}' second(s)",
          locator, waitTime);
      Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
      boolean invisible = wait.until(
          ExpectedConditions.invisibilityOfElementLocated(findBy(locator)));
      if (invisible) {
        LOGGER.info("Web element located by '{}' is not invisible within '{}' second(s)", locator,
            waitTime);
        return true;
      }
      LOGGER.error("Web element located by '{}' is invisible within '{}' second(s)", locator,
          waitTime);
    } catch (Exception e) {
      LOGGER.error("Failed to wait web element located by '{}' not invisible. Root cause: {}",
          locator, e.getMessage());
    }
    return false;
  }

  public void selectOptionByIndex(String locator, int index, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Selection option of web element located by '{}' by index '{}'", locator, index);
      Select select = new Select(we);
      select.selectByIndex(index);
      LOGGER.info("Selected option of web element located by '{}' by index '{}' successfully",
          locator, index);
    } catch (Exception e) {
      LOGGER.error(
          "Failed to select option of web element located by '{}' by index '{}'. Root cause: {}",
          locator, index, e.getMessage());
    }
  }

  public void selectOptionByValue(String locator, String value, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Selection option of web element located by '{}' by value '{}'", locator, value);
      Select select = new Select(we);
      select.selectByValue(value);
      LOGGER.info("Selected option of web element located by '{}' by value '{}' successfully",
          locator, value);
    } catch (Exception e) {
      LOGGER.error(
          "Failed to select option of web element located by '{}' by value '{}'. Root cause: {}",
          locator, value, e.getMessage());
    }
  }

  public void selectOptionByText(String locator, String text, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Selection option of web element located by '{}' by text '{}'", locator, text);
      Select select = new Select(we);
      select.selectByVisibleText(text);
      LOGGER.info("Selected option of web element located by '{}' by text '{}' successfully",
          locator, text);
    } catch (Exception e) {
      LOGGER.error(
          "Failed to select option of web element located by '{}' by text '{}'. Root cause: {}",
          locator, text, e.getMessage());
    }
  }

  public void selectAllOptions(String locator, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Selecting all options of web element located by '{}'", locator);
      Select select = new Select(we);
      if (!select.isMultiple()) {
        LOGGER.error("Web element located by '{}' does not support multiple selections", locator);
        return;
      }
      for (int i = 0; i < select.getOptions().size(); i++) {
        select.selectByIndex(i);
      }
      LOGGER.info("Selected all options of web element located by '{}' successfully", locator);
    } catch (Exception e) {
      LOGGER.error("Failed to select all options of web element located by '{}'. Root cause: {}",
          locator, e.getMessage());
    }
  }

  public void deselectOptionByIndex(String locator, int index, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Deselecting option of web element located by '{}' by index '{}'", locator,
          index);
      Select select = new Select(we);
      if (!select.isMultiple()) {
        LOGGER.error("Web element located by '{}' does not support multiple selections", locator);
        return;
      }
      select.deselectByIndex(index);
      LOGGER.info("Deselected option of web element located by '{}' by index '{}' successfully",
          locator,
          index);
    } catch (Exception e) {
      LOGGER.error(
          "Failed to deselect option of web element located by '{}' by index '{}'. Root cause: {}",
          locator, index, e.getMessage());
    }
  }

  public void deselectOptionByValue(String locator, String value, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Deselecting option of web element located by '{}' by value '{}'", locator,
          value);
      Select select = new Select(we);
      if (!select.isMultiple()) {
        LOGGER.error("Web element located by '{}' does not support multiple selections", locator);
        return;
      }
      select.deselectByValue(value);
      LOGGER.info("Deselected option of web element located by '{}' by value '{}' successfully",
          locator,
          value);
    } catch (Exception e) {
      LOGGER.error(
          "Failed to deselect option of web element located by '{}' by value '{}'. Root cause: {}",
          locator, value, e.getMessage());
    }
  }

  public void deselectOptionByText(String locator, String text, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Deselecting option of web element located by '{}' by text '{}'", locator, text);
      Select select = new Select(we);
      if (!select.isMultiple()) {
        LOGGER.error("Web element located by '{}' does not support multiple selections", locator);
        return;
      }
      select.deselectByVisibleText(text);
      LOGGER.info("Deselected option of web element located by '{}' by text '{}' successfully",
          locator,
          text);
    } catch (Exception e) {
      LOGGER.error(
          "Failed to deselect option of web element located by '{}' by text '{}'. Root cause: {}",
          locator, text, e.getMessage());
    }
  }

  public void deselectAllOptions(String locator, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Deselecting all options of web element located by '{}'", locator);
      Select select = new Select(we);
      if (!select.isMultiple()) {
        LOGGER.error("Web element located by '{}' does not support multiple selections", locator);
        return;
      }
      select.deselectAll();
      LOGGER.info("Deselected all options of web element located by '{}' successfully", locator);
    } catch (Exception e) {
      LOGGER.error("Failed to deselect all options of web element located by '{}'. Root cause: {}",
          locator, e.getMessage());
    }
  }

  public boolean verifyOptionSelectedByIndex(String locator, int index, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Verifying option of web element located by '{}' at index '{}' is selected",
          locator,
          index);
      Select select = new Select(we);
      boolean isSelected = select.getOptions().get(index).isSelected();
      if (isSelected) {
        LOGGER.info("Option of web element located by '{}' at index '{}' is selected", locator,
            index);
        return true;
      } else {
        LOGGER.error("Option of web element located by '{}' at index '{}' is still not selected",
            locator,
            index);
      }
    } catch (Exception e) {
      LOGGER.error(
          "Failed to verify option of web element located by '{}' at index '{}' is selected. Root cause: {}",
          locator, index, e.getMessage());
    }
    return false;
  }

  public boolean verifyOptionSelectedByValue(String locator, String value, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Verifying option of web element located by '{}' with value '{}' is selected",
          locator,
          value);
      Select select = new Select(we);
      Optional<WebElement> option = select.getOptions().stream()
          .filter(o -> value.equals(o.getAttribute("value")))
          .findFirst();
      if (option.isPresent() && option.get().isSelected()) {
        LOGGER.info("Option of web element located by '{}' with value '{}' is selected", locator,
            value);
        return true;
      } else {
        LOGGER.error("Option of web element located by '{}' with value '{}' is still not selected",
            locator,
            value);
      }
    } catch (Exception e) {
      LOGGER.error(
          "Failed to verify option of web element located by '{}' with value '{}' is selected. Root cause: {}",
          locator, value, e.getMessage());
    }
    return false;
  }

  public boolean verifyOptionSelectedByText(String locator, String text, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Verifying option of web element located by '{}' with text '{}' is selected",
          locator,
          text);
      Select select = new Select(we);
      Optional<WebElement> option = select.getOptions().stream()
          .filter(o -> text.equals(o.getText()))
          .findFirst();
      if (option.isPresent() && option.get().isSelected()) {
        LOGGER.info("Option of web element located by '{}' with text '{}' is selected", locator,
            text);
        return true;
      } else {
        LOGGER.error("Option of web element located by '{}' with text '{}' is still not selected",
            locator,
            text);
      }
    } catch (Exception e) {
      LOGGER.error(
          "Failed to verify option of web element located by '{}' with text '{}' is selected. Root cause: {}",
          locator, text, e.getMessage());
    }
    return false;
  }

  public boolean verifyOptionNotSelectedByText(String locator, String text, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Verifying option of web element located by '{}' with text '{}' is not selected",
          locator,
          text);
      Select select = new Select(we);
      Optional<WebElement> option = select.getOptions().stream()
          .filter(o -> text.equals(o.getText()))
          .findFirst();
      if (option.isPresent() && !option.get().isSelected()) {
        LOGGER.info("Option of web element located by '{}' with text '{}' is not selected", locator,
            text);
        return true;
      } else {
        LOGGER.error("Option of web element located by '{}' with text '{}' is still selected",
            locator,
            text);
      }
    } catch (Exception e) {
      LOGGER.error(
          "Failed to verify option of web element located by '{}' with text '{}' is not selected. Root cause: {}",
          locator, text, e.getMessage());
    }
    return false;
  }

  public boolean verifyOptionNotSelectedByValue(String locator, String value, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Verifying option of web element located by '{}' with value '{}' is not selected",
          locator,
          value);
      Select select = new Select(we);
      Optional<WebElement> option = select.getOptions().stream()
          .filter(o -> value.equals(o.getAttribute("value")))
          .findFirst();
      if (option.isPresent() && !option.get().isSelected()) {
        LOGGER.info("Option of web element located by '{}' with value '{}' is not selected",
            locator, value);
        return true;
      } else {
        LOGGER.error("Option of web element located by '{}' with value '{}' is still selected",
            locator,
            value);
      }
    } catch (Exception e) {
      LOGGER.error(
          "Failed to verify option of web element located by '{}' with value '{}' is not selected. Root cause: {}",
          locator, value, e.getMessage());
    }
    return false;
  }

  public boolean verifyOptionNotSelectedByIndex(String locator, int index, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Verifying option of web element located by '{}' at index '{}' is not selected",
          locator,
          index);
      Select select = new Select(we);
      boolean isSelected = select.getOptions().get(index).isSelected();
      if (!isSelected) {
        LOGGER.info("Option of web element located by '{}' at index '{}' is not selected", locator,
            index);
        return true;
      } else {
        LOGGER.error("Option of web element located by '{}' at index '{}' is still selected",
            locator,
            index);
      }
    } catch (Exception e) {
      LOGGER.error(
          "Failed to verify option of web element located by '{}' at index '{}' is not selected. Root cause: {}",
          locator, index, e.getMessage());
    }
    return false;
  }

  public boolean verifyAllOptionsSelected(String locator, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Verifying all options of web element located by '{}' are selected", locator);
      Select select = new Select(we);
      if (!select.isMultiple()) {
        LOGGER.error("Web element located by '{}' does not support multiple selections", locator);
        return false;
      }
      boolean allSelected = select.getOptions().stream().allMatch(WebElement::isSelected);
      if (allSelected) {
        LOGGER.info("All options of web element located by '{}' are selected", locator);
        return true;
      } else {
        LOGGER.error("Not all options of web element located by '{}' are selected", locator);
      }
    } catch (Exception e) {
      LOGGER.error(
          "Failed to verify all options of web element located by '{}' are selected. Root cause: {}",
          locator, e.getMessage());
    }
    return false;
  }

  public boolean verifyAllOptionsNotSelected(String locator, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Verifying all options of web element located by '{}' are not selected", locator);
      Select select = new Select(we);
      if (!select.isMultiple()) {
        LOGGER.error("Web element located by '{}' does not support multiple selections", locator);
        return false;
      }
      boolean noneSelected = select.getOptions().stream().noneMatch(WebElement::isSelected);
      if (noneSelected) {
        LOGGER.info("All options of web element located by '{}' are not selected", locator);
        return true;
      } else {
        LOGGER.error("Some options of web element located by '{}' are still selected", locator);
      }
    } catch (Exception e) {
      LOGGER.error(
          "Failed to verify all options of web element located by '{}' are not selected. Root cause: {}",
          locator, e.getMessage());
    }
    return false;
  }

  public void acceptAlert(int... timeOut) {
    int waitTime = timeOut.length > 0 ? timeOut[0] : DEFAULT_TIMEOUT;
    try {
      LOGGER.info("Accepting alert");
      Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
      Alert alert = wait.until(ExpectedConditions.alertIsPresent());
      alert.accept();
      LOGGER.info("Accepted alert successfully");
    } catch (Exception e) {
      LOGGER.error("Failed to accept alert. Root cause: {}", e.getMessage());
    }
  }

  public void dismissAlert(int... timeOut) {
    int waitTime = timeOut.length > 0 ? timeOut[0] : DEFAULT_TIMEOUT;
    try {
      LOGGER.info("Dismissing alert");
      Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
      Alert alert = wait.until(ExpectedConditions.alertIsPresent());
      alert.dismiss();
      LOGGER.info("Dismissed alert successfully");
    } catch (Exception e) {
      LOGGER.error("Failed to dismiss alert. Root cause: {}", e.getMessage());
    }
  }

  public String getAlertText(int... timeOut) {
    int waitTime = timeOut.length > 0 ? timeOut[0] : DEFAULT_TIMEOUT;
    try {
      LOGGER.info("Getting text of alert");
      Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
      Alert alert = wait.until(ExpectedConditions.alertIsPresent());
      String text = alert.getText();
      LOGGER.info("Text of alert is '{}'", text);
      return text;
    } catch (Exception e) {
      LOGGER.error("Failed to get text of alert. Root cause: {}", e.getMessage());
    }
    return null;
  }

  public void sendTextToAlert(String text, int... timeOut) {
    int waitTime = timeOut.length > 0 ? timeOut[0] : DEFAULT_TIMEOUT;
    try {
      LOGGER.info("Sending text '{}' to alert", text);
      Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
      Alert alert = wait.until(ExpectedConditions.alertIsPresent());
      alert.sendKeys(text);
      LOGGER.info("Sent text '{}' to alert successfully", text);
    } catch (Exception e) {
      LOGGER.error("Failed to send text '{}' to alert. Root cause: {}", text, e.getMessage());
    }
  }

  public boolean verifyAlertPresent(int... timeOut) {
    int waitTime = timeOut.length > 0 ? timeOut[0] : DEFAULT_TIMEOUT;
    try {
      LOGGER.info("Verifying alert is present");
      Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
      Alert alert = wait.until(ExpectedConditions.alertIsPresent());
      if (alert != null) {
        LOGGER.info("Alert is present");
        return true;
      }
    } catch (Exception e) {
      LOGGER.error("Alert is not present. Root cause: {}", e.getMessage());
    }
    return false;
  }

  public boolean verifyAlertNotPresent(int... timeOut) {
    int waitTime = timeOut.length > 0 ? timeOut[0] : DEFAULT_TIMEOUT;
    try {
      LOGGER.info("Verifying alert is not present");
      Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
      boolean notPresent = wait.until(d -> {
        try {
          d.switchTo().alert();
          return false;
        } catch (NoAlertPresentException e) {
          return true;
        }
      });
      if (notPresent) {
        LOGGER.info("Alert is not present");
        return true;
      }
    } catch (Exception e) {
      LOGGER.error("Alert is still present. Root cause: {}", e.getMessage());
    }
    return false;
  }

  public void switchToFrame(String locator, int... timeOut) {
    WebElement we = findWebElement(locator, timeOut);
    try {
      LOGGER.info("Switching to frame located by '{}'", locator);
      driver.switchTo().frame(we);
      LOGGER.info("Switched to frame located by '{}' successfully", locator);
    } catch (Exception e) {
      LOGGER.error("Failed to switch to frame located by '{}'. Root cause: {}", locator,
          e.getMessage());
    }
  }

  public void switchToDefaultContent() {
    try {
      LOGGER.info("Switching to default content");
      driver.switchTo().defaultContent();
      LOGGER.info("Switched to default content successfully");
    } catch (Exception e) {
      LOGGER.error("Failed to switch to default content. Root cause: {}", e.getMessage());
    }
  }

  public void switchToParentFrame() {
    try {
      LOGGER.info("Switching to parent frame");
      driver.switchTo().parentFrame();
      LOGGER.info("Switched to parent frame successfully");
    } catch (Exception e) {
      LOGGER.error("Failed to switch to parent frame. Root cause: {}", e.getMessage());
    }
  }

  public boolean waitForAlertPresent(int... timeOut) {
    int waitTime = timeOut.length > 0 ? timeOut[0] : DEFAULT_TIMEOUT;
    try {
      LOGGER.info("Waiting alert to be present within '{}' second(s)", waitTime);
      Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
      Alert alert = wait.until(ExpectedConditions.alertIsPresent());
      if (alert != null) {
        LOGGER.info("Alert is present within '{}' second(s)", waitTime);
        return true;
      }
    } catch (Exception e) {
      LOGGER.error("Alert is not present within '{}' second(s). Root cause: {}", waitTime,
          e.getMessage());
    }
    return false;
  }

}
