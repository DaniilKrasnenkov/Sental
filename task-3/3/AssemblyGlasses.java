package dz2_task3;

public class AssemblyGlasses implements IAssemblyLine{
    LineStepTemples Temples;
    LineStepLenses Lenses;
    LineStepFrame Frame;

    AssemblyGlasses(LineStepFrame Frame,  LineStepLenses Lenses, LineStepTemples Temples){
        this.Frame=Frame;
        this.Lenses=Lenses;
        this.Temples=Temples;
    }

    public IProduct assembleProduct(IProduct p){
        p.installFirstPart(Temples.buildProductPart());
        p.installSecondPart(Lenses.buildProductPart());
        p.installThirdPart(Frame.buildProductPart());

        System.out.println("Очки собраны");
        return p;
    };
}
