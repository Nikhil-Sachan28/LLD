package dev.nikhil.lld2Repo.Module3_prototypeRegistry.Enemy;


public class Dragon extends Enemy {

    private final int fireDamage;

    public Dragon(String name, int health, int attack, String weapon, int fireDamage) {
        super(name, health, attack, weapon);
        this.fireDamage = fireDamage;
    }

    public Dragon(Dragon dragon) {
        super(dragon);
        this.fireDamage = dragon.fireDamage;
    }

    @Override
    public Dragon copy(){
        return new Dragon(this);
    }
}
