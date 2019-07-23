package com.demo.web.sort;

/**
 * 归并算法
 */
public class MergeSort {

    public static void sortArr(int[] arr,int length){

        splitArr(arr,0,length-1);

    }

    private static void splitArr(int[] arr,int l,int r){
        //System.out.println("----------");
        if(l>=r){
            return ;
        }
        //System.out.println("----------");
        int mid=(r+l)/2;
        splitArr(arr,l,mid);
        splitArr(arr,mid+1,r);
        if(arr[mid]>arr[mid+1])
            mergeArr(arr,l,mid,r);
    }

    private static void mergeArr(int[] arr,int l,int mid,int r){
        //System.out.println("++++++++++++");

        int[] temp=new int[r-l+1];
        for(int i=l;i<=r;i++){
            temp[i-l]=arr[i];
        }

        int i=l;
        int j=mid+1;
        for(int k=l;k<=r;k++){
            if(i>mid){
                arr[k]=temp[j-l];
                j++;
            }else if(j>r){
                arr[k]=temp[i-l];
                i++;
            }else if(temp[i-l]<temp[j-l]){
                arr[k]=temp[i-l];
                i++;
            }else{
                arr[k]=temp[j-l];
                j++;
            }

        }


    }

}
