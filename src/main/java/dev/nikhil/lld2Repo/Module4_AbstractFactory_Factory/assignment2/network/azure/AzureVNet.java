package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.network.azure;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.network.Network;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.provider.CloudProvider;

import java.util.UUID;

public class AzureVNet implements Network {
    private final String cidrBlock;
    private String networkId;

    public AzureVNet(String cidrBlock) {
        this.cidrBlock = cidrBlock;
    }

    @Override
    public void configure() {
        this.networkId = "vnet-" + UUID.randomUUID().toString().substring(0, 8);
        System.out.println("Configuring Azure VNet " + networkId + " with address space " + cidrBlock);
    }

    @Override
    public String getNetworkId() {
        return networkId;
    }

    @Override
    public CloudProvider getProvider() {
        return CloudProvider.AZURE;
    }
}
