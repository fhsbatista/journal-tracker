package com.fhsbatista.journal.domain.habit;

import com.fhsbatista.journal.data.habit.HabitRepository;
import com.fhsbatista.journal.domain.habit.dto.HabitUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class UpdateHabitUsecaseTest {
    @Mock
    private HabitRepository repository;

    @InjectMocks
    private UpdateHabitUsecase usecase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCall_onRepositorySuccess_returnCorrectResult() {
        var dto = new HabitUpdateDTO(1L,"updated area", 1.0);
        var persistedHabit = mock(Habit.class);

        when(repository.getReferenceById(eq(dto.id()))).thenReturn(persistedHabit);

        var result = usecase.call(dto);

        verify(repository).getReferenceById(eq(dto.id()));
        verify(persistedHabit).updateData(eq(dto));
        assertNotNull(result);
        assertEquals(persistedHabit, result);
    }
}