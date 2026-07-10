package dev.nikhil.lldrepo.Module2.B__solvingAddSubProbUsingExplicitLock;

import java.util.concurrent.Callable;

public class Subtracter implements Callable<Void> {
    Calculator cal;

    Subtracter(Calculator cal){
        this.cal = cal;
    }

    @Override
    public Void call() {
        cal.subtract();
        return null;
    }
}
