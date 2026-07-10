package dev.nikhil.lldrepo.Module2.C__solvinfAddSubUsingImplicitLock;

import java.util.concurrent.Callable;

public class SynchronizedBlockSubtracter implements Callable<Void> {
    Calculator cal;

    SynchronizedBlockSubtracter(Calculator cal){
        this.cal = cal;
    }

    @Override
    public Void call() {
        cal.synchronizedBlockSubtract();
        return null;
    }
}
