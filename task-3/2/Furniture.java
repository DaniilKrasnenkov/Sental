package dz2_task2;

public class Furniture extends Product{
    private String material;
    Furniture(double weight,String name, String material){
        super(weight, name);
        this.material=material;
    }
}
