package dev.nikhil.lldrepo.Module0.Topic5;

public class Main {
    static {
        System.out.println("main static block execute first, even before main");
    }
    public static void main(String[] args) {
        System.out.println("main executes");
        Hello hello = new Hello();
        hello.sayHello();

        System.out.println(StaticBlock.PI);

        StaticBlock sb = new StaticBlock(7);
        System.out.println("Value of var is " + sb.getVar());
//
//        StaticBlock sb1 = new StaticBlock(7);
//        System.out.println("Value of var is " + sb1.getVar());
//
//        StaticBlock sb2 = new StaticBlock(7);
//        System.out.println("Value of var is " + sb2.getVar());
    }
}
