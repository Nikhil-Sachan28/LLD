package dev.nikhil.lld2Repo.Module3_prototypeRegistry.Enemy;

public class HumanEnemy extends Enemy {
    int intelligence;
    public HumanEnemy(String name, int health, int attack, String weapon, int intelligence) {
        super(name, health, attack, weapon);
        this.intelligence = intelligence;
    }

    public HumanEnemy(HumanEnemy human) {
        super(human.name, human.health, human.attack, human.weapon);
        this.intelligence = human.intelligence;
    }

    @Override
    public HumanEnemy copy(){
        return new HumanEnemy(this);
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}
