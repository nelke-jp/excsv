package jp.nelke.excsv.filter;

public class PositionEqualExpression extends PositionExpression {

    private Object value;

    protected PositionEqualExpression(int position, Object value) {
        super(position);
        this.value = value;
    }

    @Override
    protected boolean isAccept(Object value) {
        return this.value.equals(value);
    }

}
