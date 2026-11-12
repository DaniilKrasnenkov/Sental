package view;

public class ButtonOrder implements IAction{
    Navigator navigator;
    Menu bookMenu;

    public ButtonOrder(Menu bookMenu, Navigator navigator) {
        this.bookMenu = bookMenu;
        this.navigator = navigator;
    }

    @Override
    public void execute() {
        navigator.navigate(bookMenu);
    }
}
