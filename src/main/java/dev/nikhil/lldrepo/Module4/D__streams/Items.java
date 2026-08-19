package dev.nikhil.lldrepo.Module4.D__streams;

public class Items {
    int id;
    int name;

    public Items(int id, int name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
