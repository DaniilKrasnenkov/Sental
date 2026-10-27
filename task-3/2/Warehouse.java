package dz2_task2;
import java.util.ArrayList;

public class Warehouse {
    private int limit;
    private ArrayList<Product> storege;

    Warehouse(int limit){
        this.limit=limit;
        this.storege=new ArrayList<Product>();
    }

    public int getLimit() {
        return limit;
    }

    public ArrayList<Product> getStorege() {
        return storege;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setStorege(ArrayList<Product> storege) {
        this.storege = storege;
    }

    public int weights(){
        int weights=0;
        for(int i=0;i< storege.size();i++){
            weights+=storege.get(i).getWeight();
        }
        return weights;
    }

    public void addProduct(Product n){
        if(weights()+n.getWeight()<limit){
            storege.add(n);
        }
        else{
            System.out.println("Склад переполнен,нельзя добавить товар.\n"+"Лимит: "+getLimit()+"\nСейчас товары на складе весят: " + weights());
        }
    }
}
