package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment2.exception;

/**
 * Thrown when a resource from one cloud provider's family (e.g. an AWS EBS volume)
 * is attached to a resource from a different provider's family (e.g. an Azure VM).
 * This is what makes the Abstract Factory's "family" guarantee real rather than a comment.
 */
public class IncompatibleResourceException extends RuntimeException {
    public IncompatibleResourceException(String message) {
        super(message);
    }
}
