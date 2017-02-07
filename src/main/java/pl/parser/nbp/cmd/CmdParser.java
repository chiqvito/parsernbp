package pl.parser.nbp.cmd;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.parser.nbp.model.Currency;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CmdParser {

    private static final Logger logger = LoggerFactory.getLogger(CmdParser.class);

    private static final String msg = "Missing arguments. Should be [currency EUR] [start_date yyyy-mm-dd] [end_date yyyy-mm-dd]";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private List<String> listArgs;

    public CmdParser(String[] args) throws ParseException {
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();

        CommandLine line = parser.parse(options, args);
        listArgs = line.getArgList();
        if (listArgs.size() != 3)
            throw new MissingArgumentException(msg);
    }

    private Currency currency() throws ParseException {
        try {
            String value = listArgs.get(0);
            return Currency.valueOf(value);
        } catch (Exception e) {
            throw new ParseException("Invalid currency code: " + listArgs.get(0) + ". " + msg);
        }
    }

    private LocalDate startDate() throws ParseException {
        try {
            String value = listArgs.get(1);
            return LocalDate.parse(value, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid start date: " + listArgs.get(1) + ". " + msg);
        }
    }

    private LocalDate endDate() throws ParseException {
        try {
            String value = listArgs.get(2);
            return LocalDate.parse(value, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid start date: " + listArgs.get(2) + ". " + msg);
        }
    }

    public CmdProperties parser() throws ParseException {
        return CmdProperties
                .cmdProperties()
                .withCurrency(currency())
                .withDateStart(startDate())
                .withDateEnd(endDate());
    }
}
