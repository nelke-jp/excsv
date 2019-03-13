package jp.nelke.excsv.filter;

public class NameAndExpression implements NameFilter {

    private NameFilter[] expressions;

    protected NameAndExpression(NameFilter... expressions) {
        this.expressions = expressions;
    }

    public boolean isAccept(String name, Object value) {
        for (NameFilter expression : expressions) {
            if (!expression.isAccept(name, value)) {
                return false;
            }
        }

        return true;
    }

}
