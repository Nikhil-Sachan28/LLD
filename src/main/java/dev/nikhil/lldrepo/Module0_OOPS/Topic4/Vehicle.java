package dev.nikhil.lldrepo.Module0_OOPS.Topic4;

public abstract class Vehicle {

    String NumberPlate;

    public boolean acceptRide(int otp){
        System.out.println("Ride accepted");
        return true;
    }

    public boolean endRide(){
        System.out.println("ride ended");
        return true;
    }

    public abstract int calculateFare(int km);

}
