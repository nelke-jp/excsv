package jp.nelke.excsv.converter;

import java.math.BigInteger;

import jp.nelke.excsv.exception.ConvertException;

public class BigIntegerConverter implements Converter<BigInteger> {

    public BigInteger convert(Object value) throws ConvertException {
        if (value == null) {
            return null;
        }

        BigInteger integer = null;
        try {
            if (value instanceof String) {
                integer = new BigInteger((String) value);
            }
            else if (value instanceof Double) {
                integer = BigInteger.valueOf(((Double) value).longValue());
            }
        }
        catch (NumberFormatException nfe) {
            throw new ConvertException(value.toString() + " cannot convert to BigInteger.");
        }
        if (integer == null) {
            throw new ConvertException(value.toString() + " cannot convert to BigInteger.");
        }

        return integer;
    }

}
