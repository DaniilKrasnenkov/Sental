package model;

import java.util.Comparator;

public class SortByDataOfPublication implements Comparator<Book> {
    public int compare(Book b1, Book b2) {
        return b1.getDateOfPublication().compareTo(b2.getDateOfPublication());
    }
}