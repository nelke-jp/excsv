package jp.nelke.excsv.converter;

import java.math.BigDecimal;

import jp.nelke.excsv.exception.ConvertException;

/**
 * セルをBigDecimalとして解釈するParser
 */
public class BigDecimalConverter implements Converter<BigDecimal> {

    public BigDecimal convert(Object value) throws ConvertException {
        if (value == null) {
            return null;
        }

        BigDecimal decimal = null;
        try {
            if (value instanceof String) {
                decimal = new BigDecimal((String) value);
            }
            else if (value instanceof Double) {
                decimal = BigDecimal.valueOf((Double) value);
            }
        }
        catch (NumberFormatException nfe) {
            throw new ConvertException(value.toString() + " cannot convert to BigDecimal.");
        }
        if (decimal == null) {
            throw new ConvertException(value.toString() + " cannot convert to BigDecimal.");
        }

        return decimal;
    }

}
