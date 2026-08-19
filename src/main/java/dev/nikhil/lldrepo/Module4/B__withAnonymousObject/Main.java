package dev.nikhil.lldrepo.Module4.B__withAnonymousObject;

import dev.nikhil.lldrepo.Module4.A__withoutAnthing.ComparableImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Double> list = new ArrayList<>( List.of(2.1, 2.5, 5.5, 6.2, 8.8, 9.1, 5.9));
        //anonymous class can have more than one abstract methods
        Comparator impl = new Comparator<Double>(){
            @Override
            public int compare(Double o1, Double o2) {
                Integer int11 = (int) (o1*1);
                Integer int12 = (int)  ((o1 - int11)*10);

                int sum1 = int11 + int12;

                Integer int21 = o2.intValue();
                int int22 = (int) ((o2 - int21)*10);
                int sum2 = int22 + int21;

                return  sum1 - sum2;
            }

            @Override
            public boolean equals(Object o){
                return true;
            }
        };
        Collections.sort(list, impl);

        for(double num: list){
            System.out.println(num);
        }
    }
}
