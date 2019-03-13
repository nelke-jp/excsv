package jp.nelke.excsv;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Excelのシートを解析し、結果を返すためのParser。
 *
 * @param <E> 解析結果の型
 */
public interface SheetParser<E> {

    /**
     * シートを解析して結果を返す。
     *
     * @param sheet 解析する対象のシート
     * @param config 設定値
     * @return
     */
    public E parse(Workbook workbook, Sheet sheet, Config config) throws Exception;

}
