package jp.nelke.excsv.filter;

public class NameNotExpression implements NameFilter {

    private NameFilter expression;

    protected NameNotExpression(NameFilter expression) {
        this.expression = expression;
    }

    public boolean isAccept(String name, Object value) {
        return !expression.isAccept(name, value);
    }

}
