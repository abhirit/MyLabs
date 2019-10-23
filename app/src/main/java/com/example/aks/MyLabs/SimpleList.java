package com.example.aks.MyLabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckedTextView;

public class SimpleList extends AppCompatActivity {
    CheckedTextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list);
        textView=(CheckedTextView)findViewById(R.id.textView);

    }

}
