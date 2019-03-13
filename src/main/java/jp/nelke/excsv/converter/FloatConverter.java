package jp.nelke.excsv.converter;

import jp.nelke.excsv.exception.ConvertException;

public class FloatConverter implements Converter<Float> {

    public Float convert(Object value) throws ConvertException {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            return Float.valueOf((String) value);
        }
        else if (value instanceof Double) {
            return ((Double) value).floatValue();
        }

        throw new ConvertException(value.toString() + " cannot convert to Integer.");
    }

}
