package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.compute;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.provider.CloudProvider;

public interface VirtualMachine {
    void launch();
    void terminate();
    String getInstanceId();

    /**
     * Identifies which provider family this instance belongs to.
     * Storage/network implementations use this to reject cross-provider attachment.
     */
    CloudProvider getProvider();
}
