package jp.nelke.excsv.filter;

public class PositionNullExpression extends PositionExpression {

    protected PositionNullExpression(int position) {
        super(position);
    }

    @Override
    protected boolean isAccept(Object value) {
        return value == null;
    }

}
