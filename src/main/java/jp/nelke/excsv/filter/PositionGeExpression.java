package jp.nelke.excsv.filter;

public class PositionGeExpression extends PositionExpression {

    private Comparable<Object> value;

    protected PositionGeExpression(int position, Comparable<Object> value) {
        super(position);
        this.value = value;
    }

    @Override
    protected boolean isAccept(Object value) {
        return this.value.compareTo(value) >= 0;
    }

}
