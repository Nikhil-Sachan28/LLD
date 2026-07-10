package dev.nikhil.lldrepo.Module2.E__ProducerConsumerProblem;

import java.util.Random;
import java.util.concurrent.Callable;

public class Producer implements Runnable {
    Store store;

    Producer(Store store){
        this.store = store;
    }

    @Override
    public void run() {
        while(true){

            store.produce(1);

            Random random = new Random();
            try {
                int secondsToSleep = random.nextInt(1, 2);
                Thread.sleep(secondsToSleep * 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
