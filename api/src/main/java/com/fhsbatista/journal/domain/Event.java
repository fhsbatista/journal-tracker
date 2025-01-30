package com.fhsbatista.journal.domain;

import java.time.LocalDate;
import java.util.Objects;

import com.fhsbatista.journal.domain.habit.Habit;
import jakarta.persistence.*;

@Table(name = "events")
@Entity(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "habit_id")
    private Habit habit;

    private LocalDate time;
    private Double score;

    public Event() {}

    public Event(Habit habit, LocalDate time, Double score) {
        this.habit = habit;
        this.time = time;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public LocalDate getTime() {
        return time;
    }

    public Double getScore() {
        return score;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Event event = (Event) obj;
        return Double.compare(event.score, score) == 0 &&
                Objects.equals(habit, event.habit) &&
                Objects.equals(time, event.time);
    }
}
