package jp.nelke.excsv;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import jp.nelke.excsv.exception.SheetNotFoundException;
import jp.nelke.excsv.exception.WorkbookNotOpenException;

/**
 * エクセル解析用のクラス
 */
public class ExCsv {

    public static <E> E load(String filePath, Config config, SheetParser<E> sheetParser) throws Exception {
        InputStream is = new FileInputStream(filePath);
        try {
            return load(is, 0, config, sheetParser);
        }
        finally {
            close(is);
        }
    }

    public static <E> E load(File file, Config config, SheetParser<E> sheetParser) throws Exception {
        InputStream is = new FileInputStream(file);
        try {
            return load(is, 0, config, sheetParser);
        }
        finally {
            close(is);
        }
    }

    public static <E> E load(String filePath, int sheetIndex, Config config, SheetParser<E> sheetParser)
            throws Exception {
        InputStream is = new FileInputStream(filePath);
        try {
            return load(is, sheetIndex, config, sheetParser);
        }
        finally {
            close(is);
        }
    }

    public static <E> E load(File file, int sheetIndex, Config config, SheetParser<E> sheetParser) throws Exception {
        InputStream is = new FileInputStream(file);
        try {
            return load(is, sheetIndex, config, sheetParser);
        }
        finally {
            close(is);
        }
    }

    public static <E> E load(InputStream is, int sheetIndex, Config config, SheetParser<E> sheetParser)
            throws Exception {
        // Workbookの取得
        Workbook workbook = getWorkbook(is);
        // Sheetの取得
        Sheet sheet = workbook.getSheetAt(sheetIndex);

        return load(workbook, sheet, config, sheetParser);
    }

    public static <E> E load(String filePath, String sheetName, Config config, SheetParser<E> sheetParser)
            throws Exception {
        InputStream is = new FileInputStream(filePath);
        try {
            return load(is, sheetName, config, sheetParser);
        }
        finally {
            close(is);
        }
    }

    public static <E> E load(File file, String sheetName, Config config, SheetParser<E> sheetParser) throws Exception {
        InputStream is = new FileInputStream(file);
        try {
            return load(is, sheetName, config, sheetParser);
        }
        finally {
            close(is);
        }
    }

    public static <E> E load(InputStream is, String sheetName, Config config, SheetParser<E> sheetParser)
            throws Exception {
        // Workbookの取得
        Workbook workbook = getWorkbook(is);
        // Sheetの取得
        Sheet sheet = workbook.getSheet(sheetName);

        return load(workbook, sheet, config, sheetParser);
    }

    private static <E> E load(Workbook workbook, Sheet sheet, Config config, SheetParser<E> sheetParser)
            throws Exception {
        if (sheet == null) {
            throw new SheetNotFoundException("Sheet does not exist.");
        }

        // 解析
        E result = sheetParser.parse(workbook, sheet, config);

        return result;
    }

    public static <E> Map<String, E> loadAll(String filePath, Config config, SheetParser<E> sheetParser)
            throws Exception {
        InputStream is = new FileInputStream(filePath);
        try {
            return loadAll(is, config, sheetParser);
        }
        finally {
            close(is);
        }
    }

    public static <E> Map<String, E> loadAll(File file, Config config, SheetParser<E> sheetParser) throws Exception {
        InputStream is = new FileInputStream(file);
        try {
            return loadAll(is, config, sheetParser);
        }
        finally {
            close(is);
        }
    }

    public static <E> Map<String, E> loadAll(InputStream is, Config config, SheetParser<E> sheetParser)
            throws Exception {
        // Workbookの取得
        Workbook workbook = getWorkbook(is);

        Map<String, E> map = new LinkedHashMap<String, E>();
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            map.put(sheet.getSheetName(), load(workbook, sheet, config, sheetParser));
        }

        return map;
    }

    /**
     * ファイルデータをWorkbook形式に変換する
     *
     * @return Workbook ワークブック
     * @throws WorkbookNotOpenException Excelファイルが壊れている、パスワードがかかっているなどで開けない場合
     */
    private static Workbook getWorkbook(InputStream is) throws IOException, WorkbookNotOpenException {
        return getWorkbook(is, null);
    }

    /**
     * ファイルデータをWorkbook形式に変換する
     *
     * @return Workbook ワークブック
     * @throws WorkbookNotOpenException Excelファイルが壊れている、パスワードがかかっているなどで開けない場合
     */
    private static Workbook getWorkbook(InputStream is, String password) throws IOException, WorkbookNotOpenException {
        if (password != null) {
            Biff8EncryptionKey.setCurrentUserPassword(password);
        }

        try {
            return WorkbookFactory.create(is);
        }
        catch (IOException e) {
            throw e;
        }
        catch (Exception e) {
            throw new WorkbookNotOpenException(e);
        }
    }

    protected static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        }
        catch (final IOException e) {

        }
    }
}
