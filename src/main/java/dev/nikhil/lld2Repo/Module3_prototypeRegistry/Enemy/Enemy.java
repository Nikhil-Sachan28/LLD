package dev.nikhil.lld2Repo.Module3_prototypeRegistry.Enemy;

import dev.nikhil.lld2Repo.Module3_prototypeRegistry.prototype.Prototype;

public class Enemy implements Prototype<Enemy> {
    protected String name;
    protected int health;
    protected int attack;
    protected String weapon;

    public Enemy(String name, int health, int attack, String weapon) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.weapon = weapon;
    }

    public Enemy(Enemy enemy) {
        this.name = enemy.name;
        this.health = enemy.health;
        this.attack = enemy.attack;
        this.weapon = enemy.weapon;
    }

    public void attack() {
        System.out.println(name + " attacks with " + weapon);
    }

    @Override
    public Enemy copy(){
        return new Enemy(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
