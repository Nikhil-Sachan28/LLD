package dev.nikhil.lldrepo.Module0.Topic3;

import java.util.Objects;

public class Client1 {
    int id;
    String name;

    public Client1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client1 client1 = (Client1) o;
        return id == client1.id;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name);
//    }
}
