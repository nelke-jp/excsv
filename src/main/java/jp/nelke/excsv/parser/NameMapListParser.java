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
import jp.nelke.excsv.filter.NameFilter;
import jp.nelke.excsv.util.CellUtil;
import jp.nelke.excsv.util.ValueUtil;

public class NameMapListParser extends AbstractListParser<Map<String, Object>> {

    private Map<Integer, String> headerMap;

    private NameFilter filter;

    private Map<String, Converter<?>> converterMap = new HashMap<String, Converter<?>>();

    @Override
    public Map<String, Object> parse(Workbook workbook, Sheet sheet, Row row, Config config) throws Exception {
        Map<String, Object> valueMap = new LinkedHashMap<String, Object>();

        if (row == null) {
            return valueMap;
        }

        createHeaderMap(workbook, sheet, config);

        int firstColumuNumber = CellUtil.getFirstColumnNumber(workbook, sheet, config);
        int lastColumuNumber = CellUtil.getLastColumnNumber(workbook, sheet, config);

        for (int colIndex = firstColumuNumber; colIndex <= lastColumuNumber; colIndex++) {
            int headerIndex = colIndex - firstColumuNumber;
            String headerName = headerMap.get(headerIndex);
            // ヘッダーが存在しない場合、取り込み対象外
            if (headerName == null) {
                continue;
            }
            // セルの取得
            Cell cell = row.getCell(colIndex);
            // 値の取得
            Object value = CellUtil.getCellValue(cell);
            // 値の変換
            value = convertValue(headerName, value);
            // フィルターチェック
            if (!isAccept(headerName, value)) {
                return null;
            }
            // Mapに詰める
            valueMap.put(headerName, value);
        }

        return valueMap;
    }

    private void createHeaderMap(Workbook workbook, Sheet sheet, Config config) {
        if (headerMap == null) {
            Row header = sheet.getRow(config.getHeadRowNumber());
            if (header == null) {
                throw new IllegalStateException("Header not found.");
            }

            int firstColumnNumber = CellUtil.getFirstColumnNumber(workbook, header, config);
            int lastColumnNumber = CellUtil.getLastColumnNumber(workbook, header, config);

            headerMap = new LinkedHashMap<Integer, String>();

            for (int colIndex = firstColumnNumber; colIndex <= lastColumnNumber; colIndex++) {
                // セルの取得
                Cell cell = header.getCell(colIndex);
                // 値の取得
                Object value = CellUtil.getCellValue(cell);
                // 値を変換
                String stringValue = ValueUtil.convertStringValue(value);
                // Mapに詰める
                headerMap.put(colIndex - firstColumnNumber, stringValue);
            }
        }
    }

    public NameMapListParser filter(NameFilter filter) {
        this.filter = filter;
        return this;
    }

    public NameMapListParser addConverter(String name, Converter<?> converter) {
        converterMap.put(name, converter);
        return this;
    }

    private Object convertValue(String name, Object value) {
        Converter<?> converter = converterMap.get(name);
        if (converter != null) {
            value = converter.convert(value);
        }

        return value;
    }

    private boolean isAccept(String name, Object value) {
        if (filter == null) {
            return true;
        }

        return filter.isAccept(name, value);
    }

}
