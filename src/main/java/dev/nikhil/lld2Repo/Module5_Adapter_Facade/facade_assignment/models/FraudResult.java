package dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models;

public class FraudResult {

    private final boolean blocked;

    public FraudResult(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }
}
