package dz2_task3;

public class Glasses implements IProduct{
    IProductPart frame;
    IProductPart lenses;
    IProductPart temples;

    public void installFirstPart(IProductPart n1){
        this.frame=n1;
        System.out.println("Корпус установлен");
    };
    public void installSecondPart(IProductPart n2) {
        this.lenses=n2;
        System.out.println("Линзы установлены");
    };
    public void installThirdPart(IProductPart n3) {
        this.temples=n3;
        System.out.println("Дужки установлены");
    };
}
//Очки (корпус, линзы, дужки)
//Glasses (frame, lenses, temples)