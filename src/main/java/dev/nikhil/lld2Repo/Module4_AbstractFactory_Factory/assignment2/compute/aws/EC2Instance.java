package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.aws;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.VirtualMachine;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.provider.CloudProvider;

import java.util.UUID;

public class EC2Instance implements VirtualMachine {
    private final String size;
    private String instanceId;
    private boolean running;

    public EC2Instance(String size) {
        this.size = size;
    }

    @Override
    public void launch() {
        this.instanceId = "i-" + UUID.randomUUID().toString().replace("-", "").substring(0, 17);
        this.running = true;
        System.out.println("Launching EC2 instance of type " + size + " (" + instanceId + ")");
    }

    @Override
    public void terminate() {
        if (!running) {
            System.out.println("EC2 instance is not running, nothing to terminate");
            return;
        }
        System.out.println("Terminating EC2 instance " + instanceId);
        running = false;
    }

    @Override
    public String getInstanceId() {
        return instanceId;
    }

    @Override
    public CloudProvider getProvider() {
        return CloudProvider.AWS;
    }
}
