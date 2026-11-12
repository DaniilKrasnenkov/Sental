package view;

public class Navigator {
    private Menu currentMenu;
    private boolean closed;

    public Navigator(Menu currentMenu) {
        this.currentMenu = currentMenu;
        this.closed = true;
    }

    public void run(){
        closed=false;
        printMenu();
    }

    public void printMenu(){
        for(MenuItem menuitem : this.currentMenu.getMenuitems()){
            System.out.println(menuitem.getTitle());
        }
    }

    public void navigate(Menu menu){
        setCurrentMenu(menu);
    }


    public void setCurrentMenu(Menu m) {
        this.currentMenu=m;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }
}
