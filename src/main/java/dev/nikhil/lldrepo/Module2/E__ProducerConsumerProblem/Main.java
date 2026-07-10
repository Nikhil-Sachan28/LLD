package dev.nikhil.lldrepo.Module2.E__ProducerConsumerProblem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws Exception{
        ExecutorService ex = Executors.newFixedThreadPool(10);
        int maxCapacity = 1;
        Store store = new Store(maxCapacity);
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        ex.execute(producer);
        ex.execute(new Producer(store));
        ex.execute(consumer);

    }
}
