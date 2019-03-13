package jp.nelke.excsv.filter;

public class PositionInExpression extends PositionExpression {

    private Object[] values;

    protected PositionInExpression(int position, Object[] values) {
        super(position);
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
