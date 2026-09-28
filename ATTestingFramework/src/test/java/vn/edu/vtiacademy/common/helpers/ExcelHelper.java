package vn.edu.vtiacademy.common.helpers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExcelHelper.class);

  private static XSSFWorkbook workbook;
  private static XSSFSheet sheet;

  public static void setExcelFile(String excelFilePath, String sheetName) {
    try {
      LOGGER.info("Loading Excel File");
      workbook = new XSSFWorkbook(excelFilePath);
      sheet = workbook.getSheet(sheetName);
      LOGGER.info("Excel File Loaded");
    } catch (Exception e) {
      LOGGER.error("Failed to set Excel file '{}'. Root cause: {}", excelFilePath, e.getMessage());
    }
  }

  public static XSSFWorkbook getWorkbook(String excelFilePath) {
    try {
      LOGGER.info("Getting Excel Workbook from Excel File {}", excelFilePath);
      XSSFWorkbook workbook1 = new XSSFWorkbook(excelFilePath);
      LOGGER.info("Excel Workbook Loaded");
      return workbook1;
    } catch (Exception e) {
      LOGGER.error("Failed to get Excel Workbook from Excel File {}. Root cause: {}", excelFilePath,
          e.getMessage());
    }
    return null;
  }

  public static XSSFSheet getSheet(String sheetName) {
    try {
      LOGGER.info("Getting Excel Sheet {}", sheetName);
      XSSFSheet sheet = workbook.getSheet(sheetName);
      LOGGER.info("Excel Sheet Loaded");
      return sheet;
    } catch (Exception e) {
      LOGGER.error("Failed to get Excel Sheet {}. Root cause: {}", sheetName, e.getMessage());
    }
    return null;
  }

  public static XSSFSheet getSheet(XSSFWorkbook workbook, String sheetName) {
    try {
      LOGGER.info("Getting Excel Sheet {}", sheetName);
      XSSFSheet sheet = workbook.getSheet(sheetName);
      LOGGER.info("Excel Sheet Loaded");
      return sheet;
    } catch (Exception e) {
      LOGGER.error("Failed to get Excel Sheet {}. Root cause: {}", sheetName, e.getMessage());
    }
    return null;
  }

  public static String getCellValue(XSSFSheet sheet, int rowIndex, int colIndex) {
    String cellValue = "";
    try {
      XSSFCell cell = sheet.getRow(rowIndex).getCell(colIndex);
      if (cell == null) {
        return "";
      }
      switch (cell.getCellType()) {
        case STRING:
          cellValue = cell.getStringCellValue();
          break;
        case NUMERIC:
          cellValue = String.valueOf(cell.getNumericCellValue());
          break;
        case BOOLEAN:
          cellValue = String.valueOf(cell.getBooleanCellValue());
          break;
        case FORMULA:
          cellValue = cell.getCellFormula();
          break;
        default:
          cellValue = "";
      }
    } catch (Exception e) {
      LOGGER.error("Failed to get Cell Value from Excel Sheet {}. Root cause: {}", sheet,
          e.getMessage());
    }
    return cellValue;
  }

  public static Cell findFirstCellWithStringValue(XSSFSheet sheet, String value) {
    for(Row row : sheet) {
      for(Cell cell : row) {
        if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(value)) {
          return cell;
        }
      }
    }
    return null;
  }

  public static Cell findLastCellWithStringValue(XSSFSheet sheet, String value) {
    Cell lastCell = null;
    for(Row row : sheet) {
      for(Cell cell : row) {
        if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(value)) {
          lastCell = cell;
        }
      }
    }
    return lastCell;
  }

  public static int getRowFromCell(Cell cell) {
    return cell.getRow().getRowNum();
  }

  public static int getColFromCell(Cell cell) {
    return cell.getColumnIndex();
  }

  public static XSSFRow getRowByIndex(XSSFSheet sheet, int rowIndex) {
    return sheet.getRow(rowIndex);
  }
}
