package com.fhsbatista.journal.domain;

public class PerformHabitException extends Exception {

    public PerformHabitError reason;

    public PerformHabitException(PerformHabitError reason) {
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return switch (reason) {
            case ALREADY_PERFORMED -> "Cannot perform same habit twice on same day";
        };
    }
}

