package com.anke.EarthQuake.exception;

/**
 * Herhangi bir deprem bulunamadığında fırlatılacak exception
 */
public class NoEarthquakesFoundException extends RuntimeException {
    private final int days;

    public NoEarthquakesFoundException(String message, int days) {
        super(message);
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}
