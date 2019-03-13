package jp.nelke.excsv.filter;

public class PositionLtExpression extends PositionExpression {

    private Comparable<Object> value;

    protected PositionLtExpression(int position, Comparable<Object> value) {
        super(position);
        this.value = value;
    }

    @Override
    protected boolean isAccept(Object value) {
        return this.value.compareTo(value) < 0;
    }

}
