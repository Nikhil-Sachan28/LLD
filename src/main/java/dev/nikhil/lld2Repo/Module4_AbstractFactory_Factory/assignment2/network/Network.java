package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.network;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.provider.CloudProvider;

public interface Network {
    void configure();
    String getNetworkId();
    CloudProvider getProvider();
}
