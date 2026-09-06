package dev.nikhil.lldrepo.Module0_OOPS.Topic2;

public class Main {
    public static void main(String[] args) {
        FunctionOverloading fl = new FunctionOverloading();

        // bad piece of code
        System.out.println(fl.addTwoNums(2,3));
        System.out.println(fl.addThreeNums(2,3, 4));

        // Good piece of code
        System.out.println(fl.sum(2,4));
        System.out.println(fl.sum(2,4, 5));

    }
}
