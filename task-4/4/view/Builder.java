package view;

import model.Book;

import java.util.ArrayList;

public class Builder {
    private Menu rootMenu;

    public void buildMenu(){}

    public Menu getRootMenu() {
        return rootMenu;
    }
    // создание кнопок и меню
    public void build(){
        Book alice = new Book("Алиса в стране чудес", true);
        ArrayList<MenuItem> buttons1=new ArrayList<MenuItem>();
        ArrayList<MenuItem> buttons2=new ArrayList<MenuItem>();

        Menu mainMenu = new Menu("Главное меню", buttons1);
        Menu bookMenu = new Menu("Главное меню", buttons1);
        Navigator navigator = new Navigator(mainMenu);

        ButtonOrder order = new ButtonOrder(mainMenu,navigator);
        ButtonBook book = new ButtonBook(bookMenu,navigator, alice);
        ButtonPrintDescription description = new ButtonPrintDescription(book, navigator);
        ButtonAuthor author = new ButtonAuthor(book, navigator);

        MenuItem item1 = new MenuItem(order, "Заказ");
        MenuItem item2 = new MenuItem(book, "Книга");
        item2.setNextMenu(bookMenu);
        MenuItem itemb_1 = new MenuItem(description, "Посмотреть описание");
        MenuItem itemb_2 = new MenuItem(author, "Автор");

        buttons1.add(item1);
        buttons1.add(item2);
        buttons2.add(itemb_1);
        buttons2.add(itemb_2);

        navigator.printMenu();
        System.out.println(item1.getTitle());
    }
}
