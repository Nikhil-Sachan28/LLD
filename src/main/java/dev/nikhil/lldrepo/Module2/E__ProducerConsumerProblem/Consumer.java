package dev.nikhil.lldrepo.Module2.E__ProducerConsumerProblem;


import java.util.Random;

public class Consumer implements Runnable {
    Store store;

    Consumer(Store store){
        this.store = store;
    }

    @Override
    public void run() {
        while(true){

            store.consume();

            Random random = new Random();
            try {
                int secondsToSleep = random.nextInt(1, 2);
                Thread.sleep(secondsToSleep * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
