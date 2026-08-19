package dev.nikhil.lldrepo.Module4.D__streams;

import java.util.List;

public class Streams5 {
    public static void main(String[] args) {
        List<Items> list = List.of(
                new Items(1,1),
                new Items(2,1),
                new Items(3,1),
                new Items(4,1),
                new Items(5,1)
        );

        list.stream()
                .forEach(i-> System.out.println(i));

        list.stream()
                .map(s-> s.id)
                .forEach(i-> System.out.println(i));
    }
}
