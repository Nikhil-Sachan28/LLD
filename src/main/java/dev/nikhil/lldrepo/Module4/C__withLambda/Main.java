package dev.nikhil.lldrepo.Module4.C__withLambda;

import dev.nikhil.lldrepo.Module4.A__withoutAnthing.ComparableImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Double> list = new ArrayList<>( List.of(2.1, 2.5, 5.5, 6.2, 8.8, 9.1, 5.9));

        Collections.sort(list, ( o1,  o2)->{
            Integer int11 = (int) (o1*1);
            Integer int12 = (int)  ((o1 - int11)*10);

            int sum1 = int11 + int12;

            int int21 = o2.intValue();
            int int22 = (int) ((o2 - int21)*10);
            int sum2 = int22 + int21;

            return  sum1 - sum2;
        });

        for(double num: list){
            System.out.println(num);
        }

        /**
        lambda only work with final and effective final outer captured variable
        **/
        // final
        final int x = 10;
        list.forEach(v ->{
            System.out.println(x);
        });

        // effective - final
        int y = 10; // if we neve change value of y its effectively final.
        // y  = 11; // now lambda won't work as this variable is not effectively final
        list.forEach(v ->{
            // y++; wont work
            System.out.println(y);
        });

        // this also work as reference of list is not changing, object can change
        list.forEach(v ->{
            list.add(10.0);
            System.out.println(v);
        });



    }
}