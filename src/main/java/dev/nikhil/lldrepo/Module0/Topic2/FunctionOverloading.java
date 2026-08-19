package dev.nikhil.lldrepo.Module0.Topic2;

public class FunctionOverloading {

    /**
        for below code we have 2 function having same task but different parameters,
        This is not a good piece of code user have to learn which method to call instead we should use
        function overloading here, which make more sense
    **/
    int addTwoNums(int a, int b){
        return a+b;
    }
    int addThreeNums(int a, int b, int c){
        return a+b+c;
    }

    /**
        function overloading make it simple, and useful, and client dont have learn all the methods
        for different signature.
        Signature means
            1- data type of parameters
            2- number of parameters
            3- order of parameters
        changing them help function overloading
            - changing return type doesn't do function overloading
    **/

    int sum(int a, int b){
        return a+b;
    }
    int sum(int a, int b, int c){
        return a+b+c;
    }



}
