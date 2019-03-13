package jp.nelke.excsv.filter;

public class PositionNotEqualExpression extends PositionExpression {

    private Object value;

    protected PositionNotEqualExpression(int position, Object value) {
        super(position);
        this.value = value;
    }

    @Override
    protected boolean isAccept(Object value) {
        return !this.value.equals(value);
    }

}
