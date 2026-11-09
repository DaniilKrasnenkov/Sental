package model;

import java.util.Comparator;

public class SortByPrice implements Comparator<Book>{

    public int compare(Book book1, Book book2) {
        return Integer.compare(book1.getPrice(), book2.getPrice());
    }

}