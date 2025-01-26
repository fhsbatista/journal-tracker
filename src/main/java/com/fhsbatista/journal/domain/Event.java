package com.fhsbatista.journal.domain;

import java.time.LocalDate;
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
}
