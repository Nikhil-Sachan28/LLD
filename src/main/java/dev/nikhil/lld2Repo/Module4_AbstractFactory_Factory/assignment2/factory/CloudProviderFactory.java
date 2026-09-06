package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.factory;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.VirtualMachine;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.network.Network;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.storage.StorageVolume;

/**
 * The Abstract Factory. Its only job is producing a family of related resources
 * for one provider. It intentionally knows nothing about *how* those resources
 * get wired together into a running deployment - that's InfrastructureProvisioner's job.
 */
public interface CloudProviderFactory {
    VirtualMachine createVirtualMachine(String size);
    StorageVolume createStorageVolume(int sizeGb);
    Network createNetwork(String cidrBlock);
}
