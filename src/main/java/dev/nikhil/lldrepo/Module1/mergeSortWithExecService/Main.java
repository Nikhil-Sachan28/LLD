package dev.nikhil.lldrepo.Module1.mergeSortWithExecService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static  void main(String[] args) throws Exception{
        int[] arr = new int[]{1,4,2,5,78,3,5,7,2};
        ExecutorService ex = Executors.newCachedThreadPool();
        MergeSort mr = new MergeSort(arr, ex, 0, arr.length);

        Future<Void> f = ex.submit(mr);

        f.get();
        ex.shutdown();
        for(int num: arr){
            System.out.println(num);
        }
    }
}
