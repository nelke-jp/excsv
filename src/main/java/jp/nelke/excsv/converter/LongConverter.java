package jp.nelke.excsv.converter;

import jp.nelke.excsv.exception.ConvertException;

public class LongConverter implements Converter<Long> {

    public Long convert(Object value) throws ConvertException {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            return Long.valueOf((String) value);
        }
        else if (value instanceof Double) {
            return ((Double) value).longValue();
        }

        throw new ConvertException(value.toString() + " cannot convert to Long.");
    }

}
