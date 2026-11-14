package view;

import model.Shop;

import java.time.LocalDate;

public class ButtonCountOrders implements IAction{
    Navigator navigator;
    Shop shop;
    LocalDate date1;
    LocalDate date2;

    public ButtonCountOrders(LocalDate date1, LocalDate date2, Navigator navigator, Shop shop) {
        this.date1 = date1;
        this.date2 = date2;
        this.navigator = navigator;
        this.shop = shop;
    }

    @Override
    public void execute() {
        shop.countOrders(date1, date2);
    }

}
