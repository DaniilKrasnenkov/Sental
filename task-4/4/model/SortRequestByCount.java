package model;

import java.util.Comparator;

public class SortRequestByCount implements Comparator<Request> {
    public int compare(Request r1, Request r2) {
        return Integer.compare(r1.getCount(), r2.getCount());
    }
}
