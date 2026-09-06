package dev.nikhil.lldrepo.Module1_MultiThreading.I_Visibilty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
    Yes — it is possible for this code to never stop, even though one thread calls stop().
    The key issue is visibility.
        boolean running = true;
    Thread may read running as true and keep that value in a CPU register/cache rather than
    repeatedly reading the latest value from main memory.
*/
public class Main {
    public static void main(String[] args) {
        Shared shared = new Shared();

        ExecutorService ex = Executors.newFixedThreadPool(3);

        ex.submit(shared::work);
        ex.submit(shared::work);
        ex.submit(shared::stop);


    }
}
