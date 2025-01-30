package com.fhsbatista.journal.domain;

import com.fhsbatista.journal.data.habit.EventRepository;
import com.fhsbatista.journal.domain.area.Area;
import com.fhsbatista.journal.domain.habit.Habit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

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
    void testCall_shouldCreateEventCorrectly() throws PerformHabitException {
        var area = new Area("test");
        var habit = new Habit(area, "test", 5.0);
        var event = new Event(habit, LocalDate.now(), 5.0);

        when(eventRepository.save(eq(event))).thenReturn(event);

        var result = usecase.call(habit);

        verify(eventRepository).save(eq(event));
        assertEquals(event, result);
    }

    @Test
    void testCall_whenEventHasAlreadyBeenPerformedOnDat_shouldThrowException() {
        var area = new Area("test");
        var habit = new Habit(area, "test", 5.0);
        var alreadyPerformedEvents = List.of(new Event(habit, LocalDate.now(), 5.0));
        var event = new Event(habit, LocalDate.now(), 5.0);

        when(eventRepository.findByTimeAndHabit(eq(LocalDate.now()), eq(habit)))
                .thenReturn(alreadyPerformedEvents);
        when(eventRepository.save(eq(event))).thenReturn(event);

        assertThrows(PerformHabitException.class, () -> {
            usecase.call(habit);
        });
    }

}