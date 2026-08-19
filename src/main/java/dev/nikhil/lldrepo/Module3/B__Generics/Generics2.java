package dev.nikhil.lldrepo.Module3.B__Generics;


import java.util.ArrayList;

public class Generics2 {
    public static void main(String[] args) {
        // recommended way,
        Box1<String> box = new Box1<>();
        box.setValue("baby");
        System.out.println(box.getValue());

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        int y = list.get(0);
        System.out.println(y);
    }
}

class Box1<T>{
    private T value;

    void setValue(T val){
        this.value = val;
    }

    T getValue(){
        return this.value;
    }

    //static method have to write <T> to declare T is generic data type
    static <T> void getCount(T val){
        System.out.println("hello");
    }
}

