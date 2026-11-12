package view;

import model.Book;

public class Builder {
    private final Factory factory;
    private final Book book;          // модель приходит снаружи, Builder её не создаёт
    private Menu rootMenu;
    private Navigator navigator;

    public Builder(Factory factory, Book book) {
        this.factory = factory;
        this.book = book;
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public void build() {
        Menu mainMenu  = factory.createMenu("Главное меню");
        Menu orderMenu = factory.createMenu("Заказы");
        Menu bookMenu  = factory.createMenu("Книга");
        rootMenu = mainMenu;

        navigator = new Navigator(mainMenu);

        ButtonOrder toOrders = new ButtonOrder(orderMenu, navigator);
        ButtonBook toBook = new ButtonBook(bookMenu, navigator, book);
        ButtonPrintDescription showDescription = new ButtonPrintDescription(toBook, navigator);
        ButtonAuthor showAuthor = new ButtonAuthor(toBook, navigator);
        ButtonOrder backToMain = new ButtonOrder(mainMenu, navigator);

        mainMenu.getMenuitems().add(factory.createMenuItem(toOrders, "Заказы"));
        mainMenu.getMenuitems().add(factory.createMenuItem(toBook, "Книга"));

        bookMenu.getMenuitems().add(factory.createMenuItem(showDescription, "Посмотреть описание"));
        bookMenu.getMenuitems().add(factory.createMenuItem(showAuthor, "Автор"));
        bookMenu.getMenuitems().add(factory.createMenuItem(backToMain, "Назад"));

        orderMenu.getMenuitems().add(factory.createMenuItem(backToMain, "Назад"));
    }
}