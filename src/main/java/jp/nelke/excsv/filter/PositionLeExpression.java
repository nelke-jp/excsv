package jp.nelke.excsv.filter;

public class PositionLeExpression extends PositionExpression {

    private Comparable<Object> value;

    protected PositionLeExpression(int position, Comparable<Object> value) {
        super(position);
        this.value = value;
    }

    @Override
    protected boolean isAccept(Object value) {
        return this.value.compareTo(value) <= 0;
    }

}
