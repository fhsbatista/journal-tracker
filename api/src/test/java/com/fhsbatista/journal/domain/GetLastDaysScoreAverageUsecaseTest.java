package com.fhsbatista.journal.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetLastDaysScoreAverageUsecaseTest {
    @InjectMocks
    private GetLastDaysScoreAverageUsecase usecase;

    @Mock
    private GetDayScoreAverageUsecase getDayScoreAverageUsecase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCall() {
        var day0 = LocalDate.now();
        var dayMinus1 = LocalDate.now().minusDays(1);
        var dayMinus2 = LocalDate.now().minusDays(2);
        var dayMinus3 = LocalDate.now().minusDays(3);
        var dayMinus4 = LocalDate.now().minusDays(4);
        var dayMinus5 = LocalDate.now().minusDays(5);
        var dayMinus6 = LocalDate.now().minusDays(6);

        when(getDayScoreAverageUsecase.call(day0)).thenReturn(5.0);
        when(getDayScoreAverageUsecase.call(dayMinus1)).thenReturn(4.0);
        when(getDayScoreAverageUsecase.call(dayMinus2)).thenReturn(5.0);
        when(getDayScoreAverageUsecase.call(dayMinus3)).thenReturn(4.0);
        when(getDayScoreAverageUsecase.call(dayMinus4)).thenReturn(3.0);
        when(getDayScoreAverageUsecase.call(dayMinus5)).thenReturn(2.0);
        when(getDayScoreAverageUsecase.call(dayMinus6)).thenReturn(5.0);

        double result = usecase.call(7);

        assertEquals(4.0, result);
    }

}