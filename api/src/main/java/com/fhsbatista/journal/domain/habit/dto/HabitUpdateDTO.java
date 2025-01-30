package com.fhsbatista.journal.domain.habit.dto;

import com.fhsbatista.journal.domain.area.Area;
import com.fhsbatista.journal.domain.habit.Habit;
import jakarta.validation.constraints.NotNull;

public record HabitUpdateDTO(
        @NotNull
        Long id,

        String description,
        Double currentScore
) {
}
