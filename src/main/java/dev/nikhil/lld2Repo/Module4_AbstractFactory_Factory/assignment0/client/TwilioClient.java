package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.client;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.model.Notification;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.model.NotificationResult;

public final class TwilioClient
        implements NotificationClient {

    private final String apiKey;

    public TwilioClient(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException(
                    "Twilio API key is required"
            );
        }

        this.apiKey = apiKey;
    }

    @Override
    public NotificationResult send(
            Notification notification) {

        System.out.println(
                "Sending SMS to "
                        + notification.recipient()
        );

        return new NotificationResult(
                true,
                "twilio-123"
        );
    }
}
