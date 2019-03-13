package jp.nelke.excsv.parser;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import jp.nelke.excsv.Config;
import jp.nelke.excsv.annotation.Column;
import jp.nelke.excsv.converter.Converter;
import jp.nelke.excsv.exception.ConvertException;
import jp.nelke.excsv.filter.NameFilter;
import jp.nelke.excsv.util.CellUtil;
import jp.nelke.excsv.util.ReflectionUtil;
import jp.nelke.excsv.util.ValueUtil;

public class NameBeanListParser<E> extends AbstractListParser<E> {

    private Class<E> clazz;

    private Map<String, Integer> headerMap;

    private NameFilter filter;

    private Map<String, Converter<?>> converterMap = new HashMap<String, Converter<?>>();

    public NameBeanListParser(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E parse(Workbook workbook, Sheet sheet, Row row, Config config) throws Exception {
        E bean = ReflectionUtil.createEntity(clazz);

        if (row == null) {
            return bean;
        }

        createHeaderMap(workbook, sheet, config);

        int firstColumnNumber = CellUtil.getFirstColumnNumber(workbook, row, config);

        Cell cell = null;

        try {
            // Field情報を取得
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    // 列名称の取得
                    String name = column.name();
                    // 列番号の取得
                    int position = headerMap.get(name);
                    // セルの取得
                    cell = row.getCell(position + firstColumnNumber);
                    // 値の取得
                    Object value = CellUtil.getCellValue(cell);
                    // 値を変換
                    Object convertValue = convertValue(name, value, field.getType());
                    // フィルターチェック
                    if (!isAccept(name, convertValue)) {
                        return null;
                    }
                    // 値の設定
                    ReflectionUtil.setProperty(bean, field, convertValue);
                }
            }
        }
        catch (ConvertException ce) {
            ce.setRow(row.getRowNum());
            ce.setCol(cell.getColumnIndex());
            throw ce;
        }

        return bean;
    }

    private void createHeaderMap(Workbook workbook, Sheet sheet, Config config) {
        if (headerMap == null) {
            Row header = sheet.getRow(config.getHeadRowNumber());
            if (header == null) {
                throw new IllegalStateException("Header not found.");
            }

            int firstColumnNumber = CellUtil.getFirstColumnNumber(workbook, header, config);
            int lastColumnNumber = CellUtil.getLastColumnNumber(workbook, header, config);

            headerMap = new LinkedHashMap<String, Integer>();

            for (int colIndex = firstColumnNumber; colIndex <= lastColumnNumber; colIndex++) {
                // セルの取得
                Cell cell = header.getCell(colIndex);
                // 値の取得
                Object value = CellUtil.getCellValue(cell);
                // 値を変換
                String stringValue = ValueUtil.convertStringValue(value);
                // Mapに詰める
                headerMap.put(stringValue, colIndex - firstColumnNumber);
            }
        }
    }

    public NameBeanListParser<E> filter(NameFilter filter) {
        this.filter = filter;
        return this;
    }

    public NameBeanListParser<E> addConverter(String name, Converter<?> converter) {
        converterMap.put(name, converter);
        return this;
    }

    private Object convertValue(String name, Object value, Class<?> clazz) {
        Converter<?> converter = converterMap.get(name);
        if (converter != null) {
            return converter.convert(value);
        }
        else {
            return ValueUtil.convertValue(clazz, value);
        }
    }

    private boolean isAccept(String name, Object value) {
        if (filter == null) {
            return true;
        }

        return filter.isAccept(name, value);
    }

}
