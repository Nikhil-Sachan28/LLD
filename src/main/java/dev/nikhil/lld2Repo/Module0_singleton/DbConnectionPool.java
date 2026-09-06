package dev.nikhil.lld2Repo.Module0_singleton;

import java.util.ArrayList;

public class DbConnectionPool {
    ArrayList<String> connections;
    private final int maxPool;

    private static DbConnectionPool instance = new DbConnectionPool("url", "pass", 5);


    private DbConnectionPool(String url, String pass, int maxPool){
        System.out.println("const. call");
        this.maxPool = maxPool;
    }

    public static DbConnectionPool getInstance(){
        System.out.println("get Instance");
        return instance;
    }

}
