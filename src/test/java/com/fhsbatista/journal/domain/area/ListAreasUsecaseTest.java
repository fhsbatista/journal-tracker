package com.fhsbatista.journal.domain.area;

import com.fhsbatista.journal.data.area.AreaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ListAreasUsecaseTest {
    @Mock
    private AreaRepository repository;

    @InjectMocks
    private ListAreasUsecase usecase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCall_onRepositorySuccess_returnCorrectResult() {
        var areas = List.of(
                new Area("test1"),
                new Area("test2")
        );
        when(repository.findAll()).thenReturn(areas);

        var result = usecase.call();

        assertEquals(areas, result);
    }

}