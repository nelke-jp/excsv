package jp.nelke.excsv.converter;

import jp.nelke.excsv.exception.ConvertException;

/**
 * CellをStringとして解釈するParser
 */
public class StringConverter implements Converter<String> {

    public String convert(Object value) throws ConvertException {
        if (value == null) {
            return null;
        }

        return value.toString();
    }

}
