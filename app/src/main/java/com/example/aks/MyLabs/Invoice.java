package com.example.aks.MyLabs;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

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

//import com.example.aks.mylabs.R;

public class Invoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        final TextView msg = (TextView) findViewById(R.id.message);
        msg.setMovementMethod(new ScrollingMovementMethod());
        final Button exit=(Button)findViewById(R.id.exit);
        final ProgressBar progressBar= (ProgressBar)findViewById(R.id.progressBar);
        String[] orders=TestArray.getArray();
        final StringBuffer order= new StringBuffer("");
        for(int i=0;i<orders.length;i++){
            if(orders[i]!=null){
                order.append(orders[i]+"\n");
            }

        }
        User user = SharedPrefManager.getInstance(this).getUser();
        final int userId = user.getId();
        final String id= ""+userId;
        final String name=user.getname();
        final String mobile=user.getmobileno();
        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        final String address=extras.getString("Address");
        final String area = extras.getString("Area");
        final String date= extras.getString("Date");
        final String amount = extras.getString("Total");
        long advancePay= (long) (0.25*Integer.parseInt(amount));
        int round=Math.round(advancePay);
        final int advance = ((round+5)/10)*10;
        final int balance=Integer.parseInt(amount)-advance;
        final String lab=extras.getString("Lab");
        String url="https://angrier-brushes.000webhostapp.com/order.php";
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonresponse = new JSONObject(response);
                    String errmsg = jsonresponse.getString("error");
                    if (errmsg.equalsIgnoreCase("true")) {
                        SpannableString message=new SpannableString("Sorry your order placement \nFAILED");
                        message.setSpan(new BackgroundColorSpan(Color.RED),28,34,0);
                        msg.setTextSize(25);
                        msg.setTextColor(Color.BLACK);
                        msg.setText(message);


                    } else {
                        String code = jsonresponse.getString("code");
                        SpannableString message = new SpannableString("Your order placed succesfully \n\n" +
                                "Please provide the below code and pay the advance payment to the technician at the time of sample collection\n" +
                                code + "\n" +
                                "Advance payment : " + advance + "\n\n" +
                                "Pay the balance payment at the time of delivery of report\nBalance payment : "+balance+"\n"+
                                "Please pay only the amount displayed on the screen to the technician \n" +
                                "Tests you have selected:\n"+order+"\nLab selected : "+lab+
                                "\nFor any query please contact 8092570085");
                        message.setSpan(new RelativeSizeSpan(2f), 0, 29, 0);
                        message.setSpan(new ForegroundColorSpan(Color.GREEN), 0, 29, 0);
                        message.setSpan(new StyleSpan(Typeface.BOLD), 0, 29, 0);
                        message.setSpan(new ForegroundColorSpan(Color.BLACK), 31, message.length(), 0);
                        message.setSpan(new BackgroundColorSpan(Color.YELLOW), 141, 148, 0);
                        message.setSpan(new RelativeSizeSpan(1.5f), 141, 148, 0);
                        message.setSpan(new RelativeSizeSpan(1.5f), 165, 170, 0);
                        message.setSpan(new RelativeSizeSpan(1.5f), 245, 249, 0);
                        msg.setText(message);
                        progressBar.setVisibility(View.INVISIBLE);
                        exit.setVisibility(View.VISIBLE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                msg.setText("Something went wrong your order placement failed");
                msg.setTextSize(25);
                msg.setTextColor(Color.BLACK);
                progressBar.setVisibility(View.INVISIBLE);
                exit.setVisibility(View.VISIBLE);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("name", name);
                params.put("mobile", mobile);
                params.put("address", address);
                params.put("area", area);
                params.put("date", date);
                params.put("order", order.toString());
                params.put("lab",lab);
                params.put("advance",String.valueOf(advance));
                params.put("balance",String.valueOf(balance));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Invoice.this);
        requestQueue.add(stringRequest);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(Invoice.this,User_area.class);
                startActivity(test);
            }
        });
    }
}
