package jp.nelke.excsv.filter;

public class NameNotEqualExpression extends NameExpression {

    private Object value;

    public NameNotEqualExpression(String name, Object value) {
        super(name);
        this.value = value;
    }

    @Override
    protected boolean isAccept(Object value) {
        return !this.value.equals(value);
    }

}
