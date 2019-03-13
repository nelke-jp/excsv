package jp.nelke.excsv.parser;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import jp.nelke.excsv.Config;
import jp.nelke.excsv.annotation.Column;
import jp.nelke.excsv.converter.Converter;
import jp.nelke.excsv.exception.ConvertException;
import jp.nelke.excsv.filter.PositionFilter;
import jp.nelke.excsv.util.CellUtil;
import jp.nelke.excsv.util.ReflectionUtil;
import jp.nelke.excsv.util.ValueUtil;

/**
 * Beanに変換するためのParser。
 * BeanはColumnアノテーションを指定する必要あり。
 */
public class PositionBeanListParser<E> extends AbstractListParser<E> {

    private Class<E> clazz;

    private PositionFilter filter;

    private Map<Integer, Converter<?>> converterMap = new HashMap<Integer, Converter<?>>();

    public PositionBeanListParser(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E parse(Workbook workbook, Sheet sheet, Row row, Config config) throws Exception {
        E bean = ReflectionUtil.createEntity(clazz);

        if (row == null) {
            return bean;
        }

        int firstColumnNumber = CellUtil.getFirstColumnNumber(workbook, row, config);

        Cell cell = null;

        try {
            // Field情報を取得
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    // 列番号の取得
                    int position = column.position();
                    // セルの取得
                    cell = row.getCell(position + firstColumnNumber);
                    // 値の取得
                    Object value = CellUtil.getCellValue(cell);
                    // 値を変換
                    Object convertValue = convertValue(position, value, field.getType());
                    // フィルターチェック
                    if (filter != null && !filter.isAccept(position, value)) {
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

    public PositionBeanListParser<E> filter(PositionFilter filter) {
        this.filter = filter;
        return this;
    }

    public PositionBeanListParser<E> addConverter(int position, Converter<?> converter) {
        converterMap.put(position, converter);
        return this;
    }

    private Object convertValue(int position, Object value, Class<?> clazz) {
        Converter<?> converter = converterMap.get(position);
        if (converter != null) {
            return converter.convert(value);
        }
        else {
            return ValueUtil.convertValue(clazz, value);
        }
    }

}
