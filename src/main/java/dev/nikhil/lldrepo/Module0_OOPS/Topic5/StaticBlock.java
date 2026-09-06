package dev.nikhil.lldrepo.Module0_OOPS.Topic5;

public class StaticBlock {
    private int var;

    public static float PI = 3.14f;

    public StaticBlock(int var){
        System.out.println("constructor call executes");
        this.var = var;
    }

    public int getVar(){
        return var;
    }

    static {
        System.out.println("static block execute first, even before constructor call");
    }

    {
        var = 5;
        System.out.println("initialization blocks executes");
    }
}
