package com.fhsbatista.journal.presentation.habit;

import com.fhsbatista.journal.data.habit.HabitRepository;
import com.fhsbatista.journal.domain.Area;
import com.fhsbatista.journal.domain.Event;
import com.fhsbatista.journal.domain.Habit;
import com.fhsbatista.journal.domain.PerformHabitUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HabitControllerTest {
    @Mock
    private PerformHabitUsecase performHabitUsecase;

    @Mock
    private HabitRepository habitRepository;

    @InjectMocks
    private HabitController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class PerformTests {
        @Test
        void testCall_callsUsecaseCorrectly() {
            var area = new Area("test");
            var habit = new Habit(area, "test", 1.0 );
            var expectedEvent = new Event(habit, LocalDate.now(), 1.0);

            when(habitRepository.getReferenceById(eq(habit.getId()))).thenReturn(habit);
            when(performHabitUsecase.call(eq(habit))).thenReturn(expectedEvent);

            controller.perform(habit.getId());

            verify(performHabitUsecase).call(habit);
        }
    }

}