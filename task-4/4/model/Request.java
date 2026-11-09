package model;

import java.time.LocalDate;

public class Request {
    private final Book book;
    private final LocalDate date;
    private boolean closed;
    int count;

    public Request(Book book) {
        this.book = book;
        this.date = LocalDate.now();
        this.count=1;
    }
    public Book getBook() { return book; }
    public LocalDate getDate() { return date; }
    public boolean isClosed() { return closed; }
    public void close() { closed = true; }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public void inc(){
        count++;
    }
}