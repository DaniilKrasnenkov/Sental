package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Datainitializer {

    public static Shop initShop() {
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book("Алиса в стране чудес", "Льюис Кэрролл", "Сказка о приключениях в воображаемом мире.",
                550, LocalDate.of(2021, 5, 10), LocalDate.now().minusMonths(8), true));
        books.add(new Book("1984", "Джордж Оруэлл", "Культовый роман-антиутопия.",
                700, LocalDate.of(2023, 2, 15), LocalDate.now().minusMonths(2), false));
        books.add(new Book("Мастер и Маргарита", "Михаил Булгаков", "Мистический роман о визите Воланда.",
                900, LocalDate.of(2020, 11, 1), LocalDate.now().minusMonths(1), true));
        books.add(new Book("Преступление и наказание", "Федор Достоевский", "Социально-философский роман.",
                450, LocalDate.of(2019, 3, 20), LocalDate.now().minusMonths(10), true));
        books.add(new Book("Ведьмак: Последнее желание", "Анджей Сапковский", "Первая книга цикла о Геральте.",
                850, LocalDate.of(2022, 8, 12), LocalDate.now().minusMonths(3), false));
        books.add(new Book("Дюна", "Фрэнк Герберт", "Фантастический роман о планете Арракис.",
                1200, LocalDate.of(2024, 1, 25), LocalDate.now().minusMonths(7), true));
        books.add(new Book("Философия Java", "Брюс Эккель", "Руководство по языку Java и ООП.",
                1800, LocalDate.of(2018, 6, 5), LocalDate.now().minusMonths(4), false));

        WareHouse warehouse = new WareHouse(books);
        Shop shop = new Shop(new ArrayList<>(), warehouse);

        Customer c1 = new Customer("Иван Иванов", "ул. Ленина 21, кв. 5");
        c1.setPhone("+7-900-111-22-33");
        Customer c2 = new Customer("Анна Смирнова", "пр. Гагарина 104, кв. 12");
        c2.setPhone("+7-900-444-55-66");
        Customer c3 = new Customer("Петр Соколов", "ул. Белинского 58, кв. 40");
        c3.setPhone("+7-900-777-88-99");
        Customer c4 = new Customer("Елена Попова", "ул. Горького 15, кв. 8");
        c4.setPhone("+7-900-000-11-22");

        Order o1 = shop.createOrder("Алиса в стране чудес", c1);
        shop.changeStatus(o1, OrderStatus.COMPLETED);
        o1.setDateOfOrder(LocalDate.now().minusDays(10));

        Order o2 = shop.createOrder("Мастер и Маргарита", c2);
        shop.changeStatus(o2, OrderStatus.COMPLETED);
        o2.setDateOfOrder(LocalDate.now().minusDays(5));

        Order o3 = shop.createOrder("Дюна", c3);
        shop.changeStatus(o3, OrderStatus.COMPLETED);
        o3.setDateOfOrder(LocalDate.now().minusDays(2));

        Order o4 = shop.createOrder("Преступление и наказание", c4);
        shop.cancelOrder(o4);
        o4.setDateOfOrder(LocalDate.now().minusDays(3));

        Order o5 = shop.createOrder("Мастер и Маргарита", c1);
        o5.setDateOfOrder(LocalDate.now().minusDays(1));

        shop.createOrder("1984", c2);
        shop.createOrder("1984", c3);
        shop.createOrder("Философия Java", c1);

        shop.leaveRequest("1984");
        shop.leaveRequest("Философия Java");
        shop.leaveRequest("Ведьмак: Последнее желание");

        return shop;
    }
}
