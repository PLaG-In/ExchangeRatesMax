package data;

import com.maxim.exchangerates.R;

public class ExchangeRates {
    private String valuta;
    private String date;
    private String value;
    private String temperatureUnit;
    private int humidity;
    private String humUnit;
    private int flag;
    private static final String ID = "R01235";

    public ExchangeRates(String valuta, String date, String value) {
        this.valuta = valuta;
        this.date = date;
        this.value = value;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValutaAttbt(){
        if (valuta.equals(ID))
            return "USD";
        else
            return "EUR";
    }

    public int getImage() {
        if (valuta.equals(ID))
            flag = R.mipmap.usa;
        else
            flag = R.mipmap.euro;
        return flag;
    }
}
