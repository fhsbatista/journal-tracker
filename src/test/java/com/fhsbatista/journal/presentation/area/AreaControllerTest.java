package com.fhsbatista.journal.presentation.area;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.domain.Area;
import com.fhsbatista.journal.domain.GetAreaScoreAverageUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class AreaControllerTest {

    @Mock
    private AreaRepository repository;

    @Mock
    private GetAreaScoreAverageUsecase getAreaScoreAverageUsecase;

    @InjectMocks
    private AreaController areaController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void todayAverage_shouldReturnCorrectAverage() {
        var area = new Area("test");
        var averageScore = 4.5;

        when(repository.getReferenceById(area.getId())).thenReturn(area);
        when(getAreaScoreAverageUsecase.call(any(), eq(0))).thenReturn(averageScore);

        var responseEntity = areaController.todayAverage(area.getId());
        DateAverageDetails response = (DateAverageDetails) responseEntity.getBody();

        assertEquals(200, responseEntity.getStatusCode().value());
        assertNotNull(response);
        assertEquals("test", response.areaDescription());
        assertEquals(4.5, response.average());
        assertEquals(LocalDate.now(), response.date());
    }

}