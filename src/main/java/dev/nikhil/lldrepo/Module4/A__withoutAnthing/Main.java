package dev.nikhil.lldrepo.Module4.A__withoutAnthing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Double> list = new ArrayList<>( List.of(2.1, 2.5, 5.5, 6.2, 8.8, 9.1, 5.9));
        ComparableImpl impl = new ComparableImpl();
        Collections.sort(list, impl);

        for(double num: list){
            System.out.println(num);
        }
    }
}
