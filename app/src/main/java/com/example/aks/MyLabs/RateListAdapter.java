package com.example.aks.MyLabs;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import static com.example.aks.MyLabs.CustomAdapter.names;

/**
 * Created by AKS on 1/25/2018.
 */

public class RateListAdapter extends BaseAdapter {
    String[][]labs;
    //int[] ratesum;
    Context context;
    static LayoutInflater inflter;
    public RateListAdapter(Context context, String[][]labs){
       // Log.d("Messageeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee","Inside constructor");
        this.context=context;
        this.labs=labs;
        //this.ratesum=ratesum;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return labs.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Log.d("Messageeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee","inside get view");
        convertView = inflter.inflate(R.layout.activity_lab_rate,null);
        final TextView tvlabs = (TextView) convertView.findViewById(R.id.tvlabs);
        final TextView tvrate = (TextView) convertView.findViewById(R.id.tvrate);
        tvlabs.setText(labs[position][0]);
        tvrate.setText(labs[position][1]);
        /*tvlabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LabDetail.class);
                context.startActivity(intent);
            }
        });*/
        return convertView;
    }
}
