package dev.nikhil.lldrepo.Module3.B__Generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsInvarianceProperty {
    public static void main(String[] args) {
        Demo demo = new Demo();
        // demo.printNumbers(new ArrayList<Integer>()); it is wrong, becuz of invariance property
        demo.printNumbers(new ArrayList<Number>());
    }
}

class Demo{
    public void printNumbers(List<Number> nums){
        System.out.println(nums);
    }
}
