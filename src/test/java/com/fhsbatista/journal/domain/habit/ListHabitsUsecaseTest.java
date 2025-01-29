package com.fhsbatista.journal.domain.habit;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.data.habit.HabitRepository;
import com.fhsbatista.journal.domain.area.Area;
import com.fhsbatista.journal.domain.habit.ListHabitsUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ListHabitsUsecaseTest {
    @Mock
    private HabitRepository repository;

    @InjectMocks
    private ListHabitsUsecase usecase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCall_onRepositorySuccess_returnCorrectResult() {
        var area = new Area("test");
        var habits = List.of(
                new Habit(area, "test1", 1.0),
                new Habit(area, "test2", 1.0)
        );
        when(repository.findAll()).thenReturn(habits);

        var result = usecase.call();

        assertEquals(habits, result);
    }
}