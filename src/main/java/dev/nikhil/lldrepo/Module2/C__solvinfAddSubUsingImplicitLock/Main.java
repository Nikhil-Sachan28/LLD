package dev.nikhil.lldrepo.Module2.C__solvinfAddSubUsingImplicitLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static  void main(String[] args) throws Exception{
        int times = 1000000;
        ExecutorService ex = Executors.newFixedThreadPool(2);
        Calculator calculator = new Calculator(times);

        //-------------------
        SynchronizedFunctionAdder adder = new SynchronizedFunctionAdder(calculator);
        SynchronizedFunctionSubtracter sub = new SynchronizedFunctionSubtracter(calculator);

        ex.submit(adder).get();
        ex.submit(sub).get();

        System.out.println("current count is using Synchronized Function "+ calculator.getCount1());

        SynchronizedBlockAdder adder1 = new SynchronizedBlockAdder(calculator);
        SynchronizedBlockSubtracter sub1 = new SynchronizedBlockSubtracter(calculator);

        ex.submit(adder1).get();
        ex.submit(sub1).get();

        System.out.println("current count is using Synchronized block "+ calculator.getCount2());


        ex.shutdown();
    }
}
