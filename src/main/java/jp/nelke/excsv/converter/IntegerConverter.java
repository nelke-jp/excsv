package jp.nelke.excsv.converter;

import jp.nelke.excsv.exception.ConvertException;

public class IntegerConverter implements Converter<Integer> {

    public Integer convert(Object value) throws ConvertException {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            return Integer.valueOf((String) value);
        }
        else if (value instanceof Double) {
            return ((Double) value).intValue();
        }

        throw new ConvertException(value.toString() + " cannot convert to Integer.");
    }

}
