package controller;

import view.Builder;
import view.MenuItem;
import view.Navigator;

import java.util.Scanner;

public class MenuController {
    private Builder builder;
    private Navigator navigator;
    boolean work;
    private Scanner scanner;

    public MenuController(Navigator navigator) {
        this.navigator = navigator;
        this.work = true;
        this.scanner = new Scanner(System.in);
    }

    public int in(){
        int a = scanner.nextInt();
        return a;
    }

    public void start() {
        while (work) {
            navigator.printMenu();
            int choice = in();

            if (choice == 0) {
                work = false;
            } else if(choice >= 1 && choice <= navigator.getCurrentMenu().getMenuitems().size()) {
                navigator.getCurrentMenu().getMenuitems().get(choice - 1).doAction();
            } else {
                System.out.println("Нет такого пункта");
            }
        }
    }
}