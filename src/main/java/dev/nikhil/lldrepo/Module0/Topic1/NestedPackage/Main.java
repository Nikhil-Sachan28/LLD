package dev.nikhil.lldrepo.Module0.Topic1.NestedPackage;

import dev.nikhil.lldrepo.Module0.Topic1.Client;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();

        /**
            In java subpackages are also considered as new package, subpackage are for us,
            while for java its another package
        **/

        // System.out.println(client.name); ---> not accessible, so even subpackages can access default variables

    }
}
