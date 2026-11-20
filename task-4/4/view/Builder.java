package view;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Builder {
    private final ConsoleFactory consoleFactory;
    private final Shop shop;
    private final WareHouse warehouse;
    private Menu rootMenu;
    private final Scanner scanner = new Scanner(System.in);

    public Builder(ConsoleFactory consoleFactory, Shop shop, WareHouse warehouse) {
        this.consoleFactory = consoleFactory;
        this.shop = shop;
        this.warehouse = warehouse;
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public void build() {
        Menu mainMenu = consoleFactory.createMenu("Главное меню");
        Menu orderMenu = consoleFactory.createMenu("Заказы");
        Menu listOrderMenu = consoleFactory.createMenu("Список заказов");
        Menu requestMenu = consoleFactory.createMenu("Запросы");
        Menu listRequestMenu = consoleFactory.createMenu("Список запросов");
        Menu warehouseMenu = consoleFactory.createMenu("Склад");
        Menu listBookMenu = consoleFactory.createMenu("Список книг");
        Menu oldBookMenu = consoleFactory.createMenu("Залежавшиеся книги");

        rootMenu = mainMenu;
        Navigator navigator = Navigator.getNavigator(mainMenu);

        IAction backToMain = () -> navigator.navigate(mainMenu);
        IAction backToOrders = () -> navigator.navigate(orderMenu);
        IAction backToRequests = () -> navigator.navigate(requestMenu);
        IAction backToWarehouse = () -> navigator.navigate(warehouseMenu);

        // === ГЛАВНОЕ МЕНЮ ===
        mainMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> navigator.navigate(orderMenu), "Заказы"));
        mainMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> navigator.navigate(requestMenu), "Запросы"));
        mainMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> navigator.navigate(warehouseMenu), "Склад"));
        mainMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> System.exit(0), "Выход"));

        // === МЕНЮ: ЗАКАЗЫ ===
        orderMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> navigator.navigate(listOrderMenu), "Список заказов"));
        orderMenu.getMenuitems().add(consoleFactory.createMenuItem(this::createOrderView, "Создать заказ"));
        orderMenu.getMenuitems().add(consoleFactory.createMenuItem(this::cancelOrderView, "Отменить заказ"));
        orderMenu.getMenuitems().add(consoleFactory.createMenuItem(this::changeOrderStatusView, "Сменить статус заказа"));
        orderMenu.getMenuitems().add(consoleFactory.createMenuItem(this::orderDetailsView, "Детали заказа"));
        orderMenu.getMenuitems().add(consoleFactory.createMenuItem(this::periodStatsView, "Статистика за период"));
        orderMenu.getMenuitems().add(consoleFactory.createMenuItem(backToMain, "Назад"));

        // Подменю: Список заказов
        listOrderMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> printOrders(shop.sortedOrderStatus()), "По статусу"));
        listOrderMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> printOrders(shop.sortedOrderData()), "По дате исполнения"));
        listOrderMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> printOrders(shop.sortedOrderPrice()), "По цене"));
        listOrderMenu.getMenuitems().add(consoleFactory.createMenuItem(backToOrders, "Назад"));

        // === МЕНЮ: ЗАПРОСЫ ===
        requestMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> navigator.navigate(listRequestMenu), "Список запросов"));
        requestMenu.getMenuitems().add(consoleFactory.createMenuItem(this::leaveRequestView, "Оставить запрос на книгу"));
        requestMenu.getMenuitems().add(consoleFactory.createMenuItem(backToMain, "Назад"));

        // Подменю: Список запросов
        listRequestMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> printRequests(shop.sortRequestsByCount()), "По количеству"));
        listRequestMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> printRequests(shop.sortRequestsByAlphabet()), "По алфавиту"));
        listRequestMenu.getMenuitems().add(consoleFactory.createMenuItem(backToRequests, "Назад"));

        // === МЕНЮ: СКЛАД ===
        warehouseMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> navigator.navigate(listBookMenu), "Список книг"));
        warehouseMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> navigator.navigate(oldBookMenu), "Залежавшиеся книги"));
        warehouseMenu.getMenuitems().add(consoleFactory.createMenuItem(this::addBookView, "Добавить книгу"));
        warehouseMenu.getMenuitems().add(consoleFactory.createMenuItem(this::writeOffBookView, "Списать книгу"));
        warehouseMenu.getMenuitems().add(consoleFactory.createMenuItem(this::findBookView, "Найти книгу и посмотреть всё о ней"));
        warehouseMenu.getMenuitems().add(consoleFactory.createMenuItem(backToMain, "Назад"));

        // Подменю: Список книг
        listBookMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> printBooks(warehouse.sortedName()), "По алфавиту"));
        listBookMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> printBooks(warehouse.sortedData()), "По дате издания"));
        listBookMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> printBooks(warehouse.sortedPrice()), "По цене"));
        listBookMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> printBooks(warehouse.sortedStatus()), "По наличию"));
        listBookMenu.getMenuitems().add(consoleFactory.createMenuItem(backToWarehouse, "Назад"));

        // Подменю: Залежавшиеся книги
        oldBookMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> printBooks(warehouse.oldBookByData()), "По дате поступления"));
        oldBookMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> printBooks(warehouse.oldBookByPrice()), "По цене"));
        oldBookMenu.getMenuitems().add(consoleFactory.createMenuItem(backToWarehouse, "Назад"));
    }

    // ================= МЕТОДЫ ОТОБРАЖЕНИЯ (VIEW) =================

    private void printOrders(ArrayList<Order> list) {
        if (list.isEmpty()) {
            System.out.println("Список заказов пуст.");
            return;
        }
        int idx = 1;
        for (Order o : list) {
            System.out.println(idx++ + ". Книга: '" + o.getBook().getName()
                    + "' Цена: " + o.getBook().getPrice()
                    + " Статус: " + o.getStatus()
                    + " Дата заказа: " + o.getDateOfOrder()
                    + " Покупатель: " + o.getCustomer().getFullname());
        }
    }

    private void printRequests(ArrayList<Request> list) {
        if (list.isEmpty()) {
            System.out.println("Список запросов пуст.");
            return;
        }
        for (Request r : list) {
            System.out.println("Книга: '" + r.getBook().getName()
                    + "' Запросов: " + r.getCount()
                    + " Статус: " + (r.isClosed() ? "Закрыт" : "Открыт"));
        }
    }

    private void printBooks(ArrayList<Book> list) {
        if (list.isEmpty()) {
            System.out.println("Книги не найдены.");
            return;
        }
        for (Book b : list) {
            System.out.println("Книга: '" + b.getName()
                    + "' Автор: " + b.getAuthor()
                    + " Цена: " + b.getPrice()
                    + " Издана: " + b.getDateOfPublication()
                    + " Поступила: " + b.getDateOfReceipt()
                    + " В наличии: " + (b.isStatus() ? "Да" : "Нет"));
        }
    }

    private void createOrderView() {
        System.out.print("Введите название книги: ");
        String bookName = scanner.nextLine();
        System.out.print("Введите ФИО покупателя: ");
        String fullName = scanner.nextLine();
        System.out.print("Введите адрес покупателя: ");
        String address = scanner.nextLine();
        System.out.print("Введите телефон покупателя: ");
        String phone = scanner.nextLine();

        Customer customer = new Customer(fullName, address);
        customer.setPhone(phone);

        Order o = shop.createOrder(bookName, customer);
        if (o == null) {
            System.out.println("Книга не найдена в каталоге склада.");
        } else if (o.hasRequest()) {
            System.out.println("Заказ создан! Книги нет в наличии — автоматически оформлен запрос.");
        } else {
            System.out.println("Заказ на книгу '" + bookName + "' успешно создан!");
        }
    }

    private Order selectOrderFromList() {
        ArrayList<Order> orders = shop.getOrders();
        if (orders.isEmpty()) {
            System.out.println("Заказов пока нет.");
            return null;
        }
        printOrders(orders);
        System.out.print("Введите номер заказа из списка: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= orders.size()) {
            System.out.println("Неверный номер заказа.");
            return null;
        }
        return orders.get(index);
    }

    private void cancelOrderView() {
        Order o = selectOrderFromList();
        if (o != null) {
            if (shop.cancelOrder(o)) {
                System.out.println("Заказ на '" + o.getBook().getName() + "' отменён.");
            } else {
                System.out.println("Не удалось отменить заказ (возможно, он уже завершён или отменён).");
            }
        }
    }

    private void changeOrderStatusView() {
        Order o = selectOrderFromList();
        if (o == null) return;

        System.out.println("Выберите новый статус: 1 - COMPLETED, 2 - CANCELED");
        int st = Integer.parseInt(scanner.nextLine());
        OrderStatus newStatus = (st == 1) ? OrderStatus.COMPLETED : OrderStatus.CANCELED;

        if (shop.changeStatus(o, newStatus)) {
            System.out.println("Статус заказа успешно изменён на " + newStatus);
        } else {
            System.out.println("Ошибка смены статуса (книга отсутствует, запрос не закрыт или заказ уже не NEW).");
        }
    }

    private void orderDetailsView() {
        Order o = selectOrderFromList();
        if (o != null) {
            System.out.println(" Детали заказа ");
            System.out.println("Книга: " + o.getBook().getName() + " (Автор: " + o.getBook().getAuthor() + ")");
            System.out.println("Цена: " + o.getBook().getPrice());
            System.out.println("Покупатель: " + o.getCustomer().getFullname()
                    + " Адрес: " + o.getCustomer().getAddress()
                    + " Тел.: " + o.getCustomer().getPhone());
            System.out.println("Статус: " + o.getStatus());
            System.out.println("Дата создания: " + o.getDateOfOrder());
        }
    }

    private void periodStatsView() {
        System.out.print("Введите начальную дату (ГГГГ-ММ-ДД, например 2026-01-01): ");
        LocalDate from = LocalDate.parse(scanner.nextLine());
        System.out.print("Введите конечную дату (ГГГГ-ММ-ДД, например 2026-12-31): ");
        LocalDate to = LocalDate.parse(scanner.nextLine());

        System.out.println("Выполненных заказов за период: " + shop.countOrders(from, to));
        System.out.println("Сумма заработанных средств: " + shop.fullPrice(from, to));

        System.out.println("Сортировать выполненные заказы: 1 - по дате, 2 - по цене");
        int sortChoice = Integer.parseInt(scanner.nextLine());
        if (sortChoice == 1) {
            printOrders(shop.CompletedOrderSortByData(from, to));
        } else {
            printOrders(shop.CompletedOrderSortByPrice(from, to));
        }
    }

    private void leaveRequestView() {
        System.out.print("Введите название книги для запроса: ");
        String name = scanner.nextLine();
        Book b = warehouse.searchBook(name);
        if (b == null) {
            System.out.println("Такой книги нет в каталоге.");
        } else if (b.isStatus()) {
            System.out.println("Эта книга уже есть в наличии на складе!");
        } else {
            Request r = shop.leaveRequest(name);
            System.out.println("Запрос на книгу '" + name + "' оформлен. Всего запросов: " + r.getCount());
        }
    }

    private void addBookView() {
        System.out.print("Введите название книги: ");
        String name = scanner.nextLine();
        Book existing = warehouse.searchBook(name);

        if (existing != null) {
            shop.addBookInShop(existing);
            System.out.println("Книга '" + name + "' снова в наличии! Запросы закрыты, ожидающие заказы выполнены.");
        } else {
            System.out.print("Введите автора: ");
            String author = scanner.nextLine();
            System.out.print("Введите описание: ");
            String desc = scanner.nextLine();
            System.out.print("Введите цену: ");
            int price = Integer.parseInt(scanner.nextLine());
            System.out.print("Введите дату издания (ГГГГ-ММ-ДД): ");
            LocalDate pubDate = LocalDate.parse(scanner.nextLine());

            Book newBook = new Book(name, true);
            newBook.setAuthor(author);
            newBook.setDescription(desc);
            newBook.setPrice(price);
            newBook.setDateOfPublication(pubDate);

            shop.addBookInShop(newBook);
            System.out.println("Новая книга '" + name + "' добавлена на склад.");
        }
    }

    private void writeOffBookView() {
        System.out.print("Введите название книги для списания: ");
        String name = scanner.nextLine();
        if (warehouse.writeOffBook(name)) {
            System.out.println("Книга '" + name + "' списана со склада (переведена в статус 'отсутствует').");
        } else {
            System.out.println("Книга не найдена или уже отсутствует.");
        }
    }

    private void findBookView() {
        System.out.print("Введите название книги: ");
        String name = scanner.nextLine();
        Book b = warehouse.searchBook(name);
        if (b == null) {
            System.out.println("Книга не найдена.");
        } else {
            System.out.println(" Информация о книге ");
            System.out.println("Название: " + b.getName());
            System.out.println("Автор: " + b.getAuthor());
            System.out.println("Описание: " + b.getDescription());
            System.out.println("Цена: " + b.getPrice());
            System.out.println("Дата издания: " + b.getDateOfPublication());
            System.out.println("Дата поступления: " + b.getDateOfReceipt());
            System.out.println("Статус: " + (b.isStatus() ? "В наличии" : "Отсутствует"));
        }
    }
}