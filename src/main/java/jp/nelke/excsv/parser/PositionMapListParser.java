package jp.nelke.excsv.parser;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import jp.nelke.excsv.Config;
import jp.nelke.excsv.converter.Converter;
import jp.nelke.excsv.filter.PositionFilter;
import jp.nelke.excsv.util.CellUtil;

/**
 * キーを項目の位置、値をセルの項目値としたマップとして解析するためのParser。
 */
public class PositionMapListParser extends AbstractListParser<Map<Integer, Object>> {

    private PositionFilter filter;

    private Map<Integer, Converter<?>> converterMap = new HashMap<Integer, Converter<?>>();

    public Map<Integer, Object> parse(Workbook workbook, Sheet sheet, Row row, Config config) throws Exception {
        Map<Integer, Object> parseMap = new LinkedHashMap<Integer, Object>();

        if (row == null) {
            return parseMap;
        }

        int firstColumuNumber = CellUtil.getFirstColumnNumber(workbook, row, config);
        int lastColumuNumber = CellUtil.getLastColumnNumber(workbook, row, config);

        for (int colIndex = firstColumuNumber; colIndex <= lastColumuNumber; colIndex++) {
            // セルの取得
            Cell cell = row.getCell(colIndex);
            // 値の取得
            Object value = CellUtil.getCellValue(cell);
            // 値の変換
            value = convertValue(colIndex - firstColumuNumber, value);
            // フィルターチェック
            if (!isAccept(colIndex - firstColumuNumber, value)) {
                return null;
            }
            // Mapに詰める
            parseMap.put(colIndex - firstColumuNumber, value);
        }

        return parseMap;
    }

    public PositionMapListParser filter(PositionFilter filter) {
        this.filter = filter;
        return this;
    }

    public PositionMapListParser addConverter(int position, Converter<?> converter) {
        converterMap.put(position, converter);
        return this;
    }

    private Object convertValue(int position, Object value) {
        Converter<?> converter = converterMap.get(position);
        if (converter != null) {
            value = converter.convert(value);
        }

        return value;
    }

    private boolean isAccept(int position, Object value) {
        if (filter == null) {
            return true;
        }

        return filter.isAccept(position, value);
    }

}
