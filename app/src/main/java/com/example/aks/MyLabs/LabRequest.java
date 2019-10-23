package com.example.aks.MyLabs;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AKS on 1/20/2018.
 */

public class LabRequest extends StringRequest {
    private static final String url="https://angrier-brushes.000webhostapp.com/getLab.php";
    private Map<String, String> params;
    public LabRequest(String array, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, listener, errorListener);
        params=new HashMap<>();
        params.put("items",array);

    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
