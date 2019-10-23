package com.example.aks.MyLabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;


/**
 * Created by AKS on 1/19/2018.
 */

public class CustomAdapter extends BaseAdapter {

    static String[]  names;
    Context context;
    static LayoutInflater inflter;



    public CustomAdapter(Context context, String[] names) {
        this.context = context;
        this.names = names;
        inflter = (LayoutInflater.from(context));

    }

    @Override
    public int getCount() {
        return names.length;
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
    public View getView(final int position, View view, final ViewGroup parent) {
        view = inflter.inflate(R.layout.activity_simple_list, null);
        final CheckedTextView simpleCheckedTextView = (CheckedTextView) view.findViewById(R.id.textView);
        simpleCheckedTextView.setText(names[position]);
        simpleCheckedTextView.setChecked(Status.getStatus(position));
       // final TestArray obj = new TestArray(names.length);
        simpleCheckedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (simpleCheckedTextView.isChecked()) {

                    TestArray.delItem(simpleCheckedTextView.getText().toString());

                   Status.setStatus(false,position);

                    simpleCheckedTextView.setChecked(false);
                } else {

                    TestArray.addItem(simpleCheckedTextView.getText().toString());
                    Status.setStatus(true,position);

                    simpleCheckedTextView.setChecked(true);
                }

            }
        });
        return view;
    }
}
