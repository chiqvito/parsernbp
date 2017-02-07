package pl.parser.nbp.calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.parser.nbp.model.xml.ExchangeRatesSeries;

public abstract class BaseCalculator implements Calculator {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final ExchangeRatesSeries series;
    protected final CalcMode mode;

    public BaseCalculator(ExchangeRatesSeries series, CalcMode mode) {
        this.series = series;
        this.mode = mode;
    }
}
