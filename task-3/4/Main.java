package dz2_task4;

import dz2_task2.Warehouse;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Book book1 = new Book("Алиса в стране чудес", true);
        Book book2 = new Book("1984",  false);

        ArrayList<Book> Books = new ArrayList<>();
        Books.add(book1);
        Books.add(book2);

        WareHouse warehouse = new WareHouse(Books);

        Shop shop = new Shop(new ArrayList<>(), warehouse);

        Customer customer = new Customer("Иван Иванов", "Ленина 21, 5");

        shop.createOrder("Алиса в стране чудес", customer);
        shop.createOrder("1984", customer);

        shop.HandlerOrders();

        shop.addBookInShop(book2);

    }
}