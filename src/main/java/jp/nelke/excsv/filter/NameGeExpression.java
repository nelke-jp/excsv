package jp.nelke.excsv.filter;

public class NameGeExpression extends NameExpression {

    private Comparable<Object> value;

    public NameGeExpression(String name, Comparable<Object> value) {
        super(name);
    }

    @Override
    protected boolean isAccept(Object value) {
        return this.value.compareTo(value) >= 0;
    }

}
