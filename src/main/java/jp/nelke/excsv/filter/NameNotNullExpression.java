package jp.nelke.excsv.filter;

public class NameNotNullExpression extends NameExpression {

    public NameNotNullExpression(String name) {
        super(name);
    }

    @Override
    protected boolean isAccept(Object value) {
        return value != null;
    }

}
