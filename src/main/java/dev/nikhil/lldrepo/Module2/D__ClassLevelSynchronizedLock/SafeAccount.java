package dev.nikhil.lldrepo.Module2.D__ClassLevelSynchronizedLock;

public class SafeAccount {
    private static int transactions = 0;
    int amount;

    SafeAccount(int amount){
        this.amount = amount;
    }

    void debit(int money, int repeat){
        for(int i = 1; i<=repeat; i++){
            synchronized (this){
                amount -= money;
            }
            synchronized (SafeAccount.class){
                transactions++;
            }
        }
    }

    void credit(int money, int repeat){
        for(int i = 1; i<=repeat; i++){
            synchronized (this){
                amount += money;
            }
            synchronized (SafeAccount.class){
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
