package com.maxim.exchangerates;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import adapter.ExchangeRatesAdapter;
import data.ExchangeRangeRates;
import data.ExchangeRates;

//example date 02/03/2002

//Euro ID R01239, Dollar ID R01235
//URL example http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=02/03/2015&date_req2=14/03/2016&VAL_NM_RQ=R01235

public class RangeRateActivity extends AppCompatActivity {
    private String main_url = "http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=";
    private static final String VAL_NM_RQ = "&VAL_NM_RQ=";
    private static final String DATE_REQ_2 = "&date_req2=";
    private String url_string;
    private ListView lstView;
    private String fromDate;
    private String toDate;
    private String valuta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        lstView = (ListView) findViewById(R.id.lstRatesChanges);
        Intent intent = getIntent();

        fromDate = intent.getStringExtra("from");
        toDate = intent.getStringExtra("to");
        valuta = intent.getStringExtra("valuta");
        (new ParseSimpleXML()).execute();
    }

    class ParseSimpleXML extends AsyncTask<Context, Void, Void>{
        private ArrayList<ExchangeRates> ratesList;

        @Override
        protected Void doInBackground(Context... params) {
            InputStream is = null;
            try {
                url_string = main_url + fromDate + DATE_REQ_2 + toDate + VAL_NM_RQ + valuta;
                URL url = new URL(url_string);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                is = connection.getInputStream();

                if (null != is) {
                    try{
                        ratesList = new ArrayList<ExchangeRates>();
                        Serializer serializer = new Persister();
                        ExchangeRangeRates exchangeRangeRates = serializer.read(ExchangeRangeRates.class, is);

                        for (ExchangeRangeRates.RateStats stats : exchangeRangeRates.getRecords()){
                            ratesList.add(new ExchangeRates(stats.getID(), stats.getDate(), stats.getValue()));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                try{
                    if (null != is){
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (null != lstView && null != ratesList){
                ExchangeRatesAdapter adapter = new ExchangeRatesAdapter(RangeRateActivity.this, ratesList);
                lstView.setAdapter(adapter);
            }
        }
    }
}

