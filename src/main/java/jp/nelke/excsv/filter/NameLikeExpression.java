package jp.nelke.excsv.filter;

public class NameLikeExpression extends NameExpression {

    private String value;

    public NameLikeExpression(String name, String value) {
        super(name);
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
