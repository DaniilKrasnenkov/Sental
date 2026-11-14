package view;

public class ButtonRequest implements IAction{
    Navigator navigator;
    Menu bookMenu;

    public ButtonRequest(Menu bookMenu, Navigator navigator) {
        this.bookMenu = bookMenu;
        this.navigator = navigator;
    }

    @Override
    public void execute() {
        navigator.navigate(bookMenu);
    }
}
