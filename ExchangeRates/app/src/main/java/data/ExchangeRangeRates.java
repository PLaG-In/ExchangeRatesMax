package data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "ValCurs", strict = false)
public class ExchangeRangeRates {

    @ElementList(name = "Record", required = false, inline = true)
    private ArrayList<RateStats> records;

    public ArrayList<RateStats> getRecords() {
        return records;
    }

    @Root(name = "Record", strict = false)
    public static class RateStats {

        @Attribute(name = "Date", required = false)
        private String date;

        @Attribute(name = "Id", required = false)
        private String id;

        @Element(name = "Value", required = false)
        private String value;

        public String getDate() {
            return date;
        }

        public String getID() {
            return id;
        }

        public String getValue() {
            return value;
        }
    }
}
