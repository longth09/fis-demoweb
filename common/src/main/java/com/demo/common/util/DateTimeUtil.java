package com.demo.common.util;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class DateTimeUtil {

    public boolean isBetween(ChronoLocalDateTime<?> input,
                             ChronoLocalDateTime<?> from,
                             ChronoLocalDateTime<?> to) {

        if (input.isBefore(from)) {
            return false;
        }
        return !input.isAfter(to);
    }

    public boolean isInFutureOrPresent(Instant input) {
        if (input == null) {
            return true;
        }
        return isInFutureOrEqualOfInstantDate(input, Instant.now());
    }

    public boolean isInFutureOrEqualOfInstantDate(Instant input, Instant inputCompare) {
        Instant instantTruncated = input.truncatedTo(ChronoUnit.DAYS);
        Instant currentTruncated = inputCompare.truncatedTo(ChronoUnit.DAYS);

        if (instantTruncated.equals(currentTruncated) || instantTruncated
                .isAfter(currentTruncated)) {
            return true;
        }
        return false;
    }

}
