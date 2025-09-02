package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    public static String getCellData(int rowNum, int colNum) {
        String cellValue = "";
        String excelFilePath = "src/test/resources/testData.xlsx";
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);  // Reads the first sheet
            Row row = sheet.getRow(rowNum);
            if (row == null) return "";  // Handle empty rows

            Cell cell = row.getCell(colNum);
            if (cell == null) return "";  // Handle empty cells

            // Handle different cell types
            switch (cell.getCellType()) {
                case STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = cell.getDateCellValue().toString();
                    } else {
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case FORMULA:
                    cellValue = cell.getCellFormula();
                    break;
                case BLANK:
                default:
                    cellValue = "";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cellValue;
    }

    public static Map<String, String> loadKeyValueData(String filePath, String sheetName) {
        Map<String, String> dataMap = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header

                Cell keyCell = row.getCell(0);
                Cell valueCell = row.getCell(1);

                // تحقق من null للخلايا لتجنب NullPointerException
                if (keyCell == null || valueCell == null) {
                    continue;  // تجاهل الصفوف التي بها خلايا فارغة
                }

                String key = keyCell.getStringCellValue().trim();
                String value = valueCell.getStringCellValue().trim();
                dataMap.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataMap;
    }
}
