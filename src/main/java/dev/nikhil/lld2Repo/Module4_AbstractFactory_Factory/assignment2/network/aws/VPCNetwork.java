package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.network.aws;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.network.Network;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.provider.CloudProvider;

import java.util.UUID;

public class VPCNetwork implements Network {
    private final String cidrBlock;
    private String networkId;

    public VPCNetwork(String cidrBlock) {
        this.cidrBlock = cidrBlock;
    }

    @Override
    public void configure() {
        this.networkId = "vpc-" + UUID.randomUUID().toString().substring(0, 8);
        System.out.println("Configuring AWS VPC " + networkId + " with CIDR " + cidrBlock);
    }

    @Override
    public String getNetworkId() {
        return networkId;
    }

    @Override
    public CloudProvider getProvider() {
        return CloudProvider.AWS;
    }
}
