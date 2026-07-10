package dev.nikhil.lldrepo.Module2.G__SolvingProdConsProblemSemaphores;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;

public class Store {

    private final Queue<Integer> conveyorBelt;
    Semaphore consumerSemaphore;
    Semaphore producerSemaphore;
    Semaphore mutex;

    Store(int maxCapacity){
        conveyorBelt = new ArrayBlockingQueue<>(maxCapacity);
        producerSemaphore = new Semaphore(maxCapacity);
        consumerSemaphore = new Semaphore(0);
        mutex = new Semaphore(1);
    }

    public void produce(int item) {

        try{
            producerSemaphore.acquire();
            mutex.acquire();
            try {
                conveyorBelt.add(item);
                System.out.println("item Produced and list size after production is " + conveyorBelt.size());
            } finally {
                mutex.release();
            }
            consumerSemaphore.release();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void consume() {
        try{
            consumerSemaphore.acquire();
            mutex.acquire();
            try {
                conveyorBelt.remove();
                System.out.println("item Consumed and list size after consume is " + conveyorBelt.size());
            } finally {
                mutex.release();
            }
            producerSemaphore.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}
