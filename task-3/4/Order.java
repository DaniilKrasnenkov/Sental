package dz2_task4;

public class Order {
    enum StatusOrder{NEW,CANCELED,COMPLETED};
    StatusOrder a;
    Book book;
    Customer customer;
    boolean request;

    public Order(StatusOrder a, Book book, Customer customer) {
        this.a = a;
        this.book = book;
        this.customer=customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public StatusOrder getStatus(){
        return this.a;
    }

    public void setStatus(StatusOrder status){
        this.a=status;
    }

    public boolean getRequest(){
        return this.request;
    }

    public void setRequest(boolean a){
        this.request=a;
    }
}
