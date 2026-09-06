package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.client;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.model.Notification;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment0.model.NotificationResult;

public interface NotificationClient {
    NotificationResult send(Notification notification);
}
