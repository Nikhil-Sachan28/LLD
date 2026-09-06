package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.factory;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.service.EmailNotificationService;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.service.NotificationService;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.service.SmsNotificationService;

public final class NotificationServiceFactory {

    public NotificationService create(
            String type,
            String apiKey) {

        return switch (type) {
            case "email" ->
                    new EmailNotificationService(apiKey);

            case "sms" ->
                    new SmsNotificationService(apiKey);

            default ->
                    throw new IllegalArgumentException(
                            "Unsupported notification type: " + type
                    );
        };
    }
}
