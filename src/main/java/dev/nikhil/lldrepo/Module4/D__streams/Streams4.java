package dev.nikhil.lldrepo.Module4.D__streams;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Streams4 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        System.out.println("--------------------------------");
        System.out.println(" Summing All numbers");
        System.out.println("--------------------------------");

        BinaryOperator<Integer> bo = (a,b) -> a+b;

        int sum = numbers.stream()
                .reduce(0, bo);

        Optional<Integer> sum2 = numbers.stream()
                .reduce(bo);

        System.out.println(sum);
        System.out.println(sum2.get());

        System.out.println("--------------------------------");
        System.out.println("Finding min and max");
        System.out.println("--------------------------------");

        Optional<Integer> max = numbers.stream()
                .reduce((a, b) -> a>b? a:b);

        Optional<Integer> min = numbers.stream()
                .reduce((a, b) -> a>b? b:a);

        System.out.println(max.get());
        System.out.println(min.get());

        System.out.println("--------------------------------");
        System.out.println("Concatenate Strings");
        System.out.println("--------------------------------");

        List<String> list = List.of("java", "is", "beautiful");

        Optional<String> s = list.stream()
                .reduce((a,b) -> a+ " " + b);

        System.out.println(s.get());

        System.out.println("--------------------------------");
        System.out.println("Finding all evens");
        System.out.println("--------------------------------");

        List<Integer> evens = numbers.stream()
                .filter(num -> num%2 == 0)
                .collect(Collectors.toList());

        System.out.println(evens);

        System.out.println("--------------------------------");
        System.out.println("count the number of strings containing a specific character ‘a’ ");
        System.out.println("--------------------------------");

        List<String> strings = Arrays.asList("apple", "banana", "orange", "grape");

        long count = strings.stream()
                .filter(str -> str.contains("a"))
                .count();

        System.out.println(count);

        System.out.println("--------------------------------");
        System.out.println("Write a program to convert a list of strings to uppercase using Java Stream API.");
        System.out.println("--------------------------------");

        strings.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("--------------------------------");
        System.out.println("Get distinct numbers");
        System.out.println("--------------------------------");

        List<Integer> list1 = numbers.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println(list1);

        System.out.println("--------------------------------");
        System.out.println("group by length");
        System.out.println("--------------------------------");

        Map<Integer, List<String>> map = strings.stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println(map);

        /* these all are function interface which are consumed by sreams function
            BinaryOperator<Integer> bo1 = (a,b) -> a+b;
            IntFunction<Integer> ifn = (a) -> a*a;
            Function<Integer, String> f = (a) -> a.toString();
            Predicate<Integer> pd = (a) -> a%2 ==0;
         */

        System.out.println("--------------------------------");
        System.out.println("Use for flatmap");
        System.out.println("--------------------------------");

        List<String> sentence = Arrays.asList(
                "Python is a programming language.",
                "JavaScript is used for web development.",
                "Ruby is known for its simplicity.");

         sentence.stream()
                .flatMap((s2)-> Arrays.stream(s2.split(" ")))
                .distinct()
                 .forEach(s2-> System.out.print(s2 + ", "));

        System.out.println("\n--------------------------------");
        System.out.println("Given a list of integers, write a program to calculate the average of all the numbers using Java Stream API.");
        System.out.println("--------------------------------");

        OptionalDouble average = numbers.stream()
                .mapToDouble(num -> num.intValue())
                .average();

        if(average.isPresent()){
            System.out.println(average.getAsDouble());
        }


    }
}
