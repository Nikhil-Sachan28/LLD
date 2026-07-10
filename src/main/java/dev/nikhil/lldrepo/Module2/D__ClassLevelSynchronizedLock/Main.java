package dev.nikhil.lldrepo.Module2.D__ClassLevelSynchronizedLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws Exception{
        ExecutorService ex = Executors.newFixedThreadPool(2);
        Account account1 = new Account(0);
        Account account2 = new Account(0);
        int money = 100;
        int repeat = 100000;
        Debitor debitor = new Debitor(account1, money, repeat);
        Creditor creditor = new Creditor(account2, money, repeat);

        Future f1 = ex.submit(creditor);
        Future f2 = ex.submit(debitor);

        f1.get();
        f2.get();

        System.out.println("total money in account1 is " + account1.totalMoney());
        System.out.println("total money in account2 is " + account2.totalMoney());
        System.out.println("Total transactions now is " + Account.totalTransactions() + " While total transactions should be " + 2*repeat);
        System.out.println("");

        System.out.println("This is because 2 thread different thread working on different object can acquire lock on same time \nbut transaction is same for both object as it belong to class not object so we should use class level lock for transactions");
        System.out.println("");

        SafeAccount safeAccount1 = new SafeAccount(0);
        SafeAccount safeAccount2 = new SafeAccount(0);

        SafeDebitor safeDebitor = new SafeDebitor(safeAccount1, money, repeat);
        SafeCreditor safeCreditor = new SafeCreditor(safeAccount2, money, repeat);

        Future sf1 = ex.submit(safeCreditor);
        Future sf2 = ex.submit(safeDebitor);

        sf1.get();
        sf2.get();

        System.out.println("total money in safe account1 is " + safeAccount1.totalMoney());
        System.out.println("total money in safe account2 is " + safeAccount2.totalMoney());
        System.out.println("Total transactions now is " + SafeAccount.totalTransactions() + " And total transactions should be " + 2*repeat);


        ex.shutdown();
    }
}
