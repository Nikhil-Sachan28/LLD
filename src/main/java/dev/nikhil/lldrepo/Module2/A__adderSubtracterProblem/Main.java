package dev.nikhil.lldrepo.Module2.A__adderSubtracterProblem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static  void main(String[] args) throws Exception{
        int times = 1000000;
        ExecutorService ex = Executors.newFixedThreadPool(2);
        Calculator calculator = new Calculator(times);
        Adder adder = new Adder(calculator);
        Subtracter sub = new Subtracter(calculator);

        Future f1 = ex.submit(adder);
        Future f2 = ex.submit(sub);
        f1.get();
        f2.get();
        System.out.println("current count is "+ calculator.getCount());
        ex.shutdown();
    }
}
