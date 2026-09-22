package vn.edu.vtiacademy.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBKeywords {

  private static final Logger LOGGER = LoggerFactory.getLogger(DBKeywords.class);

  private String getConnectionUrl(DBType dbType, String server, String port, String dbName) {
    LOGGER.info("Getting database connection url");
    String url;
    switch (dbType) {
      case MYSQL -> {
        url = "jdbc:mysql://" + server + ":" + port + "/" + dbName;
        LOGGER.info("MySQL Database connection url is {}", url);
        return url;
      }
      case POSTGRESQL -> {
        url = "jdbc:postgresql://" + server + ":" + port + "/" + dbName;
        LOGGER.info("PostgreSQL Database connection url is {}", url);
        return url;
      }
      case ORACLE -> {
        url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + dbName;
        LOGGER.info("Oracle Database connection url is {}", url);
        return url;
      }
      case SQLSERVER -> {
        url = "jdbc:sqlserver://" + server + ":" + port + "/" + dbName;
        LOGGER.info("SQL Server Database connection url is {}", url);
        return url;
      }
      default -> {
        return "";
      }
    }
  }

  public Connection createConnection(DBType dbType, String server, String port, String dbName,
      String username, String password) {
    String url = getConnectionUrl(dbType, server, port, dbName);
    Connection connection = null;
    try {
      LOGGER.info("Connecting to {} ...", url);
      connection = DriverManager.getConnection(url, username, password);
      if (connection != null) {
        LOGGER.info("Connected to {} successfully", url);
      }
    } catch (Exception e) {
      LOGGER.error("Failed to create connection to database. Root cause: {}", e.getMessage());
    }
    return connection;
  }

  public void closeConnection(Connection connection) {
    try {
      LOGGER.info("Closing the connection ...");
      if (connection != null && !connection.isClosed()) {
        connection.close();
        LOGGER.info("Connection closed successfully");
      } else {
        LOGGER.warn("Connection is already closed");
      }
    } catch (Exception e) {
      LOGGER.error("Failed to close connection. Root cause: {}", e.getMessage());
    }
  }

  public ResultSet executeQuery(Connection connection, String sqlQuery) {
    ResultSet resultSet = null;
    try {
      LOGGER.info("Executing query '{}'", sqlQuery);
      Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
          ResultSet.CONCUR_UPDATABLE);
      resultSet = statement.executeQuery(sqlQuery);
      LOGGER.info("Query '{}' executed successfully", sqlQuery);
    } catch (Exception e) {
      LOGGER.error("Failed to execute query '{}'. Root cause: {}", sqlQuery, e.getMessage());
    }
    return resultSet;
  }

  public List<String> getCellValues(ResultSet resultSet, String columnName) {
    List<String> cellValues = new ArrayList<>();
    LOGGER.info("Getting cell values from column '{}'", columnName);
    try {
      resultSet.beforeFirst();
      while (resultSet.next()) {
        cellValues.add(resultSet.getString(columnName));
      }
      LOGGER.info("Cell values from column '{}' retrieved successfully", columnName);
    } catch (Exception e) {
      LOGGER.error("Failed to get cell values from column '{}'. Root cause: {}", columnName,
          e.getMessage());
    }
    return cellValues;
  }

  public List<String> getCellValues(ResultSet resultSet, int columnIndex) {
    List<String> cellValues = new ArrayList<>();
    LOGGER.info("Getting cell values from column '{}'", columnIndex);
    try {
      resultSet.beforeFirst();
      while (resultSet.next()) {
        cellValues.add(resultSet.getString(columnIndex));
      }
      LOGGER.info("Cell values from column '{}' retrieved successfully", columnIndex);
    } catch (Exception e) {
      LOGGER.error("Failed to get cell values from column '{}'. Root cause: {}", columnIndex,
          e.getMessage());
    }
    return cellValues;
  }

  public String getStringCellValue(ResultSet resultSet, int rowIndex, String columnName) {
    String cellValue = null;
    try {
      LOGGER.info("Retrieve string cell value at row '{}' and collum '{}'", rowIndex, columnName);
      resultSet.absolute(rowIndex);
      cellValue = resultSet.getString(columnName);
    } catch (Exception e) {
      LOGGER.info(
          "Failed to retrieve string cell value at row '{}' and column '{}'. Root cause: {}",
          rowIndex, columnName, e.getMessage());
    }
    return cellValue;
  }

  public String getStringCellValue(ResultSet resultSet, int rowIndex, int columnIndex) {
    String cellValue = null;
    try {
      LOGGER.info("Retrieve string cell value at row '{}' and collum '{}'", rowIndex, columnIndex);
      resultSet.absolute(rowIndex);
      cellValue = resultSet.getString(columnIndex);
    } catch (Exception e) {
      LOGGER.info(
          "Failed to retrieve string cell value at row '{}' and column '{}'. Root cause: {}",
          rowIndex, columnIndex, e.getMessage());
    }
    return cellValue;
  }
}
