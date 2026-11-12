import controller.MenuController;
import model.*;
import view.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
        public static void main(String[] args) {
                LocalDate today = LocalDate.now();

                Book alice = new Book("Алиса в стране чудес", true);
                alice.setPrice(500);
                alice.setAuthor("Льюис Кэрролл");
                alice.setDescription("Сказка о девочке, упавшей в кроличью нору");
                alice.setDateOfPublication(LocalDate.of(1865, 11, 26));
                alice.setDateOfReceipt(today.minusMonths(8));

                Book war = new Book("Война и мир", true);
                war.setPrice(900);
                war.setDateOfPublication(LocalDate.of(1869, 1, 1));
                war.setDateOfReceipt(today.minusMonths(1));

                Book b1984 = new Book("1984", false);
                b1984.setPrice(400);
                b1984.setAuthor("Джордж Оруэлл");
                b1984.setDateOfPublication(LocalDate.of(1949, 6, 8));

                Book master = new Book("Мастер и Маргарита", false);
                master.setPrice(600);
                master.setDateOfPublication(LocalDate.of(1967, 1, 1));

                Book dune = new Book("Дюна", true);
                dune.setPrice(700);
                dune.setDateOfPublication(LocalDate.of(1965, 8, 1));
                dune.setDateOfReceipt(today.minusMonths(2));

                ArrayList<Book> books = new ArrayList<>();
                books.add(alice);
                books.add(war);
                books.add(b1984);
                books.add(master);
                books.add(dune);

                WareHouse warehouse = new WareHouse(books);
                Shop shop = new Shop(new ArrayList<>(), warehouse);

                Customer c1 = new Customer("Иван Иванов", "Ленина 21, 5");
                c1.setPhone("+7 900 111-22-33");
                Customer c2 = new Customer("Пётр Петров", "Мира 10, 3");
//
//                System.out.println("о алфавиту");      warehouse.sortedName();
//                System.out.println("по цене");          warehouse.sortedPrice();
//                System.out.println("по дате издания");  warehouse.sortedData();
//                System.out.println("по наличию");       warehouse.sortedStatus();
//                System.out.print("описание: ");        alice.watchDescription();

//                shop.createOrder("Война и мир", c1);         // в наличии, запроса нет
//                shop.createOrder("1984", c1);                // нет в наличии, новый запрос
//                shop.createOrder("1984", c2);                // тот же запрос, счётчик +1
//                shop.createOrder("Мастер и Маргарита", c2);  // нет в наличии, новый запрос
//                shop.createOrder("Несуществующая", c1);      // нет в каталоге

//                shop.getOrders().get(0).setDateOfOrder(today.minusDays(5));
//                shop.getOrders().get(1).setDateOfOrder(today.minusDays(2));
//                shop.getOrders().get(2).setDateOfOrder(today.minusDays(10));
//                shop.getOrders().get(3).setDateOfOrder(today);
//
//                System.out.println("запросы по количеству");  shop.sortRequestsByCount();
//                System.out.println("запросы по алфавиту");    shop.sortRequestsByAlphabet();
//
//                shop.leaveRequest("1984");              // открытый запрос уже есть, счётчик +1
//                shop.leaveRequest("Война и мир");       // в наличии, отказ
//                shop.leaveRequest("Несуществующая");    // нет в каталоге, отказ
//                for (Request r : shop.getRequests()) {
//                        System.out.println(r.getBook().getName() + "запросов: " + r.getCount() + "закрыт: " + r.isClosed());
//                }
//
//                shop.HandlerOrders();
//                System.out.println("второй вызов");
//                shop.HandlerOrders();
//                for (Order o : shop.getOrders()) {
//                        System.out.println(o.getBook().getName() + o.getStatus()
//                                + "создан: " + o.getDateOfOrder()
//                                + "выполнен: " + (o.getDateOfCompletion() != null ? o.getDateOfCompletion() : "—"));
//                }
//
//                System.out.println("'1984' в COMPLETED (книги нет), отказ");
//                shop.changeStatus(shop.getOrders().get(1), OrderStatus.COMPLETED);
//                System.out.println("'Война и мир' в COMPLETED (уже выполнен), отказ");
//                shop.changeStatus(shop.getOrders().get(0), OrderStatus.COMPLETED);
//                System.out.println("'Война и мир' в NEW (нельзя откатить), отказ");
//                shop.changeStatus(shop.getOrders().get(0), OrderStatus.NEW);
//                System.out.println("'Мастер и Маргарита' в CANCELED, запрос закрывается");
//                shop.changeStatus(shop.getOrders().get(3), OrderStatus.CANCELED);
//                System.out.println("cancelOrder на выполненный заказ, ничего не меняется");
//                shop.cancelOrder(shop.getOrders().get(0));
//                for (Request r : shop.getRequests()) {
//                        System.out.println(r.getBook().getName() + "запросов: " + r.getCount() + "закрыт: " + r.isClosed());
//                }
//
//                System.out.println("по статусу");              shop.sortedOrderStatus();
//                System.out.println("по дате исполнения");      shop.sortedOrderData();
//                System.out.println("по цене");                 shop.sortedOrderPrice();
//
//                shop.addBookInShop(b1984);
//                System.out.println("Книг на складе: " + warehouse.getBooks().size());
//                for (Request r : shop.getRequests()) {
//                        System.out.println(r.getBook().getName() + "запросов: " + r.getCount() + " закрыт: " + r.isClosed());
//                }
//                // даты исполнения задаём вручную, чтобы проверить границы периода
//                shop.getOrders().get(0).setDateOfCompletion(today.minusDays(10));  // вне периода
//                shop.getOrders().get(1).setDateOfCompletion(today.minusDays(3));   // ровно нижняя граница
//                shop.getOrders().get(2).setDateOfCompletion(today.plusDays(1));    // ровно верхняя граница
//
//                LocalDate from = today.minusDays(3);
//                LocalDate to = today.plusDays(1);
//                System.out.println("Сумма: " + shop.fullPrice(from, to) + " заказов: " + shop.countOrders(from, to));
//                System.out.println("выполненные по цене");
//                for (Order o : shop.CompletedOrderSortByPrice(from, to)) {
//                        System.out.println(o.getBook().getName() + " " + o.getBook().getPrice() + " выполнен: " + o.getDateOfCompletion());
//                }
//                System.out.println("выполненные по дате исполнения");
//                for (Order o : shop.CompletedOrderSortByData(from, to)) {
//                        System.out.println(o.getBook().getName() + " | " + o.getBook().getPrice() + " выполнен: " + o.getDateOfCompletion());
//                }
//                System.out.println("Узкий период (today-2 .. today): сумма "
//                        + shop.fullPrice(today.minusDays(2), today) + " (ожидаем 0)");
//
//                System.out.println("по цене");             warehouse.oldBookByPrice();
//                System.out.println("по дате поступления"); warehouse.oldBookByData();
//                System.out.println("после списания 'Алисы' список пуст");
//                warehouse.writeOffBook(alice);
//                warehouse.oldBookByPrice();
//
//                shop.getOrders().get(1).DetailsOfOrder();
//
//
//                shop.createOrder("Дюна", c1);
//                shop.createOrder("Дюна", c2);
//                warehouse.writeOffBook(dune);
//                shop.HandlerOrders();
//                for (Request r : shop.getRequests()) {
//                        System.out.println(r.getBook().getName() + " запросов: " + r.getCount() + " закрыт: " + r.isClosed());
//                }
//                System.out.println(" книга снова поступила");
//                shop.addBookInShop(dune);
//                for (Order o : shop.getOrders()) {
//                        System.out.println(o.getBook().getName() + " " + o.getStatus());
//                }

                Factory factory = new Factory();

                Builder builder = new Builder(factory, alice);
                builder.build();
                MenuController controller = new MenuController(builder.getNavigator());

                controller.start();

        }
}