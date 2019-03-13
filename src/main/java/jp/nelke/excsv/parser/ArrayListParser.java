package jp.nelke.excsv.parser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import jp.nelke.excsv.Config;
import jp.nelke.excsv.util.CellUtil;

public class ArrayListParser extends AbstractListParser<Object[]> {

    @Override
    public Object[] parse(Workbook workbook, Sheet sheet, Row row, Config config) throws Exception {
        int firstColumuNumber = CellUtil.getFirstColumnNumber(workbook, sheet, config);
        int lastColumuNumber = CellUtil.getLastColumnNumber(workbook, sheet, config);

        Object[] objects = new Object[lastColumuNumber - firstColumuNumber + 1];

        if (row == null) {
            return objects;
        }

        for (int colIndex = firstColumuNumber; colIndex <= lastColumuNumber; colIndex++) {
            // セルの取得
            Cell cell = row.getCell(colIndex);
            // 値の取得
            Object value = CellUtil.getCellValue(cell);
            // 配列に詰める
            objects[colIndex - firstColumuNumber] = value;
        }

        return objects;
    }

}
