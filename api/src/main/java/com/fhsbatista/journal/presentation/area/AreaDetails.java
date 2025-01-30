package com.fhsbatista.journal.presentation.area;

import com.fhsbatista.journal.domain.area.Area;

public record AreaDetails(
        Long id,
        String description
) {
    public AreaDetails(Area area) {
        this(area.getId(), area.getDescription());
    }
}
