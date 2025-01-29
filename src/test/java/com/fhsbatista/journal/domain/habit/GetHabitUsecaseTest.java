package com.fhsbatista.journal.domain.habit;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.data.habit.HabitRepository;
import com.fhsbatista.journal.domain.area.Area;
import com.fhsbatista.journal.domain.area.GetAreaUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetHabitUsecaseTest {
    @Mock
    private HabitRepository repository;

    @InjectMocks
    private GetHabitUsecase usecase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCall_onRepositorySuccess_returnCorrectResult() {
        var area = new Area("test");
        var habit = new Habit(area, "test", 1.0 );
        when(repository.getReferenceById(habit.getId())).thenReturn(habit);

        var result = usecase.call(habit.getId());

        assertEquals(habit, result);
    }
}


