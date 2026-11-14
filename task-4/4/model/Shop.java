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
        if (b == null) {
            System.out.println("Книга не найдена в каталоге: " + name);
            return null;
        }
        if (b.isStatus()) {
            System.out.println("Книга " + name + " есть в наличии");
            return null;
        }
        for (Request r : requests) {
            if (r.getBook().getName().equals(name) && !r.isClosed()) {
                r.inc();
                System.out.println("Запрос на " + name + " увеличен до " + r.getCount());
                return r;
            }
        }
        Request r = new Request(b);
        requests.add(r);
        System.out.println("Создан запрос на книгу: " + name);
        return r;
    }

    public void changeStatus(Order o, OrderStatus newStatus) {
        if (o == null) return;
        OrderStatus current = o.getStatus();

        if (current == newStatus) {
            System.out.println("Заказ уже в статусе " + newStatus);
            return;
        }
        if (current != OrderStatus.NEW) {
            System.out.println("Нельзя изменить статус завершённого заказа (" + current + ")");
            return;
        }
        switch (newStatus) {
            case COMPLETED:
                if (!o.getBook().isStatus()) {
                    System.out.println("Нельзя завершить заказ: книга '"
                            + o.getBook().getName() + "' отсутствует");
                    return;
                }
                if (o.hasRequest() && !o.getRequest().isClosed()) {
                    System.out.println("Нельзя завершить заказ: запрос на книгу ещё не выполнен");
                    return;
                }
                o.setStatus(OrderStatus.COMPLETED);
                System.out.println("Заказ на '" + o.getBook().getName() + "' выполнен");
                break;

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
                System.out.println("Заказ на '" + o.getBook().getName() + "' отменён");
                break;

            case NEW:
                System.out.println("Нельзя вернуть заказ в статус NEW");
                break;
        }
    }


    public void createOrder(String name, Customer customer) {
        Book b = warehouse.searchBook(name);
        if (b == null) {
            System.out.println("Книга не найдена в каталоге.");
            return;
        }

        Order o = new Order(OrderStatus.NEW, b, customer);
        o.setDateOfOrder(LocalDate.now());

        if (!b.isStatus()) {
            Request r = null;
            for (Request req : requests) {
                if (req.getBook().getName().equals(b.getName()) && !req.isClosed()) {
                    req.inc();
                    r = req;
                    break;
                }
            }
            if (r == null) {
                r = new Request(b);
                requests.add(r);
                System.out.println("Книги нет в наличии, cоздан запрос на книгу: " + b.getName());
            }
            o.setRequest(r);
        }

        orders.add(o);
    }
    public void cancelOrder(Order order) {
        if (order.getStatus() != OrderStatus.NEW) return;

        if (order.hasRequest() && !order.getRequest().isClosed()) {
            Request r = order.getRequest();
            if (r.getCount() > 1) {
                r.setCount(r.getCount() - 1);
            } else {
                r.close();
            }
        }
        order.setStatus(OrderStatus.CANCELED);
    }

    public void HandlerOrders() {
        for (Order o : orders) {
            if (o.getStatus() != OrderStatus.NEW) continue;
            boolean bookAvailable    = o.getBook().isStatus();
            boolean requestSatisfied = !o.hasRequest() || o.getRequest().isClosed();

            if (bookAvailable && requestSatisfied) {
                o.setStatus(OrderStatus.COMPLETED);
                o.setDateOfCompletion(LocalDate.now());
                System.out.println("Книга " + o.getBook().getName() + " выдана");
            } else {
                if (!o.hasRequest()) {
                    Request r = new Request(o.getBook());
                    requests.add(r);
                    o.setRequest(r);
                }
                System.out.println("Книга " + o.getBook().getName() + " ожидает");
            }
        }
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
            if (r.getBook().getName().equals(b.getName()) && !r.isClosed()) {
                r.close();
            }
        }
        for (Order o : orders) {
            if (o.getBook().getName().equals(b.getName())
                    && o.getStatus() == OrderStatus.NEW
                    && (!o.hasRequest() || o.getRequest().isClosed())) {
                o.setStatus(OrderStatus.COMPLETED);
                o.setDateOfCompletion(LocalDate.now());
                System.out.println("Ожидающий заказ на книгу " + b.getName() + " выполнен");
            }
        }
    }

    private boolean inPeriod(LocalDate d, LocalDate from, LocalDate to) {
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

    public void sortedOrderStatus() {
        ArrayList<Order> copy = new ArrayList<>(orders);
        copy.sort(new SortOrderByStatus());
        for (Order order : copy) {
            System.out.println("Заказ на книгу: " + order.getBook().getName()
                    + " Статус: " + order.getStatus()
                    + " Заказчик: " + order.getCustomer().getFullname());
        }
    }

    public void sortedOrderData() {
        ArrayList<Order> copy = new ArrayList<>(orders);
        copy.sort(new SortOrderByData());
        for (Order order : copy) {
            System.out.println("Заказ на книгу: " + order.getBook().getName()
                    + " Дата: " + order.getDateOfOrder()
                    + " Заказчик: " + order.getCustomer().getFullname());
        }
    }

    public void sortedOrderPrice() {
        ArrayList<Order> copy = new ArrayList<>(orders);
        copy.sort(new SortOrderByPrice());
        for (Order order : copy) {
            System.out.println("Заказ на книгу: " + order.getBook().getName()
                    + " Цена: " + order.getBook().getPrice()
                    + " Заказчик: " + order.getCustomer().getFullname());
        }
    }

    public void sortRequestsByCount(){
        requests.sort(new SortRequestByCount());
        this.print();

    }

    public void sortRequestsByAlphabet(){
        requests.sort(new SortRequestByAlphabet());
        this.print();
    }

    public void print(){
        for(Request r: requests){
            System.out.println(r.getBook().getName());
        }
    }



}