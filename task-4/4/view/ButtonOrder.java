package view;

public class ButtonOrder implements IAction{
    Navigator navigator;
    Menu BookMenu;

    public ButtonOrder(Menu bookMenu, Navigator navigator) {
        BookMenu = bookMenu;
        this.navigator = navigator;
    }

    public ButtonOrder() {

    }

    @Override
    public void execute() {
        navigator.setCurrentMenu(BookMenu);
    }
}
