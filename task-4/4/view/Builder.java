package view;

import model.Book;
import model.Shop;

public class Builder {
    private final ConsoleFactory consoleFactory;
    private final Book book;
    private Shop shop;
    private Menu rootMenu;
    private Navigator navigator;

    public Builder(ConsoleFactory consoleFactory, Book book) {
        this.consoleFactory = consoleFactory;
        this.book = book;
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public void build() {
        //Главное меню
        //├── Заказы
        //│     ├── Список заказов (по статусу / по дате исполнения / по цене)
        //│     ├── Создать заказ          (здесь вводим данные покупателя)
        //│     ├── Отменить заказ
        //│     ├── Сменить статус заказа
        //│     ├── Детали заказа
        //│     └── Статистика за период  (выполненные заказы, количество, сумма)
        //├── Запросы
        //│     ├── Список запросов (по количеству / по алфавиту)
        //│     └── Оставить запрос на книгу
        //├── Склад
        //│     ├── Список книг (по алфавиту / по дате издания / по цене / по наличию)
        //│     ├── Залежавшиеся книги (по дате поступления / по цене)
        //│     ├── Добавить книгу
        //│     ├── Списать книгу
        //│     └── Найти книгу и посмотреть всё о ней
        //└── Выход

        Menu mainMenu  = consoleFactory.createMenu("Главное меню");
        Menu orderMenu = consoleFactory.createMenu("Заказы");
        Menu ListorderMenu = consoleFactory.createMenu("Список заказов");
        Menu bookMenu  = consoleFactory.createMenu("Книга");
        Menu RequestMenu = consoleFactory.createMenu("Запросы");
        rootMenu = mainMenu;

        navigator = new Navigator(mainMenu);

        mainMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> navigator.navigate(orderMenu), "Заказы"));
        mainMenu.getMenuitems().add(consoleFactory.createMenuItem(()-> navigator.navigate(bookMenu), "Книга"));
        bookMenu.getMenuitems().add(consoleFactory.createMenuItem(()-> navigator.navigate(mainMenu), "Назад"));

        bookMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> System.out.println(this.book.getDescription()), "Посмотреть описание"));
        bookMenu.getMenuitems().add(consoleFactory.createMenuItem(() -> , "Автор"));

        orderMenu.getMenuitems().add(consoleFactory.createMenuItem(backToMain, "Назад"));
    }
}