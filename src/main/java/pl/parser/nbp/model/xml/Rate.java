package pl.parser.nbp.model.xml;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import pl.parser.nbp.model.xml.adapter.BigDecimalAdapter;
import pl.parser.nbp.model.xml.adapter.DateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Rate {

    private String no;
    private LocalDate date;
    private BigDecimal buy;
    private BigDecimal sale;

    public String getNo() {
        return no;
    }

    @XmlElement(name = "No")
    public void setNo(String no) {
        this.no = no;
    }

    public LocalDate getDate() {
        return date;
    }

    @XmlElement(name = "EffectiveDate")
    @XmlJavaTypeAdapter(type = LocalDate.class, value = DateAdapter.class)
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    @XmlElement(name = "Bid")
    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getSale() {
        return sale;
    }

    @XmlElement(name = "Ask")
    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("no", no)
                .append("date", date)
                .append("buy", buy)
                .append("sale", sale)
                .toString();
    }
}
