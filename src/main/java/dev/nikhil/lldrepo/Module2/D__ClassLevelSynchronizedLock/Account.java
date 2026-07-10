package dev.nikhil.lldrepo.Module2.D__ClassLevelSynchronizedLock;

public class Account {
    private static int transactions = 0;
    int amount;

    Account(int amount){
        amount = 0;
    }

    void debit(int money, int repeat){
        for(int i = 1; i<=repeat; i++){
            synchronized (this){
                amount -= money;
                transactions++;
            }
        }
    }

    void credit(int money, int repeat){
        for(int i = 1; i<=repeat; i++){
            synchronized (this){
                amount += money;
                transactions++;
            }
        }
    }


    int totalMoney(){
        return this.amount;
    }

    static int totalTransactions(){
        return transactions;
    }
}
