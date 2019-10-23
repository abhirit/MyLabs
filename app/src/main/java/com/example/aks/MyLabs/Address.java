package com.example.aks.MyLabs;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.view.View;




//import com.example.aks.mylabs.R;

public class Address extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] places={"Area","Jharia","Bhagatdih","Dhansar","Bank More","Joraphatak Road","Mutkuria Road","Rangatand","Court More","Hirapur","Police Line","Bertaand","Steel Gate","Koylanagar","Wasseypur"};
String saddress,address,sarea, area;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        User user = SharedPrefManager.getInstance(this).getUser();
        final String mobileno=user.getmobileno();
        final CheckBox cbsaveaddress=(CheckBox)findViewById(R.id.cbsaveaddress);
        final EditText etaddress=(EditText)findViewById(R.id.etaddress);
        Button bnext= (Button)findViewById(R.id.bnext);
        final TextView nosavedaddress =(TextView)findViewById(R.id.nosavedaddress);
        final CheckBox cbsavedaddress = (CheckBox)findViewById(R.id.cbsavedaddress);
        final Spinner placess= (Spinner) findViewById(R.id.spinner);
        final Button bdate= (Button)findViewById(R.id.bdate);
        Intent amount = getIntent();
        Bundle extras = amount.getExtras();
        final String total=extras.getString("Total");
        final String lab = extras.getString("Lab");
        placess.setOnItemSelectedListener(this);
        ArrayAdapter place= new ArrayAdapter(this,android.R.layout.simple_spinner_item,places);
        place.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        placess.setAdapter(place);
        Response.Listener<String> resplist= new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               try {
                   JSONObject jsonresponse = new JSONObject(response);
                   String saddress = jsonresponse.getString("address");
                   String sarea=jsonresponse.getString("area");
                   if(saddress!=null && sarea!=null){
                       setaddress(saddress,sarea);
                       cbsavedaddress.setVisibility(View.VISIBLE);
                       cbsavedaddress.setChecked(true);
                       cbsavedaddress.setText(saddress+", "+sarea);
                       nosavedaddress.setVisibility(View.INVISIBLE);
                       cbsaveaddress.setEnabled(false);
                       etaddress.setEnabled(false);
                       placess.setEnabled(false);
                   }
               } catch (JSONException e){
                   e.printStackTrace();
               }
            }
        };
        Response.ErrorListener erresp = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        AddressRequest addreq = new AddressRequest(mobileno,resplist,erresp);
        RequestQueue queue = Volley.newRequestQueue(Address.this);
        queue.add(addreq);
        cbsavedaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbsavedaddress.isChecked()){
                    cbsaveaddress.setEnabled(false);
                    etaddress.setEnabled(false);
                    placess.setEnabled(false);
                }
                else{
                    cbsaveaddress.setEnabled(true);
                    etaddress.setEnabled(true);
                    placess.setEnabled(true);
                }
            }
        });



        bdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment=new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(),"Date Picker");

        }
        });


        bnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address=etaddress.getText().toString();
                area =placess.getSelectedItem().toString();
                int flag=1;
                if(!(cbsavedaddress.isChecked())){
                    if(TextUtils.isEmpty(address)){
                        etaddress.setError("Please enter your address");
                        flag=0;
                    }
                    else if(TextUtils.isEmpty(area)||area.equalsIgnoreCase("Area")){
                        Toast.makeText(Address.this,"Please select your area",Toast.LENGTH_SHORT).show();
                        flag=0;
                    }
                    else if(bdate.getText().toString().equalsIgnoreCase("select date")){
                        Toast.makeText(Address.this,"Please select the date",Toast.LENGTH_LONG).show();
                        flag=0;
                    }

                }
                if(cbsaveaddress.isChecked()&&!(TextUtils.isEmpty(address))&&!(TextUtils.isEmpty(area)||area.equalsIgnoreCase("Area"))){
                    Response.Listener<String> resplist= new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    };
                    Response.ErrorListener errlist= new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    };
                    SendAddressRequest sendaddreq= new SendAddressRequest(mobileno, address, area,resplist,errlist);
                    RequestQueue reqqueue=Volley.newRequestQueue(Address.this);
                    reqqueue.add(sendaddreq);
                }
                Intent intent = new Intent(Address.this,Invoice.class);
                if(cbsavedaddress.isChecked()){
                    intent.putExtra("Address",saddress);
                    intent.putExtra("Area",sarea);
                }
                else{
                    intent.putExtra("Address",address);
                    intent.putExtra("Area",area);
                }
                    intent.putExtra("Date",bdate.getText());
                    intent.putExtra("Total", total);
                intent.putExtra("Lab",lab);
                if(flag==1){
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }
        });

    }
    public void setaddress(String address, String area){
        this.saddress=address;
        this.sarea=area;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        area= (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
