package com.example.aks.MyLabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import com.example.aks.mylabs.R;

public class LabDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_detail);
        EditText metname =(EditText)findViewById(R.id.metname);
        EditText metrate =(EditText)findViewById(R.id.metrate);
        TextView tvlabname =(TextView)findViewById(R.id.tvlabname);
        TextView tvtotal = (TextView)findViewById(R.id.tvtotal);
        Button bconfirm=(Button)findViewById(R.id.bconfirm);
        Intent intent= getIntent();
        Bundle extras=intent.getExtras();
        String[] array=extras.getStringArray("testnames");
        final String[] labdet=extras.getStringArray("labdetails");
        int length = TestArray.getlength();
        final String total= (String) extras.get("total");


        //setting the texts
        tvlabname.setText(""+labdet[0]);
        StringBuffer str1= new StringBuffer("");
        StringBuffer str2=new StringBuffer("");

        for(int i=0;i<length;i++){
            str1.append(""+array[i]+"\n\n");
            str2.append(""+labdet[i+1]+"\n\n");

        }
        String mettext=str1.toString();
        String metrat =str2.toString();
        metrate.setText(metrat);
        metname.setText(mettext);
        tvtotal.setText(total);


        bconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent address=new Intent(LabDetail.this,Address.class);
                address.putExtra("Total",total);
                address.putExtra("Lab",labdet[0]);
                startActivity(address);
            }
        });

    }
}
