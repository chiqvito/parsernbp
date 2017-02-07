package pl.parser.nbp.calculator;

import pl.parser.nbp.model.xml.ExchangeRatesSeries;

public class CalcFactory {

    private static class InstanceHolder {
        public static CalcFactory instance = new CalcFactory();
    }

    public static CalcFactory getInstance() {
        return InstanceHolder.instance;
    }

    private CalcFactory() {
    }

    public Calculator factor(CalcMode mode, ExchangeRatesSeries series) {
        switch (mode) {
            case AVERAGE_BUY:
                return new Average(series, CalcMode.AVERAGE_BUY);
            case AVERAGE_SALE:
                return new Average(series, CalcMode.AVERAGE_SALE);
            case STANDARD_DEVIATION_BUY:
                return new StandardDeviation(series, CalcMode.STANDARD_DEVIATION_BUY);
            case STANDARD_DEVIATION_SALE:
                return new StandardDeviation(series, CalcMode.STANDARD_DEVIATION_SALE);
        }
        throw new IllegalArgumentException("Cannot factor calculator for mode:" + mode);
    }

}
