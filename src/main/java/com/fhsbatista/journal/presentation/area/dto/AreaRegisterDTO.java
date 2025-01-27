package com.fhsbatista.journal.presentation.area.dto;

import com.fhsbatista.journal.domain.area.Area;
import jakarta.validation.constraints.NotBlank;

public record AreaRegisterDTO(
        @NotBlank
        String description
) {
    public Area toArea() {
        return new Area(description);
    }
}
