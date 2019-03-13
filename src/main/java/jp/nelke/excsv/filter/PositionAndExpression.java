package jp.nelke.excsv.filter;

import java.util.Arrays;
import java.util.Collection;

public class PositionAndExpression implements PositionFilter {

    private Collection<PositionFilter> expressions;

    protected PositionAndExpression(PositionFilter... expressions) {
        this.expressions = Arrays.asList(expressions);
    }

    public boolean isAccept(int position, Object value) {
        for (PositionFilter expression : expressions) {
            if (!expression.isAccept(position, value)) {
                return false;
            }
        }

        return true;
    }

}
