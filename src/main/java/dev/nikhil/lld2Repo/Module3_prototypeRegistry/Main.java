package dev.nikhil.lld2Repo.Module3_prototypeRegistry;

import dev.nikhil.lld2Repo.Module3_prototypeRegistry.Enemy.Enemy;
import dev.nikhil.lld2Repo.Module3_prototypeRegistry.enums.EnemyType;
import dev.nikhil.lld2Repo.Module3_prototypeRegistry.registry.EnemyInitializer;
import dev.nikhil.lld2Repo.Module3_prototypeRegistry.registry.EnemyRegistry;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        // we need 50 orc and 50 Dragon and 50 human enemy
        EnemyRegistry registry = EnemyRegistry.getInstance();
        EnemyInitializer.registerEnemies(registry);

        List<String> humanNames = List.of("Nikhil", "Nitin", "Rahul", "Rehul", "hella", "Shreyash", "Sahil", "Naman", "Hartej", "Kartik");
        List<String> orcName = List.of("NikhilORC", "NitinORC", "RahulORC", "RehulORC", "hellaORC", "ShreyashORC", "SahilORC", "NamanORC", "HartejORC", "KartikORC");
        List<String> dragonNames = List.of("Dragon1", "Dragon2", "Dragon3", "Dragon4", "Dragon5", "Dragon6", "Dragon7", "Dragon8", "Dragon9", "Dragon10");

        List<Enemy> enemies = new ArrayList<>();
        humanNames.forEach(name->{
            Enemy enemy =  registry.get(EnemyType.HUMAN);
            enemy.setName(name);
            enemies.add(enemy);
        });

        orcName.forEach(name->{
            Enemy enemy = registry.get(EnemyType.ORC);
            enemy.setName(name);
            enemies.add(enemy);
        });

        dragonNames.forEach(name->{
            Enemy enemy = registry.get(EnemyType.DRAGON);
            enemy.setName(name);
            enemies.add(enemy);
        });

        enemies.forEach(enemy -> {
            System.out.println(enemy.getClass() + " " + enemy.getName());
        });


    }
}
