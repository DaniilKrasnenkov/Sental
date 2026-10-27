package dz2_task2;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Product phone = new Technic(0.5, "iphone", "15pro");
        Product sofa = new Furniture(30, "cheburashka", "Velur");
        Product apples = new Food(10, "antonovka", "3 weeks");

        Warehouse warehouse = new Warehouse(40);

        warehouse.addProduct(phone);
        warehouse.addProduct(sofa);
        warehouse.addProduct(apples);
    }
}
