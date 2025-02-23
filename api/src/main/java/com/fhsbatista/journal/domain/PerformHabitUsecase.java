package com.fhsbatista.journal.domain;

import com.fhsbatista.journal.data.habit.EventRepository;
import com.fhsbatista.journal.domain.habit.Habit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PerformHabitUsecase {
    @Autowired
    private EventRepository eventRepository;

    public Event call(Habit habit) throws PerformHabitException {
        if (isAlreadyPerformed(habit)) throw new PerformHabitException(PerformHabitError.ALREADY_PERFORMED);
        var event = new Event(habit, LocalDate.now(), habit.getCurrentScore());
        return eventRepository.save(event);
    }

    private boolean isAlreadyPerformed(Habit habit) {
        var events = eventRepository.findByTimeAndHabit(LocalDate.now(), habit);
        return !events.isEmpty();
    }
}

