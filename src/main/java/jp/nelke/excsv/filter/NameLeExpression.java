package jp.nelke.excsv.filter;

public class NameLeExpression extends NameExpression {

    private Comparable<Object> value;

    public NameLeExpression(String name, Comparable<Object> value) {
        super(name);
    }

    @Override
    protected boolean isAccept(Object value) {
        return this.value.compareTo(value) <= 0;
    }

}
