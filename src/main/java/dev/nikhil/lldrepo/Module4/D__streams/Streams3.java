package dev.nikhil.lldrepo.Module4.D__streams;

import java.util.List;


public class Streams3 {
    public static void main(String[] args) {
        List<Integer> list =  List.of(1,34,2,5,6,7,5,32,5,8,9,4,23,2);
        /*
        * Operation are not happening like, handle first filter and let it complete then second filter,
        * no, this is not happening, one number passes the stream first then second and so on
        * */
        list = list.stream()
                .filter(num ->{
                    System.out.println("check if " + num + " divisible by 2 or not");
                    return num%2==0;
                })
                .filter(num->{
                    System.out.println("check if " + num + " divisible by 3 or not");
                    return num%3==0;
                })

                .toList();

//        Stream.iterate(1, n-> n+1).forEach(num -> System.out.println(num));

        System.out.println(list);

    }
}
