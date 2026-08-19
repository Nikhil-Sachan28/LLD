package dev.nikhil.lldrepo.Module3.B__Generics;

public class BoundedGenericWithInterface {

    public static <T extends Human> void print(T person){
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        print(new Students()); // allowed
        print(new Teacher()); //allowed
        // print(new Cow()); // not allowed
    }
}

interface Human{

}

class Students implements Human{

}

class Teacher implements Human{

}

class Cow{

}