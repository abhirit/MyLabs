package com.example.aks.MyLabs;

import android.util.Log;

/**
 * Created by AKS on 1/19/2018.
 */

public class TestArray {
    static int len=0;
    static int length;
    static String[] array;
    public TestArray(int length) {
        this.length=length;
        array=new String[length];

    }

    public static void addItem(String item){
        for(int i=0;i<length;i++){
            if (array[i]==null) {
                array[i]=item;
                Log.d("addddddddddddddddddddddddding item and value of iiiiiiiiiii ",array[i]+"  "+i);
                break;
            }
        }
    }
    public static void delItem(String item){
        for(int i=0;i<length;i++){
            if (array[i]==item) {
                array[i]=null;
                Log.d("delettttttttttttttttttttttttting item and value of iiiiiiiiiii ",array[i]+"  "+i);
                break;
            }
        }
    }
    public static String[] getArray(){
        int l=0;
        String trimarray[];
        for(int i=0;i<length;i++){
            if(array[i]!=null){
                l++;
            }
        }
        len=l;
        Log.d("valueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee of llllll"," "+l);
        trimarray=new String[l];
        int k=0;
        for(int j=0;j<l;j++){
            while(k<length){
                if(array[k]!=null){
                    trimarray[j]=array[k];
                    k++;
                    Log.d("value inserted in trim arrayyyyyyyyyyyyyyyy  and  jjjjjjjjjjj",trimarray[j]+"  "+j);
                    break;
                }
                k++;
            }
        }
        return trimarray;
    }
    public static int getlength(){
        return len;
    }
}
