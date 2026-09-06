package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.service;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.client.NotificationClient;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.client.SendGridClient;

public final class EmailNotificationService extends NotificationService {

    private final String apiKey;

    public EmailNotificationService(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    protected NotificationClient createClient() {
        return new SendGridClient(apiKey);
    }
}
