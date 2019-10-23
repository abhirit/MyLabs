package com.example.aks.MyLabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final Button blogout = (Button)findViewById(R.id.blogout);
        final TextView tvwelcome = (TextView)findViewById(R.id.tvwelcome);
        final TextView tvmobileno = (TextView)findViewById(R.id.tvmobileno);

        User user = SharedPrefManager.getInstance(this).getUser();
        String name = user.getname();
        String mobileno = user.getmobileno();

        tvwelcome.setText("WELCOME  "+name.toUpperCase());
        tvmobileno.setText(mobileno);


        blogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });
    }
}
