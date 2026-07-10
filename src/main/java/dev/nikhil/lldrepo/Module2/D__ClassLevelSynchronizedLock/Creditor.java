package dev.nikhil.lldrepo.Module2.D__ClassLevelSynchronizedLock;

import java.util.concurrent.Callable;

public class Creditor implements Callable<Void> {
    Account account;
    int money;
    int repeat;
    Creditor(Account acc, int money, int repeat){
        account = acc;
        this.money = money;
        this.repeat = repeat;
    }
    @Override
    public Void call() throws Exception {
        account.credit(money, repeat);
        return null;
    }
}
