package com.example.aks.MyLabs;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AKS on 2/25/2018.
 */

public class AddressRequest extends StringRequest {
    private static final String url="https://angrier-brushes.000webhostapp.com/getAddress.php";
    private Map<String, String> params;
    public AddressRequest(String mobileno, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, listener, errorListener);
        params = new HashMap<>();
        params.put("mobileno",mobileno);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
