package com.fhsbatista.journal.data.habit;

import com.fhsbatista.journal.domain.habit.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<Habit, Long> {
}
