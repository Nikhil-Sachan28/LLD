package dev.nikhil.lldrepo.Module0.Topic4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Uber {
    public static void main(String[] args) {
        // benefit of writing code this way is, if in future we need to remove a vehicle or add another vehicle
        // we don't need to change the code we just need to make one more class and extend it with vehicle
        List<Vehicle> vehicles = new ArrayList<>();


        Car car = new Car();
        Bike bike = new Bike();
        Auto auto = new Auto();

        vehicles.add(car);
        vehicles.add(bike);
        vehicles.add(auto);

        Random random = new Random();
        int num = random.nextInt(vehicles.size());

        vehicles.get(num).acceptRide(123);
        vehicles.get(num).endRide();
        int fare = vehicles.get(num).calculateFare(10);
        System.out.println("fare charged is "+ fare + " by " + vehicles.get(num).getClass());
    }
}
