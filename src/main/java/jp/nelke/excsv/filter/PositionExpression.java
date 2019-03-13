package jp.nelke.excsv.filter;

public abstract class PositionExpression implements PositionFilter {

    protected int position;

    protected PositionExpression(int position) {
        this.position = position;
    }

    public boolean isAccept(int position, Object value) {
        if (this.position < 0 || this.position == position) {
            return isAccept(value);
        }
        return true;
    }

    protected abstract boolean isAccept(Object value);

}
