package dev.nikhil.lldrepo.Module3.A__withoutGenerics;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Object> list1 = new ArrayList<>();
        list1.add("nikhil");
        list1.add(1);
        list1.add(12.33);
        int count = 0;

        for(int i = 0; i<list1.size(); i++){
            Object m = list1.get(i);
            count+=(int) m;
            System.out.println(m);
        }
    }
}
