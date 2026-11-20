package model;

import java.util.Comparator;
public class SortByStatus implements Comparator<Book>{

    public int compare(Book book1, Book book2) {
        return Boolean.compare(book2.isStatus(), book1.isStatus());
    }

}
