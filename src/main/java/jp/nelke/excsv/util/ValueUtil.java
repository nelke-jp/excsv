package jp.nelke.excsv.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.nelke.excsv.converter.BigDecimalConverter;
import jp.nelke.excsv.converter.BigIntegerConverter;
import jp.nelke.excsv.converter.BooleanConverter;
import jp.nelke.excsv.converter.ByteConverter;
import jp.nelke.excsv.converter.Converter;
import jp.nelke.excsv.converter.DateConverter;
import jp.nelke.excsv.converter.DoubleConverter;
import jp.nelke.excsv.converter.FloatConverter;
import jp.nelke.excsv.converter.IntegerConverter;
import jp.nelke.excsv.converter.LongConverter;
import jp.nelke.excsv.converter.ObjectConverter;
import jp.nelke.excsv.converter.ShortConverter;
import jp.nelke.excsv.converter.StringConverter;

public class ValueUtil {
    private static Map<Object, Converter<?>> converterMap = new HashMap<Object, Converter<?>>();
    static {
        converterMap.put(Boolean.class, new BooleanConverter());
        converterMap.put(Boolean.TYPE, new BooleanConverter());
        converterMap.put(Byte.class, new ByteConverter());
        converterMap.put(Byte.TYPE, new ByteConverter());
        converterMap.put(Short.class, new ShortConverter());
        converterMap.put(Short.TYPE, new ShortConverter());
        converterMap.put(Integer.class, new IntegerConverter());
        converterMap.put(Integer.TYPE, new IntegerConverter());
        converterMap.put(Long.class, new LongConverter());
        converterMap.put(Long.TYPE, new LongConverter());
        converterMap.put(Float.class, new FloatConverter());
        converterMap.put(Float.TYPE, new FloatConverter());
        converterMap.put(Double.class, new DoubleConverter());
        converterMap.put(Double.TYPE, new DoubleConverter());
        converterMap.put(String.class, new StringConverter());
        converterMap.put(Date.class, new DateConverter());
        converterMap.put(BigDecimal.class, new BigDecimalConverter());
        converterMap.put(BigInteger.class, new BigIntegerConverter());
        converterMap.put(Object.class, new ObjectConverter());
    }

    private static Map<Class<?>, Object> primitiveDefaultValueMap = new HashMap<Class<?>, Object>();
    static {
        primitiveDefaultValueMap.put(Boolean.TYPE, Boolean.FALSE);
        primitiveDefaultValueMap.put(Byte.TYPE, Byte.valueOf((byte) 0));
        primitiveDefaultValueMap.put(Short.TYPE, Short.valueOf((short) 0));
        primitiveDefaultValueMap.put(Integer.TYPE, Integer.valueOf(0));
        primitiveDefaultValueMap.put(Long.TYPE, Long.valueOf(0L));
        primitiveDefaultValueMap.put(Float.TYPE, Float.valueOf(0F));
        primitiveDefaultValueMap.put(Double.TYPE, Double.valueOf(0D));
    }

    public static Object convertValue(Class<?> clazz, Object value) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class is null.");
        }

        if (value == null && clazz.isPrimitive()) {
            return primitiveDefaultValueMap.get(clazz);
        }

        Converter<?> converter = converterMap.get(clazz);
        if (converter != null) {
            return converter.convert(value);
        }
        else {
            return null;
        }
    }

    public static String convertStringValue(Object value) {
        Converter<String> converter = new StringConverter();
        return converter.convert(value);
    }

}
