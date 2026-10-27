package dz2_task3;

public class LineStepFrame implements ILineStep{
    public Frame buildProductPart(){
        Frame n=new Frame();
        System.out.println("Корпус для очков сделан");
        return n;
    };
}
