package pl.parser.nbp.calculator;

import com.jidesoft.utils.BigDecimalMathUtils;
import pl.parser.nbp.model.xml.ExchangeRatesSeries;
import pl.parser.nbp.model.xml.Rate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class StandardDeviation extends BaseCalculator implements Calculator {
    public StandardDeviation(ExchangeRatesSeries series, CalcMode mode) {
        super(series, mode);
    }

    @Override
    public BigDecimal calculate() {
        List<BigDecimal> vs = series
                .getRates()
                .stream()
                .map(CalcMode.STANDARD_DEVIATION_SALE.equals(mode) ? Rate::getSale : Rate::getBuy)
                .collect(Collectors.toList());
        BigDecimal bd = BigDecimalMathUtils.stddev(vs, false, new MathContext(4, RoundingMode.HALF_UP));
        BigDecimal value = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
        logger.info("[{}]", value);
        return value;
    }
}
