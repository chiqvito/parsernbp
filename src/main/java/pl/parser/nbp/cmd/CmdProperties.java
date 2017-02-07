package pl.parser.nbp.cmd;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import pl.parser.nbp.model.Currency;

import java.time.LocalDate;

public class CmdProperties {

    private Currency currency;
    private LocalDate dateStart;
    private LocalDate dateEnd;

    public static CmdProperties cmdProperties() {
        return new CmdProperties();
    }

    public CmdProperties withCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public CmdProperties withDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    public CmdProperties withDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    private CmdProperties() {
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("currency", currency)
                .append("dateStart", dateStart)
                .append("dateEnd", dateEnd)
                .toString();
    }
}
