package dev.nikhil.lldrepo.Module1_MultiThreading.I_Visibilty;

public class Shared {
    boolean running = true;

    void stop(){
        running = false;
    }

    void work(){
        while(running){
            System.out.println("hello");
        }
    }
}
