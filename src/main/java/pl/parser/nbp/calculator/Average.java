package pl.parser.nbp.calculator;

import pl.parser.nbp.model.xml.ExchangeRatesSeries;
import pl.parser.nbp.model.xml.Rate;

import java.math.BigDecimal;

public class Average extends BaseCalculator {
    public Average(ExchangeRatesSeries series, CalcMode mode) {
        super(series, mode);
    }

    @Override
    public BigDecimal calculate() {
        BigDecimal sum = series
                .getRates()
                .stream()
                .map(CalcMode.AVERAGE_BUY.equals(mode) ? Rate::getBuy : Rate::getSale)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal count = new BigDecimal(series.getRates().size());
        BigDecimal value = sum.divide(count, 4, BigDecimal.ROUND_HALF_UP);
        logger.info("value [{}]", value);
        return value;
    }
}
