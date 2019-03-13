package jp.nelke.excsv.converter;

import jp.nelke.excsv.exception.ConvertException;

public class BooleanConverter implements Converter<Boolean> {

    public Boolean convert(Object value) throws ConvertException {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            return Boolean.valueOf((String) value);
        }
        else if (value instanceof Boolean) {
            return (Boolean) value;
        }
        else if (value instanceof Double) {
            Double doubleValue = (Double) value;
            if (doubleValue.equals(Double.valueOf(0D))) {
                return Boolean.FALSE;
            }
            else if (doubleValue.equals(Double.valueOf(1D))) {
                return Boolean.TRUE;
            }
        }

        throw new ConvertException(value.toString() + " cannot convert to Boolean.");
    }

}
