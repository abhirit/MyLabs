package com.example.aks.MyLabs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //progressBar= (ProgressBar)findViewById(R.id.progressBar);
        //progressBar.setVisibility(View.GONE);
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, User_area.class));
            return;
        }
        final EditText etmobileno = (EditText)findViewById(R.id.etmobileno);
        final EditText etpassword = (EditText)findViewById(R.id.etpassword);
        final Button blogin = (Button)findViewById(R.id.blogin);
        final TextView tvregister = (TextView)findViewById(R.id.tvregister);

        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent= new Intent(Login.this, Register.class);
                Login.this.startActivity(registerIntent);
            }
        });
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progressBar.setVisibility(View.VISIBLE);
                final ProgressDialog loading = ProgressDialog.show(Login.this, "Logging", "Please wait while we log you in", false,false);
                final String mobileno = 91+etmobileno.getText().toString();
                final String password = etpassword.getText().toString();
                if (TextUtils.isEmpty(mobileno)) {
                    etmobileno.setError("Please enter your Mobile no.");
                    etmobileno.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etpassword.setError("Please enter your password");
                    etpassword.requestFocus();
                    return;
                }
                Response.Listener<String> resplist = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse= new JSONObject(response);
                            String success = jsonresponse.getString("error");
                            if(success=="false"){
                                String name = jsonresponse.getString("name");
                                User user=new User(jsonresponse.getInt("id"),name,mobileno);
                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                //starting the profile activity
                                loading.dismiss();
                                Intent intent = new Intent(Login.this, User_area.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                loading.dismiss();
                                builder.setMessage("Invalid mobile no. or password").setNegativeButton("Retry", null).create().show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Response.ErrorListener erresp= new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                        loading.dismiss();
                        builder.setMessage("Login failed!").setNegativeButton("Retry", null).create().show();
                    }
                };



                LoginRequest regreq= new LoginRequest(mobileno,password,resplist,erresp);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(regreq);
                //progressBar.setVisibility(View.GONE);
            }
        });

    }
}
