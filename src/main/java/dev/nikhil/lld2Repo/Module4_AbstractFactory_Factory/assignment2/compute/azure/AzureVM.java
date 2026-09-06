package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.azure;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.VirtualMachine;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.provider.CloudProvider;

import java.util.UUID;

public class AzureVM implements VirtualMachine {
    private final String size;
    private String instanceId;
    private boolean running;

    public AzureVM(String size) {
        this.size = size;
    }

    @Override
    public void launch() {
        this.instanceId = "vm-" + UUID.randomUUID().toString().substring(0, 8);
        this.running = true;
        System.out.println("Provisioning Azure VM, size " + size + " (" + instanceId + ")");
    }

    @Override
    public void terminate() {
        if (!running) {
            System.out.println("Azure VM is not running, nothing to terminate");
            return;
        }
        System.out.println("Deallocating Azure VM " + instanceId);
        running = false;
    }

    @Override
    public String getInstanceId() {
        return instanceId;
    }

    @Override
    public CloudProvider getProvider() {
        return CloudProvider.AZURE;
    }
}
