package dev.nikhil.lldrepo.Module3.B__Generics;

class Animal { }

class Dog extends Animal { }

class Cat extends Animal { }

class Car { }

public class BoundedInheritanceGenerics {

    public static <T extends Animal> void register(T animal) {

    }
    public static void main(String[] args) {
        register(new Dog()); // allowed

        register(new Cat()); // allowed

        register(new Animal());// allowed

        // register(new Car());--> not allowed
    }
}
