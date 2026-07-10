package dev.nikhil.lldrepo.Module2.A__adderSubtracterProblem;

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
