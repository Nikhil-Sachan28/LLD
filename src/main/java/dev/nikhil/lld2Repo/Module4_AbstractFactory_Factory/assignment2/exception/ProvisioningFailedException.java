package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.exception;

public class ProvisioningFailedException extends RuntimeException {
    public ProvisioningFailedException(String message) {
        super(message);
    }

    public ProvisioningFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
