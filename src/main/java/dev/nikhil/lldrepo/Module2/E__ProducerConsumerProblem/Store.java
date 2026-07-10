package dev.nikhil.lldrepo.Module2.E__ProducerConsumerProblem;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;

public class Store {
    private final Queue<Integer> conveyorBelt;
    private final int maxCapacity;
    int count;


    Store(int maxCapacity){
        this.maxCapacity = maxCapacity;
        conveyorBelt = new ArrayBlockingQueue<>(maxCapacity);
        count =0;
    }

    public void produce(int item) {
        if(count < maxCapacity){
            conveyorBelt.add(item);
            count++;
            System.out.println("item Produced and list size after production is " + conveyorBelt.size());
        }
    }

    public void consume() {
        if(count != 0){
            conveyorBelt.remove();
            count--;
            System.out.println("item Consumed and list size after consume is " + conveyorBelt.size());
        }
    }


}
