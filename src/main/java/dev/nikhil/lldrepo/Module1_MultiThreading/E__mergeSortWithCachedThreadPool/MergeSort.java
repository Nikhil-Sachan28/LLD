package dev.nikhil.lldrepo.Module1_MultiThreading.E__mergeSortWithCachedThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.Future;

public class MergeSort implements Callable<Void> {
    private final int[] arr;
    ExecutorService ex;
    int start;
    int end;

    MergeSort(int[] arr, ExecutorService ex, int start, int end){
        this.arr = arr;
        this.ex = ex;
        this.start = start;
        this.end = end;
    }



    public void mergeSort() throws Exception{
        if(end - start <= 1) return;
        int mid = start + (end - start)/2;

        MergeSort mr1 = new MergeSort(arr, ex, start, mid);
        Future<Void> f1 = ex.submit(mr1);
        MergeSort mr2 = new MergeSort(arr, ex, mid, end);
        Future<Void> f2 = ex.submit(mr2);
        f1.get();
        f2.get();

        int[] temp = merge(start, mid, mid, end);
        System.arraycopy(temp, 0, arr, start, temp.length);
    }

    public int[] merge(int start1, int end1, int start2, int end2){
        int[] temp = new int[end2-start1];
        int i = 0;

        while(start1<end1 && start2<end2){
            if(arr[start1] > arr[start2]){
                temp[i] = arr[start2];
                start2++;
            }else{
                temp[i] = arr[start1];
                start1++;
            }
            i++;
        }

        while(start1<end1){
            temp[i] = arr[start1];
            start1++;
            i++;
        }

        while(start2<end2){
            temp[i] = arr[start2];
            start2++;
            i++;
        }

        return temp;
    }

    @Override
    public Void call() throws Exception {
        mergeSort();
        return null;
    }
}
