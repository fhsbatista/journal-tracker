package com.fhsbatista.journal.presentation.habit;

import com.fhsbatista.journal.domain.Area;
import com.fhsbatista.journal.domain.Habit;
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
