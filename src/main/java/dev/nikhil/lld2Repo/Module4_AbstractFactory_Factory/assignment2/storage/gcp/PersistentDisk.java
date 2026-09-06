package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.storage.gcp;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.VirtualMachine;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.exception.IncompatibleResourceException;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.provider.CloudProvider;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.storage.StorageVolume;

public class PersistentDisk implements StorageVolume {
    private final int sizeGb;
    private VirtualMachine attachedTo;

    public PersistentDisk(int sizeGb) {
        this.sizeGb = sizeGb;
    }

    @Override
    public void attach(VirtualMachine vm) {
        if (vm.getProvider() != CloudProvider.GCP) {
            throw new IncompatibleResourceException(
                    "Cannot attach a GCP Persistent Disk to a " + vm.getProvider() + " virtual machine");
        }
        this.attachedTo = vm;
        System.out.println("Attaching Persistent Disk (" + sizeGb + "GB) to " + vm.getInstanceId());
    }

    @Override
    public void detach() {
        if (attachedTo == null) {
            System.out.println("Persistent Disk is not attached to any instance");
            return;
        }
        System.out.println("Detaching Persistent Disk from " + attachedTo.getInstanceId());
        attachedTo = null;
    }

    @Override
    public int getSizeGb() {
        return sizeGb;
    }

    @Override
    public CloudProvider getProvider() {
        return CloudProvider.GCP;
    }
}
