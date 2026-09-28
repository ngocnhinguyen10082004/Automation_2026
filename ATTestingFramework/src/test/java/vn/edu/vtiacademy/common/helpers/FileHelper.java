package vn.edu.vtiacademy.common.helpers;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(FileHelper.class);

  // [{fristName: John}, {lastName: Doe}]
  public static List<HashMap<String, String>> getExcelData(String excelFilePath, String sheetName, String testCaseId) {
    List<HashMap<String, String>> data = new ArrayList<>();
    XSSFWorkbook workbook = ExcelHelper.getWorkbook(excelFilePath);
    if (workbook == null) {
      LOGGER.error("Excel file {} not found", excelFilePath);
      return data;
    }
    XSSFSheet sheet = ExcelHelper.getSheet(workbook, sheetName);
    if (sheet == null) {
      LOGGER.error("Sheet {} not found", sheetName);
      return data;
    }

    Cell startCell = ExcelHelper.findFirstCellWithStringValue(sheet, testCaseId);
    if (startCell == null) {
      LOGGER.error("Test case id '{}' not found in sheet '{}'", sheetName, sheetName);
      return data;
    }

    Cell endCell = ExcelHelper.findLastCellWithStringValue(sheet, testCaseId);

    int startRow = ExcelHelper.getRowFromCell(startCell);
    int endRow = ExcelHelper.getRowFromCell(endCell);
    int startCol = ExcelHelper.getColFromCell(startCell);
    int endCol = ExcelHelper.getColFromCell(endCell);

//    LOGGER.info("[{}, {}] to [{}, {}]", startRow, endRow, startCol, endCol);
    if (startRow != -1) {
//      LOGGER.info("Get all data from Excel Sheet {}", sheetName);
      XSSFRow headerRow = ExcelHelper.getRowByIndex(sheet, startRow);
      if (headerRow == null) {
        LOGGER.error("Header row for test case '{}' is not found in sheet '{}'", testCaseId, sheetName);
        return data;
      }

      for(int i = startRow + 1; i <= endRow; i++) {
        HashMap<String, String> row = new HashMap<>();
        XSSFRow dataRow = ExcelHelper.getRowByIndex(sheet, i);
        if (dataRow != null) {
          for(int j = startCol + 1; j < endCol; j++) {
            String header = ExcelHelper.getCellValue(sheet, startRow, j);
            String cellValue = ExcelHelper.getCellValue(sheet, i, j);
//            LOGGER.info("Header: {}", header);
//            LOGGER.info("Cell value: {}", cellValue);
            if(header != null && !header.isEmpty()) {
              row.put(header, cellValue);
            }
          }
        }
        data.add(row);
      }
    }
    try {
      workbook.close();
    } catch (Exception e) {
      LOGGER.error("Failed to close workbook. Root cause: {}", e.getMessage());
    }
    return data;
  }
}
