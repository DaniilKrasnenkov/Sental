package dz2_task3;

public class Main {
    public static void main(String[] args) {
        Glasses glasses = new Glasses();
        LineStepFrame linestep1= new LineStepFrame();
        LineStepLenses linestep2= new LineStepLenses();
        LineStepTemples linestep3= new LineStepTemples();
        AssemblyGlasses assemblyGlasses = new AssemblyGlasses(linestep1,linestep2,linestep3);
        assemblyGlasses.assembleProduct(glasses);
    }
}
