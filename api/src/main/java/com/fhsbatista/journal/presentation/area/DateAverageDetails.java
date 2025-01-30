package com.fhsbatista.journal.presentation.area;

import java.time.LocalDate;

public record DateAverageDetails(
        String areaDescription,
        double average,
        LocalDate date) {
}
