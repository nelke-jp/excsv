package jp.nelke.excsv.filter;

public class NameEqualExpression extends NameExpression {

    private Object value;

    public NameEqualExpression(String name, Object value) {
        super(name);
        this.value = value;
    }

    @Override
    protected boolean isAccept(Object value) {
        return this.value.equals(value);
    }

}
