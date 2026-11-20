package view;

import java.util.ArrayList;

public class Menu {
    private String name;
    private ArrayList<MenuItem> menuitems;

    public Menu(String name, ArrayList<MenuItem> menuitems) {
        this.name = name;
        this.menuitems = menuitems;
    }

    public Menu(String name) {
        this.name = name;
        this.menuitems = new ArrayList<>(); // Список создается пустым
    }

    public ArrayList<MenuItem> getMenuitems() {
        return menuitems;
    }

    public void setMenuitems(ArrayList<MenuItem> menuitems) {
        this.menuitems = menuitems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
