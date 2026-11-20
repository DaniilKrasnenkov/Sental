package model;

import java.util.Comparator;

public class SortRequestByAlphabet implements Comparator<Request> {

    public int compare(Request r1, Request r2){
        return r1.getBook().getName().compareTo(r2.getBook().getName());
    }
}
