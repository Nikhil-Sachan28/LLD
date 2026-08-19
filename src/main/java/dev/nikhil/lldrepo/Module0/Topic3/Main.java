package dev.nikhil.lldrepo.Module0.Topic3;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        Client2 a1 = new Client2(1, "Nikhil");
        Client2 a2 = new Client2(1, "Nikhil");
        /**
         *
         * Normally we think == compare address and equals() compare the value, If so
         * then why a1 is not equal to a2??
         * because we by default, equals() method also compare the address but we override it
         * and define our own implementation, then in our impl. we write logic to compare values
         * but can we do the same with ==?
         * No, java doesn't allows operator overriding and overloading
         *
         **/
        System.out.println(a1 == a2);
        System.out.println(a1.equals(a2));


        Client1 c1 = new Client1(1, "Nikhil");
        Client1 c2 = new Client1(1, "Gaurav");

        System.out.println(c1== c2);
        System.out.println(c1.equals(c2));

        HashMap<Client1, String> map = new HashMap<>();
        map.put(c1, "Nikhil");
        /*
            Normally when 2 thing are equal they goes into same bucket, here
            c1 and c2 are equal but still we get on finding the value of c2 we get null,
            while it should return "Nikhil"

            Because we haven't override hashCode method, by default it uses address value to hash,
            and both have different address, so null returned
        */
        System.out.println(map.get(c2));


        Client3 b1 = new Client3(1, "Nikhil");
        Client3 b2 = new Client3(1, "Gaurav");

        HashMap<Client3, String> map1 = new HashMap<>();
        map1.put(b1, "nikhil");
        System.out.println(map1.get(b2));


    }
}
