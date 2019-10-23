package com.example.aks.MyLabs;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AKS on 4/29/2018.
 */

public class SendAddressRequest extends StringRequest {
    private static final String url="https://angrier-brushes.000webhostapp.com/setaddress.php";
    private Map<String,String> params;
    public SendAddressRequest(String mobileno, String Address, String Area, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, listener, errorListener);
        params=new HashMap<>();
        params.put("mobileno", mobileno);
        params.put("Address", Address);
        params.put("Area", Area);
    }
    @Override
    public Map<String, String> getParams(){ return params;}
}