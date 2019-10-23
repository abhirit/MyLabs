package com.example.aks.MyLabs;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AKS on 12/17/2017.
 */

public class RgisterRequest extends StringRequest {
    private static final String Register_Request_url = "https://angrier-brushes.000webhostapp.com/registerUser.php";
    private Map<String, String> params;
    public RgisterRequest(String name,String mobileno, String password, Response.Listener<String> listener, Response.ErrorListener erresp){
        super(Method.POST, Register_Request_url, listener, erresp);
        params = new HashMap<>();
        params.put("name", name);
        params.put("mobileno", mobileno);
        params.put("password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
