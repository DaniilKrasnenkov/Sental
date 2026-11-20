package model;

import java.util.Comparator;

public class SortByName implements Comparator<Book>{

    public int compare(Book book1, Book book2) {
        return book1.getName().compareTo(book2.getName());
    }

}
