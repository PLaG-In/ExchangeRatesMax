package data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "ValCurs", strict = false)
public class ExchangeDailyRates {

    @Attribute(name = "Date", required = false)
    private static String date;

    @ElementList(name = "Valute", required = false, inline = true)
    private ArrayList<RateStats> records;

    public ArrayList<RateStats> getRecords() {
        return records;
    }

    @Root(name = "Valute", strict = false)
    public static class RateStats {

        @Attribute(name = "ID", required = false)
        private String id;

        @Element(name = "Value", required = false)
        private String value;

        public String getID() {
            return id;
        }

        public String getDate(){ return date; }

        public String getValue() {
            return value;
        }
    }
}