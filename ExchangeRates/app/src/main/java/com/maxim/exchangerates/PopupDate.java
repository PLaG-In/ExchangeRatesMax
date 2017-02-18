package com.maxim.exchangerates;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PopupDate extends Activity {

    private EditText fromDate;
    private EditText toDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_date);

        fromDate = (EditText) findViewById(R.id.fromDate);
        toDate = (EditText) findViewById(R.id.toDate);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7), (int)(height*.4));

        Intent intents = getIntent();
        final String valuta = intents.getStringExtra("valuta");

        Button showButton = (Button) findViewById(R.id.btnShowTable);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fromStringDate = fromDate.getText().toString();
                final String toStringDate = toDate.getText().toString();
                if(checkDates(fromStringDate, toStringDate)){
                    Intent intent = new Intent(PopupDate.this, RangeRateActivity.class);
                    intent.putExtra("from", fromStringDate);
                    intent.putExtra("to", toStringDate);
                    intent.putExtra("valuta", valuta);
                    startActivity(intent);
                    finish();
                }else {
                    callMessageBox();
                }
            }
        });
    }

    private void callMessageBox(){
        Toast.makeText(getApplicationContext(), "Incorrect date, check it, please", Toast.LENGTH_LONG).show();
    }

    private boolean checkDates(String from, String to){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            //no info before this date
            String min = "01/01/1999";

            Date date1 = formatter.parse(from);
            Date date2 = formatter.parse(to);
            Date minDate = formatter.parse(min);

            if ((date1.compareTo(date2) <= 0)
                    && (date1.compareTo(minDate) >= 0)) {
                return true;
            }

        }catch (ParseException e1){
            e1.printStackTrace();
            return false;
        }
        return false;
    }
}
