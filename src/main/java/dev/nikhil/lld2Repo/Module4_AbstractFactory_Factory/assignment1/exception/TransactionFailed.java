package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.exception;

public class TransactionFailed extends RuntimeException {
    public TransactionFailed(String message) {
        super(message);
    }
}
