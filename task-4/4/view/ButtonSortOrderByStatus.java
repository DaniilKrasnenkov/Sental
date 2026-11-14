package view;

import model.Shop;

public class ButtonSortOrderByStatus implements IAction{
    Navigator navigator;
    Shop shop;

    public ButtonSortOrderByStatus(Shop shop, Navigator navigator) {
        this.navigator = navigator;
        this.shop=shop;
    }

    @Override
    public void execute() {
        shop.sortedOrderStatus();
    }
}
