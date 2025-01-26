package com.fhsbatista.journal.presentation.habit;

import com.fhsbatista.journal.domain.Event;

import java.time.LocalDate;

public record EventDetails(
        Long id,
        LocalDate time,
        HabitDetails habit
) {
    public EventDetails(Event event) {
        this(
                event.getId(),
                event.getTime(),
                new HabitDetails(event.getHabit())
        );
    }
}
