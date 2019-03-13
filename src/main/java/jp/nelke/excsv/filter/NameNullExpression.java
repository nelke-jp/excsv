package jp.nelke.excsv.filter;

public class NameNullExpression extends NameExpression {

    public NameNullExpression(String name) {
        super(name);
    }

    @Override
    protected boolean isAccept(Object value) {
        return value == null;
    }

}
