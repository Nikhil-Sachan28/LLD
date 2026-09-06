package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.factory;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.factory.aws.AWSFactory;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.factory.azure.AzureFactory;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.factory.gcp.GCPFactory;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.provider.CloudProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CloudProviderFactoryRegistry {
    private static final Map<CloudProvider, Supplier<CloudProviderFactory>> registry = new HashMap<>();
    static {
        registry.put(CloudProvider.AWS, AWSFactory::new);
        registry.put(CloudProvider.AZURE, AzureFactory::new);
        registry.put(CloudProvider.GCP, GCPFactory::new);
    }

    public static CloudProviderFactory getFactory(CloudProvider provider) {
        Supplier<CloudProviderFactory> supplier = registry.get(provider);
        if (supplier == null) throw new IllegalArgumentException("Unsupported provider: " + provider);
        return supplier.get();
    }
}
