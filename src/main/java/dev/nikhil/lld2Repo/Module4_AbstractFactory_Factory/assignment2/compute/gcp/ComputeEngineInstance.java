package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.gcp;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.VirtualMachine;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.provider.CloudProvider;

import java.util.concurrent.atomic.AtomicInteger;

public class ComputeEngineInstance implements VirtualMachine {
    private static final AtomicInteger COUNTER = new AtomicInteger(0);
    private static final String ZONE = "us-central1-a";

    private final String size;
    private String instanceId;
    private boolean running;

    public ComputeEngineInstance(String size) {
        this.size = size; // bug in original: constructor took `size` but never assigned it
    }

    @Override
    public void launch() {
        this.instanceId = "instance-" + COUNTER.incrementAndGet();
        this.running = true;
        System.out.println("Creating GCE instance " + instanceId + " (" + size + ") in zone " + ZONE);
    }

    @Override
    public void terminate() {
        if (!running) {
            System.out.println("GCE instance is not running, nothing to terminate");
            return;
        }
        System.out.println("Deleting GCE instance " + instanceId);
        running = false;
    }

    @Override
    public String getInstanceId() {
        return instanceId;
    }

    @Override
    public CloudProvider getProvider() {
        return CloudProvider.GCP;
    }
}
