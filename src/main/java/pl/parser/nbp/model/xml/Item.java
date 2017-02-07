package pl.parser.nbp.model.xml;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import pl.parser.nbp.model.Currency;
import pl.parser.nbp.model.xml.adapter.BigDecimalAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;

public class Item {

    private String name;
    private Integer ratio;
    private Currency currency;
    private BigDecimal sale;
    private BigDecimal buy;

    public String getName() {
        return name;
    }

    public Integer getRatio() {
        return ratio;
    }

    @XmlElement(name = "przelicznik")
    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public Currency getCurrency() {
        return currency;
    }

    @XmlElement(name = "kod_waluty")
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getSale() {
        return sale;
    }

    @XmlElement(name = "kurs_sprzedazy")
    @XmlJavaTypeAdapter(type = BigDecimal.class, value = BigDecimalAdapter.class)
    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    @XmlElement(name = "kurs_kupna")
    @XmlJavaTypeAdapter(type = BigDecimal.class, value = BigDecimalAdapter.class)
    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    @XmlElement(name = "nazwa_waluty")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("name", name)
                .append("ratio", ratio)
                .append("currency", currency)
                .append("sale", sale)
                .append("buy", buy)
                .toString();
    }
}
