package jp.nelke.excsv.filter;

public class NameInExpression extends NameExpression {

    private Object[] values;

    public NameInExpression(String name, Object[] values) {
        super(name);
        this.values = values;
    }

    @Override
    protected boolean isAccept(Object value) {
        for (Object target : values) {
            if (target.equals(value)) {
                return true;
            }
        }

        return false;
    }

}
