package view;

public class ButtonListOrders implements IAction{
    Navigator navigator;
    Menu bookMenu;

    public ButtonListOrders(Menu bookMenu, Navigator navigator) {
        this.bookMenu = bookMenu;
        this.navigator = navigator;
    }

    @Override
    public void execute() {
        navigator.navigate(bookMenu);
    }
}
