package com.fhsbatista.journal.presentation.habit;

import com.fhsbatista.journal.data.habit.HabitRepository;
import com.fhsbatista.journal.domain.*;
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

    @Mock
    private PerformHabitException performHabitException;

    @InjectMocks
    private HabitController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class PerformTests {
        @Test
        void testCall_callsUsecaseCorrectly() throws PerformHabitException {
            var area = new Area("test");
            var habit = new Habit(area, "test", 1.0 );
            var expectedEvent = new Event(habit, LocalDate.now(), 1.0);

            when(habitRepository.getReferenceById(eq(habit.getId()))).thenReturn(habit);
            when(performHabitUsecase.call(eq(habit))).thenReturn(expectedEvent);

            controller.perform(habit.getId());

            verify(performHabitUsecase).call(habit);
        }

        @Test
        void testCall_whenUsecaseSucceeds_shouldReturnCorrectResponse() throws PerformHabitException {
            var area = new Area("test");
            var habit = new Habit(area, "test", 1.0 );
            var expectedEvent = new Event(habit, LocalDate.now(), 1.0);

            when(habitRepository.getReferenceById(eq(habit.getId()))).thenReturn(habit);
            when(performHabitUsecase.call(eq(habit))).thenReturn(expectedEvent);

            var responseEntity = controller.perform(habit.getId());
            var response = responseEntity.getBody();

            assertEquals(200, responseEntity.getStatusCode().value());
            assertEquals(new EventDetails(expectedEvent), response);
        }

        @Test
        void testCall_whenUsecaseFailsUseExpectedException_shouldReturnCorrectResponse() throws PerformHabitException {
            var area = new Area("test");
            var habit = new Habit(area, "test", 1.0 );
            var exceptionMessage = "exception message";

            when(habitRepository.getReferenceById(eq(habit.getId()))).thenReturn(habit);
            when(performHabitUsecase.call(eq(habit))).thenThrow(performHabitException);
            when(performHabitException.getMessage()).thenReturn(exceptionMessage);


            var responseEntity = controller.perform(habit.getId());
            ErrorDetails response = (ErrorDetails) responseEntity.getBody();

            assertEquals(400, responseEntity.getStatusCode().value());
            assertNotNull(response);
            assertEquals(exceptionMessage, response.message());
        }
    }

}