package jp.nelke.excsv.util;

import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell;

import jp.nelke.excsv.Config;

public class CellUtil {

    private static final String DOUBLE_QUOTE = "\"";

    public static int getFirstRowNumber(Workbook workbook, Sheet sheet, Config config) {
        if (config.getName() != null) {
            AreaReference areaReference = getAreaReference(workbook, config.getName());
            CellReference firstCell = areaReference.getFirstCell();
            return firstCell.getRow();
        }
        else if (config.getFirstRowNumber() != null) {
            return config.getFirstRowNumber();
        }

        return sheet.getFirstRowNum();
    }

    public static int getLastRowNumber(Workbook workbook, Sheet sheet, Config config) {
        if (config.getName() != null) {
            AreaReference areaReference = getAreaReference(workbook, config.getName());
            CellReference lastCell = areaReference.getLastCell();
            return lastCell.getRow();
        }
        else if (config.getLastRowNumber() != null) {
            return config.getLastRowNumber();
        }

        return sheet.getLastRowNum();
    }

    public static int getFirstColumnNumber(Workbook workbook, Sheet sheet, Config config) {
        if (config.getName() != null) {
            AreaReference areaReference = getAreaReference(workbook, config.getName());
            CellReference firstCell = areaReference.getFirstCell();
            return firstCell.getCol();
        }
        else if (config.getFirstColumnNumber() != null) {
            return config.getFirstColumnNumber();
        }

        int minCol = Integer.MAX_VALUE;
        for (Row row : sheet) {
            if (minCol > row.getFirstCellNum()) {
                minCol = row.getFirstCellNum();
            }
        }

        return minCol;
    }

    public static int getLastColumnNumber(Workbook workbook, Sheet sheet, Config config) {
        if (config.getName() != null) {
            AreaReference areaReference = getAreaReference(workbook, config.getName());
            CellReference lastCell = areaReference.getLastCell();
            return lastCell.getCol();
        }
        else if (config.getLastColumnNumber() != null) {
            return config.getLastColumnNumber();
        }

        int maxCol = -1;
        for (Row row : sheet) {
            if (maxCol < row.getLastCellNum()) {
                maxCol = row.getLastCellNum();
            }
        }

        return maxCol;
    }

    public static int getFirstColumnNumber(Workbook workbook, Row row, Config config) {
        if (config.getName() != null) {
            AreaReference areaReference = getAreaReference(workbook, config.getName());
            CellReference firstCell = areaReference.getFirstCell();
            return firstCell.getCol();
        }
        else if (config.getFirstColumnNumber() != null) {
            return config.getFirstColumnNumber();
        }

        return row.getFirstCellNum();
    }

    public static int getLastColumnNumber(Workbook workbook, Row row, Config config) {
        if (config.getName() != null) {
            AreaReference areaReference = getAreaReference(workbook, config.getName());
            CellReference lastCell = areaReference.getLastCell();
            return lastCell.getCol();
        }
        else if (config.getLastColumnNumber() != null) {
            return config.getLastColumnNumber();
        }

        return row.getLastCellNum();
    }

    private static AreaReference getAreaReference(Workbook workbook, String name) {
        Name nameDef = workbook.getName(name);
        if (nameDef == null) {
            throw new IllegalArgumentException("Specified name cannot be found. name:" + name);
        }
        AreaReference areaReference = new AreaReference(nameDef.getRefersToFormula(), SpreadsheetVersion.EXCEL97);

        return areaReference;
    }

    public static Object getCellValue(Cell cell) {
        return getCellValue(cell, true);
    }

    public static Object getCellValue(Cell cell, boolean isRegion) {
        if (cell == null) {
            return null;
        }

        return getCellValue(cell, cell.getCellTypeEnum(), isRegion);
    }

    public static Object getCellValue(Cell cell, CellType cellType, boolean isRegion) {
        if (cell == null) {
            return null;
        }

        if (isRegion) {
            cell = getRegionCell(cell);
            cellType = cell.getCellTypeEnum();
        }

        if (CellType.BLANK == cellType) {
            return null;
        }
        else if (CellType.BOOLEAN == cellType) {
            return cell.getBooleanCellValue();
        }
        else if (CellType.ERROR == cellType) {
            if (cell instanceof XSSFCell) {
                XSSFCell xssfCell = (XSSFCell) cell;
                CTCell ctCell = xssfCell.getCTCell();
                return ctCell.getV();
            }
            else if (cell instanceof HSSFCell) {
                return cell.getErrorCellValue();
            }
            return cell.getErrorCellValue();
        }
        else if (CellType.STRING == cellType) {
            return cell.getStringCellValue();
        }
        else if (CellType.NUMERIC == cellType) {
            if (isCellDateFormatted(cell)) {
                return cell.getDateCellValue();
            }
            else {
                return cell.getNumericCellValue();
            }
        }
        else if (CellType.FORMULA == cellType) {
            FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
            CellValue cellValue = evaluator.evaluate(cell);
            return CellUtil.getCellValue(cell, cellValue.getCellTypeEnum(), false);
        }

        return null;
    }

    public static boolean isCellDateFormatted(Cell cell) {
        if (cell == null) {
            return false;
        }
        boolean bDate = false;

        double d = cell.getNumericCellValue();
        if (DateUtil.isValidExcelDate(d)) {
            CellStyle style = cell.getCellStyle();
            if (style == null) {
                return false;
            }
            String dateFormat = style.getDataFormatString();
            if (dateFormat != null) {
                while (dateFormat.contains(DOUBLE_QUOTE)) {
                    int begIndex = dateFormat.indexOf(DOUBLE_QUOTE);
                    int endIndex = dateFormat.indexOf(DOUBLE_QUOTE, begIndex + 1);
                    dateFormat = dateFormat.replaceFirst(Pattern.quote(dateFormat.substring(begIndex, endIndex + 1)),
                            "");
                }
            }
            bDate = DateUtil.isADateFormat(style.getDataFormat(), dateFormat);
        }
        return bDate;
    }

    public static Cell getRegionCell(Cell cell) {
        int rowIndex = cell.getRowIndex();
        int colIndex = cell.getColumnIndex();

        Sheet sheet = cell.getSheet();
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            if (range.isInRange(rowIndex, colIndex)) {
                Cell firstCell = getCell(sheet, range.getFirstRow(), range.getFirstColumn());
                return firstCell;
            }
        }
        return cell;
    }

    public static Cell getCell(Sheet sheet, int rowIndex, int colIndex) {
        if (sheet != null) {
            Row row = sheet.getRow(rowIndex);
            if (row != null) {
                return row.getCell(colIndex);
            }
        }
        return null;
    }

}
