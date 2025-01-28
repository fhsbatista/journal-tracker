package com.fhsbatista.journal.domain.area;

import com.fhsbatista.journal.data.area.AreaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetAreaUsecaseTest {
    @Mock
    private AreaRepository repository;

    @InjectMocks
    private GetAreaUsecase usecase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCall_onRepositorySuccess_returnCorrectResult() {
        var area = new Area(1L, "test1");
        when(repository.getReferenceById(area.getId())).thenReturn(area);

        var result = usecase.call(area.getId());

        assertEquals(area, result);
    }
}


