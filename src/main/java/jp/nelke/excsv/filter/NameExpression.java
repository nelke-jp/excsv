package jp.nelke.excsv.filter;

public abstract class NameExpression implements NameFilter {

    protected String name;

    protected NameExpression(String name) {
        this.name = name;
    }

    public boolean isAccept(String name, Object value) {
        if (this.name.equals(name)) {
            return isAccept(value);
        }
        return true;
    }

    protected abstract boolean isAccept(Object value);

}
