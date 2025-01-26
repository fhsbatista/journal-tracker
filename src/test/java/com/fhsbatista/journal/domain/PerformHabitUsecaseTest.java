package com.fhsbatista.journal.domain;

import com.fhsbatista.journal.data.habit.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PerformHabitUsecaseTest {
    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private PerformHabitUsecase usecase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCall_shouldCreateEventCorrectly() {
        var area = new Area("test");
        var habit = new Habit(area, "test", 5.0);
        var event = new Event(habit, LocalDate.now(), 5.0);

        when(eventRepository.save(eq(event))).thenReturn(event);

        var result = usecase.call(habit);

        verify(eventRepository).save(eq(event));
        assertEquals(event, result);
    }

}