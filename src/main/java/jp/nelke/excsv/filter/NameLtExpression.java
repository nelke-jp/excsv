package jp.nelke.excsv.filter;

public class NameLtExpression extends NameExpression {

    private Comparable<Object> value;

    public NameLtExpression(String name, Comparable<Object> value) {
        super(name);
        this.value = value;
    }

    @Override
    protected boolean isAccept(Object value) {
        return this.value.compareTo(value) < 0;
    }

}
