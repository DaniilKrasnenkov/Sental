package dz2_task4;

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

    public void addBook(Book a){
        books.add(a);
        a.setStatus(true);
    }

    public void writeOffBook(Book a){
        for (Book  b: books){
            if(b==a){
                a.setStatus(false);;
            }
        }
    }


    public Book searchBook(String name){
        for (Book  b: books){
            if(b.getName().equals(name)){
                return b;
            }
        }
        System.out.println("Книги нет на складе");
        return null;
    }


}
