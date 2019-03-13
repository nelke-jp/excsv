package jp.nelke.excsv.converter;

import jp.nelke.excsv.exception.ConvertException;

public class DoubleConverter implements Converter<Double> {

    public Double convert(Object value) throws ConvertException {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            return Double.valueOf((String) value);
        }
        else if (value instanceof Double) {
            return (Double) value;
        }

        throw new ConvertException(value.toString() + " cannot convert to Double.");
    }

}
