package dev.nikhil.lldrepo.Module1.mergeSortWithForkJoinPool;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static  void main(String[] args) throws Exception{
        int[] arr = new int[]{1,4,2,5,78,3,5,7,2};
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(
                new MergeSortTask(arr, 0, arr.length)
        );

        for(int num: arr){
            System.out.println(num);
        }
    }
}
