package view;

import model.Shop;

public class ButtonSortOrderByData implements  IAction{
    Navigator navigator;
    Shop shop;

    public ButtonSortOrderByData(Shop shop, Navigator navigator) {
        this.navigator = navigator;
        this.shop=shop;
    }

    @Override
    public void execute() {
        shop.sortedOrderData();
    }
}
