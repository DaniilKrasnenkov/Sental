package model;

import java.time.LocalDate;
import java.util.Comparator;

public class SortOrderByData implements Comparator<Order> {
    public int compare(Order o1, Order o2) {
        LocalDate d1 = o1.getDateOfCompletion();
        LocalDate d2 = o2.getDateOfCompletion();
        if (d1 == null && d2 == null) return 0;
        if (d1 == null) return 1;   // null — в конец
        if (d2 == null) return -1;
        return d1.compareTo(d2);
    }
}
