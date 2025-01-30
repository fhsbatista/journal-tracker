package com.fhsbatista.journal.data.habit;

import com.fhsbatista.journal.domain.Event;
import com.fhsbatista.journal.domain.habit.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByTimeAndHabit(LocalDate time, Habit habit);
}
