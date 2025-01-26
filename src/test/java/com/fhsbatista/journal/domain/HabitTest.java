package com.fhsbatista.journal.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HabitTest {

    @Test
    void testHabitConstructorAndGetters() {
        // Arrange
        Area area = new Area("Health");
        String description = "Morning workout";
        Double currentScore = 2.0;

        // Act
        Habit habit = new Habit(area, description, currentScore);

        // Assert
        assertNotNull(habit);
        assertNull(habit.getId());
        assertEquals(area, habit.getArea());
        assertEquals(description, habit.getDescription());
        assertEquals(currentScore, habit.getCurrentScore());
    }

    @Test
    void testAddEventToHabit() {
        // Arrange
        Habit habit = new Habit(new Area("Health"), "Morning workout", 1.0);
        var timeEvent1 = LocalDate.now();
        var timeEvent2 = LocalDate.now().plusDays(1);

        // Act
        habit.perform(timeEvent1);
        habit.perform(timeEvent2);

        // Assert
        List<Event> events = habit.getEvents();
        assertNotNull(events);
        assertEquals(2, events.size());
        assertEquals(events.get(0).getTime(), timeEvent1);
        assertEquals(events.get(1).getTime(), timeEvent2);
    }

}