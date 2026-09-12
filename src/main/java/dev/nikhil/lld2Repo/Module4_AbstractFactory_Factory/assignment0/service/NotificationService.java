package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.service;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.model.Notification;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.client.NotificationClient;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.model.NotificationResult;

// service should neve have mutable states
public abstract class NotificationService {

    public final NotificationResult process(Notification notification) {

        validate(notification);

        NotificationClient client = createClient();

        NotificationResult result = client.send(notification);

        audit(result);

        return result;
    }

    // ⭐ Factory Method
    protected abstract NotificationClient createClient();

    private void validate(Notification notification) {

        if (notification == null) {
            throw new IllegalArgumentException(
                    "Notification cannot be null"
            );
        }

        if (notification.recipient() == null ||
                notification.recipient().isBlank()) {

            throw new IllegalArgumentException(
                    "Recipient is required"
            );
        }

        if (notification.message() == null ||
                notification.message().isBlank()) {

            throw new IllegalArgumentException(
                    "Message is required"
            );
        }
    }

    private void audit(NotificationResult result) {
        System.out.println(
                "Notification result: "
                        + result.success()
        );
    }
}
