package jp.nelke.excsv.converter;

import jp.nelke.excsv.exception.ConvertException;

/**
 * 指定した値を特定の値に変換するコンバータ
 *
 * @param <E> 変換後のオブジェクトの型
 */
public interface Converter<E> {

    /**
     * 引数を特定の型に変換して返す。
     *
     * @param value セルの値
     * @return 変換後の値
     */
    public E convert(Object value) throws ConvertException;
}
