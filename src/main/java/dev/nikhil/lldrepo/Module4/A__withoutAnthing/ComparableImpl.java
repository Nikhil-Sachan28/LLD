package dev.nikhil.lldrepo.Module4.A__withoutAnthing;

import java.util.Comparator;

public class ComparableImpl implements Comparator<Double> {


    @Override
    public int compare(Double o1, Double o2) {
        Integer int11 = (int) (o1*1);
        Integer int12 = (int)  ((o1 - int11)*10);

        int sum1 = int11 + int12;

        Integer int21 = o2.intValue();
        int int22 = (int) ((o2 - int21)*10);
        int sum2 = int22 + int21;

        return sum1 - sum2;
    }
}
