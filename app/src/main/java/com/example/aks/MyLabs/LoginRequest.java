package com.example.aks.MyLabs;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AKS on 1/11/2018.
 */

public class LoginRequest extends StringRequest {
    private static final String url ="https://angrier-brushes.000webhostapp.com/userLogin.php";
    private Map<String, String> params;
    public LoginRequest(String mobileno, String password, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, listener, errorListener);
        params = new HashMap<>();
        params.put("mobileno", mobileno);
        params.put("password", password);

    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
