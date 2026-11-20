package view;

public class Navigator {
    private Menu currentMenu;
    private boolean closed;

    private Navigator(Menu currentMenu) {
        this.currentMenu = currentMenu;
        this.closed = true;
    }

    private static Navigator instance;

    public static Navigator getNavigator(Menu menu) {
        if (instance == null) {
            instance = new Navigator(menu);
        }
        return instance;
    }

    public void run() {
        closed = false;
        printMenu();
    }

    public void printMenu() {
        System.out.println("\n " + this.currentMenu.getName() + "  ");
        int i = 1;
        for (MenuItem menuitem : this.currentMenu.getMenuitems()) {
            System.out.println(i++ + ". " + menuitem.getTitle());
        }
        System.out.println("0. Выход из программы");
        System.out.print("Выберите пункт: ");
    }

    public void navigate(Menu menu) {
        setCurrentMenu(menu);
    }

    public void setCurrentMenu(Menu m) {
        this.currentMenu = m;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }
}