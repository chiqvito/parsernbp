package pl.parser.nbp.model.xml;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import pl.parser.nbp.model.Currency;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "ExchangeRatesSeries")
public class ExchangeRatesSeries {

    private String table;
    private String name;
    private Currency currency;

    private List<Rate> rates;

    public List<Rate> getRates() {
        return rates;
    }

    @XmlElement(name = "Rate")
    @XmlElementWrapper(name = "Rates")
    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public String getTable() {
        return table;
    }

    @XmlElement(name = "Table")
    public void setTable(String table) {
        this.table = table;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "Currency")
    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    @XmlElement(name = "Code")
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("table", table)
                .append("name", name)
                .append("currency", currency)
                .append("rates", rates)
                .toString();
    }
}
