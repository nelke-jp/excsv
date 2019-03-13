package jp.nelke.excsv.filter;

public class PositionNotExpression implements PositionFilter {

    private PositionFilter expression;

    protected PositionNotExpression(PositionFilter expression) {
        this.expression = expression;
    }

    public boolean isAccept(int position, Object value) {
        return !expression.isAccept(position, value);
    }

}
