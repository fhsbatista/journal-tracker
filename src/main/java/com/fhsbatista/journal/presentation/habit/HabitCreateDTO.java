package com.fhsbatista.journal.presentation.habit;

import com.fhsbatista.journal.domain.Area;
import com.fhsbatista.journal.domain.Habit;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HabitCreateDTO(
        @NotBlank
        String description,

        @NotNull
        Long areaId,

        @NotNull
        @DecimalMin(value = "0.25")
        @DecimalMax(value = "5.0")
        double currentScore
) {
    public Habit toHabit(Area area) {
        return new Habit(area, description, currentScore);
    }
}
