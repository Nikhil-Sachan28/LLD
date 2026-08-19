package dev.nikhil.lldrepo.Module3.B__Generics;

import java.util.ArrayList;
import java.util.List;

public class InheritanceGenerics {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(6);
        list.add(2);
        List<String> stringList = new ArrayList<String>();
        stringList.add("nikhil");

        Demo2 demo2 = new Demo2();
        Demo3 demo3 = new Demo3();
        Demo4 demo4 = new Demo4();
        Demo5 demo5 = new Demo5();

        System.out.println("");
        demo2.print(list);
        System.out.println("");
        demo2.print(stringList); // string can be added, or any data type allowed
        System.out.println("");
        demo3.print(list);
        System.out.println("");
        demo2.print(stringList); // string can be added, or any data type allowed
        System.out.println("");
        demo4.print(list);
        System.out.println("");
        // demo4.print(stringList); // string can't be added, only Number data type allowed now
        System.out.println("");
        demo5.print(list);
        System.out.println("");
//        demo5.print(stringList); // string can't be added, only Number, Object data type allowed now

    }
}

class Demo2{
    public <T> void print(List<T> list){
        T element = list.get(0); // Safe to capture as type T
        list.add(element);// we know type is T so we can add T
        for (int i = 0; i<list.size(); i++) {
            T x = list.get(i);
            Object y = list.get(i);
            System.out.print(x + " ");
        }
    }
}

class Demo3{
    public void print(List<?> list){
        //list.add(0); // write not allowed as we dont know the current type
        for (int i = 0; i<list.size(); i++) {
            Object x = list.get(i);
            System.out.print(x + " ");
        }
    }
}

class Demo4{
    public void print(List<? extends Number> list){
        // list.add(10); // write not allowed as we can add double, integer or byte and we dont know which data type list has
        for (int i = 0; i<list.size(); i++) {
            Number x = list.get(i);
            System.out.print(x + " ");
        }
    }
}

class Demo5{
    public void print(List<? super Integer> list){
        list.add(10); // write not allowed not integer only
//        list.add(10.0); // adding number or object is not allowed as number can be double or object can be String
        for (int i = 0; i<list.size(); i++) {
            Object x = list.get(i);
            System.out.print(x + " ");
        }
    }
}