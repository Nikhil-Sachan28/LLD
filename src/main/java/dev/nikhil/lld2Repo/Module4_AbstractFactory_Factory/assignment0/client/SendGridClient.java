package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.client;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.model.Notification;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.model.NotificationResult;

public final class SendGridClient implements NotificationClient {

    private final String apiKey;

    public SendGridClient(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException(
                    "SendGrid API key is required"
            );
        }

        this.apiKey = apiKey;
    }

    @Override
    public NotificationResult send(
            Notification notification) {

        // Real production code would call SendGrid here.

        System.out.println(
                "Sending EMAIL to "
                        + notification.recipient()
        );

        return new NotificationResult(
                true,
                "sendgrid-123"
        );
    }
}
