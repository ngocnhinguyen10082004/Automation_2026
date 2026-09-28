package vn.edu.vtiacademy.pages;

import com.jayway.jsonpath.JsonPath;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.vtiacademy.common.keywords.WebUI;

public class BasePage {

  private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

  private final static String OBJECT_REPO_FOLDER =
      System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
          + File.separator + "resources" + File.separator + "object_repositories";
  //"/Users/tuyenluu/training-workspace/VTI_AT_202602/ATTestingFramework/src/test/resources/object_repositories";

  private String repo;

  protected WebUI webUI;

  public BasePage(WebUI webUi) {
    this.webUI = webUi;
  }

  public void setRepo(String repo) {
    this.repo = OBJECT_REPO_FOLDER + File.separator + repo + ".json";
    // /Users/tuyenluu/training-workspace/VTI_AT_202602/ATTestingFramework/src/test/resources/object_repositories/EmployeesPage.json
  }

  private String getRepo() {
    return repo;
  }

  public String findTestObject(String testObjectName) {
    File file = new File(getRepo());
    try {
      String locatorValue = JsonPath.read(file, "$." + testObjectName).toString();
      return locatorValue;
    } catch (IOException e) {
      LOGGER.error("Failed to find test object '{}' in '{}'. Root cause: {}", testObjectName, getRepo(), e.getMessage());
    }
    return null;
  }
}
