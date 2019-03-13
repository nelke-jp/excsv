package jp.nelke.excsv.converter;

import jp.nelke.excsv.exception.ConvertException;

public class ByteConverter implements Converter<Byte> {

    public Byte convert(Object value) throws ConvertException {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            return Byte.valueOf((String) value);
        }
        else if (value instanceof Double) {
            return ((Double) value).byteValue();
        }

        throw new ConvertException(value.toString() + " cannot convert to Byte.");
    }

}
