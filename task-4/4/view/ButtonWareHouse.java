package view;

public class ButtonWareHouse implements IAction{
    Navigator navigator;
    Menu bookMenu;

    public ButtonWareHouse(Menu bookMenu, Navigator navigator) {
        this.bookMenu = bookMenu;
        this.navigator = navigator;
    }

    @Override
    public void execute() {
        navigator.navigate(bookMenu);
    }

}
