package com.fhsbatista.journal.presentation.area.body;

import com.fhsbatista.journal.domain.area.Area;
import jakarta.validation.constraints.NotBlank;

public record AreaRegisterBody(
        @NotBlank
        String description
) {
    public Area toArea() {
        return new Area(description);
    }
}
