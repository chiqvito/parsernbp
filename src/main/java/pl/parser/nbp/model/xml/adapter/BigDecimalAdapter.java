package pl.parser.nbp.model.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class BigDecimalAdapter extends XmlAdapter<String, BigDecimal> {

    private final DecimalFormat decimalFormat;

    public BigDecimalAdapter() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator(',');
        String pattern = "#,##0.0#";
        this.decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
    }

    @Override
    public BigDecimal unmarshal(String v) throws Exception {
        return (BigDecimal) decimalFormat.parse(v);
    }

    @Override
    public String marshal(BigDecimal v) throws Exception {
        return v.toString();
    }
}
