package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class WareHouse{
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
        a.setDateOfReceipt(LocalDate.now());
    }

    public void writeOffBook(Book a){
        for (Book  b: books){
            if(b==a){
                a.setStatus(false);
            }
        }
    }

    public Book searchBook(String name){
        for (Book  b: books){
            if(b.getName().equals(name)){
                return b;
            }
        }
        return null;
    }

    public void sortedData(){
        books.sort(new SortByDataOfPublication());
        for (Book book : books) {
            System.out.println(book.getName() + "дата издания: " + book.getDateOfPublication());
        }
    }

    public void sortedName(){
        books.sort(new SortByName());
        for(Book book:books){
            System.out.println(book.getName());
        }
    }

    public void sortedPrice(){
        books.sort(new SortByPrice());
        for(Book book:books){
            System.out.println(book.getName() + "Цена: " + book.getPrice());
        }
    }

    public void sortedStatus(){
        books.sort(new SortByStatus());
        for(Book book:books){
            System.out.println(book.getName() + "В наличии: " + book.isStatus());
        }
    }

    public void oldBookByPrice() {
        books.sort(new SortByPrice());
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        for (Book book : books) {
            if (book.getDateOfReceipt() != null
                    && book.getDateOfReceipt().isBefore(sixMonthsAgo)
                    && book.isStatus()) {
                System.out.println(book.getName() + " | " + book.getPrice()
                        + " | поступила: " + book.getDateOfReceipt());
            }
        }
    }

    public void oldBookByData() {
        books.sort(new SortByDataOfReceipt());
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        for (Book book : books) {
            if (book.getDateOfReceipt() != null
                    && book.getDateOfReceipt().isBefore(sixMonthsAgo)
                    && book.isStatus()) {
                System.out.println(book.getName() + " | поступила: " + book.getDateOfReceipt());
            }
        }
    }

}
