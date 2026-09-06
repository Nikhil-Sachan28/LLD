package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.factory.aws;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.VirtualMachine;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute.aws.EC2Instance;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.factory.CloudProviderFactory;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.network.Network;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.network.aws.VPCNetwork;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.storage.StorageVolume;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.storage.aws.EBSVolume;

public class AWSFactory implements CloudProviderFactory {
    @Override
    public VirtualMachine createVirtualMachine(String size) {
        return new EC2Instance(size);
    }

    @Override
    public StorageVolume createStorageVolume(int sizeGb) {
        return new EBSVolume(sizeGb);
    }

    @Override
    public Network createNetwork(String cidrBlock) {
        return new VPCNetwork(cidrBlock);
    }
}
