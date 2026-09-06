package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.factory.NotificationServiceFactory;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.model.Notification;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.model.NotificationResult;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.service.NotificationService;

public class Main {

    public static void main(String[] args) {

        NotificationServiceFactory factory = new NotificationServiceFactory();

        NotificationService service =
                factory.create(
                        "email",
                        "sendgrid-api-key"
                );

        Notification notification =
                new Notification(
                        "nikhil@example.com",
                        "Order shipped!"
                );

        NotificationResult result =
                service.process(notification);

        System.out.println(
                "Success: " + result.success()
        );
    }
}
