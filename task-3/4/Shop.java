package dz2_task4;

import dz2_task2.Warehouse;

import java.util.ArrayList;

public class Shop {
    ArrayList<Order> orders;
    WareHouse warehouse;
//    ArrayList<Request> requests;

    public Shop(ArrayList<Order> orders, WareHouse warehouse) {
        this.orders = orders;
        this.warehouse = warehouse;
    }

    public Shop(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void createOrder(String name, Customer customer){
        Book b = this.warehouse.searchBook(name);
        Order o=new Order(Order.StatusOrder.NEW,b,customer);
        orders.add(o);
    }

    public void cancelOrder(Order order){
        if(order.request==true){
            order.setRequest(false);
        }
        order.setStatus(Order.StatusOrder.CANCELED);
    }

    public void HandlerOrders(){
        if(orders.isEmpty()){
            System.out.println("Запросов нет");
        }
        for(Order o: this.orders){
            if(o.book.isStatus()==true){
                System.out.println("Книга "+ o.book.getName() + " выдана");
                o.setStatus(Order.StatusOrder.COMPLETED);
            }
            else{
                o.setRequest(true);
                System.out.println("Создан запрос на книгу: "+o.book.getName());
            }
        }
    }

    public void addBookInShop(Book b) {
        this.warehouse.addBook(b);
        for (Order o : this.orders) {
            if (o.book.getName().equals(b.getName()) && o.getRequest() && o.getStatus() == Order.StatusOrder.NEW) {
                o.setRequest(false);
                o.setStatus(Order.StatusOrder.COMPLETED);
                System.out.println("Заказ на книгу " + b.getName() + " выполнен");
            }
        }
    }

}
