package view;

public class MenuItem {
    private String title;
    private IAction action;
    private Menu nextMenu;

    public void doAction(){
        action.execute();
    }

    public MenuItem(IAction action, String title) {
        this.action = action;
        this.title = title;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }

    public void setNextMenu(Menu nextMenu) {
        this.nextMenu = nextMenu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
