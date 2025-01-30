package com.fhsbatista.journal.domain.area;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.domain.area.dto.AreaUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class UpdateAreaUsecaseTest {
    @Mock
    private AreaRepository repository;

    @InjectMocks
    private UpdateAreaUsecase usecase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCall_onRepositorySuccess_returnCorrectResult() {
        var dto = new AreaUpdateDTO(1L,"updated area");
        var persistedArea = mock(Area.class);

        when(repository.getReferenceById(eq(dto.id()))).thenReturn(persistedArea);

        var result = usecase.call(dto);

        verify(repository).getReferenceById(eq(dto.id()));
        verify(persistedArea).updateData(eq(dto));
        assertNotNull(result);
        assertEquals(persistedArea, result);
    }
}