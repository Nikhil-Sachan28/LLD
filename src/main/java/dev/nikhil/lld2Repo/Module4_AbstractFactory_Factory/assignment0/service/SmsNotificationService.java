package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.service;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.client.NotificationClient;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.client.TwilioClient;

public final class SmsNotificationService
        extends NotificationService {

    private final String apiKey;

    public SmsNotificationService(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    protected NotificationClient createClient() {
        return new TwilioClient(apiKey);
    }
}
