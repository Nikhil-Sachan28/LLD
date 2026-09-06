package dev.nikhil.lld2Repo.Module3_prototypeRegistry.registry;

import dev.nikhil.lld2Repo.Module3_prototypeRegistry.Enemy.Enemy;
import dev.nikhil.lld2Repo.Module3_prototypeRegistry.enums.EnemyType;

import java.util.HashMap;
import java.util.Map;

public class EnemyRegistry {

    private static EnemyRegistry instance;
    private final Map<EnemyType, Enemy> map;

    EnemyRegistry(){
        map = new HashMap<>();
    }

    public static EnemyRegistry getInstance(){
        if(instance == null){
            synchronized(EnemyRegistry.class){
                if(instance == null){
                    instance = new EnemyRegistry();
                }
            }
        }
        return instance;
    }

    public void register(EnemyType type, Enemy enemy){
        map.put(type, enemy.copy());
    }

    public Enemy get(EnemyType type){
        return map.get(type).copy();
    }

}
