package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.factory.gcp;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.VirtualMachine;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.gcp.ComputeEngineInstance;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.factory.CloudProviderFactory;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.network.Network;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.network.gcp.GCPVPCNetwork;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.storage.StorageVolume;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.storage.gcp.PersistentDisk;

public class GCPFactory implements CloudProviderFactory {
    @Override
    public VirtualMachine createVirtualMachine(String size) {
        return new ComputeEngineInstance(size);
    }

    @Override
    public StorageVolume createStorageVolume(int sizeGb) {
        return new PersistentDisk(sizeGb);
    }

    @Override
    public Network createNetwork(String cidrBlock) {
        return new GCPVPCNetwork(cidrBlock);
    }
}
