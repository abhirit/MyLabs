package com.example.aks.MyLabs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Register extends AppCompatActivity {
private AppCompatButton bConfirm;
    private EditText editTextConfirmOtp;
    private String mobileno;
    private  String confirmUrl="https://angrier-brushes.000webhostapp.com/confirm.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText etmobileno = (EditText)findViewById(R.id.etmobileno);
        final EditText etpassword = (EditText)findViewById(R.id.etpassword);

        final EditText etname=(EditText) findViewById(R.id.etname);
        final Button bregister = (Button)findViewById(R.id.bregister);


        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etname.getText().toString();
                mobileno = 91+etmobileno.getText().toString();
                final String password = etpassword.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    etname.setError("Please enter your name");
                    etname.requestFocus();
                    return;
                }if (TextUtils.isEmpty(mobileno)) {
                    etmobileno.setError("Please enter your mobileno");
                    etmobileno.requestFocus();
                    return;
                }if (TextUtils.isEmpty(password)) {
                    etpassword.setError("Please enter your password");
                    etpassword.requestFocus();
                    return;
                }

                Response.Listener<String> resplist = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse= new JSONObject(response);
                            String errmsg=jsonresponse.getString("message");
                            Log.d("err",errmsg);
                            String success = jsonresponse.getString("error");
                            if(success.equals("false")){
                                confirmOtp();
                                //Intent intent = new Intent(Register.this, Login.class);
                                //startActivity(intent);

                            }
                            else{

                                Log.d("err",errmsg);
                                 if(errmsg.equals("mobileno  already registered")){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                    builder.setMessage("Mobile no. is already registered").setNegativeButton("Retry",null).create().show();
                                }
                                else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                    builder.setMessage("Registration failed!").setNegativeButton("Retry", null).create().show();
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Response.ErrorListener erresp= new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                        builder.setMessage("Registration failed!").setNegativeButton("Retry", null).create().show();
                    }
                };



                RgisterRequest regreq= new RgisterRequest(name,mobileno,password, resplist,erresp);
                RequestQueue queue = Volley.newRequestQueue(Register.this);
                queue.add(regreq);
            }
        });
    }
    private void confirmOtp() throws JSONException {
        //Creating a LayoutInflater object for the dialog box
        LayoutInflater li = LayoutInflater.from(this);
        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.dialog_confirm, null);

        //Initizliaing confirm button fo dialog box and edittext of dialog box
        bConfirm = (AppCompatButton) confirmDialog.findViewById(R.id.bConfirm);
        editTextConfirmOtp = (EditText) confirmDialog.findViewById(R.id.editTextOtp);

        //Creating an alertdialog builder
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        //Adding our dialog box to the view of alert dialog
        alert.setView(confirmDialog);

        //Creating an alert dialog
        final AlertDialog alertDialog = alert.create();

        //Displaying the alert dialog
        alertDialog.show();

        //On the click of the confirm button from alert dialog
        bConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hiding the alert dialog
                alertDialog.dismiss();

                //Displaying a progressbar
                final ProgressDialog loading = ProgressDialog.show(Register.this, "Authenticating", "Please wait while we check the entered code", false,false);

                //Getting the user entered otp from edittext
                final String otp = editTextConfirmOtp.getText().toString().trim();

                //Creating an string request
                StringRequest stringRequest = new StringRequest(Request.Method.POST, confirmUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonresponse = new JSONObject(response);
                                    String errmsg = jsonresponse.getString("error");
                                    //if the server response is success
                                    if (errmsg.equalsIgnoreCase("false")) {
                                        //dismissing the progressbar
                                        loading.dismiss();
                                        Toast.makeText(Register.this,"Verified successfully",Toast.LENGTH_LONG).show();
                                        //Starting a new activity
                                        startActivity(new Intent(Register.this, Login.class));
                                        finish();
                                    } else {
                                        //Displaying a toast if the otp entered is wrong
                                        Toast.makeText(Register.this, "Wrong OTP Please Try Again", Toast.LENGTH_LONG).show();
                                        try {
                                            //Asking user to enter otp again
                                            confirmOtp();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                alertDialog.dismiss();
                                Toast.makeText(Register.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        //Adding the parameters otp and username
                        params.put("otp", otp);
                        params.put("mobileno", mobileno);
                        return params;
                    }
                };

                //Adding the request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(Register.this);
                requestQueue.add(stringRequest);
            }
        });
    }
}
