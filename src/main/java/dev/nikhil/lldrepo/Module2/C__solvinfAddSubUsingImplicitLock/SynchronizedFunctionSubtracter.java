package dev.nikhil.lldrepo.Module2.C__solvinfAddSubUsingImplicitLock;

import java.util.concurrent.Callable;

public class SynchronizedFunctionSubtracter implements Callable<Void> {
    Calculator cal;

    SynchronizedFunctionSubtracter(Calculator cal){
        this.cal = cal;
    }

    @Override
    public Void call() {
        cal.synchronizedFunctionSubtract();
        return null;
    }
}
