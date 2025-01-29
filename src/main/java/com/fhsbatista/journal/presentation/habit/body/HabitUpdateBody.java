package com.fhsbatista.journal.presentation.habit.body;

import com.fhsbatista.journal.domain.area.dto.AreaUpdateDTO;
import com.fhsbatista.journal.domain.habit.dto.HabitUpdateDTO;
import com.fhsbatista.journal.presentation.area.body.AreaUpdateBody;
import jakarta.validation.constraints.NotNull;

public record HabitUpdateBody(
        @NotNull
        Long id,

        String description,
        Double currentScore
) {
    public HabitUpdateDTO toDto() {
        return new HabitUpdateDTO(
                id,
                description,
                currentScore
        );
    }
}
