package dev.nikhil.lldrepo.Module3.B__Generics;

public class BoundedGenerics {
    public static void main(String[] args) {
        Calc calc = new Calc();

        System.out.println("square of 10 is " + calc.square(10));
    }
}

class Calc{
    public <T extends Number> double square(T num) {
        return num.doubleValue() * num.doubleValue();
    }
}
