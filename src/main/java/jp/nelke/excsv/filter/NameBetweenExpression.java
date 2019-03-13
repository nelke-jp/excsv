package jp.nelke.excsv.filter;

public class NameBetweenExpression extends NameExpression {

    private Comparable<Object> lo;
    private Comparable<Object> hi;

    public NameBetweenExpression(String name, Comparable<Object> lo, Comparable<Object> hi) {
        super(name);
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    protected boolean isAccept(Object value) {
        return this.lo.compareTo(value) <= 0 && hi.compareTo(value) >= 0;
    }

}
