package jp.nelke.excsv.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import jp.nelke.excsv.Config;
import jp.nelke.excsv.util.CellUtil;

public abstract class AbstractListParser<E> implements ListParser<E> {

    private int limit;

    private int offset;

    public List<E> parse(Workbook workbook, Sheet sheet, Config config) throws Exception {
        List<E> resultList = new ArrayList<E>();

        int firstRowNumber = CellUtil.getFirstRowNumber(workbook, sheet, config);
        int lastRowNumber = CellUtil.getLastRowNumber(workbook, sheet, config);

        for (int rowIndex = firstRowNumber + offset; rowIndex <= lastRowNumber; rowIndex++) {
            // 行の取得
            Row row = sheet.getRow(rowIndex);
            // 空行で無視する場合
            if (row == null && config.isIgnoreEmptyRow()) {
                continue;
            }
            // 行の解析
            E result = parse(workbook, sheet, row, config);
            // 解析結果をリストに詰める。
            if (result != null) {
                resultList.add(result);
            }

            if (limit > 0 && resultList.size() >= limit) {
                break;
            }
        }

        return resultList;
    }

    /**
     * 行の解析を行う
     *
     * @param row
     *            行
     * @return 解析結果
     */
    public abstract E parse(Workbook workbook, Sheet sheet, Row row, Config config) throws Exception;

    public ListParser<E> limit(int limit) {
        this.limit = limit;
        return this;
    }

    public ListParser<E> offset(int offset) {
        this.offset = offset;
        return this;
    }

}
