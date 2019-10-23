package com.example.aks.MyLabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import static com.example.aks.MyLabs.R.layout.activity_test;

public class Test extends AppCompatActivity {
    ListView list;
    ProgressBar progressBar;
    Button next;
    int flag=0;
    String[] array;
    int pos=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_test);
        next=(Button)findViewById(R.id.next);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        final RadioButton bloodtest = (RadioButton) findViewById(R.id.bloodtest);
        RadioButton bimagine = (RadioButton) findViewById(R.id.bimagine);
        final String bturl = "https://angrier-brushes.000webhostapp.com/getTest.php";
        final String dturl="https://angrier-brushes.000webhostapp.com/getDiagTest.php";
        list= (ListView)findViewById(R.id.listview);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setItemsCanFocus(false);



        //Blood Test
        bloodtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=1;
                progressBar.setVisibility(View.VISIBLE);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, bturl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray array = new JSONArray(response);
                                    int length=array.length();

                                    String string[] = new String[array.length()];
                                    //JSONObject jsonresponse;
                                    for (int i = 0; i < length; i++) {
                                        //jsonresponse = array.getJSONObject(i);
                                        string[i] = array.getString(i);

                                    }
                                    new TestArray(array.length());
                                    filldata(string);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                );
                Volley.newRequestQueue(Test.this).add(stringRequest);

            }
        });

        //Diagnostic test
        bimagine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=2;
                progressBar.setVisibility(View.VISIBLE);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, dturl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray array = new JSONArray(response);
                                    int length=array.length();

                                    String string[] = new String[array.length()];
                                    //JSONObject jsonresponse;
                                    for (int i = 0; i < length; i++) {
                                        //jsonresponse = array.getJSONObject(i);
                                        string[i] = array.getString(i);

                                    }
                                    new TestArray(array.length());
                                    filldata(string);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                );
                Volley.newRequestQueue(Test.this).add(stringRequest);
            }
        });



        //next Button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Test.this,LabSelection.class);
                intent.putExtra("flag",flag);
                startActivity(intent);

            }
        });


    }
    public void filldata(String[] array){
        this.array=array;
        new Status(array.length);
        CustomAdapter arrayAdapter=new CustomAdapter(getApplicationContext(),array);
        list.setAdapter(arrayAdapter);
        next.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }


}
