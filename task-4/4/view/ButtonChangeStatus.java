package view;

import model.Order;
import model.OrderStatus;
import model.Shop;

public class ButtonChangeStatus implements IAction{
    Navigator navigator;
    Shop shop;
    Order o;
    OrderStatus status;

    public ButtonChangeStatus(Navigator navigator, Order o, Shop shop, OrderStatus status) {
        this.navigator = navigator;
        this.o = o;
        this.shop = shop;
        this.status = status;
    }

    @Override
    public void execute() {
        shop.changeStatus(o, status);
    }
}
