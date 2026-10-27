package dz2_task2;

public abstract class Product {
    private double weight;
    private String name;

    Product(double weight,String name){
        this.weight=weight;
        this.name=name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName(){
        return name;
    }

    public double getWeight(){
        return weight;
    }

}
