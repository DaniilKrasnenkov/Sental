package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Shop {
    private ArrayList<Order> orders;
    private WareHouse warehouse;
    private ArrayList<Request> requests;

    public Shop(ArrayList<Order> orders, WareHouse warehouse) {
        this.orders = orders;
        this.warehouse = warehouse;
        this.requests = new ArrayList<>();
    }

    public WareHouse getWarehouse() {
        return warehouse;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public Request leaveRequest(String name) {
        Book b = warehouse.searchBook(name);
        if (b == null || b.isStatus()) {
            return null;
        }
        for (Request r : requests) {
            if (r.getBook().getName().equalsIgnoreCase(name) && !r.isClosed()) {
                r.inc();
                return r;
            }
        }
        Request r = new Request(b);
        requests.add(r);
        return r;
    }

    public boolean changeStatus(Order o, OrderStatus newStatus) {
        if (o == null) return false;
        OrderStatus current = o.getStatus();

        if (current == newStatus || current != OrderStatus.NEW) {
            return false;
        }

        switch (newStatus) {
            case COMPLETED:
                if (!o.getBook().isStatus()) {
                    return false;
                }
                if (o.hasRequest() && !o.getRequest().isClosed()) {
                    return false;
                }
                o.setStatus(OrderStatus.COMPLETED);
                o.setDateOfCompletion(LocalDate.now());
                return true;

            case CANCELED:
                if (o.hasRequest() && !o.getRequest().isClosed()) {
                    Request r = o.getRequest();
                    if (r.getCount() > 1) {
                        r.setCount(r.getCount() - 1);
                    } else {
                        r.close();
                    }
                }
                o.setStatus(OrderStatus.CANCELED);
                o.setDateOfCompletion(LocalDate.now());
                return true;

            case NEW:
            default:
                return false;
        }
    }

    public Order createOrder(String name, Customer customer) {
        Book b = warehouse.searchBook(name);
        if (b == null) {
            return null;
        }

        Order o = new Order(OrderStatus.NEW, b, customer);
        o.setDateOfOrder(LocalDate.now());

        if (!b.isStatus()) {
            Request r = null;
            for (Request req : requests) {
                if (req.getBook().getName().equalsIgnoreCase(b.getName()) && !req.isClosed()) {
                    req.inc();
                    r = req;
                    break;
                }
            }
            if (r == null) {
                r = new Request(b);
                requests.add(r);
            }
            o.setRequest(r);
        }

        orders.add(o);
        return o;
    }

    public boolean cancelOrder(Order order) {
        return changeStatus(order, OrderStatus.CANCELED);
    }

    public void addBookInShop(Book b) {
        Book existing = warehouse.searchBook(b.getName());
        if (existing == null) {
            warehouse.addBook(b);
        } else {
            existing.setStatus(true);
            existing.setDateOfReceipt(LocalDate.now());
        }

        for (Request r : requests) {
            if (r.getBook().getName().equalsIgnoreCase(b.getName()) && !r.isClosed()) {
                r.close();
            }
        }
        for (Order o : orders) {
            if (o.getBook().getName().equalsIgnoreCase(b.getName())
                    && o.getStatus() == OrderStatus.NEW
                    && (!o.hasRequest() || o.getRequest().isClosed())) {
                o.setStatus(OrderStatus.COMPLETED);
                o.setDateOfCompletion(LocalDate.now());
            }
        }
    }

    private boolean inPeriod(LocalDate d, LocalDate from, LocalDate to) {
        if (d == null) return false;
        return !d.isBefore(from) && !d.isAfter(to);
    }

    public int fullPrice(LocalDate date1, LocalDate date2) {
        int sum = 0;
        for (Order order : orders) {
            if (order.getStatus() == OrderStatus.COMPLETED && inPeriod(order.getDateOfOrder(), date1, date2)) {
                sum += order.getBook().getPrice();
            }
        }
        return sum;
    }

    public int countOrders(LocalDate date1, LocalDate date2) {
        int count = 0;
        for (Order order : orders) {
            if (order.getStatus() == OrderStatus.COMPLETED && inPeriod(order.getDateOfOrder(), date1, date2)) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<Order> CompletedOrderSortByPrice(LocalDate date1, LocalDate date2) {
        ArrayList<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus() == OrderStatus.COMPLETED
                    && inPeriod(order.getDateOfOrder(), date1, date2)) {
                result.add(order);
            }
        }
        result.sort(new SortOrderByPrice());
        return result;
    }

    public ArrayList<Order> CompletedOrderSortByData(LocalDate date1, LocalDate date2) {
        ArrayList<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus() == OrderStatus.COMPLETED
                    && inPeriod(order.getDateOfOrder(), date1, date2)) {
                result.add(order);
            }
        }
        result.sort(new SortOrderByData());
        return result;
    }

    public ArrayList<Order> sortedOrderStatus() {
        ArrayList<Order> copy = new ArrayList<>(orders);
        copy.sort(new SortOrderByStatus());
        return copy;
    }

    public ArrayList<Order> sortedOrderData() {
        ArrayList<Order> copy = new ArrayList<>(orders);
        copy.sort(new SortOrderByData());
        return copy;
    }

    public ArrayList<Order> sortedOrderPrice() {
        ArrayList<Order> copy = new ArrayList<>(orders);
        copy.sort(new SortOrderByPrice());
        return copy;
    }

    public ArrayList<Request> sortRequestsByCount() {
        ArrayList<Request> copy = new ArrayList<>(requests);
        copy.sort(new SortRequestByCount());
        return copy;
    }

    public ArrayList<Request> sortRequestsByAlphabet() {
        ArrayList<Request> copy = new ArrayList<>(requests);
        copy.sort(new SortRequestByAlphabet());
        return copy;
    }
}