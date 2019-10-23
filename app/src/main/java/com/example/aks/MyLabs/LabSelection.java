package com.example.aks.MyLabs;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import static com.example.aks.MyLabs.TestArray.length;

public class LabSelection extends AppCompatActivity {
    ListView labratelist;
    String[][] labdet;
    String[][] total;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_selection);
        TextView textView=(TextView)findViewById(R.id.textView);
        Intent flagg=getIntent();
        Bundle extras = flagg.getExtras();
        int flag=extras.getInt("flag");
        final String[] array=TestArray.getArray();
        final int length=TestArray.getlength();
        labratelist=(ListView)findViewById(R.id.labratelist);
        StringBuffer str= new StringBuffer("SELECT Labs");
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        for(int i=0;i<length;i++){
            str.append(" , "+array[i]);
        }
        if(flag==1){
            str.append(" FROM rate WHERE "+array[0]);
        }
        else if(flag==2){
            str.append(" FROM imagineRate WHERE "+array[0]);
        }

        for(int i=0;i<length;i++){
            str.append(" AND "+array[i]);
        }
        str.append(" !=0");
        String strar=str.toString();

       Response.Listener<String> resplist =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    int len=jsonArray.length();
                    String[][] labRate=new String[len][length+1];
                   JSONArray jsonArray1=new JSONArray();
                    for(int i=0;i<len;i++){
                        jsonArray1=jsonArray.getJSONArray(i);
                        for(int k=0;k<=length;k++){
                            labRate[i][k]=jsonArray1.getString(k);
                        }

                    }
                    filldata(labRate,len,length);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener erresp= new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LabSelection.this);
                builder.setMessage("Unknown error occured").create().show();
            }
        };
        LabRequest labRequest=new LabRequest(strar,resplist,erresp);
        RequestQueue queue= Volley.newRequestQueue(LabSelection.this);
        queue.add(labRequest);
        labratelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LabSelection.this,LabDetail.class);
                intent.putExtra("labdetails",labdet[position]);
                intent.putExtra("testnames",array);
                intent.putExtra("total",total[position][1]);
                startActivity(intent);
            }
        });

    }
    public void filldata(final String[][]tab,int len, int length){
        labdet=tab;
        String[][] labs=new String [len][2];
        for(int i=0;i<len;i++){
            int sum=0;
            for (int j=0;j<=length;j++){

                if(j==0){
                    labs[i][0]=tab[i][j];
                }
                else{
                    sum=sum+Integer.parseInt(tab[i][j]);
                }

            }
            labs[i][1]=""+sum;
        }
        total=labs;
       RateListAdapter rateListAdapter=new RateListAdapter(getApplicationContext(),labs);
        labratelist.setAdapter(rateListAdapter);
        progressBar.setVisibility(View.INVISIBLE);

    }
}
