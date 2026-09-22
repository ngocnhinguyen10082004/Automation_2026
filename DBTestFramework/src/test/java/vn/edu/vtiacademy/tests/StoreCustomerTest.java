package vn.edu.vtiacademy.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.vtiacademy.common.DBKeywords;
import vn.edu.vtiacademy.common.DBType;

public class StoreCustomerTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(StoreCustomerTest.class);
  private static DBKeywords dbKeywords;
  private static Connection connection;

  @BeforeAll
  public static void beforeAll() {
    LOGGER.info("Before all tests");
    dbKeywords = new DBKeywords();
    connection = dbKeywords.createConnection(DBType.MYSQL, "127.0.0.1", "3306",
        "sql_store", "root", "root@123");
  }

//  @BeforeEach
//  public void beforeEach(TestInfo testInfo) {
//    LOGGER.info("Before test: {}", testInfo.getDisplayName());
//    dbKeywords = new DBKeywords();
//    connection = dbKeywords.createConnection(DBType.MYSQL, "127.0.0.1", "3306",
//        "sql_store", "root", "root@123");
//  }

  @Test
  public void SC001_customer_table_is_not_empty() {
    // Arrange
//    DBKeywords dbKeywords = new DBKeywords();
//    Connection connection = dbKeywords.createConnection(DBType.MYSQL, "127.0.0.1", "3306",
//        "sql_store", "root", "root@123");
    // Act
    ResultSet resultSet = dbKeywords.executeQuery(connection, "SELECT * FROM customers");
    List<String> customerIds = dbKeywords.getCellValues(resultSet, "customer_id");

    // Assert
    assertNotEquals(0, customerIds.size());
  }

  @Test
  public void SC002_found_a_record_successfully() {
    // Arrange
    DBKeywords dbKeywords = new DBKeywords();
    Connection connection = dbKeywords.createConnection(DBType.MYSQL, "127.0.0.1", "3306",
        "sql_store", "root", "root@123");
    // Act
    ResultSet resultSet = dbKeywords.executeQuery(connection, "SELECT * FROM customers");
    List<String> customerIds = dbKeywords.getCellValues(resultSet, "customer_id");
    List<String> fistNames = dbKeywords.getCellValues(resultSet, "first_name");
    List<String> lastNames = dbKeywords.getCellValues(resultSet, "last_name");

    String actualCustomerId = customerIds.get(3);
    String actualFirstName = fistNames.get(3);
    String actualLastName = lastNames.get(3);

    // Assert
    assertEquals("4", actualCustomerId);
    assertEquals("Ambur", actualFirstName);
    assertEquals("Roseburgh", actualLastName);
  }

//  @AfterEach
//  public void afterEach(TestInfo testInfo) {
//    dbKeywords.closeConnection(connection);
//    LOGGER.info("After test: {}", testInfo.getDisplayName());
//  }

  @AfterAll
  public static void afterAll() {
    dbKeywords.closeConnection(connection);
    LOGGER.info("After all tests");
  }
}