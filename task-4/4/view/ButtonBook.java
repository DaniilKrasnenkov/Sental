package view;

import model.Book;

public class ButtonBook implements IAction{
    Navigator navigator;
    Menu BookMenu;
    private Book book;

    public ButtonBook(Menu bookMenu, Navigator navigator, Book book) {
        BookMenu = bookMenu;
        this.navigator = navigator;
        this.book=book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public void execute() {
        navigator.setCurrentMenu(BookMenu);
    }
}
