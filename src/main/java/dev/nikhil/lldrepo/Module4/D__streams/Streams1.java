package dev.nikhil.lldrepo.Module4.D__streams;

import java.util.List;
import java.util.stream.Stream;

public class Streams1 {

    public static void main(String[] args) {
        List<Integer> list =  List.of(1,34,2,5,6,7,5,32,5,8,9,4,23,2);
        // 1.convert to stream
        Stream<Integer> stream = list.stream();
        // 2.filter the even
        /**
         // long method
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer num) {
                return num%2==0;
            }
        };
        stream.filter(predicate);
        **/
        // shorter lambda version
        stream = stream.filter(num -> num%2==0);

        //3. either close or collect the stream, we are closing it
        /**
         * // long method
         Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
         };
        **/
        //short method
        stream.forEach(num-> System.out.println(num));

    }
}
