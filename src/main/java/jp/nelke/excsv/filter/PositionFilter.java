package jp.nelke.excsv.filter;

public interface PositionFilter {

    public boolean isAccept(int position, Object value);

}
