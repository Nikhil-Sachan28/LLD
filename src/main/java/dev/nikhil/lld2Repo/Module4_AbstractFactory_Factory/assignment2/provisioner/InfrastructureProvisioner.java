package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.provisioner;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.VirtualMachine;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.exception.ProvisioningFailedException;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.factory.CloudProviderFactory;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.network.Network;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.storage.StorageVolume;

/**
 * The client. Depends only on CloudProviderFactory and the product abstractions -
 * never imports EC2Instance, AzureVM, etc. Swap the factory passed in and the
 * exact same code provisions a different provider's family.
 */
public class InfrastructureProvisioner {

    public static ProvisionedResources provision(CloudProviderFactory factory, String vmSize, int volumeSizeGb, String cidrBlock) {
        if (volumeSizeGb <= 0) {
            throw new ProvisioningFailedException("Volume size must be greater than 0, got: " + volumeSizeGb);
        }

        VirtualMachine vm = factory.createVirtualMachine(vmSize);
        vm.launch();

        Network network = factory.createNetwork(cidrBlock);
        network.configure();

        StorageVolume volume = factory.createStorageVolume(volumeSizeGb);
        volume.attach(vm);

        return new ProvisionedResources(vm, volume, network);
    }

    public record ProvisionedResources(VirtualMachine vm, StorageVolume volume, Network network) {}
}
