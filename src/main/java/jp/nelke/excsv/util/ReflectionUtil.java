package jp.nelke.excsv.util;

import java.lang.reflect.Field;

public class ReflectionUtil {

    public static <E> E createEntity(Class<E> clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }

    public static void setProperty(Object bean, Field field, Object value) throws IllegalArgumentException,
            IllegalAccessException {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        field.set(bean, value);
    }

}
