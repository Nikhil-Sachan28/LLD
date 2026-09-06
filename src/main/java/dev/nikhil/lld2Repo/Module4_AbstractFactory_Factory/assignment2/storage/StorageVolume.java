package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.storage;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.VirtualMachine;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.provider.CloudProvider;

public interface StorageVolume {
    void attach(VirtualMachine vm);
    void detach();
    int getSizeGb();
    CloudProvider getProvider();
}
