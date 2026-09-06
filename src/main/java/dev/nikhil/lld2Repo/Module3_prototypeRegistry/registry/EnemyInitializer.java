package dev.nikhil.lld2Repo.Module3_prototypeRegistry.registry;

import dev.nikhil.lld2Repo.Module3_prototypeRegistry.Enemy.Dragon;
import dev.nikhil.lld2Repo.Module3_prototypeRegistry.Enemy.HumanEnemy;
import dev.nikhil.lld2Repo.Module3_prototypeRegistry.Enemy.Orc;
import dev.nikhil.lld2Repo.Module3_prototypeRegistry.enums.EnemyType;

public class EnemyInitializer {
    public static void registerEnemies(EnemyRegistry registry){
        HumanEnemy humanEnemy = new HumanEnemy("nikhil", 100, 20, "Gun", 100);
        Orc orc = new Orc("nikhil", 100, 50, "Gun", 100, 80);
        Dragon dragon = new Dragon("Tim", 150, 100, "fire", 30);

        registry.register(EnemyType.HUMAN, humanEnemy);
        registry.register(EnemyType.ORC, orc);
        registry.register(EnemyType.DRAGON, dragon);
    }
}
