package model;

import java.util.Comparator;

public class SortOrderByPrice implements Comparator<Order> {

    SortByPrice sort = new SortByPrice();

    public int compare(Order order1, Order order2){
        return sort.compare(order1.getBook(),order2.getBook());
    }

}
