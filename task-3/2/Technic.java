package dz2_task2;

public class Technic extends Product{
    private String model;
    Technic(double weight,String name, String model){
        super(weight,name);
        this.model=model;
    }
}
