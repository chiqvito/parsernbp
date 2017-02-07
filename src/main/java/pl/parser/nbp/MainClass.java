package pl.parser.nbp;

import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.parser.nbp.api.ExchangeRateCall;
import pl.parser.nbp.calculator.CalcFactory;
import pl.parser.nbp.calculator.CalcMode;
import pl.parser.nbp.calculator.Calculator;
import pl.parser.nbp.cmd.CmdParser;
import pl.parser.nbp.cmd.CmdProperties;
import pl.parser.nbp.model.xml.ExchangeRatesSeries;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;

public class MainClass {

    private static final Logger logger = LoggerFactory.getLogger(MainClass.class);

    public static void main(String[] args) {
        try {
            CmdProperties properties = new CmdParser(args).parser();
            logger.debug("[{}]", properties);

            ExchangeRateCall call = new ExchangeRateCall(properties);
            ExchangeRatesSeries series = call.call();

            Calculator calculator = CalcFactory.getInstance().factor(CalcMode.AVERAGE_BUY, series);
            BigDecimal avgBuy = calculator.calculate();
            calculator = CalcFactory.getInstance().factor(CalcMode.STANDARD_DEVIATION_SALE, series);
            BigDecimal stdDev = calculator.calculate();

            System.out.println("=============================");
            System.out.println(avgBuy);
            System.out.println(stdDev);
            System.out.println("=============================");

        } catch (ParseException exp) {
            System.err.println("ERROR:" + exp.getMessage());
            logger.error("[{}]", exp.getMessage(), exp);
        } catch (JAXBException e) {
            System.err.println("ERROR:" + e.getMessage());
            logger.error("[{}]", e.getMessage(), e);
        } catch (IOException e) {
            System.err.println("ERROR:" + e.getMessage());
            logger.error("[{}]", e.getMessage(), e);
        }
    }
}
