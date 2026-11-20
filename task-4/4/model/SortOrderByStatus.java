package model;

import java.util.Comparator;

public class SortOrderByStatus implements Comparator<Order>{

    public int compare(Order order1, Order order2){
        return order1.getStatus().compareTo(order2.getStatus());
    }

}
