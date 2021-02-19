package ru.sergeantalexander.paste.enumiration;

import java.time.LocalDateTime;

public enum Expiration {

    NEVER,
    TEN_MINUTES,
    HOUR,
    DAY,
    WEEK,
    TWO_WEEKS,
    MONTH,
    SIX_MONTH,
    YEAR;

    public static LocalDateTime getDateForExpiring(Expiration expiration) {

        LocalDateTime now = LocalDateTime.now();

        switch (expiration) {
            case NEVER:
                return now.plusYears(100);
            case TEN_MINUTES:
                return now.plusMinutes(10);
            case HOUR:
                return now.plusHours(1);
            case DAY:
                return now.plusDays(1);
            case WEEK:
                return now.plusWeeks(1);
            case TWO_WEEKS:
                return now.plusWeeks(2);
            case MONTH:
                return now.plusMonths(1);
            case SIX_MONTH:
                return now.plusMonths(6);
            case YEAR:
                return now.plusYears(1);
            default:
                return now;
        }
    }
}
