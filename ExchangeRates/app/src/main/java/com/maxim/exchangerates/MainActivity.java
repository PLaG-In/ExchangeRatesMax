package com.maxim.exchangerates;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import data.ExchangeRates;


//example date 02/03/2002

//Euro ID R01239, Dollar ID R01235

public class MainActivity extends AppCompatActivity {
    private static final String USD = "R01235";
    private static final String EUR = "R01239";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView lblDollar = (TextView) findViewById(R.id.lblDollar);
        TextView lblEuro = (TextView) findViewById(R.id.lblEuro);

        ParseSimpleXML parse = new ParseSimpleXML();
        parse.execute();

        Button btnShowMonthRates1 = (Button) findViewById(R.id.btnShowMonthRates1);
        btnShowMonthRates1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPopup(USD);
            }
        });

        Button btnShowMonthRates2 = (Button) findViewById(R.id.btnShowMonthRates2);
        btnShowMonthRates2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPopup(EUR);
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (parse.ratesList != null) {
            lblDollar.setText(parse.ratesList.get( 0 ).getValue().substring( 0, 5 ) );
            lblEuro.setText( parse.ratesList.get( 1 ).getValue().substring( 0, 5 ) );
        }
       /* Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);*/
    }

    private void callPopup(final String valuta){
        Intent intent = new Intent(MainActivity.this, PopupDate.class);
        intent.putExtra("valuta", valuta);
        startActivity(intent);
    }

}
