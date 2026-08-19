package dev.nikhil.lldrepo.Module3.B__Generics;

import java.util.ArrayList;

public class Generic1 {
    public static void main(String[] args) {
        /*
            when Type is NOT specified, it is called a raw type. Now Java behaves almost like it did before generics were introduced (Java 5).
            You can add anything. Everything is accepted. What happens when retrieving?
            You have to cast manually. What if the cast is wrong?
            Compilation succeeds. But at runtime:
            Exception in thread "main"   ->>>    java.lang.ClassCastException
            Why does Java still allow raw types?
            Because of backward compatibility. Before Java 5 (before generics), people wrote code like this:
            List list = new ArrayList();
            Millions of existing programs used raw types. If Java suddenly made them illegal, all that code would stop compiling. So Java still allows raw types but gives you a warning.
        */
        Box box = new Box();
        box.setValue(1);
        System.out.println(box.getValue());
        box.setValue("baby");
        System.out.println(box.getValue());
        ArrayList list = new ArrayList();
        list.add("nikhil");
        list.add(1);
        String x = (String) list.get(0);
        System.out.println(x);
        int y = (int) list.get(1);
        System.out.println(y);
    }
}

class Box<T>{
    private T value;

    void setValue(T val){
        this.value = val;
    }

    T getValue(){
        return this.value;
    }
}
