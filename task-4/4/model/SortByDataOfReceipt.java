package model;

import java.util.Comparator;
public class SortByDataOfReceipt implements Comparator<Book>{

    public int compare(Book b1, Book b2) {
        if (b1.getDateOfReceipt() == null && b2.getDateOfReceipt() == null) return 0;
        if (b1.getDateOfReceipt() == null) return 1;
        if (b2.getDateOfReceipt() == null) return -1;
        return b1.getDateOfReceipt().compareTo(b2.getDateOfReceipt());
    }

}