package jp.nelke.excsv.converter;

import jp.nelke.excsv.exception.ConvertException;

public class ShortConverter implements Converter<Short> {

    public Short convert(Object value) throws ConvertException {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            return Short.valueOf((String) value);
        }
        else if (value instanceof Double) {
            return ((Double) value).shortValue();
        }

        throw new ConvertException(value.toString() + " cannot convert to Short.");
    }

}
