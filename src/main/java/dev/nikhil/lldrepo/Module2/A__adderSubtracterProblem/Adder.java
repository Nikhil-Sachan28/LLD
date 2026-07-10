package dev.nikhil.lldrepo.Module2.A__adderSubtracterProblem;

import java.util.concurrent.Callable;

public class Adder implements Callable<Void> {
    Calculator cal;

    Adder(Calculator cal){
        this.cal = cal;
    }

    @Override
    public Void call() {

        cal.adder();
        return null;
    }
}
