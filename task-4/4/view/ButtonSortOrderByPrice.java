package view;

import model.Shop;

public class ButtonSortOrderByPrice implements IAction {
    Navigator navigator;
    Shop shop;

    public ButtonSortOrderByPrice(Shop shop, Navigator navigator) {
        this.navigator = navigator;
        this.shop=shop;
    }

    @Override
    public void execute() {
        shop.sortedOrderPrice();
    }
}
