package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.VirtualMachine;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.exception.IncompatibleResourceException;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.exception.ProvisioningFailedException;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.factory.CloudProviderFactory;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.factory.CloudProviderFactoryRegistry;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.provider.CloudProvider;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.provisioner.InfrastructureProvisioner;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.provisioner.InfrastructureProvisioner.ProvisionedResources;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.storage.StorageVolume;

public class CloudProvisioningApp {
    public static void main(String[] args) {

        // Provision the same shape of infrastructure across every provider.
        // Notice this loop body never mentions EC2Instance, AzureVM, VPCNetwork, etc.
        // Swapping the provider is just swapping which factory the registry hands back.
        for (CloudProvider provider : CloudProvider.values()) {
            System.out.println("\n=== Provisioning on " + provider + " ===");
            try {
                CloudProviderFactory factory = CloudProviderFactoryRegistry.getFactory(provider);
                ProvisionedResources resources = InfrastructureProvisioner.provision(
                        factory, "medium", 50, "10.0.0.0/16");

                System.out.println("Done. VM=" + resources.vm().getInstanceId()
                        + ", Volume=" + resources.volume().getSizeGb() + "GB"
                        + ", Network=" + resources.network().getNetworkId());
            } catch (ProvisioningFailedException ex) {
                System.out.println("Provisioning failed: " + ex.getMessage());
            }
        }

        // Prove the family constraint is actually enforced, not just conceptual:
        // deliberately try to attach an AWS volume to an Azure VM.
        System.out.println("\n=== Demonstrating cross-provider family enforcement ===");
        try {
            CloudProviderFactory azureFactory = CloudProviderFactoryRegistry.getFactory(CloudProvider.AZURE);
            VirtualMachine azureVm = azureFactory.createVirtualMachine("small");
            azureVm.launch();

            CloudProviderFactory awsFactory = CloudProviderFactoryRegistry.getFactory(CloudProvider.AWS);
            StorageVolume awsVolume = awsFactory.createStorageVolume(20);

            awsVolume.attach(azureVm); // should throw - AWS volume, Azure VM
            System.out.println("This line should never print.");
        } catch (IncompatibleResourceException ex) {
            System.out.println("Caught expected error: " + ex.getMessage());
        }
    }
}
