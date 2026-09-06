package dev.nikhil.lldrepo.Module1_MultiThreading.H__mergeSortWithForkJoinPool;

import java.util.concurrent.RecursiveAction;

public class MergeSortTask extends RecursiveAction {
    private final int[] arr;

    int start;
    int end;

    MergeSortTask(int[] arr, int start, int end){
        this.arr = arr;

        this.start = start;
        this.end = end;
    }



    public void mergeSort() throws Exception{
        if(end - start <= 1) return;
        int mid = start + (end - start)/2;

        MergeSortTask mr1 = new MergeSortTask(arr, start, mid);

        MergeSortTask mr2 = new MergeSortTask(arr, mid, end);

       invokeAll(mr1, mr2);

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
    protected void compute() {
        try {
            mergeSort();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
