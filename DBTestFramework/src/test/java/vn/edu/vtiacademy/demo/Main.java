package vn.edu.vtiacademy.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.vtiacademy.common.DBKeywords;
import vn.edu.vtiacademy.common.DBType;

public class Main {

  public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) throws SQLException {
    DBKeywords dbKeywords = new DBKeywords();
    Connection connection = dbKeywords.createConnection(DBType.MYSQL, "127.0.0.1", "3306",
        "sql_store", "root", "root@123");
    ResultSet resultSet = dbKeywords.executeQuery(connection, "SELECT * FROM customers");
//    showResultSet(resultSet);
    List<String> firstNames = dbKeywords.getCellValues(resultSet, "first_name");
    for (String firstName: firstNames) {
      LOGGER.info(firstName);
    }

    List<String> firstNamesIndex = dbKeywords.getCellValues(resultSet, 2);
    for (String firstName: firstNamesIndex) {
      LOGGER.info(firstName);
    }

    String firstName = dbKeywords.getStringCellValue(resultSet, 6, "first_name");
    LOGGER.info(firstName);
    firstName = dbKeywords.getStringCellValue(resultSet, 6, 2);
    LOGGER.info(firstName);
    dbKeywords.closeConnection(connection);
  }

  public static void showResultSet(ResultSet resultSet) throws SQLException {
    var metaData = resultSet.getMetaData();
    var columnCount = metaData.getColumnCount();
    for (int i = 1; i <= columnCount; i++) {
      LOGGER.info("{}", metaData.getColumnName(i));
      while (resultSet.next()) {
        for (int j = 1; j <= columnCount; j++) {
          LOGGER.info("{}", resultSet.getObject(j));
        }
      }
    }
  }
}