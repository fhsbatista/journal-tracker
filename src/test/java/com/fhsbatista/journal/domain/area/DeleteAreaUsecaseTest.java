package com.fhsbatista.journal.domain.area;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.domain.area.dto.AreaUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class DeleteAreaUsecaseTest {
    @Mock
    private AreaRepository repository;

    @InjectMocks
    private DeleteAreaUsecase usecase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCall_onRepositorySuccess_returnCorrectResult() {
        var area = new Area(1L, "test");

        usecase.call(area.getId());

        verify(repository).deleteById(eq(area.getId()));
    }
}