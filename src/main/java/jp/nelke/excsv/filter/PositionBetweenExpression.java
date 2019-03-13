package jp.nelke.excsv.filter;

public class PositionBetweenExpression extends PositionExpression {

    private Comparable<Object> lo;
    private Comparable<Object> hi;

    protected PositionBetweenExpression(int position, Comparable<Object> lo, Comparable<Object> hi) {
        super(position);
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    protected boolean isAccept(Object value) {
        return this.lo.compareTo(value) <= 0 && hi.compareTo(value) >= 0;
    }

}
