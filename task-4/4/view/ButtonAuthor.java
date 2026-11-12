package view;

import model.Book;

public class ButtonAuthor implements IAction{
    Navigator navigator;
    ButtonBook book;

    public ButtonAuthor(ButtonBook book, Navigator navigator) {
        this.book = book;
        this.navigator = navigator;
    }

    @Override
    public void execute() {
        System.out.println(book.getBook().getAuthor());
    }
}