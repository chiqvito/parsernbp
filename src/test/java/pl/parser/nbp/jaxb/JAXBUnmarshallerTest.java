package pl.parser.nbp.jaxb;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.parser.nbp.model.xml.ExchangeRatesSeries;
import pl.parser.nbp.model.xml.RateTable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBUnmarshallerTest {

    private static final Logger logger = LoggerFactory.getLogger(JAXBUnmarshallerTest.class);

//    http://api.nbp.pl/api/exchangerates/rates/C/USD/2012-01-03/2012-01-31/

    @Test
    public void unmarshallerApi() throws JAXBException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("data.xml").getFile());
        logger.info("[{}]", file.getAbsolutePath());

        JAXBContext jaxbContext = JAXBContext.newInstance(ExchangeRatesSeries.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ExchangeRatesSeries series = (ExchangeRatesSeries) jaxbUnmarshaller.unmarshal(file);

        logger.info("[{}]", series);

        Assertions.assertThat(series.getName()).isNotEmpty();
        Assertions.assertThat(series.getCurrency()).isNotNull();
        Assertions.assertThat(series.getTable()).isNotEmpty();
        Assertions.assertThat(series.getRates()).isNotEmpty();
        series.getRates().forEach(i -> {
            Assertions.assertThat(i.getBuy()).isNotNull();
            Assertions.assertThat(i.getDate()).isNotNull();
            Assertions.assertThat(i.getNo()).isNotNull();
            Assertions.assertThat(i.getSale()).isNotNull();
        });
    }

    @Test
    public void unmarshaller() throws JAXBException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("c073z070413.xml").getFile());
        logger.info("[{}]", file.getAbsolutePath());

        JAXBContext jaxbContext = JAXBContext.newInstance(RateTable.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        RateTable table = (RateTable) jaxbUnmarshaller.unmarshal(file);

        logger.info("[{}]", table);

        Assertions.assertThat(table.getType()).isNotEmpty();
        Assertions.assertThat(table.getPublicationDate()).isNotNull();
        Assertions.assertThat(table.getRecordDate()).isNotNull();
        Assertions.assertThat(table.getTableNo()).isNotEmpty();
        Assertions.assertThat(table.getItems()).isNotEmpty();
        table.getItems().forEach(i -> {
            Assertions.assertThat(i.getBuy()).isNotNull();
            Assertions.assertThat(i.getCurrency()).isNotNull();
            Assertions.assertThat(i.getName()).isNotNull();
            Assertions.assertThat(i.getRatio()).isNotNull();
            Assertions.assertThat(i.getSale()).isNotNull();
        });
    }

}
