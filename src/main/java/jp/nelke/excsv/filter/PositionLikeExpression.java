package jp.nelke.excsv.filter;

public class PositionLikeExpression extends PositionExpression {

    private String value;

    protected PositionLikeExpression(int position, String value) {
        super(position);
        this.value = value;
    }

    @Override
    protected boolean isAccept(Object value) {
        if (value == null) {
            return true;
        }

        if (value.toString().indexOf(this.value) != -1) {
            return true;
        }

        return false;
    }

}
