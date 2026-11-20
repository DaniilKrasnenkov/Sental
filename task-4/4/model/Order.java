package model;

import java.time.LocalDate;

public class Order {
    private OrderStatus status;
    private Book book;
    private LocalDate dateOfOrder;
    private LocalDate dateOfCompletion;
    private Customer customer;
    private Request request;


    public Order(OrderStatus a, Book book, Customer customer) {
        this.status = a;
        this.book = book;
        this.customer=customer;
    }


    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(LocalDate dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public LocalDate getDateOfCompletion() { return dateOfCompletion; }

    public void setDateOfCompletion(LocalDate dateOfCompletion) { this.dateOfCompletion = dateOfCompletion; }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public boolean hasRequest() {
        return request != null;
    }

    public void DetailsOfOrder(){
        System.out.println("Книга: " + book.getName() + " Автор: " + book.getAuthor());
        System.out.println("Цена: " + book.getPrice());
        System.out.println("Заказчик: " + customer.getFullname() + " Телефон: " + customer.getPhone());
        System.out.println("Статус заказа: " + status);
        System.out.println("Дата создания: " + dateOfOrder);
    }


}
