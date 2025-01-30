package com.fhsbatista.journal.domain.area.dto;

import com.fhsbatista.journal.domain.area.Area;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AreaUpdateDTO(
        @NotNull
        Long id,

        String description
) {
    public Area toArea() {
        return new Area(
                id,
                description
        );
    }
}
