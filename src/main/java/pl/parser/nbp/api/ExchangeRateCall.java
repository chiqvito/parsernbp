package pl.parser.nbp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.parser.nbp.cmd.CmdProperties;
import pl.parser.nbp.model.xml.ExchangeRatesSeries;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

public class ExchangeRateCall {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateCall.class);

    private final CmdProperties properties;

    public ExchangeRateCall(CmdProperties properties) {
        this.properties = properties;
    }

    private String resourceUrl() {
        return MessageFormat.format(
                "http://api.nbp.pl/api/exchangerates/rates/C/{0}/{1}/{2}/?format=xml",
                properties.getCurrency(),
                properties.getDateStart(),
                properties.getDateEnd()
        );
    }

    public ExchangeRatesSeries call() throws JAXBException, IOException {
        String resourceUrl = resourceUrl();
        logger.info("[{}]", resourceUrl);
        URL url = new URL(resourceUrl);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStream is = http.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(ExchangeRatesSeries.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ExchangeRatesSeries series = (ExchangeRatesSeries) jaxbUnmarshaller.unmarshal(is);
        logger.info("[{}]", series);
        return series;
    }

}
