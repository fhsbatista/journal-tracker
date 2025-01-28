package com.fhsbatista.journal.presentation.area;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.domain.area.*;
import com.fhsbatista.journal.presentation.area.body.AreaUpdateBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AreaControllerTest {
    @Mock
    private AreaRepository repository;

    @Mock
    private GetAreaScoreAverageUsecase getAreaScoreAverageUsecase;

    @Mock
    private ListAreasUsecase listAreasUsecase;

    @Mock
    private GetAreaUsecase getAreaUsecase;

    @Mock
    private UpdateAreaUsecase updateAreaUsecase;

    @InjectMocks
    private AreaController areaController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void get_onUsecaseSuccess_returnsCorrectResponse() {
        var area = new Area(1L, "test");
        when(getAreaUsecase.call(eq(area.getId()))).thenReturn(area);

        var responseEntity = areaController.get(area.getId());
        AreaDetails response = responseEntity.getBody();

        assertEquals(200, responseEntity.getStatusCode().value());
        assertNotNull(response);
        assertEquals(response, new AreaDetails(area));
    }

    @Test
    void list_onUsecaseSuccess_returnsCorrectResponse() {
        var areas = List.of(
                new Area("test1"),
                new Area("test2")
        );

        when(listAreasUsecase.call()).thenReturn(areas);

        var responseEntity = areaController.list();
        List<AreaDetails> response = responseEntity.getBody();

        assertEquals(200, responseEntity.getStatusCode().value());
        assertNotNull(response);
        assertEquals(response, areas.stream().map(AreaDetails::new).toList());
    }

    @Test
    void update_onUsecaesSuccess_returnsCorrectResponse() {
        var body = new AreaUpdateBody(1L, "updated area");
        var updatedArea = body.toDto().toArea();

        when(updateAreaUsecase.call(eq(body.toDto()))).thenReturn(updatedArea);

        var responseEntity = areaController.update(body);
        var response = responseEntity.getBody();

        assertEquals(200, responseEntity.getStatusCode().value());
        assertNotNull(response);
        assertEquals(response, new AreaDetails(body.toDto().toArea()));
    }

    @Test
    void todayAverage_shouldReturnCorrectAverage() {
        var area = new Area("test");
        var averageScore = 4.5;

        when(repository.getReferenceById(area.getId())).thenReturn(area);
        when(getAreaScoreAverageUsecase.call(eq(area), eq(1))).thenReturn(averageScore);

        var responseEntity = areaController.todayAverage(area.getId());
        DateAverageDetails response = (DateAverageDetails) responseEntity.getBody();

        verify(getAreaScoreAverageUsecase).call(eq(area), eq(1));
        assertEquals(200, responseEntity.getStatusCode().value());
        assertNotNull(response);
        assertEquals("test", response.areaDescription());
        assertEquals(4.5, response.average());
        assertEquals(LocalDate.now(), response.date());
    }

}