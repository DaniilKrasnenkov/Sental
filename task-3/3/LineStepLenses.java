package dz2_task3;

public class LineStepLenses implements ILineStep {
    public Lenses buildProductPart(){
        Lenses n=new Lenses();
        System.out.println("Линзы для очков сделаны");
        return n;
    };
}
