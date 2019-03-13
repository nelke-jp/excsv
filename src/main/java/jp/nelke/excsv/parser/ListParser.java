package jp.nelke.excsv.parser;

import java.util.List;

import jp.nelke.excsv.SheetParser;

public interface ListParser<E> extends SheetParser<List<E>> {

    public ListParser<E> limit(int limit);

    public ListParser<E> offset(int offset);

}
