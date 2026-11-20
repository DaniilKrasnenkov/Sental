package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class WareHouse {
    private ArrayList<Book> books;

    public WareHouse(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void addBook(Book a) {
        books.add(a);
        a.setStatus(true);
        a.setDateOfReceipt(LocalDate.now());
    }

    public boolean writeOffBook(String name) {
        Book b = searchBook(name);
        if (b != null && b.isStatus()) {
            b.setStatus(false);
            return true;
        }
        return false;
    }

    public void writeOffBook(Book a) {
        for (Book b : books) {
            if (b == a) {
                a.setStatus(false);
            }
        }
    }

    public Book searchBook(String name) {
        for (Book b : books) {
            if (b.getName().equalsIgnoreCase(name)) {
                return b;
            }
        }
        return null;
    }

    public ArrayList<Book> sortedData() {
        ArrayList<Book> copy = new ArrayList<>(books);
        copy.sort(new SortByDataOfPublication());
        return copy;
    }

    public ArrayList<Book> sortedName() {
        ArrayList<Book> copy = new ArrayList<>(books);
        copy.sort(new SortByName());
        return copy;
    }

    public ArrayList<Book> sortedPrice() {
        ArrayList<Book> copy = new ArrayList<>(books);
        copy.sort(new SortByPrice());
        return copy;
    }

    public ArrayList<Book> sortedStatus() {
        ArrayList<Book> copy = new ArrayList<>(books);
        copy.sort(new SortByStatus());
        return copy;
    }

    public ArrayList<Book> oldBookByPrice() {
        ArrayList<Book> result = new ArrayList<>();
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        for (Book book : books) {
            if (book.getDateOfReceipt() != null
                    && book.getDateOfReceipt().isBefore(sixMonthsAgo)
                    && book.isStatus()) {
                result.add(book);
            }
        }
        result.sort(new SortByPrice());
        return result;
    }

    public ArrayList<Book> oldBookByData() {
        ArrayList<Book> result = new ArrayList<>();
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        for (Book book : books) {
            if (book.getDateOfReceipt() != null
                    && book.getDateOfReceipt().isBefore(sixMonthsAgo)
                    && book.isStatus()) {
                result.add(book);
            }
        }
        result.sort(new SortByDataOfReceipt());
        return result;
    }
}
