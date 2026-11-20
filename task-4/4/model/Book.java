package model;

import java.time.LocalDate;

public class Book {
    private String name;
    private String author;
    private String Description;

    private int price;
    private boolean status;

    private LocalDate dateOfPublication;
    private LocalDate dateOfReceipt;



    public Book(String name, boolean status){
        this.name=name;
        this.status=status;
    }

    public Book(String name, String author, String description, int price,
                LocalDate dateOfPublication, LocalDate dateOfReceipt, boolean status) {
        this.name = name;
        this.author = author;
        this.Description = description;
        this.price = price;
        this.dateOfPublication = dateOfPublication;
        this.dateOfReceipt = dateOfReceipt;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(LocalDate dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public LocalDate getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(LocalDate dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    public void watchDescription(){
        System.out.println(getDescription());
    }
}
