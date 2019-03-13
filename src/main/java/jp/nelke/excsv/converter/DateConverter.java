package jp.nelke.excsv.converter;

import java.util.Date;

import jp.nelke.excsv.exception.ConvertException;

public class DateConverter implements Converter<Date> {

    public Date convert(Object value) throws ConvertException {
        if (value == null) {
            return null;
        }

        if (value instanceof Date) {
            return (Date) value;
        }

        throw new ConvertException(value.toString() + " cannot convert to Date.");
    }

}
