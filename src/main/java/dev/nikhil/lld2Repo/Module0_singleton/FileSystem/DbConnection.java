package dev.nikhil.lld2Repo.Module0_singleton.FileSystem;

public enum DbConnection {
    Instance("url", "pass");

    private String url;
    private String pass;

    DbConnection(String url, String pass){
        this.url = url;
        this.pass = pass;
    }



}
