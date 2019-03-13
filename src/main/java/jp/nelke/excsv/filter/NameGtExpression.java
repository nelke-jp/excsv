package jp.nelke.excsv.filter;

public class NameGtExpression extends NameExpression {

    private Comparable<Object> value;

    public NameGtExpression(String name, Comparable<Object> value) {
        super(name);
        this.value = value;
    }

    @Override
    protected boolean isAccept(Object value) {
        return this.value.compareTo(value) > 0;
    }

}
