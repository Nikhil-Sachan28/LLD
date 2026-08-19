package dev.nikhil.lldrepo.Module4.D__streams;

import java.util.List;
import java.util.stream.Stream;

public class Streams2 {
    public static void main(String[] args) {
        List<Integer> list =  List.of(1,34,2,5,6,7,5,32,5,8,9,4,23,2);

        list.stream()
            .filter(num ->{
                System.out.println("Checking div by 2 ");
                return num%2==0;
            });

        // only done will be printed, as without terminal operation stream don't start the execution,
        // as they are lazily evaluated
        System.out.println("done");



    }
}
