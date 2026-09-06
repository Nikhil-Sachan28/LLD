package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.network.gcp;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.network.Network;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.provider.CloudProvider;

import java.util.UUID;

public class GCPVPCNetwork implements Network {
    private final String cidrBlock;
    private String networkId;

    public GCPVPCNetwork(String cidrBlock) {
        this.cidrBlock = cidrBlock;
    }

    @Override
    public void configure() {
        this.networkId = "network-" + UUID.randomUUID().toString().substring(0, 8);
        System.out.println("Configuring GCP VPC network " + networkId + " with range " + cidrBlock);
    }

    @Override
    public String getNetworkId() {
        return networkId;
    }

    @Override
    public CloudProvider getProvider() {
        return CloudProvider.GCP;
    }
}
