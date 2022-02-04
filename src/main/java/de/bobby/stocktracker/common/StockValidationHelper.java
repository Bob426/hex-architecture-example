package de.bobby.stocktracker.common;

import java.util.function.Predicate;

public class StockValidationHelper {

    public static Predicate<String> isValidSymbol() {
        return p -> p.length() == 4;
    }
}
