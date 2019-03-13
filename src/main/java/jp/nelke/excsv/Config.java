package jp.nelke.excsv;

/**
 * ExcelParser用の設定クラス
 */
public class Config {

    /** ヘッダの行番号 */
    private Integer headRowNumber = null;

    /** 解析を開始する列番号 */
    private Integer firstColumnNumber = null;

    /** 解析を終了する列番号 */
    private Integer lastColumnNumber = null;

    /** 解析を開始する行番号 */
    private Integer firstRowNumber = null;

    /** 解析を終了する行番号 */
    private Integer lastRowNumber = null;

    /** 名前定義 */
    private String name = null;

    /** 空行を無視するかどうか */
    private boolean ignoreEmptyRow = false;

    public Integer getHeadRowNumber() {
        return headRowNumber;
    }

    public void setHeadRowNumber(Integer headRowNumber) {
        this.headRowNumber = headRowNumber;
    }

    public Integer getFirstColumnNumber() {
        return firstColumnNumber;
    }

    public void setFirstColumnNumber(Integer firstColumnNumber) {
        this.firstColumnNumber = firstColumnNumber;
    }

    public Integer getLastColumnNumber() {
        return lastColumnNumber;
    }

    public void setLastColumnNumber(Integer lastColumnNumber) {
        this.lastColumnNumber = lastColumnNumber;
    }

    public Integer getFirstRowNumber() {
        return firstRowNumber;
    }

    public void setFirstRowNumber(Integer firstRowNumber) {
        this.firstRowNumber = firstRowNumber;
    }

    public Integer getLastRowNumber() {
        return lastRowNumber;
    }

    public void setLastRowNumber(Integer lastRowNumber) {
        this.lastRowNumber = lastRowNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIgnoreEmptyRow() {
        return ignoreEmptyRow;
    }

    public void setIgnoreEmptyRow(boolean ignoreEmptyRow) {
        this.ignoreEmptyRow = ignoreEmptyRow;
    }

}
