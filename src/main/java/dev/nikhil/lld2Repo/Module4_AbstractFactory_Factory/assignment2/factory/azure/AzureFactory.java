package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.factory.azure;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.VirtualMachine;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.azure.AzureVM;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.factory.CloudProviderFactory;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.network.Network;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.network.azure.AzureVNet;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.storage.StorageVolume;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.storage.azure.AzureManagedDisk;

public class AzureFactory implements CloudProviderFactory {
    @Override
    public VirtualMachine createVirtualMachine(String size) {
        return new AzureVM(size);
    }

    @Override
    public StorageVolume createStorageVolume(int sizeGb) {
        return new AzureManagedDisk(sizeGb);
    }

    @Override
    public Network createNetwork(String cidrBlock) {
        return new AzureVNet(cidrBlock);
    }
}
