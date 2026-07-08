package dev.nikhil.lldrepo.Module1.threadWithoutRunnable;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        Thread thread = new MyThread();
        System.out.println(Thread.currentThread().getName());
        thread.start();
    }

}
