package com.fhsbatista.journal.domain;

import com.fhsbatista.journal.data.habit.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PerformHabitUsecase {
    @Autowired
    private EventRepository eventRepository;

    public Event call(Habit habit) {
        var event = new Event(habit, LocalDate.now(), habit.getCurrentScore());
        return eventRepository.save(event);
    }
}
