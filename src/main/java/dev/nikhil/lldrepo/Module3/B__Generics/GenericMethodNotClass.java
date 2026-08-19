package dev.nikhil.lldrepo.Module3.B__Generics;
/**
 * here we dont want to make whole class generic instead, we want to make a specific function generic
 * <
 * void print(T value){
 *     System.out.println(value);
 * }
 *
 * This alone won't work, we have to add  <T>  in function definition, it tell the function T is generic not a class
 * It is similar to, when we make class generic we write class Xyz<T>{} <T> tells java T is generic parameter.
**/
public class GenericMethodNotClass {
    public static void main(String[] args) {
        Box2 box2 = new Box2();
        box2.print("hello");
        System.out.println(box2.returnBack("hey"));
    }
}

class Box2{
    <T> void print(T value){
        System.out.println(value);
    }

    <T> T returnBack(T val){
        return val;
    }
}