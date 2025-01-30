package com.fhsbatista.journal.presentation.area.body;

import com.fhsbatista.journal.domain.area.dto.AreaUpdateDTO;
import jakarta.validation.constraints.NotNull;

public record AreaUpdateBody(
        @NotNull
        Long id,

        String description
) {
    public AreaUpdateDTO toDto() {
        return new AreaUpdateDTO(id, description);
    }
}
