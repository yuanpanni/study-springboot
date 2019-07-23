package com.demo.web.nio;

import com.demo.web.sort.MergeSort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        /*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(new Date(1000)));
        System.out.println(sdf.format(new Date(2000)));
        System.out.println(sdf.format(new Date(300000000000L)));*/
        int lenth=1000000;
        int[] arr=randomArr(lenth);
        long start=System.currentTimeMillis();
        //System.out.println(Arrays.toString(arr));
        MergeSort.sortArr(arr,lenth);
        long end=System.currentTimeMillis();
        System.out.println((end-start)+"ms");
        //System.out.println(Arrays.toString(arr));
    }


    public static int[] randomArr(int length){
        int[] arr=new int[length];
        for(int i=0;i<arr.length;i++){
            arr[i]=(int)(Math.random()*100);
        }
        return arr;
    }

}
