package com.example.aks.MyLabs;

/**
 * Created by AKS on 1/24/2018.
 */

public class Status {
    public static boolean[] b;

    public Status(int len){
        b=new boolean[len];
    }

    public static boolean getStatus(int i){
        return b[i];
    }
    public static void setStatus(boolean st,int index){
        b[index]=st;
    }
}
