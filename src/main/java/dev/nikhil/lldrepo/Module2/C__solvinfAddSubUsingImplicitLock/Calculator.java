package dev.nikhil.lldrepo.Module2.C__solvinfAddSubUsingImplicitLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Calculator {
    private int count1;
    private int count2;
    int times;

    public Calculator(int times){
        count1 = 0;
        count2 = 0;
        this.times = times;
    }

    synchronized void synchronizedFunctionAdder(){
        for(int i = 1; i<times; i++){
            count1++;
        }
    }

    synchronized void synchronizedFunctionSubtract(){
        for(int i = 1; i<times; i++){
            count1--;
        }
    }

    void synchronizedBlockAdder(){
        for(int i = 1; i<times; i++){
            synchronized(this){
                count2++;
            }
        }
    }

    void synchronizedBlockSubtract(){
        for(int i = 1; i<times; i++){
            synchronized(this){
                count2--;
            }
        }
    }



    public int getCount1(){
        return count1;
    }
    public int getCount2(){
        return count2;
    }
}
