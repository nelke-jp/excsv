package jp.nelke.excsv.exception;

/**
 * コンバートの失敗を表す例外クラス
 */
@SuppressWarnings("serial")
public class ConvertException extends RuntimeException {

    Integer row = null;

    Integer col = null;

    public ConvertException() {
        super();
    }

    public ConvertException(String message) {
        super(message);
    }

    public ConvertException(Throwable cause) {
        super(cause);
    }

    public ConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return super.toString() + " (column number:" + col + ", row number:" + row + ")";
    }

}
