package jp.nelke.excsv.filter;

public class NameOrExpression implements NameFilter {

    private NameFilter[] expressions;

    protected NameOrExpression(NameFilter... expressions) {
        this.expressions = expressions;
    }

    public boolean isAccept(String name, Object value) {
        for (NameFilter expression : expressions) {
            if (expression.isAccept(name, value)) {
                return true;
            }
        }

        return false;
    }

}
