package dev.nikhil.lldrepo.Module0.Topic3;

import java.util.Objects;

public class Client3 {
    int id;
    String name;

    public Client3(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client3 client1 = (Client3) o;
        return id == client1.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
