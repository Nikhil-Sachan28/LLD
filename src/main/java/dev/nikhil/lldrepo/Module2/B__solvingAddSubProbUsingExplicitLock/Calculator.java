package dev.nikhil.lldrepo.Module2.B__solvingAddSubProbUsingExplicitLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Calculator {
    private int count;
    int times;
    Lock lock;

    public Calculator(int times){
        count = 0;
        this.times = times;
        lock = new ReentrantLock();
    }

    void adder(){
        lock.lock();
        for(int i = 1; i<times; i++){
            count++;
        }
        lock.unlock();
    }

    void subtract(){
        lock.lock();
        for(int i = 1; i<times; i++){
            count--;
        }
        lock.unlock();
    }

    public int getCount(){
        return count;
    }
}
