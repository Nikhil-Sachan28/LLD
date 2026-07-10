package dev.nikhil.lldrepo.Module2.C__solvinfAddSubUsingImplicitLock;

import java.util.concurrent.Callable;

public class SynchronizedFunctionAdder implements Callable<Void> {
    Calculator cal;

    SynchronizedFunctionAdder(Calculator cal){
        this.cal = cal;
    }

    @Override
    public Void call() {
        cal.synchronizedFunctionAdder();
        return null;
    }
}
