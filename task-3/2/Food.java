package dz2_task2;

public class Food extends Product{
    private String expirationDate;
    Food(double weight,String name,String expirationDate){
        super(weight, name);
        this.expirationDate=expirationDate;
    }
}
