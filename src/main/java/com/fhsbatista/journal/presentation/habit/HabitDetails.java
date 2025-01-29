package com.fhsbatista.journal.presentation.habit;

import com.fhsbatista.journal.domain.habit.Habit;
import com.fhsbatista.journal.presentation.area.AreaDetails;

public record HabitDetails(
        Long id,
        String description,
        AreaDetails area
) {
    public HabitDetails(Habit habit) {
        this(
                habit.getId(),
                habit.getDescription(),
                new AreaDetails(habit.getArea())
        );
    }
}
