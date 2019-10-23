package com.example.aks.MyLabs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.aks.MyLabs.R.id.bdate;
//import static com.example.aks.MyLabs.R.id.datePicker;

/**
 * Created by AKS on 5/4/2018.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Calendar minDate=Calendar.getInstance();
        int year=minDate.get(Calendar.YEAR);
        int month =minDate.get(Calendar.MONTH);
        int day=minDate.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE");
        Date date=new Date(year,month,day-1);
        String dayOfWeek = simpleDateFormat.format(date);
        Log.d("dateeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee",dayOfWeek);
        if(dayOfWeek.equalsIgnoreCase("sunday")){
            int maxdate;
            if(month==0||month==2||month==4||month==6||month==7||month==9||month==11){
                maxdate=31;
            }
            else if(month==3||month==5||month==8||month==10){
                maxdate=30;
            }
            else maxdate=28;
            if(day==maxdate){
                if(month==12){
                    month=1;
                    day=2;
                }
                else{
                    month=month+1;
                    day=1;
                }
            }
            else{
                day=day+1;
            }
        }
        minDate.set(Calendar.DAY_OF_MONTH, day);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),this,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        SimpleDateFormat simpledate= new SimpleDateFormat("EEEE");
        Date date= new Date(year,month,dayOfMonth-1);
        String dayofWeek= simpledate.format(date);
        Button bdate = (Button)getActivity().findViewById(R.id.bdate);
        Log.d("dateeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee",dayofWeek);
        int day=dayOfMonth;
        int monthOfYear=month;
        Log.d("Monthhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"," "+month);
        if(dayofWeek.equalsIgnoreCase("sunday")){
            AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
            builder.setMessage("Please Select any other day than Sunday").create().show();
            int maxdate;
            if(month==0||month==2||month==4||month==6||month==7||month==9||month==11){
                maxdate=31;
            }
            else if(month==3||month==5||month==8||month==10){
                maxdate=30;
            }
            else maxdate=28;
            if(day==maxdate){
                if(month==12){
                    monthOfYear=1;
                    day=2;
                }
                else{
                    monthOfYear=monthOfYear+1;
                    day=1;
                }
            }
            else{
                day=day+1;
            }
        }
        bdate.setText(day+"/"+(monthOfYear+1)+"/"+year);
    }


}
