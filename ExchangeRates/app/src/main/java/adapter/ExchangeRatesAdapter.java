package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxim.exchangerates.R;

import java.util.ArrayList;

import data.ExchangeRates;

public class ExchangeRatesAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    ArrayList<ExchangeRates> objects;

    public ExchangeRatesAdapter(Context context, ArrayList<ExchangeRates> items){
        layoutInflater = LayoutInflater.from(context);
        objects = items;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (null == view){
            view = layoutInflater.inflate(R.layout.activity_main, parent, false);
        }

        ExchangeRates exchangeRates = objects.get(position);
        ((TextView) view.findViewById(R.id.lblValutaName)).setText(exchangeRates.getValutaAttbt());
        ((TextView) view.findViewById(R.id.lblValue)).setText(exchangeRates.getValue().substring(0,5));
        ((ImageView) view.findViewById(R.id.flag)).setImageResource(exchangeRates.getImage());
        ((TextView) view.findViewById(R.id.lblDate)).setText(exchangeRates.getDate());

        return view;
    }
}
