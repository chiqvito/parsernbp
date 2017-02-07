package pl.parser.nbp.model.xml;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import pl.parser.nbp.model.xml.adapter.DateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.List;

@XmlRootElement(name = "tabela_kursow")
public class RateTable {

    private String type;
    private String tableNo;
    private LocalDate recordDate;
    private LocalDate publicationDate;

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    @XmlElement(name = "pozycja")
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getType() {
        return type;
    }

    @XmlAttribute(name = "typ")
    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    @XmlElement(name = "data_notowania", required = true)
    @XmlJavaTypeAdapter(type = LocalDate.class, value = DateAdapter.class)
    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    @XmlElement(name = "data_publikacji", required = true)
    @XmlJavaTypeAdapter(type = LocalDate.class, value = DateAdapter.class)
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getTableNo() {
        return tableNo;
    }

    @XmlElement(name = "numer_tabeli")
    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("type", type)
                .append("tableNo", tableNo)
                .append("publicationDate", publicationDate)
                .append("recordDate", recordDate)
                .append("items", items)
                .toString();
    }
}
