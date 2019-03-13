package jp.nelke.excsv.filter;

public class PositionGtExpression extends PositionExpression {

    private Comparable<Object> value;

    protected PositionGtExpression(int position, Comparable<Object> value) {
        super(position);
        this.value = value;
    }

    @Override
    protected boolean isAccept(Object value) {
        return this.value.compareTo(value) > 0;
    }

}
