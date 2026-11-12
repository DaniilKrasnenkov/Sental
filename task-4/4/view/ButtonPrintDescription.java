package view;

import model.Book;

public class ButtonPrintDescription implements IAction{
    Navigator navigator;
    ButtonBook book;

    public ButtonPrintDescription(ButtonBook book, Navigator navigator) {
        this.book = book;
        this.navigator = navigator;
    }

    @Override
    public void execute() {
        System.out.println(book.getBook().getDescription());
    }
}

