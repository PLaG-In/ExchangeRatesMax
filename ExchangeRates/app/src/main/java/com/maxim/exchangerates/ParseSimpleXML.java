package com.maxim.exchangerates;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import data.ExchangeDailyRates;
import data.ExchangeRates;


//example date 02/03/2002

//Euro ID R01239, Dollar ID R01235
public class ParseSimpleXML extends AsyncTask<Context, Void, Void> {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date tDate = new Date();
    String today = formatter.format(tDate);
    private String main_url = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + today;
    private ListView lstView;

    public ArrayList<ExchangeRates> ratesList;

    @Override
    protected Void doInBackground(Context... params) {
        InputStream is = null;
        try {
            URL url = new URL(main_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = connection.getInputStream();

            if (null != is) {
                try {
                    Serializer serializer = new Persister();
                    ExchangeDailyRates exchangeDailyRates = serializer.read(ExchangeDailyRates.class, is);
                    ratesList = new ArrayList<ExchangeRates>();
                    for (ExchangeDailyRates.RateStats stats : exchangeDailyRates.getRecords()) {
                        if((stats.getID().equals("R01235"))|| (stats.getID().equals("R01239"))){
                            ratesList.add(new ExchangeRates(stats.getID(), stats.getDate(), stats.getValue()));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}


