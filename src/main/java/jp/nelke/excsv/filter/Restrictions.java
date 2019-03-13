package jp.nelke.excsv.filter;

import java.util.Collection;

public class Restrictions {

    private Restrictions() {

    }

    public static PositionFilter eq(int position, Object value) {
        return new PositionEqualExpression(position, value);
    }

    public static PositionFilter ne(int position, Object value) {
        return new PositionNotEqualExpression(position, value);
    }

    public static PositionFilter like(int position, String value) {
        return new PositionLikeExpression(position, value);
    }

    public static PositionFilter gt(int position, Comparable<Object> value) {
        return new PositionGtExpression(position, value);
    }

    public static PositionFilter lt(int position, Comparable<Object> value) {
        return new PositionLtExpression(position, value);
    }

    public static PositionFilter le(int position, Comparable<Object> value) {
        return new PositionLeExpression(position, value);
    }

    public static PositionFilter ge(int position, Comparable<Object> value) {
        return new PositionGeExpression(position, value);
    }

    public static PositionFilter between(int position, Comparable<Object> lo, Comparable<Object> hi) {
        return new PositionBetweenExpression(position, lo, hi);
    }

    public static PositionFilter in(int position, Object... values) {
        return new PositionInExpression(position, values);
    }

    public static PositionFilter in(int position, Collection<Object> values) {
        return new PositionInExpression(position, values.toArray());
    }

    public static PositionFilter isNull(int position) {
        return new PositionNullExpression(position);
    }

    public static PositionFilter isNotNull(int position) {
        return new PositionNotNullExpression(position);
    }

    public static PositionFilter and(PositionFilter... expressions) {
        return new PositionAndExpression(expressions);
    }

    public static PositionFilter or(PositionFilter... expressions) {
        return new PositionOrExpression(expressions);
    }

    public static PositionFilter not(PositionFilter expression) {
        return new PositionNotExpression(expression);
    }

    public static NameFilter eq(String name, Object value) {
        return new NameEqualExpression(name, value);
    }

    public static NameFilter ne(String name, Object value) {
        return new NameNotEqualExpression(name, value);
    }

    public static NameFilter like(String name, String value) {
        return new NameLikeExpression(name, value);
    }

    public static NameFilter gt(String name, Comparable<Object> value) {
        return new NameGtExpression(name, value);
    }

    public static NameFilter lt(String name, Comparable<Object> value) {
        return new NameLtExpression(name, value);
    }

    public static NameFilter le(String name, Comparable<Object> value) {
        return new NameLeExpression(name, value);
    }

    public static NameFilter ge(String name, Comparable<Object> value) {
        return new NameGeExpression(name, value);
    }

    public static NameFilter between(String name, Comparable<Object> lo, Comparable<Object> hi) {
        return new NameBetweenExpression(name, lo, hi);
    }

    public static NameFilter in(String name, Object[] values) {
        return new NameInExpression(name, values);
    }

    public static NameFilter in(String name, Collection<Object> values) {
        return new NameInExpression(name, values.toArray());
    }

    public static NameFilter isNull(String name) {
        return new NameNullExpression(name);
    }

    public static NameFilter isNotNull(String name) {
        return new NameNotNullExpression(name);
    }

    public static NameFilter and(NameFilter... expressions) {
        return new NameAndExpression(expressions);
    }

    public static NameFilter or(NameFilter... expressions) {
        return new NameOrExpression(expressions);
    }

    public static NameFilter not(NameFilter expression) {
        return new NameNotExpression(expression);
    }

}
