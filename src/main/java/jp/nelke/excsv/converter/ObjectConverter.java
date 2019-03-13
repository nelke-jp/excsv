package jp.nelke.excsv.converter;

import jp.nelke.excsv.exception.ConvertException;

public class ObjectConverter implements Converter<Object> {

    public Object convert(Object value) throws ConvertException {
        return value;
    }

}
