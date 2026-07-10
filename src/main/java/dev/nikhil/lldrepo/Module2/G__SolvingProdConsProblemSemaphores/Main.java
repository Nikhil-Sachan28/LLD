package dev.nikhil.lldrepo.Module2.G__SolvingProdConsProblemSemaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws Exception{
        ExecutorService ex = Executors.newFixedThreadPool(10);
        int maxCapacity = 5;
        Store store = new Store(maxCapacity);
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        Producer producer1 = new Producer(store);

        ex.execute(producer);
        ex.execute(consumer);
        ex.execute(producer1);

    }
}
