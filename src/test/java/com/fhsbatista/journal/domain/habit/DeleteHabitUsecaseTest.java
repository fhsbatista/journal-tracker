package com.fhsbatista.journal.domain.habit;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.data.habit.HabitRepository;
import com.fhsbatista.journal.domain.area.Area;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

class DeleteHabitUsecaseTest {
    @Mock
    private HabitRepository repository;

    @InjectMocks
    private DeleteHabitUsecase usecase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCall_onRepositorySuccess_returnCorrectResult() {
        var area = new Area(1L, "test");
        var habit = new Habit(1L, area, "test", 1.0);

        usecase.call(habit.getId());

        verify(repository).deleteById(eq(habit.getId()));
    }
}