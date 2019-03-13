package jp.nelke.excsv.filter;

public interface NameFilter {

    public boolean isAccept(String name, Object value);

}
