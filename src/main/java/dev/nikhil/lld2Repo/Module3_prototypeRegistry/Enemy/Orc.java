package dev.nikhil.lld2Repo.Module3_prototypeRegistry.Enemy;

public class Orc extends HumanEnemy {
    int speed;
    public Orc(String name, int health, int attack, String weapon, int speed, int intelligence) {
        super(name, health, attack, weapon, intelligence);
        this.speed = speed;
    }

    public Orc(Orc orc) {
        super(orc);
        this.speed = orc.speed;
    }

    @Override
    public Orc copy() {
        return new Orc(this);
    }
}
