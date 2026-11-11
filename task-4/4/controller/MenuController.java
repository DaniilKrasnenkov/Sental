package controller;

import view.Builder;
import view.MenuItem;
import view.Navigator;

import java.util.Scanner;

Scanner scanner = new Scanner(System.in);

public class MenuController {
    private Builder builder;
    private Navigator navigator;
    boolean work;

    public MenuController(Builder builder, Navigator navigator) {
        this.builder = builder;
        this.navigator = navigator;
        this.work = true;
    }

    public int in(){
        int a = scanner.nextInt();
        return a;
    }

    public void start(){
        while(work){
            if(0<this.in() && this.in()<navigator.getCurrentMenu().getMenuitems().size()) {
                builder.getRootMenu().getMenuitems().get(this.in() - 1).doAction();
            }
            if(in()==0){
                work=false;
            }
        }
    }
}
