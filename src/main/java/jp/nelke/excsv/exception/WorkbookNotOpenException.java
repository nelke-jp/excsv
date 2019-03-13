package jp.nelke.excsv.exception;

/**
 * ワークブックが開けないことを表す例外
 */
@SuppressWarnings("serial")
public class WorkbookNotOpenException extends Exception {

    public WorkbookNotOpenException() {
        super();
    }

    public WorkbookNotOpenException(String message) {
        super(message);
    }

    public WorkbookNotOpenException(Throwable cause) {
        super(cause);
    }

    public WorkbookNotOpenException(String message, Throwable cause) {
        super(message, cause);
    }
}
