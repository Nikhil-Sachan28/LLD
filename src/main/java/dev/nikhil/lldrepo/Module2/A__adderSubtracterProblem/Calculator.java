package dev.nikhil.lldrepo.Module2.A__adderSubtracterProblem;

public class Calculator {
    private int count;
    int times;

    public Calculator(int times){
        count = 0;
        this.times = times;
    }

    void adder(){
        for(int i = 1; i<times; i++){
            count++;
        }
    }

    void subtract(){
        for(int i = 1; i<times; i++){
            count--;
        }
    }

    public int getCount(){
        return count;
    }
}
