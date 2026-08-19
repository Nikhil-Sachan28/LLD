package dev.nikhil.lldrepo.Module3.C;

import java.util.List;

public class BoundedGen {

    public void fun(List<? extends Number> num){

    }

    public <T extends Number> T fun2(T number){
        return number;
    }
}
