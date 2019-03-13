package jp.nelke.excsv.exception;

/**
 * Excelのシートが見つからないことを表す例外
 */
@SuppressWarnings("serial")
public class SheetNotFoundException extends Exception {

    public SheetNotFoundException() {
        super();
    }

    public SheetNotFoundException(String message) {
        super(message);
    }

    public SheetNotFoundException(Throwable cause) {
        super(cause);
    }

    public SheetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
